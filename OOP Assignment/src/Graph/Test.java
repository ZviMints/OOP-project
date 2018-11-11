/**
 * This Class Represent Test for Graph
 * @author Tzvi Mints And Or Abuhazira
 */

package Graph;
import javax.swing.*;
import myMath.Polynom;
import myMath.cont_function;
import java.awt.*;
import java.net.URL;

public class Test extends JFrame {

	public static Polynom var = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
	public Test()
	{
		super("Graph - Zvi Mints and Or Abuhazira Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		
	    ImageIcon img = new ImageIcon("icon.png");
	    setIconImage(img.getImage());
		
		Plane plane = new Plane();
		add(plane, BorderLayout.CENTER);
		plane.setBackground(Color.BLACK);
		
		// Create a function with expression and name
		Function f = new Function(var, "f(x)");
		f.setColor(Color.cyan); // Set color for the graph

		// Set the desired scale for the plane
		plane.setScaleInX(1);
		plane.setScaleInY(5);

		// Enable grid in plane
		plane.setShowGrid(true);

		// Plot function, the plane store a list of functions so that you can
		// graph many functions at the same time
		plane.plot(f);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		run();
	}
	public static void run()
	{
		Test test = new Test();
	}
}

