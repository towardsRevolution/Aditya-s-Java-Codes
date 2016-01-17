/**
 * Connect4Client4PRMI.java
 * @version $ID: Connect4Client4PRMI.java, v 1.8 11/19/2015 7:02pm 
 * 
 * Revision: 13.21 11/22/2015 11:43am
 *
 */
import java.io.*;
import java.net.*;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.*;
import java.util.*;

import javax.sound.midi.Receiver;

/* The class acts as the client in a network connection between
 * some clients and server formed using RMI.
 * 
 * @author Aditya Pulekar, Mandar Badave
 */

/*
 * This question ain't a conventional client-server problem. The only reason we require a 
 * server is to combat the expensive computation on client side. It ain't necessary to 
 * make the server act as the brain in this case. So, We may have Model, View and Controller,
 * all three on the client side.
 */
public class Connect4Client4PRMI {
	static int AlternateChances = 0;
	static char[][] Field;

	public Connect4Client4PRMI() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();
		for (int index = 0; index < 9; index++) {
			s.append(Field[index]);
			s.append("\n");
		}
		return s.toString();
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 * 
	 * @throws AlreadyBoundException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public static void main(String[] args) throws RemoteException,
			AlreadyBoundException, NotBoundException {
		// TODO Auto-generated method stub
		Object[] resultIndicator = null;
		Connect4Client4PRMI client = new Connect4Client4PRMI();
		/*
		 * if (System.getSecurityManager() == null) {
		 * System.setSecurityManager(new SecurityManager()); }
		 */

		// The no-argument overload of LocateRegistry.getRegistry() synthesizes
		// a reference to a
		// registry on the local host and on the default registry port, 1099.
		// You must use an
		// overload that has an int parameter if the registry is created on a
		// port other than 1099.

		String name = "Connect4Field";

		// Note: If we do not specify the port number (i.e. 2nd argument inside
		// "getRegistry()") we get
		// the connection refused error
		Registry reg = LocateRegistry.getRegistry("localhost", 65340);
		PlayerStatus c4f = (PlayerStatus) reg.lookup("Connect4Field");
		System.out.println("****Remote Object located!!****");
		Scanner sc = new Scanner(System.in);
		while (true) {
			if (AlternateChances % 4 == 0) {
				System.out.println("Passing the token to player A");
				System.out.println("Player A - Enter the column number : ");
				int column = sc.nextInt();
				Connect4RMIModel playerA = new Connect4RMIModel(column, '+');
				resultIndicator = c4f.executeForPlayer(playerA);
				Field = (char[][]) resultIndicator[0];
				System.out.println(client);
			} else if (AlternateChances % 4 == 1) {
				System.out.println("Passing the token to player B");
				System.out.println("Player B - Enter the column number : ");
				int column = sc.nextInt();
				Connect4RMIModel playerB = new Connect4RMIModel(column, '*');
				resultIndicator = c4f.executeForPlayer(playerB);
				Field = (char[][]) resultIndicator[0];
				System.out.println(client);
			} else if (AlternateChances % 4 == 2) {
				System.out.println("Passing the token to player C");
				System.out.println("Player C - Enter the column number : ");
				int column = sc.nextInt();
				Connect4RMIModel playerC = new Connect4RMIModel(column, '#');
				resultIndicator = c4f.executeForPlayer(playerC);
				Field = (char[][]) resultIndicator[0];
				System.out.println(client);
			} else if (AlternateChances % 4 == 3) {
				System.out.println("Passing the token to player D");
				System.out.println("Player D - Enter the column number : ");
				int column = sc.nextInt();
				Connect4RMIModel playerD = new Connect4RMIModel(column, '@');
				resultIndicator = c4f.executeForPlayer(playerD);
				Field = (char[][]) resultIndicator[0];
				System.out.println(client);
			}
			System.out.println("Result Indicator: " + resultIndicator[1]);
			if (resultIndicator[1].equals("Player A")
					|| resultIndicator[1].equals("Player B")
					|| resultIndicator[1].equals("Player C")
					|| resultIndicator[1].equals("Player D")) {
				System.out.println("\n\nThe Winner is: " + resultIndicator[1]);
				System.out.println("Final status of the board: \n");
				Field = (char[][]) resultIndicator[0];
				System.out.println(client);
				break;
			}
			AlternateChances++;
		}
	}

}