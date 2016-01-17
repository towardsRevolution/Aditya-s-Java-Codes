/**
 * Connect4ServerRMI.java
 * @version $ID: Connect4ServerRMI.java, v 1.8 11/19/2015 7:02pm 
 * 
 * Revision: 13.11 11/22/2015 11:43am
 *
 */

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;

/* The class acts as the Server in a network connection between
 * some clients and server formed using RMI.
 * 
 * @author Aditya Pulekar
 */

public class Connect4ServerRMI extends UnicastRemoteObject implements
		PlayerStatus {

	public Connect4ServerRMI() throws RemoteException {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T executeForPlayer(Connect4FieldRMIInterface<T> task) {
		// TODO Auto-generated method stub
		return (T) task.addNAnalyze();
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 * @throws AlreadyBoundException
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException,
			AlreadyBoundException {
		// TODO Auto-generated method stub

		// Note: If we include the piece of code on the line 58, we will also
		// have
		// to then specify the security policy in the command line arguments
		// while
		// running the code
		/*
		 * if (System.getSecurityManager() == null) {
		 * System.setSecurityManager(new SecurityManager()); }
		 */
		String name = "Connect4Field";
		PlayerStatus remObj = new Connect4ServerRMI();
	Registry registry = LocateRegistry.createRegistry(65340);
		registry.rebind(name, remObj);
		System.out.println("Server Running...");
	}
}

// Question is whether we ever get a stub? Yes, bcoz
// LocateRegistry.exportObject() also has a return
// type "Remote". Note: This object ain't a copy rather a proxy object
// We get the foll. error when we do not specify the security policy while
// running this class:
// java.security.AccessControlException: access denied
// ("java.net.SocketPermission" "127.0.0.1:1099" "connect,resolve")
