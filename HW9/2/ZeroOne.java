/**
 * ZeroOne.java
 * @version $ID: ZeroOne.java, v 1.8 10/22/2015 7:02pm 
 * 
 * Revision: 9.21 10/26/2015 10:43am
 *
 */
 /**
 * This program is an example of multithreading using synchronized,
 * wait(), notify() and notifyAll()
 * 
 * @author Hpb
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */
public class ZeroOne extends Thread {
	private String info;
	static Object o = new Object();
	static boolean oneIsRunning = false;
	static int id;
	
	public ZeroOne(int id) {
		ZeroOne.id = id;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while (true) {
			if (ZeroOne.id == 99) {
				ZeroOne.id = 0;
			}
			while (ZeroOne.id < 99) {
				synchronized (o) {
					o.notify(); 
					// If we do not include notifyAll(), then this
					// notify does the job of notifying the 99th
					// thread.
					// If we include notifyAll() in the code (at the end), the
					// the notify() in the above statement
					// will not have any effect when id=0 (i.e. first iteration
					// of the inner while loop)
					System.out.print(ZeroOne.id);
					try {
						if (!oneIsRunning) {
							(new ZeroOne(ZeroOne.id + 1)).start();
							oneIsRunning = true;
						}
						sleep(300);
						o.wait();
						ZeroOne.id++;
					} catch (Exception e) {
					}
					if (ZeroOne.id == 99) {
						o.notifyAll();
					}
				}
			}
			// o.notifyAll() notifies the 99th thread (i.e 98th, since
			// counting begins from 0)
		}
	}

	public static void main(String args[]) {
		new ZeroOne(0).start();
	}
}