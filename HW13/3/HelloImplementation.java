/**
 * HelloImplementation.java
 * @version $ID: HelloImplementation.java, v 1.8 11/19/2015 7:02pm 
 * 
 * Revision: 13.31 11/22/2015 11:43am
 *
 */
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* The class acts as the Server in a network connection between
 * a client and server formed using RMI.
 * 
 * @author Aditya Pulekar, Mandar Badave
 */
public class HelloImplementation extends UnicastRemoteObject implements HelloInterface {

	public HelloImplementation() throws RemoteException{
		super();
	}
	
	@Override
	public String test(String s) {
		// TODO Auto-generated method stub
		return s + " Aditya";
	}
	
	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 * @throws AlreadyBoundException
	 * @throws RemoteException
	 */
	public static void main(String args[] ) throws RemoteException, AlreadyBoundException {
		String name = "thisOne";
        HelloInterface remObj = new HelloImplementation();  
        Registry registry = LocateRegistry.createRegistry(65340);
        registry.rebind(name, remObj);
        System.out.println("Server Running...");
	}

}
