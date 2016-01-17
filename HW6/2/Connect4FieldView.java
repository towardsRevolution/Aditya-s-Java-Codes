/**
 * Connect4FieldView.java
 * @version $ID: Connect4FieldView.java, v 1.8 09/2/2015 7:02pm 
 * 
 * Revision: 6.21 09/5/2015 11:43am
 *
 */
/**
 * The Class represents the view for the Connect4Field game design (test board)
 * 
 * @author Aditya Pulekar, Mandar Badave
 *
 */
public class Connect4FieldView {
	String DisplayModelMsg; // For displaying messages cropping from
							// Connect4FieldModel class
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
	 * The method should perform tests on the field by giving diverse column
	 * numbers and dropping '+' and '*' in these columns
	 *
	 * @param GameModel
	 *            an instance of Connect4FieldModel
	 */
	public void testIt(Connect4FieldModel GameModel) {
		System.out.println(this);
		String Display;
		Display = GameModel.dropTest(-1);
		System.out.println(Display);
		Display = GameModel.dropTest(0);
		System.out.println(Display);
		Display = GameModel.dropTest(1);
		System.out.println(Display);
		GameModel.dropPieces(12, '+');
		System.out.println(this);
		GameModel.dropPieces(1, '*');
		System.out.println(this);
		Connect4FieldControl.notification = GameModel.didLastMoveWin();
		System.out.println("Did the last move win: "
				+ Connect4FieldControl.notification);
		Connect4FieldControl.notification = GameModel.isItaDraw();
		System.out
				.println("Is it a Draw: " + Connect4FieldControl.notification);
	}
}
