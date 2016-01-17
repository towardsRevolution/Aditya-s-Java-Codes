/**
 * T_1.java
 * @version $ID: T_1.java, v 1.8 10/16/2015 7:02pm 
 * 
 * Revision: 8.31 10/19/2015 11:43am
 *
 */
 /**
 * This program is a general multi-thread execution program
 * 
 * Commented by-
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */
 public class T_1 extends Thread	{
	static int x = 1;
	String info = "";

	public T_1 (String info) {
		this.info = info;
		x++;
	}

	public void run () {
	   /*
	 	*Due to the time slice provided by the scheduler
		*each thread may have completed only half the run()
		*method yet the other thread may begin to run by executing
		*the run() method.   
	 	*/		
		x++;  
		String output = x + " " + info + ": " + x;
		System.out.println(output);
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main (String args []) {
		//Note: All threads always complete their run.
		 
		new T_1("a").start(); 		                
		new T_1("b").start();
	}
	/*
     * ALL POSSIBLE ANSWERS: (Note: At a time there are three threads active (main, T_1_a and T_1_b) 
     * -> 5 a : 5 , 5 b : 5 | 5 b : 5 , 5 a : 5
     *    Reason: Initializing T_1_a and T_1_b increments x to 3. Then, if T_1_a and T_1_b execute one after
     *    		  the other and each happen to wait at line 15, then x is incremented to 5, after which if 
     *    		  the individual threads complete their runs, we get the output as above (The order of thread
     *            execution can be 'T_1_a then T_1_b' or vice versa).
     *            
     * -> 4 a : 4 , 5 b : 5 | 4 b : 4 , 5 a : 5
     * 	  Reason: Initializing T_1_a and T_1_b increments x to 3. Then, if T_1_a runs first and completes 
     * 			  its run, then 4 a: 4 is printed and then when T_1_b is run and it completes its run 5 b : 5
     * 			  is printed. (The order of thread execution can be 'T_1_a then T_1_b' or vice versa).
     *    
     * -> 4 a : 5 , 5 b : 5 | 4 b : 5 , 5 a : 5
     * 	  Reason: Initializing T_1_a and T_1_b increments x to 3. T_1_a runs and waits at line 16 at first 'x'.
     * 			  Then, T_1_b runs and waits at line 15 as well. Now, x=5. So, when T_1_a runs to completion it 
     * 			  prints 4 a: 5 and T_1_b runs to completion and prints 5 b: 5 (The order of thread execution 
     * 			  can be 'T_1_a then T_1_b' or vice versa).
     * 
     * -> 5 a : 5 , 4 b : 5 | 5 b : 5 , 4 a : 5
     *    Reason: Initializing T_1_a and T_1_b increments x to 3. T_1_a and T_1_b runs and waits at line 16 at
     *    		  first 'x'. Now, x s incremented to 5. When both the threads run to completion we get the above 
     *    		  output. (The order of thread execution can be 'T_1_a then T_1_b' or vice versa).
     *    
     * -> Now, if the Threads wait at line 17 (i.e. nothing being printed), then the order in which the above 
     * 	  outputs occurred can be reversed and we get 6 more probable outputs (since the first one is symmetric).
     * 	  They are as following: 
     *    5 b : 5 , 4 a : 4 | 5 a : 5 , 4 b : 4 
     *    5 b : 5 , 4 a : 5 | 5 a : 5 , 4 b : 5
     *    4 b : 5 , 5 a : 5 | 4 a : 5 , 5 b : 5
     *    
     * -> Some rare answers: (Explanation is similar to the ones above)
     * 	  3 a : 3 , 4 b : 4 | 4 b : 4 , 3 a : 3   
     * 	  3 b : 3 , 4 a : 4 | 4 a : 4 , 3 b : 3
     * 	  3 a : 4 , 4 b : 4 | 3 b : 4 , 4 a : 4
     * 	  3 a : 5 , 4 b : 5 | 4 b : 5 , 3 a : 5
     * 	  3 b : 5 , 4 a : 5 | 4 a : 5 , 3 b : 5
     */
}