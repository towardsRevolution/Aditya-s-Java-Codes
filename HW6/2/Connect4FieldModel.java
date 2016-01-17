/**
 * Connect4FieldModel.java
 * @version $ID: Connect4FieldModel.java, v 1.8 09/2/2015 7:02pm 
 * 
 * Revision: 6.21 09/5/2015 11:43am
 *
 */
/**
 * The Class represents the model for the Connect4Field game design(test board)
 * 
 * @author Aditya Pulekar
 *
 */
public class Connect4FieldModel implements Connect4FieldInterface {
	static int patternCount; // to maintain the pattern count of '+' & '*'

	/**
	 * Given the column in which the gamepiece is to be dropped, it should
	 * examine whether the column is full or not.
	 *
	 * @param column
	 *            Checking whether the column in which the gamepiece is being
	 *            dropped is full and accordingly returning a boolean value
	 * 
	 */

	@Override
	public boolean checkIfPiecedCanBeDroppedIn(int column) {
		// TODO Auto-generated method stub
		int flg = 0;
		if (column >= 0) {
			for (int index = (Connect4FieldView.Field.length - 1); index >= 0; index--) {
				if (Connect4FieldView.Field[index][column] == '.') {
					flg = 1;
					break;
				}
			}
		}
		if (flg == 1) {
			return true;
		} else
			return false;
	}

	/**
	 * Given the column, it should drop the gamepiece in that particular column
	 *
	 * @param column
	 *            , gamepiece Dropping the gamepiece specified in the arguments
	 *            into the column specified in the argument as well
	 * 
	 * 
	 */
	@Override
	public void dropPieces(int column, char gamePiece) {
		// TODO Auto-generated method stub
		Connect4FieldView Display = new Connect4FieldView();
		int flg = 0, index = 0;
		if (column >= 0) {
			for (index = (Connect4FieldView.Field.length - 1); index >= 0; index--) {
				if (Connect4FieldView.Field[index][column] == '.') {
					flg = 1;
					break;
				}
			}
		} else {
			Display.DisplayModelMsg = "You entered the wrong column!";
			flg = -1;
		}
		if (flg == 1) {
			Connect4FieldView.Field[index][column] = gamePiece;
		} else if (flg == 0) {
			Display.DisplayModelMsg = "The Board is full!";
		}
	}

	/**
	 * Given the indices of some of the locations on the board, orientation and
	 * gamepiece, it should calculate the pattern count for the gamepiece
	 * through recursion.
	 *
	 * @param index
	 *            ,index2,orientation[] Maintains a pattern count for any given
	 *            gamepiece
	 * 
	 */
	public void FollowPattern(int index, int index2, char[] orientation,
			int itr, char currentChar) {
		if (Connect4FieldView.Field[index][index2] == currentChar) {
			if (orientation[itr] == 'd') {
				if ((index + 1 < Connect4FieldView.Field.length)
						&& (index2 + 1 < Connect4FieldView.Field[0].length)) {
					FollowPattern(index + 1, index2 + 1, orientation, itr,
							currentChar);
					patternCount++;
				} else if (index == (Connect4FieldView.Field.length - 1)) {
					patternCount++;
				}
			} else if (orientation[itr] == 'l') {
				if ((index + 1 < Connect4FieldView.Field.length)
						&& (index2 + 1 <= Connect4FieldView.Field[0].length)) {
					FollowPattern(index + 1, index2 - 1, orientation, itr,
							currentChar);
					patternCount++;
				} else if (index == (Connect4FieldView.Field.length - 1)) {
					patternCount++;
				}
			} else if (orientation[itr] == 'h') {
				if ((index < Connect4FieldView.Field.length)
						&& (index2 < Connect4FieldView.Field[0].length)) {
					FollowPattern(index, index2 + 1, orientation, itr,
							currentChar);
					patternCount++;
				}
			} else if (orientation[itr] == 'v') {
				if ((index + 1 < Connect4FieldView.Field.length)
						&& (index2 + 1 < Connect4FieldView.Field[0].length)) {
					FollowPattern(index + 1, index2, orientation, itr,
							currentChar);
					patternCount++;
				} else if (index == (Connect4FieldView.Field.length - 1)) {
					patternCount++;
				}
			}
		}
	}

	/**
	 * The method should detect the orientations for every gamepiece entered on
	 * the board and if a pattern of four is detected in any of the orientations
	 * for a particular gamepiece, it should return a boolean value of true.
	 *
	 * @param Determining
	 *            whether the game at any given point of time is won or not
	 * 
	 */
	@Override
	public boolean didLastMoveWin() {
		// TODO Auto-generated method stub
		int flag = 0, orientIndex, itr;
		char[] orient = new char[10];
		here: for (int index = 0; index < Connect4FieldView.Field.length; index++) {
			for (int index2 = 0; index2 < Connect4FieldView.Field[0].length; index2++) {
				itr = 0;
				orientIndex = 0;
				if ((((index + 1) < Connect4FieldView.Field.length))
						&& ((Connect4FieldView.Field[index][index2] == '+') || (Connect4FieldView.Field[index][index2] == '*'))) {
					char currentCharacter = Connect4FieldView.Field[index][index2];
					if (index2 < (Connect4FieldView.Field[0].length - 1)) {
						if (Connect4FieldView.Field[index][index2 + 1] == Connect4FieldView.Field[index][index2]) {
							orient[orientIndex++] = 'h';
						}
						if (Connect4FieldView.Field[index + 1][index2 + 1] == Connect4FieldView.Field[index][index2]) {
							orient[orientIndex++] = 'd';
						}
					}
					if (Connect4FieldView.Field[index + 1][index2] == Connect4FieldView.Field[index][index2]) {
						orient[orientIndex++] = 'v';
					}
					if (index2 > 0) {
						if (Connect4FieldView.Field[index + 1][index2 - 1] == Connect4FieldView.Field[index][index2]) {
							orient[orientIndex++] = 'l';
						}
					}
					if (orientIndex > 0) {

						while (itr < orientIndex) {
							patternCount = 1;
							FollowPattern(index, index2, orient, itr,
									currentCharacter);
							if (patternCount == 5) {
								flag = 1;
								break here;
							} else {
								itr++;
							}
						}
					}
				} else if (index == (Connect4FieldView.Field.length - 1)
						&& ((Connect4FieldView.Field[index][index2] == '+') || (Connect4FieldView.Field[index][index2] == '*'))) {
					char currentCharacter = Connect4FieldView.Field[index][index2];
					if (Connect4FieldView.Field[index][index2 + 1] == Connect4FieldView.Field[index][index2]) {
						orient[orientIndex++] = 'h';
					}

					if (orientIndex > 0) {
						while (itr < orientIndex) {
							patternCount = 1;
							FollowPattern(index, index2, orient, itr,
									currentCharacter);
							if (patternCount == 5) {
								flag = 1;
								break here;
							} else {
								itr++;
							}
						}
					}
				}
			}
		}
		if (flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * The method should return a boolean value signifying whether the game has
	 * reached its ends in a draw or not.
	 *
	 * @param Checks
	 *            whether there are any more places where the players could drop
	 *            their gamepieces or not
	 * 
	 * @return Returns a true or false to signify a draw
	 */
	@Override
	public boolean isItaDraw() {
		// TODO Auto-generated method stub
		int flg = 0;
		here: for (int index = 0; index < Connect4FieldView.Field.length; index++) {
			for (int index2 = 0; index2 < Connect4FieldView.Field.length; index2++) {
				if (Connect4FieldView.Field[index][index2] == '.') {
					flg = 1;
					break here;
				}
			}
		}
		if (flg == 1) {
			return false;
		} else {
			return true;
		}
	}

	public String dropTest(int column) {
		if (checkIfPiecedCanBeDroppedIn(column)) {
			return "True";
		} else {
			return "False";
		}
	}

	@Override
	public void init(PlayerInterface playerA, PlayerInterface playerB) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playTheGame() {
		// TODO Auto-generated method stub

	}
}
