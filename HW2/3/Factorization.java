/**
 * Factorization.java
 * @version $ID: Factorization.java, v 1.8 09/04/2015 7:28pm 
 * 
 * Revision: 2.31 09/06/2015 3:30pm
 *
 */
import java.util.*;

/**
 * This program gives all factors of a number entered by the user
 * 
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */
public class Factorization {
	/**
	 * Given the number entered by the user and a stack, calculate the factors
	 * of the entered number and display it
	 *
	 * @param n,st 
	 *             print the factors of the entered number
	 */
	public void evalFactors(int n, Stack st) {
		int index = 2, flg, j, itr = 0;
		int initial_n = n;
		int[] fac = new int[50];
		while (n != 1) {
			flg = 0;
			if (n % index == 0) {
				st.push(index);
				n = n / index;
				flg = 1;
			}
			if (flg == 1)
				index = 1;
			index++;
		}
		System.out.print(initial_n + " = ");
		while (!st.empty())
			fac[itr++] = (int) st.pop();
		for (j = itr - 1; j >= 0; j--) {
			System.out.print(fac[j]);
			if (j != 0)
				System.out.print("*");
		}
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		int n, index = 0, j;
		Stack st = new Stack();
		Factorization f = new Factorization();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		n = sc.nextInt();
		f.evalFactors(n, st);
	}
}
