/**
 * Numbers.java
 * @version $ID: Numbers.java, v 1.8 08/28/2015 3:28pm 
 * 
 * Revision: 1.31 08/28/2015 3:28pm
 *
 */
import java.util.Vector;

/**
 * This program looks for all numbers(Prime integers) m and n, which meet all of
 * the following properties: - n is the k.st prime number (73 is the 21. prime
 * number) - m is mirror of k.st prime number (37 is the 12. prime number) - bN
 * (Binary representation of n) is a palindrome
 * 
 * @author Aditya Pulekar
 *
 */
public class Numbers {
	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int index = 0, j, k = 0, flg, p = 0, itr = 0, count;
		int num = 2, var = 0, n = 1, DecimalInt, DecimalInt2, cmp_var1, cmp_var2;
		String str, strIndex, strIndex2, reverse, reverseIndex, binaryStr;
		String binaryStr2, PalinStr, PalinStr2;
		int[] arr = new int[100000];
		int[] finalNumbers = new int[100000];
		Vector<String> Sarr = new Vector<String>();

		// Checking for prime numbers in the range 2 to 100000 and storing in an
		// array
		while (num < 100000) {
			flg = 0;
			for (index = 2; index < num; index++) {
				if (num % index == 0) {
					flg = 1;
					break;
				}

			}
			if (flg == 0) {
				arr[p++] = num;
			}

			num++;
		}

		// Converting the prime integers in the array 'arr[]' into strings
		while (itr < p) {
			str = String.valueOf(arr[itr]);
			Sarr.addElement(str);
			itr += 1;
		}
		itr = 0;
		count = 0;
		flg = 0;
		while (n < 6) {
			if (flg == 1)
				count = index;
			for (index = count; Sarr.elementAt(index).length() == n; index++) {
				if (n == 1)
					j = index;
				else
					j = index + 1;
				cmp_var1 = Integer.valueOf(Sarr.elementAt(index).charAt(0));
				cmp_var2 = Integer.valueOf(Sarr.elementAt(index).charAt(n - 1));
				if (cmp_var2 >= cmp_var1) {
					// Matching the Least significant digit of one number with
					// the most significant one of other numbers
					while (Sarr.elementAt(index).charAt(n - 1) != Sarr
							.elementAt(j).charAt(0)
							&& Sarr.elementAt(j).length() <= n) {
						j++;

					}
					reverse = new StringBuffer(Sarr.elementAt(index)).reverse()
							.toString();
					while (Sarr.elementAt(index).charAt(n - 1) == Sarr
							.elementAt(j).charAt(0))// What will happen for n>2?
					{
						if (Sarr.elementAt(j).equals(reverse)) {
							// Logic for matching the index of the two prime
							// numbers and checking if they are
							// mirror of each other
							strIndex = String.valueOf((j + 1));
							reverseIndex = new StringBuffer(strIndex).reverse()
									.toString();
							strIndex2 = String.valueOf((index + 1));
							if (strIndex2.equals(reverseIndex)) // i.e. index==j
							{
								// Logic for checking whether
								// "Sarr.elementAt(index)"'s binary
								// representation is a palindrome
								// For Integer m
								DecimalInt = Integer.valueOf(Sarr.elementAt(j));
								binaryStr = Integer.toBinaryString(DecimalInt);
								// Logic to check if "binaryStr" is a palindrome
								PalinStr = new StringBuffer(binaryStr)
										.reverse().toString();

								// For Integer n
								DecimalInt2 = Integer.valueOf(Sarr
										.elementAt(index));
								binaryStr2 = Integer
										.toBinaryString(DecimalInt2);
								// Logic to check if "binaryStr" is a palindrome
								PalinStr2 = new StringBuffer(binaryStr2)
										.reverse().toString();
								if ((binaryStr.equals(PalinStr))
										|| (binaryStr2.equals(PalinStr2))) {
									// (ANSWER)
									finalNumbers[itr] = arr[index];
									finalNumbers[itr + 1] = arr[j];
									itr += 2;
								}
							}

						}
						j++;
						if (j > 9591)
							break;
					}
				}
				if (index == 9591)
					break;
			}
			flg = 1;
			n++;
		}
		System.out
				.println("Following are the set of numbers (written along with their mirror "
						+ "numbers) that obey the conditions of the problem statement: \n");
		for (k = 0; k < itr; k = k + 2) // ANSWER
		{
			System.out.println(finalNumbers[k] + ":" + finalNumbers[k + 1]);
		}
	}

}
