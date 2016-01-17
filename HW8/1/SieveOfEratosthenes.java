/**
 * SieveOfEratosthenes.java
 * @version $ID: SieveOfEratosthenes.java, v 1.8 10/16/2015 7:02pm 
 * 
 * Revision: 8.11 10/19/2015 11:43am
 *
 */
import java.util.Scanner;

import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.text.ParagraphView;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;
/**
 * This program executes the Eratosthenes algorithm using Multithreading
 * 
 * @author HPB
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */
public class SieveOfEratosthenes extends Thread {

	/*
	 * The difference between this HW problem and the second HW problem is that
	 * in this HW problem, we are supposed to make the main thread terminate
	 * only after the other non-daemon threads are terminated. In the second
	 * problem, it doesn't matter even if the main thread is terminated first.
	 * The JVM continues to execute until one of the foll. occurrences: 1). The
	 * exit method of class Runtime has been called and the security manager has
	 * permitted the exit operation to take place. 2). All threads that are not
	 * daemon threads have died, either by returning from the call to the run
	 * method or by throwing an exception that propagates beyond the run method.
	 */

	final static int FIRSTpRIMEuSED = 2;
	static int MAX;
	final boolean[] numbers;
	int numberOfActiveThreads;
	private int multiplierCount;

	public SieveOfEratosthenes(int max) {
		numbers = new boolean[max];
		MAX = max;
	}

	public void determinePrimeNumbers() {
		for (int index = 1; index < MAX; index++) {
			numbers[index] = true;
		}
		int multiplierThreadCounter = 0;
		int limit = (MAX > 10 ? (int) Math.sqrt(MAX) + 1 : 3);
		parallelSieveOfEratosthenes[] multiplierThreads = new parallelSieveOfEratosthenes[limit - 2];
		System.out.println("limit: " + limit);
		for (int index = 2; index < limit; index++) {

			// ***Note: In spite of putting a if-else condition on thread, the
			// order of execution of Thread is still random. Though, it doesn't
			// affect our program. However, this proves that the execution order
			// of the threads will always be controlled by the scheduler and not
			// by the if-else statement. If-else will only ensure that one
			// thread is executed at a time.

			multiplierThreads[multiplierThreadCounter++] = new parallelSieveOfEratosthenes(
					index);

		}

		int count = 0;
		while (count < multiplierThreadCounter) {
			if (multiplierCount < numberOfActiveThreads) {
				multiplierCount++;
				multiplierThreads[count++].start();
			} else {
				break;
			}
		}

		// Fetching the count of active threads
		System.out.println("Number of active threads at the moment: "
				+ (multiplierCount)); // (Thread.activeCount()-1)
	}

	/*
	 * An inner class formed to support parallelism
	 */
	class parallelSieveOfEratosthenes extends Thread {
		int index;
		int activeThreadsInparallelClass;

		/*
		 * Parameterized constructor
		 * 
		 * @param index index of the numbers array
		 */
		public parallelSieveOfEratosthenes(int index) {
			this.index = index;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			if (numbers[index]) { // this is the part for the parallel part
				int counter = 2; // this is the part for the parallel part
				while (index * counter < MAX) { // this is the part for the
												// parallel part
					numbers[index * counter] = false; // this is the part for
														// the parallel part
					counter++; // this is the part for the parallel part
				} // this is the part for the parallel part
			}
		}
	}

	public void testForPrimeNumber() {
		int[] test = { 2, 3, 4, 7, 13, 17, MAX - 1, MAX };
		for (int index = 0; index < test.length; index++) {
			if (test[index] < MAX) {
				System.out.println(test[index] + " = " + numbers[test[index]]);
			}
		}
	}

	public static void main(String[] args) {
		SieveOfEratosthenes aSieveOfEratosthenes = new SieveOfEratosthenes(25);
		System.out.println("Enter the maximum number of multiplier threads"
				+ " you want to be active at any given point: ");
		Scanner sc = new Scanner(System.in);
		aSieveOfEratosthenes.numberOfActiveThreads = sc.nextInt();
		aSieveOfEratosthenes.determinePrimeNumbers();

		// Since we want to display all the numbers only after all the
		// iterations of the while loop in the determinePrimeNumbers()
		// method, we make the main thread sleep for 100 ms.
		/*
		 * Making the main thread sleep for 100 ms, helps all the possible
		 * alterations to take place in the numbers array and only then is the
		 * testForPrimeNumber() method called.
		 */

		try {
			sleep(100); // It puts the main thread to sleep
		} catch (InterruptedException e) {
			System.err.println("Interrupted!");
		}
		aSieveOfEratosthenes.testForPrimeNumber();

		// Note: We may have cases wherein even after the main thread ends,
		// there
		// may still be one or more threads active.

		sc.close();
		System.exit(0);
	}
}
