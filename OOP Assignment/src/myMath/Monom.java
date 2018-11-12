package myMath;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Tzvi Mints and Or Abuhtzira
 * @version 2.0
 *
 */
public class Monom implements function{

	private double _coefficient; 
	private int _power;

	// ************************************  Constructors ************************************
	/**
	construct monom from coefficient and power
	@a is the coefficient
	@b is the power
	 */
	public Monom(double a, int b) { // From coefficient and power
		this.set_coefficient(a);
		this.set_power(b); 
	}
	/**
	Deep Copy from other Monom
	 */
	public Monom(Monom ot) { // From other Monom
		this(ot.get_coefficient(), ot.get_power());
	}

	/**
	 * This constructor obtain a string from the user and initialize a Monom
	 * @throws error if the String is not valid";
	 * @param s is the string
	 * @throws exception if s is wrong input for Monom
	 */
	public Monom(String s) { // From String
		if(s.isEmpty()) // Empty string is valid. ""
		{
			this.set_coefficient(0);
			this.set_coefficient(0);
		}
		// regEx : https://regex101.com/r/lW9qD1/60
		// Any Int/Double Number - ([+-]?[0-9]*\.?[0-9]*)
		else if(s.matches("([+-]?[0-9]*\\.?[0-9]*\\*?[xX](\\^[0-9]+)?)|([+-]?[0-9]*\\.?[0-9]*?)")) // if Its Matches RegEx
		{
			s = s.replaceAll("X", "x");
			s = s.replaceAll(" ", "");
			
			if(!s.contains("^") && !s.contains("x"))
				// its from the form a, when a in R [not(^) and not(x)]
			{
				this.set_coefficient(Double.parseDouble(s));
				this.set_power(0);	
			}
			else if(!s.contains("^") && s.contains("x")) 
				//its from the form a_1 * x || a_1x [not(^) and (x)]
			{
				String[] temp = s.split("[*x]");
				if(s.equals("x")) // its only "x"
					this.set_coefficient(1);
				else if(temp[0].equals("-")) 
					this.set_coefficient(-1);
				else
					this.set_coefficient(Double.parseDouble(temp[0]));					
				this.set_power(1);	
			}
			else // its from the form a_1*x^b_1
				// Note that else if for 1. [(^) and (x)] &&
				//                       2. [(^) and not(x)]
				// but (2.) is not match RegEx.
			{
				String[] temp = s.split("[*x^]");
				if(temp[0].isEmpty() && !s.contains("*")) 
					this.set_coefficient(1); // its from the form x^b_1
				else if(temp[0].isEmpty() && s.contains("*")) // wrong format , *x^b_1
					throw new RuntimeException(s);
				else if(temp[0].equals("-")) 
					this.set_coefficient(-1); // its from the form -x^b_1
				else 
					this.set_coefficient(Double.parseDouble(temp[0])); // its from the form a_1x^b_1
				this.set_power(Integer.parseInt(temp[temp.length-1]));
			}
		}
		else
		{ throw new RuntimeException("Catched Wrong Input From Monom CLASS: "+s); }
	}

	// ************************************  Public Methods ************************************
	/**
	 * for f(x), returns f'(x) : https://en.wikipedia.org/wiki/Derivative
	 * @return derivative of the original Monom
	 */
	public Monom derivative()
	{
		if (!(get_power()==0 || get_coefficient()==0))
		{
			double coef = this._coefficient*this._power;
			int pow = get_power() - 1;
			return new Monom(coef,pow);
		}
		else
			return new Monom(0,0);
	}
	/**
	 * The function is responsible for adding another Monom to an existing one
	 * @param coefficient is the coefficient of the Monom
	 */
	public void add(Monom m1)
	{
		if(m1.get_power() == get_power())
			set_coefficient(this._coefficient + m1.get_coefficient());
		else
			throw new RuntimeException("two monoms must be with same power!");
	}
	/**
	 * this method will return the multiply between the input Monom and the current Monom
	 * @param m1 is the input Monom
	 * @return new Monom
	 */
	public void multiply(Monom m1)
	{
		set_coefficient(this._coefficient * m1.get_coefficient());
		set_power((this._power + m1._power));
	}
	/**
	 * check of two monoms are equals
	 * @param m1 is the input monom
	 * @return true iff for any x: this.f(x) == m1.f(x)
	 */
	public boolean equals(Monom m1) {
		String s1 = m1.toString();
		String s2 = toString();
		return s1.equals(s2);
	}
	// ************************************  Override, implements function ************************************
	@Override
	/**
	 * this function returns y, when y = monom value at x, where both y and x are real numbers.
	 * @input x 
	 * @returns y=f(x)
	 */
	public double f(double x) {
		return (get_coefficient()) * Math.pow(x, get_power()); 
	}

	//************************************  Setters and Getters ************************************
	/**
	 * monom power must be non-negative integer
	 * @param p is the power
	 * @throws exception if the power is less then 0
	 */
	private void set_power(int p) {
		if( p >= 0) this._power = p;
		else throw new RuntimeException("Don't enter negative power by definition");
	}
	/**
	 * set the coefficient of the monom
	 * @param a is the cofficient
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	public double get_coefficient() { return _coefficient; }
	public int get_power() { return _power;}

	//************************************  Print ************************************
	/**
	 * return String that represent Monom.
	 */
	public String toString()
	{
		double coef = get_coefficient();
		int power = get_power();
		if(coef==1 || coef == -1) // -x^(b_1) or x^(b_1)
		{
			// Attention! there no coefficient ( its 1 or -1 )
			if(power > 1) // ****** the power is > 1
			{
				if(coef == -1 )
					return "-"+"x"+"^"+power; // from the form -1x^2
				else
					return "x"+"^"+power; // from the form 1x^2
			}
			else if(power==1) // ****** the power is 1
			{
				if(coef == -1 )
					return "-"+"x";  // from the form -x
				else
					return "x"; // from the form x
			}
			else // ****** the power is 0
				return ""+coef; 
		}
		// Attention! there a coefficient!
		else if ( power==1 )
			return coef+"*x"; // from the form 3x^1
		else if( power==0 )
			return ""+coef; // from the form 3x^0
		else // its from regular from, with power > 1 and coef that not in {-1,1}
			return coef+"*x^"+power; 
	}
}