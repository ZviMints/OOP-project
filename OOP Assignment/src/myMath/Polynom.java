package myMath;
import java.util.*;
import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Tzvi Mints and Or Abuhtzira
 * @version 2
 */
public class Polynom implements Polynom_able{
	private static final Monom_Comperator cmp = new Monom_Comperator();
	private List<Monom> list = new ArrayList<Monom>();

	// ************************************  Constructors ************************************
	/**
	 * construct empty list
	 */
	public Polynom() {  // Empty List
		list = new ArrayList<Monom>(); 
	}
	/**
	Deep Copy from other Polynom
	 */
	public Polynom(Polynom p1) // From Other Polynon
	{
		Iterator<Monom> inputIt = p1.iteretor();
		while(inputIt.hasNext())
		{
			Monom mon = inputIt.next();
			add(new Monom(mon));
		}
	}
	/**
	 * This constructor obtain a string from the user and initialize a polynomial
	 * @throws error if the String is not valid";
	 * @param s is the string
	 */
	public Polynom(String s) { // From String
		try {
			s = s.replaceAll("\\-", "+-"); // fix the '-' problem for a_1x^b_1-a_2x^b_2
			s = s.replaceAll(" ", "");
			String[] split = s.split("[+]| ");
			for(int i=0; i<split.length; i++)
			{
				if(!split[i].isEmpty())
				{
					Monom mon = new Monom(split[i]);
					add(mon);
				}
			}
		}
		catch(Exception E)
		{ 
			throw new RuntimeException(E.getMessage());
		}
	}
	// ************************************  Override, implements Polynom_able ************************************

	@Override
	public Polynom_able copy() {
		Polynom pol = new Polynom();
		Iterator<Monom> it = list.iterator();
		while(it.hasNext())
		{
			Monom temp = it.next();
			pol.add(temp);
		}
		return pol;
	}

	@Override
	public double f(double x) {
		double sum = 0;
		Iterator<Monom> it = list.iterator();
		while(it.hasNext())
		{
			Monom temp = it.next();
			sum+= temp.f(x);
		}
		return sum;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext())
		{
			Monom temp = new Monom(it.next());
			this.add(temp);
		}
	}

	@Override
	public void add(Monom m1) { 
		if(list.size()!=0)
		{
			boolean hasNewPower = true;
			Iterator<Monom> it = list.iterator();
			while(it.hasNext())
			{
				Monom temp = it.next();
				if(m1.get_power() == temp.get_power())
				{
					if(m1.get_coefficient() + temp.get_coefficient()==0)  it.remove(); // if its from the form 0*x then remove it from the list
					else 
						temp.add(m1);
					hasNewPower = false;
				}
			}
			if(hasNewPower && m1.get_coefficient()!=0) list.add(m1);
		}
		else
			if(m1.get_coefficient() != 0) 
				list.add(m1); //if its from the form 0*x^n when n in N then no need to add
	}

	@Override
	public void substract(Polynom_able p1) { 
		/* OPTIONAL: Create P1 copy and Create p2 = -1, multiply p1 and p2 and then add p1 to this (add)  */
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext())
		{
			Monom temp = it.next();
			double newcoef = -(temp.get_coefficient());
			Monom newmonom = new Monom(newcoef,temp.get_power());
			this.add(newmonom);
		} }

	@Override
	public void multiply(Polynom_able p1) {
		Polynom c = new Polynom();
		Iterator<Monom> inputIt = p1.iteretor();
		while(inputIt.hasNext())
		{
			Monom inputMonom = inputIt.next();
			for(int i=0; i<list.size();i++)
			{
				Monom p1_temp_monom = new Monom(inputMonom);
				p1_temp_monom.multiply(list.get(i));
				c.add(p1_temp_monom);
			}
		}
		list.clear();
		add(c);
	}

	@Override
	public boolean equals(Polynom_able p1) {
		String s1 = p1.toString();
		String s2 = toString();
		return s1.equals(s2);
	}

	@Override
	public boolean isZero() {
		return size()==0;
	}

	@Override
	//  more info : https://www.geeksforgeeks.org/program-for-bisection-method/
	public double root(double x0, double x1, double eps) {
		if(f(x0)*f(x1)>0) 
		{
			throw new RuntimeException("Wrong input, f(x0)*f(x1)>0");
		}
		else
		{
			double middle = (x1 + x0) / 2; 
			while(Math.abs(x1 - x0) >= eps)
			{
				middle = (x1 + x0) / 2;
				if(f(middle)==0) return middle;
				else if(f(middle)*f(x0) < 0 ) //root lies between x0 and middle. So we recur for x0 and middle
					x1 = middle;
				else //root lies between middle and x1. So we recur for middle and x1
					x0 = middle;
			}
			return middle; 
		}
	}

	@Override
	public Polynom_able derivative() {
		Polynom pol = new Polynom();
		Iterator<Monom> it = list.iterator();
		while(it.hasNext())
		{
			Monom mon = new Monom(it.next().derivative());
			pol.add(mon);
		}
		return pol;
	}

	@Override
	// area ~ eps * (f(x_0) + f(x_1) + .. + f(x_n)), when n is number of rectangles.
	// more info : https://people.math.osu.edu/broaddus.9/116101-F2017/files/ExampleRiemann.pdf
	public double area(double x0, double x1, double eps) { 	// eps size steps
		if(x0 > x1)
		{
			double temp = x1;
			x1 = x0;
			x0 = temp;
		}

		double n = (x1 - x0) / eps;
		double area = 0;
		int i = 0;
		while(i < n)
		{
			double sum = f(x0 + i*eps);
			if(sum > 0) // we need only approximated area above the x-axis below this Polynom and between the [x0,x1] range.
				area += sum;
			i++;
		}
		return area*eps;
	}

	/**
	 * approximated area bellow the x-axis and above this polynom and between the [x0,x1] in eps step size
	 */
	public double areaBellowXAboveF(double x0, double x1, double eps) { 	// eps size steps
		if(x0 > x1)
		{
			double temp = x1;
			x1 = x0;
			x0 = temp;
		}

		double n = (x1 - x0) / eps;
		double area = 0;
		int i = 0;
		while(i < n)
		{
			double sum = f(x0 + i*eps);
			if(sum < 0) // we need only approximated area above the x-axis below this Polynom and between the [x0,x1] range.
				area += sum;
			i++;
		}
		return area*eps;
	}
	
	
	@Override
	public Iterator<Monom> iteretor() {
		return list.iterator();
	}

	// ************************************  Public Methods ************************************
	/** How many Monoms belong to the polynomial
	 * @return list size
	 */
	public int size() 
	{
		return list.size();
	}
	/**
	 * This function is responsible for sorting the Monoms by power
	 */
	private void sort()
	{
		list.sort(cmp);	
	}

	// ************************************  Print  ************************************
	/**
	 * This function is responsible for printing input to the screen
	 */
	public String toString() 
	{
		if(size() != 0)
		{
			String s = "";
			sort();
			for(int i=0; i<list.size();i++)
			{
				boolean neg = false;
				if(list.get(i).get_coefficient() < 0 ) neg = true;
				if(!neg)
				{  
					if(i==0)
						s+= list.get(i); // now its will not print + 3x at Start.
					else
						s+= "+"+list.get(i);
				}
				else //its negative
					s+= list.get(i);
			}
			return s;
		}
		else
			return "0"; 
	}
	// ************************************  Graph  ************************************
	// Open Source: https://github.com/rendon/Plane
	
	public void Graph()
	{
		Polynom pol = (Polynom) copy();
		Graph.Test.var = pol;
		Graph.Test.run();
	}
}