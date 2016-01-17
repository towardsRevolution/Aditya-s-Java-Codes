/**
 * Connect4FieldControl.java
 * @version $ID: Connect4FieldControl.java, v 1.8 09/2/2015 7:02pm 
 * 
 * Revision: 6.21 09/5/2015 11:43am
 *
 */
/**
 * The Class represents the controller for the Connect4Field game design(test
 * board)
 * 
 * @author Aditya Pulekar
 *
 */
public class Connect4FieldControl {
	static boolean notification; // Updated by the Model class

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		Connect4FieldModel GameModel = new Connect4FieldModel();
		Connect4FieldView GameView = new Connect4FieldView();
		GameView.testIt(GameModel);
	}
}
