/**
 * DeadLockAssgn2.java
 * @version $ID: DeadLockAssgn2.java, v 1.8 10/28/2015 3:04pm 
 * 
 * Revision: 10.11 11/2/2015 11:43am
 *
 */
 
 /**
 * This program guarantees the occurrence of a deadlock.
 * 
 * @author Aditya Pulekar
 *
 */
public class DeadLockAssgn2 extends Thread {
	String o1 = "Resource 1";
	String o2 = "Resource 2";
	String threadIdentity;
	
	public DeadLockAssgn2(String threadIdentity) {
		this.threadIdentity = threadIdentity;
	}

	public void method1() {
		while (true) {
			synchronized (o1) {
				System.out.println("Waiting for Resource 2 held by 'Second Thread'");
				synchronized(o2){
					//System.out.println("First Thread acquired Resource 2...No Deadlock observed!");
					//o1.notifyAll();
					o2.notify();
					try {
						o1.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//o2.notify(); //Indicating that Resource 1 (o1) is now up for grabs.
			}
		}
	}

	public void method2() {
		while (true) {
			synchronized (o2) {
				System.out.println("Waiting for Resource 1 held by 'First Thread'");
				synchronized(o1){
					//System.out.println("Second Thread acquired Resource 1...No Deadlock observed!");
					//o2.notifyAll();
					o2.notify();
					try {
						o1.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//o1.notify(); //Indicating that Resource 2 (o2) is now up for grabs.
			}

		}
	}

	public void run() {
		if (threadIdentity.equals("First Thread")) {
			method1();
		} else {
			method2();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DeadLockAssgn2("First Thread").start();
		new DeadLockAssgn2("Second Thread").start();
	}

}
