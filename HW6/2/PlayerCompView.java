/**
 * PlayerCompView.java
 * @version $ID: PlayerCompView.java, v 1.8 09/2/2015 7:02pm 
 * 
 * Revision: 6.23 09/5/2015 11:43am
 *
 */
import java.util.Scanner;

/**
 * The Class represents the view for the Connect4Field game design(Player vs
 * Computer)
 * 
 * @author Aditya Pulekar
 *
 */
public class PlayerCompView implements PlayerInterface {
	Connect4FieldInterface theField; // an instance of the class Connect4Field
	String name; // name of the player
	char gamepiece; // Gamepieces of the players ('+','*')
	static int AlternateChances = 0; // An instance variable to alternate
										// between
	// the two players
	static int column; // To keep track of the input by the user
	String DisplayModelMsg;
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

	/**
	 * Parameterized constructor
	 * 
	 * @param theField
	 *            Stores an instance of PlayerCompModel class
	 * @param name
	 *            Stores the player name
	 * @param gamepiece
	 *            Stores the player's gamepiece
	 */
	public PlayerCompView(Connect4FieldInterface theField, String name,
			char gamepiece) {
		this.theField = theField;
		this.name = name;
		this.gamepiece = gamepiece;
	}

	/**
	 * Default constructor of the class Player
	 */
	public PlayerCompView() {
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
	 * The method should return the player's gamepiece
	 * 
	 * @return returns the character gamepiece
	 */
	@Override
	public char getGamePiece() {
		// TODO Auto-generated method stub
		return this.gamepiece;
	}

	/**
	 * The method should return the player's name
	 *
	 * 
	 * @return returns the player name
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	/**
	 * The method should return the player's name
	 *
	 * @param aPlayer
	 *            an instance of human player
	 * @param computer
	 *            an instance of computer
	 * @param toCheckIfItIsComps1stTurn
	 *            To ensure computer's first turn
	 * 
	 * 
	 * @return returns a 1 or 0 to indicate whether a match is over or not
	 */
	public int getPlayerInput(PlayerCompView aPlayer, PlayerCompView computer,
			int toCheckIfItIsComps1stTurn) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		PlayerCompView PView = new PlayerCompView();
		PlayerCompModel PModel = new PlayerCompModel();
		if (PView.nextMove() % 2 != 0) {

			// For odd value of 'AlternateChances', Player A gets to play

			System.out.println("Player A - Enter the column number : ");
			column = sc.nextInt();

			/*
			 * Check whether the column is full. If the column is full, make
			 * sure that the same guy gets one more chance
			 */
			PlayerCompController.notification = PModel
					.checkIfPiecedCanBeDroppedIn(column);
			System.out.println("Can the gamepiece be dropped in this column? "
					+ PModel.checkIfPiecedCanBeDroppedIn(column));
			if (PlayerCompController.notification) {
				PModel.dropPieces(column, aPlayer.gamepiece);
				if (DisplayModelMsg != null) {
					System.out.println(DisplayModelMsg);
				}
				System.out.println(PView);
			} else {
				System.out.println("This column is full! Try another column");
				AlternateChances--;
			}
			PlayerCompController.notification = PModel.didLastMoveWin();
			System.out.println("Did the last move win: "
					+ PlayerCompController.notification);
			// sc.close();
			if (PlayerCompController.notification) {
				System.out.println("The Winner is: " + aPlayer.getName());
				return 1;
			} else {
				return 0;
			}
		} else {

			/*
			 * For even value of 'AlternateChances', Player B gets to play
			 */

			System.out.println("Computer's turn ");
			PModel.computerBrain(PModel, toCheckIfItIsComps1stTurn, column);
			toCheckIfItIsComps1stTurn = 0;
			System.out.println(PView);
			PlayerCompController.notification = PModel.didLastMoveWin();
			System.out.println("Did the last move win: "
					+ PlayerCompController.notification);
			if (PlayerCompController.notification) {
				System.out.println("The Winner is: Computer");
				return 1;
			} else {
				return 0;
			}
		}
	}
}
