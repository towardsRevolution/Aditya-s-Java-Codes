/**
 * T_2.java
 * @version $ID: T_2.java, v 1.8 10/16/2015 7:02pm 
 * 
 * Revision: 8.31 10/19/2015 11:43am
 *
 */
 /**
 * This program is a general multi-thread execution program
 * 
 * Commented by-
 * @author Aditya Pulekar
 *
 */
public class T_2 extends Thread    {
    int id = 1;
    static String  theValue  = "1";
    T_2(int id)       {
        this.id = id;
    }
    public void run () {
        if ( id == 1 )
                theValue = "3";
        else
                theValue = "2";
    }      
    
    /**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
    public static void main (String args []) {
    
    	//Note: All threads always complete their run. 
    	
        new T_2(1).start();;
        new T_2(2).start();;
            
        System.out.println("theValue = " + theValue );
        System.out.println("theValue = " + theValue );
    }   
    /*
     * ALL POSSIBLE ANSWERS: (Note: At a time there are three threads active (main, T_2_1 and T_2_2) 
     * -> 3 3 : T_2_1 runs and completes it run.'theValue' is changed to 3. Then main executes again,
     * 			prints 3 twice and exits.
     * -> 3 2 : T_2_1 runs and completes its run, then main executes, prints 3 and waits at line 18. T_2_2 runs
     * 			and overwrites 'theValue' with 2, then main continues from line 19, thereby printing 2.
     * -> 3 1 : T_2_1 runs and completes its run, then main executes, prints 3 and waits at line 18. T_2_2 
     *          initializes and 'theValue' is set to 1. Then, before T_2_2 can execute, the main continues its 
     *          execution, prints 1 and exits.
     * -> 2 3 : T_2_2 runs and completes its run, then main executes, prints 3 and waits at line 18. Then,
     * 			T_2_1 runs and changes 'theValue' to 3. Then, the main executes, prints 3 and exits.
     * -> 2 2 : T_2_2 runs and completes its run.'theValue' is changed to 2. Then main executes again,
     * 			prints 2 twice and exits.
     * -> 2 1 : T_2_2 runs and completes its run, then main executes, prints 2 and waits at line 18. T_2_1 
     * 		    initializes and 'theValue' is set to 1. Then, before T_2_1 can execute, the main continues its 
     * 		    execution, prints 1 and exits.
     * -> 1 3 : T_2_1 initializes 'theValue' to 1, then main executes, prints 1 and waits at line 18. T_2_1 runs
     * 			and completes its run. Then, main executes,prints 3 and exits
     * -> 1 2 : T_2_1 initializes 'theValue' to 1, then main executes, prints 1 and waits at line 18. T_2_2 runs
     * 			and completes its run. Then, main executes,prints 2 and exits
     * -> 1 1 : T_2_1 initializes 'theValue' to 1, then main executes, prints 1 twice and exits
     */
}  