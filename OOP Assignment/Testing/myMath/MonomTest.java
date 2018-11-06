/**
 * Test file for Monom
 * @author Tzvi Mints and Or Abuhtzira
 * @version 2.0
 */
package myMath;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class MonomTest  {
	Monom mon; // Init Monom
	String[] good = {"5.3*x^2","2.0x^2","2.0x","2.0","-2x^2","33.33X^3","0","-5.3","x^100","-7.6x^3","","3x^0","-5*x","-5x","-3x","x","3x^0","x^0","-x","-12x","-12*x"};

	@Test
	void testMonomDoubleInt() {
		mon = new Monom(3,4);
		assertEquals(3, mon.get_coefficient());
		assertEquals(4, mon.get_power());
	}

	@Test
	void testMonomMonom() {
		mon = new Monom(3,5);
		Monom m1 = new Monom(mon);
		assertEquals(mon.toString(), m1.toString());
	}

	@Test
	void testMonomGoodString() {
		for(String good_string : good)
		{
			try {
				mon = new Monom(good_string); // NOT EXCEPT ERROR
				System.out.println(good_string+" | Init to --> "+mon.toString());
			}
			catch(Exception e)
			{
				fail("you couldn't init from ("+good_string+")");
			}
		}
	}

	@Test
	void testMonomBadString() { // need to fix "x^^3" -> init it.
		String[] bad = {"2x2","^2","1*x500","-3^0","*x^50","x^-1","0x^-1","^^","3^x","-3^x^-2","-51x2","-x*-3","-2^x","*x","x^-1","3^x","2xx","2^^","^2","x^1.23","x^31.2","-1*x^12.2","2x2","2xx","-2x^1.5","-2*x^1.5","x^1.5","2^2","2^x","22xx"};	
		for(String bad_string : bad)
		{
			try {
				mon = new Monom(bad_string);
				fail("you could init from ("+bad_string+"), the monom is: "+mon.toString());
			}
			catch(Exception e)
			{
				//Success
			}
		}
	}

	@Test
	void testDerivative() {
		Monom[] mon = {new Monom("3*x^10"),new Monom("x^10"),new Monom("10*x^0"),new Monom("1*x^1")};
		Monom[] mon_derivative = {new Monom("30*x^9"),new Monom("10*x^9"),new Monom("0"),new Monom("1")};
		for(int i=0; i < mon.length;i++)
		{
			assertEquals(mon_derivative[i].toString(), mon[i].derivative().toString());
		}
	}

	@Test
	void testAdd() {
		Monom m1 = new Monom(3,2);
		Monom m2 = new Monom(3,3);
		mon = new Monom(6,2);
		mon.add(m1); // can be added 
		assertEquals(new Monom(9,2).toString(),mon.toString());
		try {
			mon.add(m2); // can't be added 
			fail("not possible to add ("+mon+")+("+m2+")");
		}
		catch(Exception e)
		{
			//Success
		}
	}

	@Test
	void testMultiply() {
		Monom m1 = new Monom(3,4);
		Monom m2 = new Monom(4,5);
		Monom m3 = new Monom(12,9);
		m1.multiply(m2);
		assertEquals(m3.toString(), m1.toString());
	}

	@Test
	void testEqualsMonom() {
		Monom m1 = new Monom ("3x^2");
		Monom m2 = new Monom(3,2);
		assertEquals(m1.toString(), m2.toString());
	}

	@Test
	void testF() { // test f(x)
		double x = 3;
		mon = new Monom("3.6*x^7");
		double result = 3.6*Math.pow(x, 7);
		assertEquals(result, mon.f(x));
	}

	@Test
	void testGet_coefficient() {
		mon = new Monom(7.8,2);
		assertEquals(7.8, mon.get_coefficient());
	}

	@Test
	void testGet_power() {
		mon = new Monom(3,2);
		assertEquals(2, mon.get_power());
	}

	@Test
	void testToString() {
		// Need to implement
	}

}
