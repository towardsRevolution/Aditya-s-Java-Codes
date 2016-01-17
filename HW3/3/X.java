/**
 * X.java
 * @version $ID: X.java, v 1.8 09/09/2015 2:01pm 
 * 
 * Revision: 3.31 09/13/2015 10:43am
 *
 */
/**
 * This program explains the operation of pre and post increment operators
 * within the 'if' and 'else if' conditions and, also, introduces the ternary
 * operator (?:).
 * 
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */
class X {
	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	 
	public static void main(String args[]) {

		int n = 0;

		/*
		 * A labeled break statement is used here
		 */
		here:

		while (true) {
			/*
			 * The loop breaks when 'n' equals 5 as for the iteration to
			 * continue, the value of 'n' should be less than 4
			 */
			while (((n != 3) || (n != 5)) && (n < 4)) {
				/*
				 * a/. 1st Iteration: Initially, the value of 'n' is 0. Since, a
				 * pre-increment operator is used between the parenthesis of the
				 * if condition, 'n' is first incremented by 1 and then compared
				 * with 0. As 1!=0, the compiler checks the first 'else if'
				 * condition.
				 * 
				 * 2nd Iteration: Since, the value of 'n' is 2, it satisfies the
				 * condition of the while loop to enter into the 2nd iteration.
				 * Value of 'n' is now incremented to 3. As it fails the
				 * comparison, the compiler checks the the next 'else if'
				 * condition.
				 */
				if (++n == 0)
					System.out.println("a/	n is " + n);
				/*
				 * b/. 1st Iteration: Here, first the value of 'n' is compared
				 * with 1 and then incremented by 1. Since, the value 'n' is 1
				 * (owing to the increment in the first if condition), this
				 * condition is satisfied (as 1=1). Now, the value of 'n' is
				 * incremented by 1 and printed on the console as '2' (i.e.
				 * n=2). Since, this 'else if' condition was satisfied, the
				 * compiler won't check whether the other following conditions
				 * are true or false in the same iteration.
				 * 
				 * 2nd Iteration: Value of 'n', which is 3, fails the comparison
				 * with 1, and is then incremented to 4.
				 */
				else if (n++ == 1) {
					System.out.println("b/	n is " + n);
				}
				/*
				 * c/. 2nd Iteration: Value of 'n', which is 4, fails the
				 * comparison with 2, and is then incremented to 5.
				 */
				else if (n++ == 2)
					System.out.println("c/	n is " + n);
				/*
				 * d/. 2nd Iteration: Value of 'n', which is 5, is printed on
				 * the console.
				 */
				else
					System.out.println("d/	n is " + n);
				/*
				 * the below statement is executed during every iteration of the
				 * while loop.
				 */
				System.out.println("	executing break here");

			}
			/*
			 * Since, value of 'n' is 5, 5%2!=0. Hence, '( n % 3 != 0 ? "3" :
			 * "!3" )' is executed. Now, as 5%3!=0, "3" is printed on the
			 * console
			 */
			System.out.println(n % 2 == 0 ? (n == 4 ? "=" : "!")
					: (n % 3 != 0 ? "3" : "!3"));
			/*
			 * Labeled break statement terminates the outer loop
			 */
			break here;
		}
	}
}
