/**
 * Player.java
 * @version $ID: Player.java, v 1.8 09/17/2015 7:02pm 
 * 
 * Revision: 4.21 09/21/2015 11:43am
 *
 */
import java.util.*;

/**
 * This program designs the game 'Connect4Field' and allows two players to play
 * against one another on the same computer screen.
 * 
 * @author Aditya Pulekar
 *
 */
public class Player implements PlayerInterface {
	Connect4FieldInterface theField; // an instance of the class Connect4Field
	String name;    				// name of the player
	char gamepiece; 				// Gamepieces of the players ('+','*')
	int AlternateChances = 0; 		// An instance variable to alternate between
									// the two players

	/*
	 * Parameterized constructor of the class Player
	 */
	public Player(Connect4FieldInterface theField, String name, char gamepiece) {
		this.theField = theField;
		this.name = name;
		this.gamepiece = gamepiece;
	}

	/*
	 * Default constructor of the class Player
	 */
	public Player() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * On incrementing the value of the instance variable 'AlternateChances',
	 * the chance should alternate between player A and player B.
	 * 
	 * @return      returns the character gamepiece
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
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
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
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int toIndicateADraw = 0;
		Connect4Field aConnect4Field = new Connect4Field();
		Player keepCount = new Player();
		Player aPlayer = new Player(aConnect4Field, "A", '+');
		Player bPlayer = new Player(aConnect4Field, "B", '*');
		Scanner sc = new Scanner(System.in);
		System.out.println(aConnect4Field);
		while (!aConnect4Field.isItaDraw()) {
			if (keepCount.nextMove() % 2 != 0) {

				// For odd value of 'AlternateChances', Player A gets to play

				System.out.println("Player A - Enter the column number : ");
				int column = sc.nextInt();

				/*
				 * Check whether the column is full. If the column is full, make
				 * sure that the same guy gets one more chance
				 */

				System.out
						.println("Can the gamepiece be dropped in this column? "
								+ aConnect4Field
										.checkIfPiecedCanBeDroppedIn(column));
				if (aConnect4Field.checkIfPiecedCanBeDroppedIn(column)) {
					aConnect4Field.dropPieces(column, aPlayer.gamepiece);
					System.out.println(aConnect4Field);
				} else {
					System.out
							.println("This column is full! Try another column");
					keepCount.AlternateChances--;
				}
				System.out.println("Did the last move win: "
						+ aConnect4Field.didLastMoveWin());
				if (aConnect4Field.didLastMoveWin()) {
					System.out.println("The Winner is: " + aPlayer.getName());
					toIndicateADraw = 1;
					break;
				}
			} else {

				// For even value of 'AlternateChances', Player B gets to play

				System.out.println("Player B - Enter the column number : ");
				int column = sc.nextInt();
				System.out
						.println("Can the gamepiece be dropped in this column? "
								+ aConnect4Field
										.checkIfPiecedCanBeDroppedIn(column));
				if (aConnect4Field.checkIfPiecedCanBeDroppedIn(column)) {
					aConnect4Field.dropPieces(column, bPlayer.gamepiece);
					System.out.println(aConnect4Field);
				} else {
					System.out
							.println("This column is full! Try another column");
					keepCount.AlternateChances--;
				}

				System.out.println("Did the last move win: "
						+ aConnect4Field.didLastMoveWin());
				if (aConnect4Field.didLastMoveWin()) {
					System.out.println("The Winner is: " + bPlayer.getName());
					toIndicateADraw = 1;
					break;
				}
			}
		}

		// In case, there is no more area on the board to drop the gamepieces,
		// the game draws

		if (toIndicateADraw == 0) {
			System.out.println("Game Over: It's a Draw!");
		}

	}
}
