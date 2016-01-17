import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Remote Interface
 */
public interface HelloInterface extends Remote {
	public String test(String s)  throws RemoteException;
}
