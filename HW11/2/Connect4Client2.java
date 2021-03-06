/**
 * Connect4Client2.java
 * @version $ID: Connect4Client2.java, v 1.8 11/7/2015 7:02pm 
 * 
 * Revision: 11.21 11/9/2015 11:43am
 *
 */
import java.io.*;
import java.net.*;
import java.util.*;

/*
 * @author Aditya Pulekar, Mandar Badave
 */
 
public class Connect4Client2 extends Thread {
	String name; // name of the player
	char gamepiece; // Gamepieces of the players ('+','*')
	Socket clientSoc;
	static boolean resultOfGame = false;
	static InetAddress IpAddr;

	public Connect4Client2(String name, char gamepiece) {
		this.name = name;
		this.gamepiece = gamepiece;
	}

	public Connect4Client2() {
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
			clientSoc = new Socket(IpAddr, 8080);
			ObjectInputStream objIn = new ObjectInputStream(
					clientSoc.getInputStream());
			try {
				while (!objIn.readObject().equals("Player B"))
					;
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
			System.out.println("Player B - Enter the column number : ");
			int column = sc.nextInt();

			DataOutputStream out = new DataOutputStream(
					clientSoc.getOutputStream());

			out.writeInt(column);
			out.writeChar('*');

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
				if (finalInputFromServer.readObject().equals("Player B")) {
					System.out.println("Winner: Player B");
					resultOfGame = finalInputFromServer.readBoolean();
					System.exit(0);
				}

				// Logic to make all the player threads terminate after a single
				// player wins!
				if (resultOfGame) {
					System.out.println("Winner: Player A");
					System.exit(0);
				}

				// finalInputFromServer.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("ClassNotFound Exception.");
			} finally {
				/*
				 * if(dispField!=null){ dispField.close(); }
				 */
			}

			// clientSoc.close();
		} catch (UnknownHostException e) {
			System.out.println("Host was not found!");
		} catch (IOException e) {
			System.out.println("Exception in the run of the client: ");
			System.out.println("IOException.");
			e.printStackTrace();
		} finally {
			/*
			 * if(clientSoc!=null){ try { clientSoc.close(); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * //e.printStackTrace(); System.out.println("IOException."); } }
			 */
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
		try {
			IpAddr = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			System.out.println("Unknown Host Exception!");
		}
		while (true) {
			// forSocketCreation.clientSoc = new Socket("AdityaPulekar", 8080);
			Connect4Client2 playerB = new Connect4Client2();
			// playerA.clientSoc=forSocketCreation.clientSoc;
			playerB.setName("Player B");
			playerB.start();
			try {
				playerB.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("Interrupted Exception.");
			}

		}
	}

}