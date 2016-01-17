/**
 * crossingTheBridge.java
 * @version $ID: crossingTheBridge.java, v 1.8 10/28/2015 3:04pm 
 * 
 * Revision: 10.21 11/2/2015 11:43am
 *
 */
import java.util.*;

/**
 * This program simulates crossing of the bridge by a random number of trucks
 * (max 10) using wait, notify, notifyALL(), synchronized 
 * 
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */
public class crossingTheBridge extends Thread {
	int truckWeight;
	static final int MAX = 20;   
	static Vector aVector = new Vector();  //Object to be synchronized on
	static int trucksWereStarted = 0;    //A flag to check whether there are 4 or less than 4 trucks 
	static int truckName = 1;
	
	/*
	 * Default constructor of the class Player
	 */
	public crossingTheBridge() {

	}
	
	/*
	 * Parameterized constructor of the class Player
	 * 
	 * @param truckWeight  Weight of the truck
	 */
	public crossingTheBridge(int truckWeight) {
		this.truckWeight = truckWeight;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		synchronized (aVector) {
			System.out.println("\nThread- "+ Thread.currentThread().getId() + " has arrived to cross the bridge!...");
			/*
			 * Making the thread sleep in order to prove that only one thread
			 * executes the synchronized block at a moment
			 */
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread- "+ Thread.currentThread().getId() + " crosses the bridge!...");
			truckName++;
			aVector.notify();
		}
	}
	
	/**
	 * On incrementing the value of the instance variable 'AlternateChances',
	 * the chance should alternate between player A and player B.
	 * 
	 * @param trucks             array of truck threads to be started
	 * @param pointerLoc		 Location of the first truck thread that needs to be started
	 * @param NoOfTrucksCrossing Maximum limit for the truck threads that are to should be started
	 */
	public void trucksCrossingBridge(crossingTheBridge[] trucks,
			int pointerForLoc, int NoOfTrucksCrossing) {
		int sum = 0, index;
		synchronized (aVector) {
			System.out.println("Number of trucks on the bridge: "
					+ (NoOfTrucksCrossing - pointerForLoc));
			for (index = pointerForLoc; index < NoOfTrucksCrossing; index++) {
				sum = sum + trucks[index].truckWeight;
				trucks[index].start();
				try {
					aVector.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//System.out.println("Number of active threads: "+ activeCount());
			trucksWereStarted = 1;
		}
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		crossingTheBridge bridge = new crossingTheBridge();
		int sum = 0, randomWeight, pointerForLoc = 0, flag = 0;
		int totalTrucks = 0;
		Random r = new Random();

		// Taking maximum 10 trucks at a time!

		int numberOfTrucks = r.nextInt(10);
		crossingTheBridge[] trucks = new crossingTheBridge[MAX];
		
		// Note: At the most there can be 4 trucks on the bridge at a time. But,
		// while crossing, just one will be crossing in either direction
		
		System.out.println("Total Number of trucks: " + (numberOfTrucks + 1));

		synchronized (aVector) {
			for (totalTrucks = 0; totalTrucks < (numberOfTrucks + 1); totalTrucks++) {
				if ((numberOfTrucks + 1) >= 4) {
					if (sum < 200000) {
						if ((totalTrucks == 0 && pointerForLoc == 0)
								|| (totalTrucks - pointerForLoc) % 4 != 0) {
							randomWeight = r.nextInt(99901);
							trucks[totalTrucks] = new crossingTheBridge(
									(100 + randomWeight)); // The weight of
															// the
															// truck is
															// within
															// 100 to
															// 100000 lbs
							sum = sum + 100 + randomWeight;
						} else if ((totalTrucks - pointerForLoc) % 4 == 0) {
							System.out
									.println("\n\nTime to make the trucks cross the bridge.... "
											+ pointerForLoc + " " + totalTrucks);
							randomWeight = r.nextInt(99901);
							trucks[totalTrucks] = new crossingTheBridge(
									(100 + randomWeight)); // So that we
															// don't miss to
															// take truck 4
															// into account
							bridge.trucksCrossingBridge(trucks,
									pointerForLoc, totalTrucks);
							pointerForLoc = totalTrucks;
							sum = 0;
						}
					} else if (sum > 200000) {
						// For any number of trucks, if the total weight of
						// trucks is more than 200000, then we have to
						// immediately make all the trucks available on the
						// bridge to cross it.
						// How-many-ever trucks are there (may be 4 or < 4),
						// make them cross the bridge
						System.out
								.println("\n\nTime to make the trucks cross the bridge.... "
										+ pointerForLoc + " " + totalTrucks);
						/*
						 * try { aVector.wait(); } catch (InterruptedException
						 * e) { // TODO Auto-generated catch block
						 * e.printStackTrace(); }
						 */
						randomWeight = r.nextInt(99901);
						trucks[totalTrucks] = new crossingTheBridge(
								(100 + randomWeight));
						bridge.trucksCrossingBridge(trucks, pointerForLoc,
								(totalTrucks-1));
						//We pass "totalTrucks-1" becoz the sum exceeded 200000 in previous iteration 
						pointerForLoc = (totalTrucks-1); // pointerForLoc is an
														// indication of which
														// all
														// trucks are supposed
														// to
														// cross the bridge
						sum = 0;
					}
				} else {
					if (sum < 200000) {
						randomWeight = r.nextInt(99901);
						trucks[totalTrucks] = new crossingTheBridge(
								(100 + randomWeight)); // The weight of
														// the
														// truck is
														// within
														// 100 to
														// 100000 lbs
						sum = sum + 100 + randomWeight;
					} else {
						System.out
								.println("\n\nTime to make the trucks cross the bridge.... "
										+ pointerForLoc + " " + totalTrucks);
						randomWeight = r.nextInt(99901);
						trucks[totalTrucks] = new crossingTheBridge(
								(100 + randomWeight));
						bridge.trucksCrossingBridge(trucks, pointerForLoc,
								(totalTrucks-1));
						pointerForLoc = (totalTrucks-1);
						sum = 0;
					}

				}
			}
			// Eg: there were only two trucks whose net weight came out to be
			// <200000, then, too, these two trucks
			// should be made to cross the road.
			if (trucksWereStarted == 0) {
				System.out
						.println("\n\nTime to make the trucks cross the bridge.... "
								+ pointerForLoc + " " + totalTrucks);
				bridge.trucksCrossingBridge(trucks, pointerForLoc,
						(numberOfTrucks + 1));
			} else {
				if (pointerForLoc <= numberOfTrucks) { // Indicating that all
														// the trucks were
														// covered.
					System.out
							.println("\n\nTime to make the trucks cross the bridge.... "
									+ pointerForLoc + " " + totalTrucks);
					bridge.trucksCrossingBridge(trucks, pointerForLoc,
							(numberOfTrucks + 1));
				}
			}
		}
	}

}
