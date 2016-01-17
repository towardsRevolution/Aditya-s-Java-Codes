import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Remote Interface
 */
public interface PlayerStatus extends Remote {
	<T> T executeForPlayer(Connect4FieldRMIInterface<T> task)
			throws RemoteException;
}
