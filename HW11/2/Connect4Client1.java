/**
 * Connect4Client1.java
 * @version $ID: Connect4Client1.java, v 1.8 11/7/2015 7:02pm 
 * 
 * Revision: 11.21 11/9/2015 11:43am
 *
 */
import java.io.*;
import java.net.*;
import java.util.*;

import javax.sound.midi.Receiver;

/*
 * @author Aditya Pulekar, Mandar Badave
 */
 
public class Connect4Client1 extends Thread {
	String name; // name of the player
	char gamepiece; // Gamepieces of the players ('+','*')
	Socket clientSoc;
	static boolean resultOfGame = false;
	static InetAddress IpAddr;

	public Connect4Client1(String name, char gamepiece) {
		this.name = name;
		this.gamepiece = gamepiece;
	}

	public Connect4Client1() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		// TODO Auto-generated method stub

		try {
			clientSoc = new Socket(IpAddr, 64230);

			ObjectInputStream bufIn = new ObjectInputStream(
					clientSoc.getInputStream());
			try {
				while (!bufIn.readObject().equals("Player A"))
					; // Note: We cannot close the socket inside this while loop
						// (it throws an error)
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				System.out.println("ClassNotFound Exception.");
			}

			// Logic to make all the player threads terminate after a single
			// player wins!
			try {
				ObjectInputStream receiveResult = new ObjectInputStream(
						clientSoc.getInputStream());
				Object s1 = receiveResult.readObject();
				if (s1.equals("A")) {
					System.out.println("Winner : Player A");
					System.exit(0);
				} else if (s1.equals("B")) {
					System.out.println("Winner : Player B");
					System.exit(0);
				} else if (s1.equals("C")) {
					System.out.println("Winner : Player C");
					System.exit(0);
				} else if (s1.equals("D")) {
					System.out.println("Winner : Player D");
					System.exit(0);
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Scanner sc = new Scanner(System.in);
			System.out.println("Player A - Enter the column number : ");
			int column = sc.nextInt();

			// Data being written from the socket to the client
			DataOutputStream out = new DataOutputStream(
					clientSoc.getOutputStream());

			out.writeInt(column);
			out.writeChar('+');

			// Object Serialization
			ObjectInputStream dispField = new ObjectInputStream(
					clientSoc.getInputStream());
			try {
				char[][] field = (char[][]) dispField.readObject();
				StringBuffer s = new StringBuffer();
				for (int index = 0; index < 9; index++) {
					s.append(field[index]);
					s.append("\n");
				}
				System.out.println(s);
				ObjectInputStream finalInputFromServer = new ObjectInputStream(
						clientSoc.getInputStream());
				if (finalInputFromServer.readObject().equals("Player A")) {
					System.out.println("Winner: Player A");
					resultOfGame = finalInputFromServer.readBoolean();
					System.exit(0);
				}

				// Logic to make all the player threads terminate after a single
				// player wins!
				/*if (resultOfGame) {
					System.out.println("Winner: Player B");
					System.exit(0);
				}*/

				// finalInputFromServer.close();
				// dispField.close();
				// DataInputStream finalOut
				// bufIn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("ClassNotFound Exception.");
			} finally {
				/*
				 * if(dispField!=null){ dispField.close(); }
				 */

			}

		} catch (UnknownHostException e) {
			System.out.println("Host was not found!");
		} catch (IOException e) {
			System.out.println("Exception in the run of the client: ");
			System.out.println("IOException.");
			e.printStackTrace();
		} finally {

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			IpAddr = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			System.out.println("Unknown Host Exception!");
		}
		while (true) {
			// forSocketCreation.clientSoc = new Socket("AdityaPulekar", 8080);
			Connect4Client1 playerA = new Connect4Client1();
			// playerA.clientSoc=forSocketCreation.clientSoc;
			playerA.setName("Player A");
			playerA.start();
			try {
				playerA.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("Interrupted Exception");
				// System.exit(0);
			}
		}
	}

}