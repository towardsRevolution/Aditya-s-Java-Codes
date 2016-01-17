/**
 * AllSubSets.java
 * @version $ID: AllSubSets.java, v 1.8 09/04/2015 7:28pm 
 * 
 * Revision: 2.41 09/06/2015 3:30pm
 *
 */
import java.util.*;

/**
 * This program forms all possible subsets out of the total number of people
 * attending a party. The number of people attending the party is taken from the
 * user.
 * 
 * @author Aditya Pulekar
 *
 */

public class Subsets {
	/**
	 * Given the stack, pop the binary characters from the stack and pair up the
	 * indices of the characters with value '1' to display the subsets.
	 *
	 * @param st
	 *            print the subsets
	 */
	public void print(Stack st) {
		int itr = 0, j = 0;
		char[] fac = new char[20];

		// Popping the characters from the stack

		while (!st.empty()) {
			fac[itr++] = (char) st.pop();
		}
		System.out.print(" {");
		while (j < itr) {
			if (fac[j] == '1')
				System.out.print(j + 1);
			j++;
		}
		System.out.print("} ");
	}

	/**
	 * Given the binary string, push the elements of this string over the stack
	 * to get the correct indices of the characters with value '1'.
	 *
	 * @param binString2
	 *                  pushing the binary characters over the stack
	 */
	public void pairUp(String binString2) {
		Stack st = new Stack();
		int index = 0;

		// Pushing the characters over the stack

		while (index < binString2.length()) {
			st.push(binString2.charAt(index));
			index++;
		}
		print(st);
	}

	/**
	 * Given the total number of people attending the party, calculate the total
	 * number of combinations and generate the corresponding binary equivalent
	 * to obtain the subsets.
	 *
	 * @param num
	 *            obtaining 2^num combinations and iterating for every Integer's
	 *            corresponding binary equivalent
	 */
	public void findSubsets(int num) {

		// Taking an Integer 1 to iterate over all the 2^num combinations

		//int one = Integer.parseInt("1", 2);
		int index = 0, index2 = 0;
		String binString = "0";

		// Zero padding

		while (index < num - 1) {
			binString = binString + "0";
			index++;
		}
		while (binString.length() < (num + 1)) {
			int int_num = Integer.parseInt(binString, 2);
			String binString2 = Integer.toBinaryString(int_num);
			pairUp(binString2);

			// Incrementing the number by one in every iteration

			int_num = int_num + 1;
			binString = Integer.toBinaryString(int_num);
		}
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of people attending the party:");
		int num = sc.nextInt();
		Subsets sb = new Subsets();
		sb.findSubsets(num);
	}
}
