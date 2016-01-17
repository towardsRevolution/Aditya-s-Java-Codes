/**
 * PlayerViewModel.java
 * @version $ID: PlayerViewModel.java, v 1.8 09/2/2015 7:02pm 
 * 
 * Revision: 6.22 09/5/2015 11:43am
 *
 */
import java.util.*;

/**
 * The Class represents the view for the Connect4Field game (Player vs Player)
 * 
 * @author Aditya Pulekar, Mandar Badave
 *
 */
public class PlayerView extends Thread implements PlayerInterface {
	Connect4FieldInterface theField; // an instance of the class Connect4Field
	String name; // name of the player
	char gamepiece; // Gamepieces of the players ('+','*')
	static int AlternateChances = 0; // An instance variable to alternate
										// between
										// the two players
	static Vector aVector = new Vector();
	static String LastThreadToExecute = "SomeThread";
	static PlayerView PView = new PlayerView();
	static PlayerModel PModel = new PlayerModel();
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

	public PlayerView(Connect4FieldInterface theField, String name,
			char gamepiece) {
		this.theField = theField;
		this.name = name;
		this.gamepiece = gamepiece;
	}

	/*
	 * Default constructor of the class Player
	 */
	public PlayerView() {
		// TODO Auto-generated constructor stub
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

	/**
	 * On incrementing the value of the instance variable 'AlternateChances',
	 * the chance should alternate between player A and player B.
	 * 
	 * @return returns the incremented instance variable 'AlternateChances'
	 */
	@Override
	public int nextMove() {
		// TODO Auto-generated method stub
		AlternateChances++;
		return AlternateChances;
	}

	/**
	 * On incrementing the value of the instance variable 'AlternateChances',
	 * the chance should alternate between player A and player B.
	 * 
	 * @return returns the character gamepiece
	 */
	@Override
	public char getGamePiece() {
		// TODO Auto-generated method stub
		return this.gamepiece;
	}

	/**
	 * On incrementing the value of the instance variable 'AlternateChances',
	 * the chance should alternate between player A and player B.
	 *
	 * 
	 * @return returns the player name
	 */
	/*
	 * @Override public String getName() { // TODO Auto-generated method stub
	 * return this.name; }
	 */

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (LastThreadToExecute.equals(Thread.currentThread().getName())
				&& PlayerController.ThreadCount != 2) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		synchronized (aVector) {

			Scanner sc = new Scanner(System.in);
			System.out.println(PView);
			if (Thread.currentThread().getName().equals("Player A")) {

				// For odd value of 'AlternateChances', Player A gets to
				// play

				System.out.println("Player A - Enter the column number : ");
				int column = sc.nextInt();

				/*
				 * Check whether the column is full. If the column is full, make
				 * sure that the same guy gets one more chance
				 */
				PlayerController.notification = PModel
						.checkIfPiecedCanBeDroppedIn(column);
				System.out
						.println("Can the gamepiece be dropped in this column? "
								+ PlayerController.notification);
				if (PlayerController.notification) {
					PModel.dropPieces(column, '+');
					System.out.println(PView);
				} else {
					System.out
							.println("This column is full! Try another column");
					AlternateChances--;
				}
				PlayerController.notification = PModel.didLastMoveWin();
				System.out.println("Did the last move win: "
						+ PlayerController.notification);
				// sc.close();
				if (PlayerController.notification) {
					System.out.println("The Winner is: "
							+ Thread.currentThread().getName());
					// PlayerController.returnValue = 1;
					System.exit(0);
				}
			} else {

				// For even value of 'AlternateChances', Player B gets to
				// play

				System.out.println("Player B - Enter the column number : ");
				int column = sc.nextInt();
				PlayerController.notification = PModel
						.checkIfPiecedCanBeDroppedIn(column);
				System.out
						.println("Can the gamepiece be dropped in this column? "
								+ PlayerController.notification);
				if (PlayerController.notification) {
					PModel.dropPieces(column, '*');
					System.out.println(PView);
				} else {
					System.out
							.println("This column is full! Try another column");
					AlternateChances--;
				}
				PlayerController.notification = PModel.didLastMoveWin();
				System.out.println("Did the last move win: "
						+ PlayerController.notification);
				// sc.close();
				if (PlayerController.notification) {
					System.out.println("The Winner is: "
							+ Thread.currentThread().getName());
					System.exit(0);
					// PlayerController.returnValue = 1;
				} /*
				 * else { PlayerController.returnValue = 0; }
				 */
			}
			PlayerController.ThreadCount -= 2;

			if (PlayerController.ThreadCount == 0) {
				LastThreadToExecute = Thread.currentThread().getName();
			}
		}

	}
}
