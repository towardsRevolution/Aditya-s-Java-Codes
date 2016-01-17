/**
 * Connect4Server4P.java
 * @version $ID: Connect4Server4P.java, v 1.8 11/7/2015 7:02pm 
 * 
 * Revision: 11.31 11/9/2015 11:43am
 *
 */
import java.net.*;
import java.io.*;

/*
 * @author Aditya Pulekar, Mandar Badave
 */

public class Connect4Server4P extends Thread {
	int column;
	char gamePiece;
	static int AlternateChances = 0;
	static String resultIndicator = null;
	static Connect4ServerModel4P PModel = new Connect4ServerModel4P();
	static char[][] Field = {
			{ '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
					'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' },
			{ ' ', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
					'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', ' ' },
			{ ' ', ' ', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
					'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', ' ', ' ' },
			{ ' ', ' ', ' ', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
					'.', '.', '.', '.', '.', '.', '.', '.', '.', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', '.', '.', '.', '.', '.', '.', '.', '.', '.',
					'.', '.', '.', '.', '.', '.', '.', '.', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', '.', '.', '.', '.', '.', '.', '.', '.',
					'.', '.', '.', '.', '.', '.', '.', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', '.', '.', '.', '.', '.', '.', '.',
					'.', '.', '.', '.', '.', '.', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '.', '.', '.', '.', '.',
					'.', '.', '.', '.', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '.', '.', '.', '.',
					'.', '.', '.', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };

	public Connect4Server4P(int column, char gamePiece) {
		this.column = column;
		this.gamePiece = gamePiece;
	}

	public Connect4Server4P() {

	}

	/**
	 * The method should display the updated game board whenever called for.
	 *
	 * @param Converts
	 *            the instance character array into a string for display
	 * 
	 * @return Returns a string
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();
		for (int index = 0; index < 9; index++) {
			s.append(Field[index]);
			s.append("\n");
		}
		return s.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		addNAnalyze();
	}

	/**
	 * The method adds the symbol onto the board and analyzes the board.
	 *
	 */
	public void addNAnalyze() {
		Connect4Server4P cs = new Connect4Server4P();
		Connect4ServerController4P.notification = PModel
				.checkIfPiecedCanBeDroppedIn(column);
		System.out.println("Can the gamepiece be dropped in this column? "
				+ Connect4ServerController4P.notification);
		if (Connect4ServerController4P.notification) {
			PModel.dropPieces(column, gamePiece);
		} else {
			System.out.println("This column is full! Try another column");
		}
		Connect4ServerController4P.notification = PModel.didLastMoveWin();
		System.out.println("Did the last move win: "
				+ Connect4ServerController4P.notification);
		// sc.close();
		System.out.println("\n");
		if (Connect4ServerController4P.notification) {
			if (gamePiece == '+') {
				System.out.println("The Winner is: Player A ");
				resultIndicator = "Player A";
			} else if (gamePiece == '*') {
				System.out.println("The Winner is: Player B ");
				resultIndicator = "Player B";
			} else if (gamePiece == '#') {
				System.out.println("The Winner is: Player C ");
				resultIndicator = "Player C";
			} else {
				System.out.println("The Winner is: Player D ");
				resultIndicator = "Player D";
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
		try {
			// Creates a server socket bound to the specified port
			ServerSocket serverSoc = new ServerSocket(8080);

			while (true) {
				Connect4Server4P conServer = new Connect4Server4P();
				Socket clientSoc = serverSoc.accept();
				System.out.println("Connection Established! Client: "
						+ clientSoc);
				ObjectOutputStream sendingToClient = new ObjectOutputStream(
						clientSoc.getOutputStream());
				if (AlternateChances % 4 == 0) {
					System.out.println("Passing the token to player A");
					sendingToClient.writeObject("Player A");
				} else if (AlternateChances % 4 == 1) {
					System.out.println("Passing the token to player B");
					sendingToClient.writeObject("Player B");
				} else if (AlternateChances % 4 == 2) {
					System.out.println("Passing the token to player C");
					sendingToClient.writeObject("Player C");
				} else if (AlternateChances % 4 == 3) {
					System.out.println("Passing the token to player D");
					sendingToClient.writeObject("Player D");
				}
				AlternateChances++;

				// Logic to make all the player threads terminate after a single
				// player wins!

				ObjectOutputStream result = new ObjectOutputStream(
						clientSoc.getOutputStream());
				if (resultIndicator != null) {
					if (resultIndicator.equals("Player A")) {
						result.writeObject("A");
					} else if (resultIndicator.equals("Player B")) {
						result.writeObject("B");
					} else if (resultIndicator.equals("Player C")) {
						result.writeObject("C");
					} else if (resultIndicator.equals("Player D")) {
						result.writeObject("D");
					}

				} else {
					result.writeObject("No Winner");

					DataInputStream listeningToClient = new DataInputStream(
							clientSoc.getInputStream());

					conServer.column = listeningToClient.readInt();
					conServer.gamePiece = listeningToClient.readChar();

					conServer.start();

					try {
						conServer.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				ObjectOutputStream displayField = new ObjectOutputStream(
						clientSoc.getOutputStream());
				displayField.writeObject(Field);
				ObjectOutputStream finalOutToClient = new ObjectOutputStream(
						clientSoc.getOutputStream());
				if (resultIndicator != null) {
					finalOutToClient.writeObject(resultIndicator);
					finalOutToClient.writeBoolean(true);
				} else {
					finalOutToClient.writeObject("No Winner yet");
					finalOutToClient.writeBoolean(false);
				}
				finalOutToClient.close();
				displayField.close();
				clientSoc.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
