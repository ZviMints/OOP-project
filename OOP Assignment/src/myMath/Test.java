/**
 * This class are used to test the Polynom,Monom and Graph Test.
 *@author Tzvi Mints and Or Abuhazira
 *@version 2.0
 */
package myMath;
import java.util.Scanner;
public class Test {
	public static String var_toGraph;
	public static void main(String[] args) throws Exception {
		try {
			int num = 0;	
			while(num<=10)
			{
				Scanner sc = new Scanner(System.in);
				System.out.println("---> 1: Monom Tester\n"+
						           "---> 2: Polynom / Monom Graph\n"+
					              "---> else: Polynom Tester");

				num = sc.nextInt();
				if(num == 1) // MONOM TESTER
				{
	// Monom						
					sc = new Scanner(System.in);
					System.out.println("Enter first Monom");
					String mon1Input = sc.nextLine();
					Monom test1_mon = new Monom(new String(mon1Input));
					System.out.println("* ---->     Monom 1 is: ("+test1_mon+")");
					System.out.println("Enter second Monom");
					String mon2Input = sc.nextLine();
					Monom test2_mon = new Monom(new String(mon2Input));
					System.out.println("* ---->     Monom 2 is: ("+test2_mon+")");
					
					System.out.print("Enter what function you want to try: \n"
							+ "2: add (Monom m1) \n"
							+ "3: derivative_Monom_1()\n"
							+ "4: multiply(Monom m1)\n"
							+ "5: f_Monom_1(x)\n"
							+ "   ---> PRESS OTHER KEY TO EXIT \n");

					num = sc.nextInt();
					System.out.println("* ----> RESULT FOR "+num+" BELOW  <----");
					System.out.println("* ---->     Monom 1 is: "+test1_mon);
					System.out.println("* ---->     Monom 2 is: "+test2_mon);

					Monom ans;
					switch(num) {

					case 2: 
						ans = new Monom(test1_mon);
						ans.add(test2_mon);
						System.out.println(ans+"\n");
						break;

					case 3:
						ans = new Monom(test1_mon.derivative());
						System.out.println(ans+"\n");
						break;

					case 4: 
						ans = new Monom(test1_mon);
						ans.multiply(test2_mon);
						System.out.println(ans+"\n");
						break;

					case 5:
						System.out.println("Enter x");
						int x = sc.nextInt();
						System.out.println("f("+x+")= "+test1_mon.f(x));
						break;
						
					default:
						num = 10;
						System.out.println("\n---> Program has finished");
						System.exit(0);
					}

				}
				else if(num == 2)
				{
	// Graph			
					
					sc = new Scanner(System.in);
					System.out.println("Enter Polynom / Monom");
					String var_toGraph = sc.nextLine();	
					Polynom pol = new Polynom(var_toGraph);
					pol.Graph();
					break;
				    
				}
	// Polynom			
				else {
					sc = new Scanner(System.in);
					System.out.println("Enter First Polynom");
					String firstPol = sc.nextLine();
					Polynom test1_pol = new Polynom(new String(firstPol));
					System.out.println("* ---->     Polynom 1 is: ("+test1_pol+")");
					System.out.println("Enter Second Polynom");
					String secondPol = sc.nextLine();
					Polynom test2_pol = new Polynom(new String(secondPol));
					System.out.println("* ---->     Polynom 2 is: ("+test2_pol+")");
					
					System.out.print("Enter what function you want to try: \n"
							+ "2: add (Polynom_able p1) \n"
							+ "3: substract(Polynom_able p1)\n"
							+ "4: area_Polynom_1(double x0,double x1, double eps)\n"
							+ "5: derivative_Polynom_1()\n"
							+ "6: equals_Polynom_1()\n"
							+ "7: isZero_Polynom_1()\n"
							+ "8: multiply(Polynom_able p1)\n"
							+ "9: root_Polynom_1(double x0,double x1, double eps)\n"
							+ "10: f_Polynom_1(x)\n"
							+ "   ---> PRESS OTHER KEY TO EXIT \n");

					num = sc.nextInt();
					System.out.println("* ----> RESULT FOR "+num+" BELOW  <----");
					System.out.println("* ---->     Polynom 1 is: "+test1_pol);
					System.out.println("* ---->     Polynom 2 is: "+test2_pol);

					switch(num) {

					case 2: 
						test1_pol.add(test2_pol);
						System.out.println(test1_pol+"\n");
						break;

					case 3:
						test1_pol.substract(test2_pol);
						System.out.println(test1_pol+"\n");
						break;

					case 4:
						try {
						sc = new Scanner(System.in);
						System.out.println("Enter x0,x1,eps");
						String s = sc.nextLine();
						String[] values = s.split(",");
						System.out.println(test1_pol.area(Double.parseDouble(values[0]),Double.parseDouble(values[1]), Double.parseDouble(values[2]))+"\n");
						break;
						}
						catch(Exception e)
						{
							System.err.println("Wrong values");
							System.exit(1);
						}

					case 5: 
						System.out.println(test1_pol.derivative()+"\n");
						break;

					case 6:
						System.out.println(test1_pol.equals(test2_pol)+"\n");
						break;

					case 7:
						System.out.println(test1_pol.isZero()+"\n");
						break;

					case 8: 
						test1_pol.multiply(test2_pol);
						System.out.println(test1_pol+"\n");
						break;

					case 9:
						try {
							sc = new Scanner(System.in);
							System.out.println("Enter x0,x1,eps");
							String s = sc.nextLine();
							String[] values = s.split(",");
							System.out.println(test1_pol.root(Double.parseDouble(values[0]),Double.parseDouble(values[1]), Double.parseDouble(values[2]))+"\n");
							break;
							}
							catch(Exception e)
							{
								System.err.println("Wrong values");
								System.exit(1);
							}
					case 10:
						System.out.println("Enter x");
						int x = sc.nextInt();
						System.out.println("f("+x+")= "+test1_pol.f(x));
						break;

					default:
						num=12;
						System.out.println("\n---> Program has finished");
						System.exit(0);
					}
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("Catched Error from Test CLASS \n"+e.getMessage());
		}
	}
}