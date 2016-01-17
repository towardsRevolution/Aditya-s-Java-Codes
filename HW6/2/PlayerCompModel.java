/**
 * PlayerCompModel.java
 * @version $ID: PlayerCompModel.java, v 1.8 09/2/2015 7:02pm 
 * 
 * Revision: 6.23 09/5/2015 11:43am
 *
 */
import java.util.Random;

/**
 * The Class represents the model for the Connect4Field game design(Player vs
 * Computer)
 * 
 * @author Aditya Pulekar
 *
 */

public class PlayerCompModel implements Connect4FieldInterface {
	static int patternCount;
	static int varyingrecentRow, varyingrecentColumn, humanRecentRow,
			humanRecentColumn;

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
			for (int index = (PlayerCompView.Field.length - 1); index >= 0; index--) {
				if (PlayerCompView.Field[index][column] == '.') {
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
		PlayerCompView Display = new PlayerCompView();
		int flg = 0, index = 0;
		if (column >= 0) {
			for (index = (PlayerCompView.Field.length - 1); index >= 0; index--) {
				if (PlayerCompView.Field[index][column] == '.') {
					flg = 1;
					break;
				}
			}
		} else {
			// Syste("You entered the wrong column!");
			Display.DisplayModelMsg = "You entered the wrong column!";
			flg = -1;
		}
		if (flg == 1) {
			PlayerCompView.Field[index][column] = gamePiece;
			// return null;
		} else if (flg == 0) {
			// return("The Board is full");
			Display.DisplayModelMsg = "The Board is full!";
		}
	}

	/**
	 * The method should return the row of the most recent human input
	 *
	 * @param aConnect4Field
	 *            , humanColumn Analyzes the game board for the location of the
	 *            most recent human input
	 * 
	 * @return Returns the row of the most recent input by the user
	 */
	public int locationOfHumanInput(int humanColumn) {
		int index = 0;
		while (PlayerCompView.Field[index][humanColumn] != '+') {
			index++;
			if (index == 9) {
				break;
			}
		}
		return index;
	}

	/**
	 * Given the indices of some of the locations on the board, orientation and
	 * gamepiece, it should calculate the pattern count for the gamepiece
	 * through recursion.
	 *
	 * @param aConnect4Field
	 *            ,index,index2,orientation[],itr,currentChar Maintains a
	 *            pattern count for any given gamepiece
	 * 
	 */
	public void FollowPatternForComp(PlayerCompModel PModel, int index,
			int index2, char[] orientation, int itr, char currentChar) {
		if (PlayerCompView.Field[index][index2] == currentChar) {
			if (orientation[itr] == 'd') {
				if ((index + 1 < PlayerCompView.Field.length)
						&& (index2 + 1 < PlayerCompView.Field[0].length)) {
					FollowPatternForComp(PModel, index + 1, index2 + 1,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == (PlayerCompView.Field.length - 1)) {
					patternCount++;
				}
			} else if (orientation[itr] == 'l') {
				if ((index + 1 < PlayerCompView.Field.length) && (index2 > 0)) {
					FollowPatternForComp(PModel, index + 1, index2 - 1,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == (PlayerCompView.Field.length - 1)) {
					patternCount++;
				}
			} else if (orientation[itr] == 'h') {

				if ((index < PlayerCompView.Field.length)
						&& (index2 < PlayerCompView.Field[0].length)) {

					FollowPatternForComp(PModel, index, index2 + 1,
							orientation, itr, currentChar);
					patternCount++;
				}
			} else if (orientation[itr] == 'v') {
				if ((index + 1 < PlayerCompView.Field.length)
						&& (index2 + 1 < PlayerCompView.Field[0].length)) {
					FollowPatternForComp(PModel, index + 1, index2,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == (PlayerCompView.Field.length - 1)) {
					patternCount++;
				}
			} else if (orientation[itr] == 'u') {
				if ((index > 0)
						&& (index2 + 1 < PlayerCompView.Field[0].length)) {
					FollowPatternForComp(PModel, index - 1, index2,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == 0) {
					patternCount++;
				}
			} else if (orientation[itr] == 'p') {

				if ((index < PlayerCompView.Field.length) && (index2 > 0)) {

					FollowPatternForComp(PModel, index, index2 - 1,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index2 == 0) {
					patternCount++;
				}
			} else if (orientation[itr] == 'q') {
				if ((index + 1 < PlayerCompView.Field.length)
						&& (index2 + 1 <= PlayerCompView.Field[0].length)) {
					FollowPatternForComp(PModel, index - 1, index2 + 1,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == 0) {
					patternCount++;
				}
			} else if (orientation[itr] == 'b') {
				if ((index > 0) && (index2 > 0)) {
					FollowPatternForComp(PModel, index - 1, index2 - 1,
							orientation, itr, currentChar);
					patternCount++;
				} else if (index == 0) {
					patternCount++;
				}
			}
		}
	}

	/**
	 * The method should check the orientation of the inputs made by the human,
	 * store it and return it in a character array
	 *
	 * @param aConnect4Field
	 *            , humanColumn Analyzes the game board for the location of the
	 *            most recent human input
	 * 
	 * @return Returns the character array containing the possible orientations
	 */
	public char[] orientationCheck(PlayerCompModel PModel, int humanColumn) {
		int itr, orientIndex;
		char[] orient = new char[10];
		itr = 0;
		orientIndex = 0;

		// Location of human input

		int humanRow = locationOfHumanInput(humanColumn);
		humanRecentRow = humanRow;
		humanRecentColumn = humanColumn;
		char currentCharacter = PlayerCompView.Field[humanRecentRow][humanRecentColumn];
		if ((((humanRecentRow + 1) < PlayerCompView.Field.length))
				&& ((PlayerCompView.Field[humanRecentRow][humanRecentColumn] == '+'))) {
			if (humanRecentColumn < (PlayerCompView.Field[0].length - 1)) {
				if (PlayerCompView.Field[humanRecentRow][humanRecentColumn + 1] == PlayerCompView.Field[humanRecentRow][humanRecentColumn]) {
					orient[orientIndex++] = 'h';
				}
				if (PlayerCompView.Field[humanRecentRow + 1][humanRecentColumn + 1] == PlayerCompView.Field[humanRecentRow][humanRecentColumn]) {
					orient[orientIndex++] = 'd';
				}
			}
			if (PlayerCompView.Field[humanRecentRow + 1][humanRecentColumn] == PlayerCompView.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'v';
			}
		}
		if (humanRecentColumn > 0) {
			if (PlayerCompView.Field[humanRecentRow][humanRecentColumn - 1] == PlayerCompView.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'p';
			}
		}
		if (humanRecentRow > 0 && humanRecentColumn > 0
				&& humanRecentColumn < (PlayerCompView.Field[0].length - 1)) {
			if (PlayerCompView.Field[humanRecentRow - 1][humanRecentColumn] == PlayerCompView.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'u';
			}
			if (PlayerCompView.Field[humanRecentRow - 1][humanRecentColumn - 1] == PlayerCompView.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'b';
			}
			if (PlayerCompView.Field[humanRecentRow - 1][humanRecentColumn + 1] == PlayerCompView.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'q';
			}
		}

		if (humanRecentColumn > 0
				&& humanRecentRow + 1 < PlayerCompView.Field.length) {
			if (PlayerCompView.Field[humanRecentRow + 1][humanRecentColumn - 1] == PlayerCompView.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'l';
			}
		}
		if (orientIndex > 0) {
			while (itr < orientIndex) {
				patternCount = 0;
				FollowPatternForComp(PModel, humanRecentRow, humanRecentColumn,
						orient, itr, currentCharacter);
				if (patternCount == 2 || patternCount == 3) {
					varyingrecentRow = humanRecentRow;
					varyingrecentColumn = humanRecentColumn;
					break;
				} else {
					itr++;
				}
			}
		} else if (humanRecentRow == (PlayerCompView.Field.length - 1)
				&& humanRecentColumn + 1 < PlayerCompView.Field[0].length
				&& PlayerCompView.Field[humanRecentRow][humanRecentColumn] == '+') {
			if (PlayerCompView.Field[humanRecentRow][humanRecentColumn + 1] == PlayerCompView.Field[humanRecentRow][humanRecentColumn]) {
				orient[orientIndex++] = 'h';
			}
			if (orientIndex > 0) {
				while (itr < orientIndex) {
					patternCount = 0;
					FollowPatternForComp(PModel, humanRecentRow,
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

	/**
	 * The method should analyze the board and decide whether to obstruct the
	 * human from forming a pattern, form its own (computer's) pattern or place
	 * it's gamepiece at random.
	 *
	 * @param aConnect4Field
	 *            , toCheckIfItIsComps1stTurn, column Prints the '*' on the
	 *            board by analyzing human's moves
	 * 
	 */
	public void computerBrain(PlayerCompModel PModel,
			int toCheckIfItIsComps1stTurn, int column) {
		int itr = 0, orientIndex, flag = 0, indicator, randomColumn;
		Random r = new Random();

		// First Attempt

		if (toCheckIfItIsComps1stTurn == 1) {
			indicator = 0;
			while (indicator == 0) {
				randomColumn = r.nextInt(PlayerCompView.Field[0].length);
				if (PModel.checkIfPiecedCanBeDroppedIn(randomColumn)) {
					indicator = 1;
					PModel.dropPieces(randomColumn, '*');
				}
			}
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

			char[] orient = orientationCheck(PModel, column);
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
					if (varyingrecentColumn < (PlayerCompView.Field[0].length)
							&& varyingrecentRow < (PlayerCompView.Field.length)) {
						if (orient[itr] == 'h') {
							/*
							 * Adding a '*' as per following the orientation in
							 * case there is a '.'
							 */
							if (varyingrecentColumn + 2 < PlayerCompView.Field[0].length) {
								if (PlayerCompView.Field[varyingrecentRow][varyingrecentColumn + 2] == '.') {
									PModel.dropPieces(varyingrecentColumn + 2,
											'*');
									flag = 1;
								} else {

									// Adding a '*' on the opposite side of the
									// orientation

									if ((varyingrecentColumn - 1) >= 0) {
										if (PlayerCompView.Field[varyingrecentRow][varyingrecentColumn - 1] == '.') {
											PModel.dropPieces(
													varyingrecentColumn - 1,
													'*');
											flag = 1;
										}
									}
								}

							}
						}
						if (orient[itr] == 'd') {
							if ((varyingrecentColumn + 2 < PlayerCompView.Field[0].length && (varyingrecentRow + 2 < PlayerCompView.Field.length))) {
								if (PlayerCompView.Field[varyingrecentRow + 2][varyingrecentColumn + 2] == '.') {
									PModel.dropPieces(varyingrecentColumn + 2,
											'*');
									flag = 1;
								} else {
									if ((varyingrecentRow - 1) >= 0
											&& (varyingrecentColumn - 1) >= 0) {
										if (PlayerCompView.Field[varyingrecentRow - 1][varyingrecentColumn - 1] == '.') {
											PModel.dropPieces(
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
							&& varyingrecentColumn < (PlayerCompView.Field[0].length - 1)) {
						if ((varyingrecentRow - 2) >= 0) {
							if (orient[itr] == 'u') {
								if (PlayerCompView.Field[varyingrecentRow - 2][varyingrecentColumn] == '.') {
									PModel.dropPieces(varyingrecentColumn, '*');
									flag = 1;
								} else {
									if ((varyingrecentRow + 1) < (PlayerCompView.Field.length - 1)) {
										if (PlayerCompView.Field[varyingrecentRow + 1][varyingrecentColumn] == '.') {
											PModel.dropPieces(
													varyingrecentColumn, '*');
											flag = 1;
										}
									}
								}

							}
							if ((varyingrecentColumn - 2) >= 0) {
								if (orient[itr] == 'b') {
									if (PlayerCompView.Field[varyingrecentRow - 2][varyingrecentColumn - 2] == '.') {
										PModel.dropPieces(
												varyingrecentColumn - 2, '*');
										flag = 1;
									} else {
										if (varyingrecentRow < PlayerCompView.Field.length - 1) {
											if (PlayerCompView.Field[varyingrecentRow + 1][varyingrecentColumn + 1] == '.') {
												PModel.dropPieces(
														varyingrecentColumn + 1,
														'*');
												flag = 1;
											}
										}
									}
								}
							}
							if (varyingrecentColumn + 2 < PlayerCompView.Field[0].length) {
								if ((varyingrecentColumn + 2) < PlayerCompView.Field[0].length) {
									if (orient[itr] == 'q') {
										if (PlayerCompView.Field[varyingrecentRow - 2][varyingrecentColumn + 2] == '.') {
											PModel.dropPieces(
													varyingrecentColumn + 2,
													'*');
											flag = 1;
										}
									}
								} else {
									if (varyingrecentRow < PlayerCompView.Field.length - 1) {
										if (PlayerCompView.Field[varyingrecentRow + 1][varyingrecentColumn - 1] == '.') {
											PModel.dropPieces(
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
								if (PlayerCompView.Field[varyingrecentRow][varyingrecentColumn - 2] == '.') {
									PModel.dropPieces(varyingrecentColumn - 2,
											'*');
									flag = 1;
								} else {
									if (varyingrecentColumn < PlayerCompView.Field[0].length - 1) {
										if (PlayerCompView.Field[varyingrecentRow][varyingrecentColumn + 1] == '.') {
											PModel.dropPieces(
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
						if (varyingrecentRow + 2 < PlayerCompView.Field.length) {
							if (PlayerCompView.Field[varyingrecentRow + 2][varyingrecentColumn] == '.') {
								PModel.dropPieces(varyingrecentColumn, '*');
								flag = 1;
							} else {
								if ((varyingrecentRow - 1) >= 0) {
									if (PlayerCompView.Field[varyingrecentRow - 1][varyingrecentColumn] == '.') {
										PModel.dropPieces(varyingrecentColumn,
												'*');
										flag = 1;
									}
								}
							}

						}
					}
					if (varyingrecentColumn > 0) {
						if (orient[itr] == 'l') {
							if (varyingrecentRow + 2 < PlayerCompView.Field.length) {
								if (PlayerCompView.Field[varyingrecentRow + 2][varyingrecentColumn - 2] == '.') {
									PModel.dropPieces(varyingrecentColumn - 2,
											'*');
									flag = 1;
								} else {
									if ((varyingrecentRow - 1) >= 0) {
										if (PlayerCompView.Field[varyingrecentRow - 1][varyingrecentColumn + 1] == '.') {
											PModel.dropPieces(
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
			}
			if (patternForComputer(PModel) && flag == 0) {
				return;
			} else if ((!patternForComputer(PModel)) && flag == 0) {
				indicator = 0;
				while (indicator == 0) {
					randomColumn = r.nextInt(PlayerCompView.Field[0].length);
					if (PModel.checkIfPiecedCanBeDroppedIn(randomColumn)) {
						indicator = 1;
						PModel.dropPieces(randomColumn, '*');
					}
				}
			}
		}
	}

	/**
	 * Given the location on the board and the instance aConnect4Field, it
	 * should calculate whether there are any patterns on the board with three
	 * '*' and one '.'. If so, then it should drop a '*' in the column of the
	 * '.'
	 *
	 * @param aConnect4Field
	 *            , row, column Checks for a pattern with three '*' and a '.'
	 * 
	 * @return Returns whether such a pattern exists using a boolean value
	 * 
	 */
	public boolean toCheckForStarPattern(PlayerCompModel PModel, int row,
			int column) {

		/*
		 * Here we check whether there are 3 stars in a quad of any of the 4
		 * orientations
		 */

		int itr = 0, flag = 0;
		int noOfStars = 0, dotColumn = 0;
		int index = row;
		int index2 = column;
		while (itr < 4) {

			// For vertical orientation

			if (index < PlayerCompView.Field.length
					&& index2 < PlayerCompView.Field[0].length) {
				if (PlayerCompView.Field[index][index2] == '*') {
					if (index + 1 < PlayerCompView.Field.length) {
						noOfStars++;
						index = index + 1;
					} else if (index == (PlayerCompView.Field.length - 1)) {
						noOfStars++;
					}
				} else if (PlayerCompView.Field[index][index2] == '.'
						&& index < PlayerCompView.Field.length) {
					// dotRow = index;
					dotColumn = index2;
					flag = 1;
				}
				if (flag == 1 && noOfStars == 3) {
					// aConnect4Field.Field[dotRow][dotColumn] = '*';
					PModel.dropPieces(dotColumn, '*');
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

			if (index < PlayerCompView.Field.length
					&& index2 < PlayerCompView.Field[0].length) {
				if (PlayerCompView.Field[index][index2] == '*') {
					if (index + 1 < PlayerCompView.Field.length) {
						noOfStars++;
						index2 = index2 + 1;
					} else if (index == (PlayerCompView.Field.length - 1)) {
						noOfStars++;
					}
				} else if (PlayerCompView.Field[index][index2] == '.'
						&& index < PlayerCompView.Field.length) {
					// dotRow = index;
					dotColumn = index2;
					flag = 1;
				}
				if (flag == 1 && noOfStars == 3) {
					PModel.dropPieces(dotColumn, '*');
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

			if (index < PlayerCompView.Field.length
					&& index2 < PlayerCompView.Field[0].length) {
				if (PlayerCompView.Field[index][index2] == '*') {
					if (index + 1 < PlayerCompView.Field.length) {
						noOfStars++;
						index = index + 1;
						index2 = index2 + 1;
					} else if (index == (PlayerCompView.Field.length - 1)) {
						noOfStars++;
					}
				} else if (PlayerCompView.Field[index][index2] == '.'
						&& index < PlayerCompView.Field.length) {
					// dotRow = index;
					dotColumn = index2;
					flag = 1;
				}
				if (flag == 1 && noOfStars == 3) {
					PModel.dropPieces(dotColumn, '*');
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

			if (index < PlayerCompView.Field.length
					&& index2 < PlayerCompView.Field[0].length) {
				if (PlayerCompView.Field[index][index2] == '*') {
					if (index + 1 < PlayerCompView.Field.length) {
						noOfStars++;
						index = index + 1;
						index2 = index2 - 1;
					} else if (index == (PlayerCompView.Field.length - 1)) {
						noOfStars++;
					}
				} else if (PlayerCompView.Field[index][index2] == '.'
						&& index < PlayerCompView.Field.length) {
					// dotRow = index;
					dotColumn = index2;
					flag = 1;
				}
				if (flag == 1 && noOfStars == 3) {
					PModel.dropPieces(dotColumn, '*');
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
	 * Given the instance aConnect4Field, it should calculate whether there are
	 * any patterns on the board with three '*' and one '.'. If so, then it
	 * should drop a '*' in the column of the '.'
	 *
	 * @param aConnect4Field
	 *            , row, column Checks for a pattern with three '*' and a '.' by
	 *            traversing the entire board
	 * 
	 * @return Returns whether such a pattern exists using a boolean value
	 * 
	 */
	public boolean patternForComputer(PlayerCompModel PModel) {
		boolean y = false;
		out: for (int index = 0; index < PlayerCompView.Field.length; index++) {
			for (int index2 = 0; index2 < PlayerCompView.Field[0].length; index2++) {
				if (PlayerCompView.Field[index][index2] == '*') {
					y = toCheckForStarPattern(PModel, index, index2);
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
		if (PlayerCompView.Field[index][index2] == currentChar) {
			if (orientation[itr] == 'd') {
				if ((index + 1 < PlayerCompView.Field.length)
						&& (index2 + 1 < PlayerCompView.Field[0].length)) {
					FollowPattern(index + 1, index2 + 1, orientation, itr,
							currentChar);
					patternCount++;
				} else if (index == (PlayerCompView.Field.length - 1)) {
					patternCount++;
				}
			} else if (orientation[itr] == 'l') {
				if ((index + 1 < PlayerCompView.Field.length)
						&& (index2 + 1 <= PlayerCompView.Field[0].length)) {
					FollowPattern(index + 1, index2 - 1, orientation, itr,
							currentChar);
					patternCount++;
				} else if (index == (PlayerCompView.Field.length - 1)) {
					patternCount++;
				}
			} else if (orientation[itr] == 'h') {
				if ((index < PlayerCompView.Field.length)
						&& (index2 < PlayerCompView.Field[0].length)) {
					FollowPattern(index, index2 + 1, orientation, itr,
							currentChar);
					patternCount++;
				}
			} else if (orientation[itr] == 'v') {
				if ((index + 1 < PlayerCompView.Field.length)
						&& (index2 + 1 < PlayerCompView.Field[0].length)) {
					FollowPattern(index + 1, index2, orientation, itr,
							currentChar);
					patternCount++;
				} else if (index == (PlayerCompView.Field.length - 1)) {
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
		here: for (int index = 0; index < PlayerCompView.Field.length; index++) {
			for (int index2 = 0; index2 < PlayerCompView.Field[0].length; index2++) {
				itr = 0;
				orientIndex = 0;
				if ((((index + 1) < PlayerCompView.Field.length))
						&& ((PlayerCompView.Field[index][index2] == '+') || (PlayerCompView.Field[index][index2] == '*'))) {
					char currentCharacter = PlayerCompView.Field[index][index2];
					if (index2 < (PlayerCompView.Field[0].length - 1)) {
						if (PlayerCompView.Field[index][index2 + 1] == PlayerCompView.Field[index][index2]) {
							orient[orientIndex++] = 'h';
						}
						if (PlayerCompView.Field[index + 1][index2 + 1] == PlayerCompView.Field[index][index2]) {
							orient[orientIndex++] = 'd';
						}
					}
					if (PlayerCompView.Field[index + 1][index2] == PlayerCompView.Field[index][index2]) {
						orient[orientIndex++] = 'v';
					}
					if (index2 > 0) {
						if (PlayerCompView.Field[index + 1][index2 - 1] == PlayerCompView.Field[index][index2]) {
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
				} else if (index == (PlayerCompView.Field.length - 1)
						&& ((PlayerCompView.Field[index][index2] == '+') || (PlayerCompView.Field[index][index2] == '*'))) {
					char currentCharacter = PlayerCompView.Field[index][index2];
					if (PlayerCompView.Field[index][index2 + 1] == PlayerCompView.Field[index][index2]) {
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
		here: for (int index = 0; index < PlayerCompView.Field.length; index++) {
			for (int index2 = 0; index2 < PlayerCompView.Field.length; index2++) {
				if (PlayerCompView.Field[index][index2] == '.') {
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

	@Override
	public void init(PlayerInterface playerA, PlayerInterface playerB) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playTheGame() {
		// TODO Auto-generated method stub

	}
}
