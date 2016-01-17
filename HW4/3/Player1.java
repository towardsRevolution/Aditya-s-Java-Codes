/**
 * Player.java
 * @version $ID: Player.java, v 1.8 09/19/2015 8:13pm 
 * 
 * Revision: 4.31 09/21/2015 11:43am
 *
 */
import java.util.*;
/**
 * This program designs the game 'Connect4Field' and allows a human to play
 * against the computer the same computer screen.
 * 
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */
public class Player1 implements PlayerInterface {
	Connect4FieldInterface theField;
	String name;
	char gamepiece;
	
	//Alternates between the human and the computer
	
	int AlternateChances = 0;
	
	//Maintains the pattern count and also the locations of the most recent human inputs
	static int patternCount, varyingrecentRow, varyingrecentColumn,
			humanRecentRow, humanRecentColumn;
	
	/*
	 * Parameterized constructor of the class Player
	 */
	public Player1(Connect4FieldInterface theField, String name, char gamepiece) {
		this.theField = theField;
		this.name = name;
		this.gamepiece = gamepiece;
	}
	
	/*
	 * Default constructor of the class Player
	 */
	public Player1() {
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
	 * Given the indices of some of the locations on the board, orientation and gamepiece,
	 * it should calculate the pattern count for the gamepiece through recursion. 
	 *
	 * @param aConnect4Field,index,index2,orientation[],itr,currentChar
	 *            Maintains a pattern count for any given gamepiece
	 * 
	 */
	public void FollowPattern(Connect4Field aConnect4Field, int index,
			int index2, char[] orientation, int itr, char currentChar) {
		if (aConnect4Field.Field[index][index2] == currentChar) {
			if (orientation[itr] == 'd') {
				if ((index + 1 < aConnect4Field.Field.length)
						&& (index2 + 1 < aConnect4Field.Field[0].length)) {
					FollowPattern(aConnect4Field, index + 1, index2 + 1,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == (aConnect4Field.Field.length - 1)) {
					patternCount++;
				}
			} else if (orientation[itr] == 'l') {
				if ((index + 1 < aConnect4Field.Field.length) && (index2 > 0)) {
					FollowPattern(aConnect4Field, index + 1, index2 - 1,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == (aConnect4Field.Field.length - 1)) {
					patternCount++;
				}
			} else if (orientation[itr] == 'h') {

				if ((index < aConnect4Field.Field.length)
						&& (index2 < aConnect4Field.Field[0].length)) {

					FollowPattern(aConnect4Field, index, index2 + 1,
							orientation, itr, currentChar);
					patternCount++;
				}
			} else if (orientation[itr] == 'v') {
				if ((index + 1 < aConnect4Field.Field.length)
						&& (index2 + 1 < aConnect4Field.Field[0].length)) {
					FollowPattern(aConnect4Field, index + 1, index2,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == (aConnect4Field.Field.length - 1)) {
					patternCount++;
				}
			} else if (orientation[itr] == 'u') {
				if ((index > 0)
						&& (index2 + 1 < aConnect4Field.Field[0].length)) {
					FollowPattern(aConnect4Field, index - 1, index2,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == 0) {
					patternCount++;
				}
			} else if (orientation[itr] == 'p') {

				if ((index < aConnect4Field.Field.length) && (index2 > 0)) {

					FollowPattern(aConnect4Field, index, index2 - 1,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index2 == 0) {
					patternCount++;
				}
			} else if (orientation[itr] == 'q') {
				if ((index + 1 < aConnect4Field.Field.length)
						&& (index2 + 1 <= aConnect4Field.Field[0].length)) {
					FollowPattern(aConnect4Field, index - 1, index2 + 1,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == 0) {
					patternCount++;
				}
			} else if (orientation[itr] == 'b') {
				if ((index > 0) && (index2 > 0)) {
					FollowPattern(aConnect4Field, index - 1, index2 - 1,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == 0) {
					patternCount++;
				}
			}
		}
	}
	
	/**
	 * Given the location on the board and the instance aConnect4Field,
	 * it should calculate whether there are any patterns on the board with 
	 * three '*' and one '.'. If so, then it should drop a '*' in the column
	 * of the '.'
	 *
	 * @param aConnect4Field, row, column
	 *            Checks for a pattern with three '*' and a '.'
	 *            
	 * @return Returns whether such a pattern exists using a boolean value
	 * 
	 */
	public boolean toCheckForStarPattern(Connect4Field aConnect4Field, int row,
			int column) {

		/*
		 * Here we check whether there are 3 stars in a quad of any of the 4
		 * orientations
		 */

		int itr = 0, allOrientations = 0, flag = 0;
		int noOfStars = 0, dotRow = 0, dotColumn = 0;
		int index = row;
		int index2 = column;
		while (itr < 4) {

			// For vertical orientation

			if (index < aConnect4Field.Field.length
					&& index2 < aConnect4Field.Field[0].length) {
				if (aConnect4Field.Field[index][index2] == '*') {
					if (index + 1 < aConnect4Field.Field.length) {
						noOfStars++;
						index = index + 1;
					} else if (index == (aConnect4Field.Field.length - 1)) {
						noOfStars++;
					}
				} else if (aConnect4Field.Field[index][index2] == '.'
						&& index < aConnect4Field.Field.length) {
					dotRow = index;
					dotColumn = index2;
					flag = 1;
				}
				if (flag == 1 && noOfStars == 3) {
					// aConnect4Field.Field[dotRow][dotColumn] = '*';
					aConnect4Field.dropPieces(dotColumn, '*');
					System.out.println("Vertical...Pattern Constructed!");
					itr = 4;
				}
			}
			itr++;
		}
		if (flag == 0 && noOfStars <= 3) {
			itr = 0;
			noOfStars = 0;
			index = row;
			index2 = column;
		}
		while (itr < 4) {

			// For Horizontal orientation

			if (index < aConnect4Field.Field.length
					&& index2 < aConnect4Field.Field[0].length) {
				if (aConnect4Field.Field[index][index2] == '*') {
					if (index + 1 < aConnect4Field.Field.length) {
						noOfStars++;
						index2 = index2 + 1;
					} else if (index == (aConnect4Field.Field.length - 1)) {
						noOfStars++;
					}
				} else if (aConnect4Field.Field[index][index2] == '.'
						&& index < aConnect4Field.Field.length) {
					dotRow = index;
					dotColumn = index2;
					flag = 1;
				}
				if (flag == 1 && noOfStars == 3) {
					aConnect4Field.dropPieces(dotColumn, '*');
					System.out.println("Horizontal...Pattern Constructed!");
					itr = 4;
				}
			}
			itr++;
		}
		if (flag == 0 && noOfStars <= 3) {
			itr = 0;
			noOfStars = 0;
			index = row;
			index2 = column;
		}
		while (itr < 4) {

			// For Diagonal orientation

			if (index < aConnect4Field.Field.length
					&& index2 < aConnect4Field.Field[0].length) {
				if (aConnect4Field.Field[index][index2] == '*') {
					if (index + 1 < aConnect4Field.Field.length) {
						noOfStars++;
						index = index + 1;
						index2 = index2 + 1;
					} else if (index == (aConnect4Field.Field.length - 1)) {
						noOfStars++;
					}
				} else if (aConnect4Field.Field[index][index2] == '.'
						&& index < aConnect4Field.Field.length) {
					dotRow = index;
					dotColumn = index2;
					flag = 1;
				}
				if (flag == 1 && noOfStars == 3) {
					aConnect4Field.dropPieces(dotColumn, '*');
					System.out.println("Diagonal...Pattern Constructed!");
					itr = 4;
				}
			}
			itr++;
		}
		if (flag == 0 && noOfStars <= 3) {
			itr = 0;
			noOfStars = 0;
			index = row;
			index2 = column;
		}
		while (itr < 4) {

			// For Left Diagonal orientation

			if (index < aConnect4Field.Field.length
					&& index2 < aConnect4Field.Field[0].length) {
				if (aConnect4Field.Field[index][index2] == '*') {
					if (index + 1 < aConnect4Field.Field.length) {
						noOfStars++;
						index = index + 1;
						index2 = index2 - 1;
					} else if (index == (aConnect4Field.Field.length - 1)) {
						noOfStars++;
					}
				} else if (aConnect4Field.Field[index][index2] == '.'
						&& index < aConnect4Field.Field.length) {
					dotRow = index;
					dotColumn = index2;
					flag = 1;
				}
				if (flag == 1 && noOfStars == 3) {
					aConnect4Field.dropPieces(dotColumn, '*');
					System.out.println("Left Diagonal...Pattern Constructed!");
					itr = 4;
				}
			}
			itr++;
		}

		if (flag == 1 && noOfStars == 3) {

			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Given the instance aConnect4Field, it should calculate whether there 
	 * are any patterns on the board with three '*' and one '.'. If so, then 
	 * it should drop a '*' in the column of the '.'
	 *
	 * @param aConnect4Field, row, column
	 *            Checks for a pattern with three '*' and a '.' by traversing
	 *            the entire board
	 *            
	 * @return Returns whether such a pattern exists using a boolean value
	 * 
	 */
	public boolean patternForComputer(Connect4Field aConnect4Field) {
		boolean y = false;
		out: for (int index = 0; index < aConnect4Field.Field.length; index++) {
			for (int index2 = 0; index2 < aConnect4Field.Field[0].length; index2++) {
				if (aConnect4Field.Field[index][index2] == '*') {
					y = toCheckForStarPattern(aConnect4Field, index, index2);
					if (y == true) {
						break out;
					}
				}
			}
		}
		if (y == true) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * The method should return the row of the most recent human input
	 *
	 * @param aConnect4Field, humanColumn	Analyzes the game board for 
	 * 										the location of the most 
	 * 										recent human input
	 * 
	 * @return 	  Returns the row of the most recent input by the user
	 */
	public int locationOfHumanInput(Connect4Field aConnect4Field,
			int humanColumn) {
		int index = 0;
		while (aConnect4Field.Field[index][humanColumn] != '+') {
			index++;
		}
		return index;
	}
	
	/**
	 * The method should check the orientation of the inputs made by the human, store it 
	 * and return it in a character array
	 *
	 * @param aConnect4Field, humanColumn	Analyzes the game board for 
	 * 										the location of the most 
	 * 										recent human input
	 * 
	 * @return 	  Returns the character array containing the possible orientations
	 */
	public char[] orientationCheck(Connect4Field aConnect4Field, int humanColumn) {
		int itr, orientIndex;
		char[] orient = new char[10];
		itr = 0;
		orientIndex = 0;

		// Location of human input

		int humanRow = locationOfHumanInput(aConnect4Field, humanColumn);
		humanRecentRow = humanRow;
		humanRecentColumn = humanColumn;
		char currentCharacter = aConnect4Field.Field[humanRecentRow][humanRecentColumn];
		if ((((humanRecentRow + 1) < aConnect4Field.Field.length))
				&& ((aConnect4Field.Field[humanRecentRow][humanRecentColumn] == '+'))) {
			if (humanRecentColumn < (aConnect4Field.Field[0].length - 1)) {
				if (aConnect4Field.Field[humanRecentRow][humanRecentColumn + 1] == aConnect4Field.Field[humanRecentRow][humanRecentColumn]) {
					orient[orientIndex++] = 'h';
				}
				if (aConnect4Field.Field[humanRecentRow + 1][humanRecentColumn + 1] == aConnect4Field.Field[humanRecentRow][humanRecentColumn]) {
					orient[orientIndex++] = 'd';
				}
			}
			if (aConnect4Field.Field[humanRecentRow + 1][humanRecentColumn] == aConnect4Field.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'v';
			}
		}
		if (humanRecentColumn > 0) {
			if (aConnect4Field.Field[humanRecentRow][humanRecentColumn - 1] == aConnect4Field.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'p';
			}
		}
		if (humanRecentRow > 0 && humanRecentColumn > 0
				&& humanRecentColumn < (aConnect4Field.Field[0].length - 1)) {
			if (aConnect4Field.Field[humanRecentRow - 1][humanRecentColumn] == aConnect4Field.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'u';
			}
			if (aConnect4Field.Field[humanRecentRow - 1][humanRecentColumn - 1] == aConnect4Field.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'b';
			}
			if (aConnect4Field.Field[humanRecentRow - 1][humanRecentColumn + 1] == aConnect4Field.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'q';
			}
		}

		if (humanRecentColumn > 0
				&& humanRecentRow + 1 < aConnect4Field.Field.length) {
			if (aConnect4Field.Field[humanRecentRow + 1][humanRecentColumn - 1] == aConnect4Field.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'l';
			}
		}
		if (orientIndex > 0) {
			while (itr < orientIndex) {
				patternCount = 0;
				FollowPattern(aConnect4Field, humanRecentRow,
						humanRecentColumn, orient, itr, currentCharacter);
				if (patternCount == 2 || patternCount == 3) {
					varyingrecentRow = humanRecentRow;
					varyingrecentColumn = humanRecentColumn;
					break;
				} else {
					itr++;
				}
			}
		} else if (humanRecentRow == (aConnect4Field.Field.length - 1)
				&& humanRecentColumn + 1 < aConnect4Field.Field[0].length
				&& aConnect4Field.Field[humanRecentRow][humanRecentColumn] == '+') {
			if (aConnect4Field.Field[humanRecentRow][humanRecentColumn + 1] == aConnect4Field.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'h';
			}
			if (orientIndex > 0) {
				while (itr < orientIndex) {
					patternCount = 0;
					FollowPattern(aConnect4Field, humanRecentRow,
							humanRecentColumn, orient, itr, currentCharacter);
					if (patternCount == 2 || patternCount == 3) {

						// Below statement to help put a '*' in place of a '.'

						varyingrecentRow = humanRecentRow;
						varyingrecentColumn = humanRecentColumn;
						break;
					} else {
						itr++;
					}
				}
			}
		}
		return orient;
	}

	// We can't initialize aConnect4Field with Connect4FieldInterface.
	
	/**
	 * The method should analyze the board and decide whether to obstruct the human from 
	 * forming a pattern, form its own (computer's) pattern or place it's gamepiece	at 
	 * random. 
	 *
	 * @param aConnect4Field, toCheckIfItIsComps1stTurn, column
	 * 										Prints the '*' on the board by analyzing human's moves
	 * 
	 */
	public void computerBrain(Connect4Field aConnect4Field,
			int toCheckIfItIsComps1stTurn, int column) {
		int itr = 0, orientIndex, flag = 0, indicator, randomColumn;
		Random r = new Random();

		// First Attempt

		if (toCheckIfItIsComps1stTurn == 1) {
			indicator = 0;
			while (indicator == 0) {
				randomColumn = r.nextInt(aConnect4Field.Field[0].length);
				if (aConnect4Field.checkIfPiecedCanBeDroppedIn(randomColumn)) {
					indicator = 1;
					aConnect4Field.dropPieces(randomColumn, '*');
				}
			}
			System.out.println(aConnect4Field);
		} else {
			/*
			 * Defense Strategy by Computer (It has, now, been perfected...No
			 * more edits required!)
			 */
			orientIndex = 0;
			int i = 0;

			/*
			 * Checking whether the human has got more than two '+' in any of
			 * the orientations
			 */

			char[] orient = orientationCheck(aConnect4Field, column);
			while (orient[i] != '\u0000') {
				orientIndex++;
				i++;
			}

			/*
			 * 'orientIndex>0' is interpreted as two '+' identified in an
			 * orientation on the board
			 */

			if (orientIndex > 0) {
				/*
				 * ****The best way to tackle the problem of recentRow and
				 * recentColumn getting the values of a particular iteration on
				 * every iteration is to keep track of the location of input by
				 * the user, each time and treat that as the recentRow and
				 * recent Column. So here is the hint: take two instance
				 * variables which will store the input by the user everytime
				 * and check in the vicinity of that location whether its
				 * probability of forming a quad has been combated.
				 */

				while (itr < orientIndex) {
					if (varyingrecentColumn < (aConnect4Field.Field[0].length)
							&& varyingrecentRow < (aConnect4Field.Field.length)) {
						if (orient[itr] == 'h') {
							/*
							 * Adding a '*' as per following the orientation in
							 * case there is a '.'
							 */
							if (varyingrecentColumn + 2 < aConnect4Field.Field[0].length) {
								if (aConnect4Field.Field[varyingrecentRow][varyingrecentColumn + 2] == '.') {
									aConnect4Field.dropPieces(
											varyingrecentColumn + 2, '*');
									flag = 1;
								} else {

									// Adding a '*' on the opposite side of the
									// orientation

									if ((varyingrecentColumn - 1) >= 0) {
										if (aConnect4Field.Field[varyingrecentRow][varyingrecentColumn - 1] == '.') {
											aConnect4Field.dropPieces(
													varyingrecentColumn - 1,
													'*');
											flag = 1;
										}
									}
								}

							}
						}
						if (orient[itr] == 'd') {
							if ((varyingrecentColumn + 2 < aConnect4Field.Field[0].length && (varyingrecentRow + 2 < aConnect4Field.Field.length))) {
								if (aConnect4Field.Field[varyingrecentRow + 2][varyingrecentColumn + 2] == '.') {
									aConnect4Field.dropPieces(
											varyingrecentColumn + 2, '*');
									flag = 1;
								} else {
									if ((varyingrecentRow - 1) >= 0
											&& (varyingrecentColumn - 1) >= 0) {
										if (aConnect4Field.Field[varyingrecentRow - 1][varyingrecentColumn - 1] == '.') {
											aConnect4Field.dropPieces(
													varyingrecentColumn - 1,
													'*');
											flag = 1;
										}
									}
								}

							}
						}

					}
					if (varyingrecentRow >= 0
							&& varyingrecentColumn > 0
							&& varyingrecentColumn < (aConnect4Field.Field[0].length - 1)) {
						if ((varyingrecentRow - 2) >= 0) {
							if (orient[itr] == 'u') {
								if (aConnect4Field.Field[varyingrecentRow - 2][varyingrecentColumn] == '.') {
									aConnect4Field.dropPieces(
											varyingrecentColumn, '*');
									flag = 1;
								} else {
									if ((varyingrecentRow + 1) < (aConnect4Field.Field[0].length - 1)) {
										if (aConnect4Field.Field[varyingrecentRow + 1][varyingrecentColumn] == '.') {
											aConnect4Field.dropPieces(
													varyingrecentColumn, '*');
											flag = 1;
										}
									}
								}

							}
							if ((varyingrecentColumn - 2) >= 0) {
								if (orient[itr] == 'b') {
									if (aConnect4Field.Field[varyingrecentRow - 2][varyingrecentColumn - 2] == '.') {
										aConnect4Field.dropPieces(
												varyingrecentColumn - 2, '*');
										flag = 1;
									} else {
										if (varyingrecentRow < aConnect4Field.Field[0].length - 1) {
											if (aConnect4Field.Field[varyingrecentRow + 1][varyingrecentColumn + 1] == '.') {
												aConnect4Field
														.dropPieces(
																varyingrecentColumn + 1,
																'*');
												flag = 1;
											}
										}
									}
								}
							}
							if (varyingrecentColumn + 2 < aConnect4Field.Field[0].length) {
								if ((varyingrecentColumn + 2) < aConnect4Field.Field[0].length) {
									if (orient[itr] == 'q') {
										if (aConnect4Field.Field[varyingrecentRow - 2][varyingrecentColumn + 2] == '.') {
											aConnect4Field.dropPieces(
													varyingrecentColumn + 2,
													'*');
											flag = 1;
										}
									}
								} else {
									if (varyingrecentRow < aConnect4Field.Field.length - 1) {
										if (aConnect4Field.Field[varyingrecentRow + 1][varyingrecentColumn - 1] == '.') {
											aConnect4Field.dropPieces(
													varyingrecentColumn - 1,
													'*');
											flag = 1;
										}
									}
								}
							}
						}
						if ((varyingrecentColumn - 2) >= 0) {
							if (orient[itr] == 'p') {
								if (aConnect4Field.Field[varyingrecentRow][varyingrecentColumn - 2] == '.') {
									aConnect4Field.dropPieces(
											varyingrecentColumn - 2, '*');
									flag = 1;
								} else {
									if (varyingrecentColumn < aConnect4Field.Field[0].length - 1) {
										if (aConnect4Field.Field[varyingrecentRow][varyingrecentColumn + 1] == '.') {
											aConnect4Field.dropPieces(
													varyingrecentColumn + 1,
													'*');
											flag = 1;
										}
									}
								}
							}
						}
					}
					if (orient[itr] == 'v') {
						if (varyingrecentRow + 2 < aConnect4Field.Field.length) {
							if (aConnect4Field.Field[varyingrecentRow + 2][varyingrecentColumn] == '.') {
								aConnect4Field.dropPieces(varyingrecentColumn,
										'*');
								flag = 1;
							} else {
								if ((varyingrecentRow - 1) >= 0) {
									if (aConnect4Field.Field[varyingrecentRow - 1][varyingrecentColumn] == '.') {
										aConnect4Field.dropPieces(
												varyingrecentColumn, '*');
										flag = 1;
									}
								}
							}

						}
					}
					if (varyingrecentColumn > 0) {
						if (orient[itr] == 'l') {
							if (varyingrecentRow + 2 < aConnect4Field.Field.length) {
								if (aConnect4Field.Field[varyingrecentRow + 2][varyingrecentColumn - 2] == '.') {
									aConnect4Field.dropPieces(
											varyingrecentColumn - 2, '*');
									flag = 1;
								} else {
									if ((varyingrecentRow - 1) >= 0) {
										if (aConnect4Field.Field[varyingrecentRow - 1][varyingrecentColumn + 1] == '.') {
											aConnect4Field.dropPieces(
													varyingrecentColumn + 1,
													'*');
											flag = 1;
										}
									}
								}

							}
						}
					}
					itr++;
					if (flag == 1) {
						break;
					}
				}
				System.out.println(aConnect4Field);
			}
			if (patternForComputer(aConnect4Field) && flag == 0) {
				System.out.println(aConnect4Field);
			} else if ((!patternForComputer(aConnect4Field)) && flag == 0) {
				indicator = 0;
				while (indicator == 0) {
					randomColumn = r.nextInt(aConnect4Field.Field[0].length);
					if (aConnect4Field
							.checkIfPiecedCanBeDroppedIn(randomColumn)) {
						indicator = 1;
						aConnect4Field.dropPieces(randomColumn, '*');
					}
				}
				System.out.println(aConnect4Field);
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
		int toCheckIfItIsComps1stTurn = 1, column = 0;
		Connect4Field aConnect4Field = new Connect4Field();
		Player1 keepCount = new Player1();
		Player1 aPlayer = new Player1(aConnect4Field, "A", '+');
		Player1 computer = new Player1(aConnect4Field, "Computer", '*');
		Scanner sc = new Scanner(System.in);
		System.out.println(aConnect4Field);
		while (!aConnect4Field.isItaDraw()) {
			if (keepCount.nextMove() % 2 != 0) {
				/*
				 * For odd value of 'AlternateChances', Player A gets to play
				 */
				System.out.println("Player A - Enter the column number : ");
				column = sc.nextInt();

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
					System.out.println("The Winner is: Human");
					break;
				}
			} else {

				/*
				 * For even value of 'AlternateChances', Player B gets to play
				 */

				System.out.println("Computer's turn ");
				computer.computerBrain(aConnect4Field,
						toCheckIfItIsComps1stTurn, column);
				toCheckIfItIsComps1stTurn = 0;
				System.out.println("Did the last move win: "
						+ aConnect4Field.didLastMoveWin());
				if (aConnect4Field.didLastMoveWin()) {
					System.out.println("The Winner is: Computer");
					break;
				}
			}
		}

	}
}
