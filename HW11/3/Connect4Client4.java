/**
 * Connect4Client4.java
 * @version $ID: Connect4Client4.java, v 1.8 11/7/2015 7:02pm 
 * 
 * Revision: 11.31 11/9/2015 11:43am
 *
 */
import java.io.*;
import java.net.*;
import java.util.*;

/*
 * @author Aditya Pulekar, Mandar Badave
 */

public class Connect4Client4 extends Thread {
	String name; // name of the player
	char gamepiece; // Gamepieces of the players ('+','*')
	Socket clientSoc;

	public Connect4Client4(String name, char gamepiece) {
		this.name = name;
		this.gamepiece = gamepiece;
	}

	public Connect4Client4() {
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
			clientSoc = new Socket("AdityaPulekar", 8080);

			ObjectInputStream bufIn = new ObjectInputStream(
					clientSoc.getInputStream());
			try {
				while (!bufIn.readObject().equals("Player D"))
					; // Note: We cannot close the socket inside this while loop
						// (it throws an error)
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
			System.out.println("Player D - Enter the column number : ");
			int column = sc.nextInt();

			// Data being written from the socket to the client
			DataOutputStream out = new DataOutputStream(
					clientSoc.getOutputStream());

			out.writeInt(column);
			out.writeChar('@');

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
				if (finalInputFromServer.readObject().equals("Player D")) {
					System.out.println("Winner: Player D");
					// resultOfGame=finalInputFromServer.readBoolean();
					System.exit(0);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (dispField != null) {
					dispField.close();
				}
			}
		} catch (UnknownHostException e) {
			System.out.println("Host was not found!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (clientSoc != null) {
				try {
					clientSoc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

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
		while (true) {
			Connect4Client4 playerD = new Connect4Client4();
			playerD.setName("Player D");
			playerD.start();
			try {
				playerD.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}