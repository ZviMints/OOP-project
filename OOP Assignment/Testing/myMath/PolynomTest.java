/**
 * Test file for Polynom
 * @author Tzvi Mints and Or Abuhtzira
 * @version 2.0
 */
package myMath;
import static org.junit.jupiter.api.Assertions.*;
import java.text.DecimalFormat;
import org.junit.jupiter.api.Test;

class PolynomTest {
	private static final double EPS = 0.001;
	private static final DecimalFormat df = new DecimalFormat("#.0000");
	Polynom pol; // Init Polynom
	String[] good = {"-2x+2","2x^1+2x+1","2*x^1-2*x+5","X^3-x","x","x^3+2x+6+12*x^100-x^3+x^1+x^0-2","x^0+3x+2","-x^0-3x+2x","-x^1+x^12+8*x^3+7",""};

	@Test
	void testPolynomStringGood() {		
		for(String good_string : good)
		{
			try {
				pol = new Polynom(good_string); // NOT EXCEPT ERROR
				System.out.println(good_string+" | Init to --> "+pol.toString());
			}
			catch(Exception e)
			{
				fail("you couldn't init from ("+good_string+")");
			}
		}
	}
	@Test

	void testPolynomStringBad() {
		String[] bad = {"2x2","xx","-1.1x^-0.5+2x^1.3+2" ,"2x+3+7x+2x^+2","-2x^-2+3x+2+7","x^3+2x+3+2x^-1","2x^1.5+x^3+2x","3xX","3Xx^","3x+--7","3x--7"};
		for(String bad_string : bad)
		{
			try {
				pol = new Polynom(bad_string);
				fail("you could init from ("+bad_string+"), the monom is: "+pol.toString());
			}
			catch(Exception e)
			{
				//Success
			}
		}
	}

	@Test
	void testF() {
		pol = new Polynom("x^2+7.3x+4");
		assertEquals(31.6375,pol.f(2.75));
	}

	@Test
	void testAddPolynom_able() {
		Polynom p1 = new Polynom("x^2+3");
		Polynom p2 = new Polynom("x^2+3+1");
		Polynom result = new Polynom("2x^2+7");
		p1.add(p2);
		assertEquals(true, p1.equals(result));
	}

	@Test
	void testAddMonom() {
		Polynom p1 = new Polynom("x^2+3");
		Monom[] monoms = {new Monom("3x^3"),new Monom("2x^2"),new Monom("7.43")};		
		Polynom result = new Polynom("3x^3+3x^2+10.43");
		for(Monom m1 : monoms)
			p1.add(m1);
		assertEquals(true, p1.equals(result));
	}

	@Test
	void testSubstract() {
		Polynom p1 = new Polynom("x^2+3");
		Polynom p2 = new Polynom("x^2+3+1");
		Polynom p3 = new Polynom("-1");
		p1.substract(p2);
		assertEquals(p3.toString(),p1.toString());
	}
	@Test
	void testMultiply() {
		Polynom p1 = new Polynom("x^2+2");
		Polynom p2 = new Polynom("x^2+2");
		Polynom p3 = new Polynom("x^4+4x^2+4");
		p1.multiply(p2);
		assertEquals(p3.toString(),p1.toString());

	}

	@Test
	void testEqualsPolynom_able() {
		Polynom p1= new Polynom("7x^4+33.33x^2+1x+7.0+0+x^0");
		Polynom p2= new Polynom("7x^4+33.33x^2+8+x");
		assertEquals(p1.toString(), p2.toString());
	}

	@Test
	void testIsZero() {
		Polynom zero_pol = new Polynom("");
		Polynom notzero_pol = new Polynom("2x");
		assertEquals(true, zero_pol.isZero());
		assertEquals(false, notzero_pol.isZero());
	}

	@Test
	void testRoot() {
		Polynom p1 = new Polynom("2x^3+2x^2+2-1");
		assertEquals(-1.2977,Double.parseDouble(df.format(p1.root(-3, 2, EPS))));
	}
	
	@Test
	void testCopy() {
		Polynom p1 = new Polynom("-2x^3 - 3x^2 + 3");
		Polynom p1_copy =  (Polynom) p1.copy();
		assertEquals(true,p1.equals(p1_copy));
	}
	@Test
	void testDerivative() {
		Polynom p1 = new Polynom("3x^3+3x^2+3x+3");
		Polynom p2 = new Polynom("9x^2+6x+3");
		assertEquals(p2.toString(),p1.derivative().toString());
	}

	@Test
	void testArea() {
		Polynom p1 = new Polynom("2x^3+2x^2+2-1");
		assertEquals(16.6575,Double.parseDouble(df.format(p1.area(-3, 2, EPS))));
	}

	@Test
	public void size() {
		pol = new Polynom();
		for (int i = 1; i <= 10; i++) {
			pol.add(new Monom(0,1)); // Should not add, the coef is zero, save storage.
			                         // if does, then u must add it one time. no duplicate!
			pol.add(new Monom(i,i));    
		}
		assertTrue(pol.size() == 10); // for (1,1),(2,2)...(10,10)
	}
}
