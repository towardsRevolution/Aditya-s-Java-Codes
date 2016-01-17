/**
 * HelloC.java
 * @version $ID: HelloC.java, v 1.8 11/19/2015 7:02pm 
 * 
 * Revision: 13.31 11/22/2015 11:43am
 *
 */

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//Doubt: Why doesn't this class terminate on its own?
public class HelloC {

	/*
	 * This static method tests whether the whether the test() method is called
	 * on a remote or a local object.
	 * 
	 * @param obj a remote or local object
	 */
	public static void localRemoteTest(HelloInterface obj) {
		try {
			obj.test("Testing is happening...");
			System.out.println(obj.getClass());
			/*if (String.valueOf(obj.getClass()).equals(
					"class com.sun.proxy.$Proxy0")) {
				System.out
						.println("'obj' is a REMOTE OBJECT . So, test() will be executed remotely! ");
			} else {
				System.out
						.println("'obj' is a LOCAL OBJECT . So, test() will be executed locally!");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 * @throws AlreadyBoundException
	 * @throws RemoteException
	 */
	public static void main(String args[]) {

		try {
			// Note: "//host:port/name ("host": it is the host where the
			// registry is located).
			// Since port number ain't mentioned in line 19, it is default 1099.
			// 1099 is a well known port that the rmi registry uses.

			// Note: test() execution will be remote for the line below
			/*localRemoteTest((HelloInterface) Naming
					.lookup("//localhost:65340/thisOne"));*/
			HelloInterface h =(HelloInterface) Naming
								.lookup("//localhost:65340/thisOne");
			String s = h.test("Hello");
			// The below statement creates a regular object with
			// "new HelloImplementation"

			// Note: test() execution will be local for the line below
			//localRemoteTest(new HelloImplementation());
			System.out.println("Msg: " + s);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}