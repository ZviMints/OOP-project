/** this class repesent Comperator
 * We will sort by the big power when the bigger power will appear at the beginning, 
 * if the holdings are equal then we will first introduce the larger coefficients
 * @author Tzvi Mints and Or Abuhtzira
 */
package myMath;
import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	@Override
	public int compare(Monom a, Monom b) {
		return (b.get_power() - a.get_power());
	}
}
