/**
 * HangMan.java
 * @version $ID: HangMan.java, v 1.8 09/11/2015 7:02pm 
 * 
 * Revision: 3.41 09/13/2015 10:43am
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This program designs the game 'HangMan'. It allows any number of players to
 * play the game and allows every player 8 guesses to reach the correct word.
 * It, also, prints the top four performers in a round (if no. of players are
 * greater than four).
 * 
 * @author Aditya Pulekar
 *
 */
public class HangMan {

	/**
	 * Given the nth number of guess, print the nth section of the gallows and
	 * the hung man.
	 *
	 * @param nthGuess
	 *            Printing gallows and the hung man part by part on every wrong
	 *            guess
	 * 
	 */
	public void displayPartByPart(int nthGuess) {
		char[][] HungMan = {
				{ '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', ' ' },
				{ '#', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#' },
				{ '#', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#' },
				{ '#', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#' },
				{ '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ' },
				{ '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ' },
				{ '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', ' ' },
				{ '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#' },
				{ '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ' },
				{ '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ' },
				{ '#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#' } };
		if (nthGuess == 1) {
			for (int index2 = 0; index2 < HungMan.length; index2++) {
				for (int index = 1; index < HungMan.length; index++) {
					HungMan[index2][index] = ' ';
				}
			}
			for (int index = 0; index < HungMan.length; index++) {
				System.out.println(HungMan[index]);
			}
		} else if (nthGuess == 2) {
			for (int index2 = 1; index2 < HungMan.length; index2++) {
				for (int index = 1; index < HungMan.length; index++) {
					HungMan[index2][index] = ' ';
				}
			}
			for (int index = 0; index < HungMan.length; index++) {
				System.out.println(HungMan[index]);
			}
		} else if (nthGuess == 3) {
			for (int index2 = 4; index2 < HungMan.length; index2++) {
				for (int index = 1; index < HungMan.length; index++) {
					HungMan[index2][index] = ' ';
				}
			}
			for (int index = 0; index < HungMan.length; index++) {
				System.out.println(HungMan[index]);
			}
		} else if (nthGuess == 4) {
			for (int index2 = 4; index2 < HungMan.length; index2++) {
				for (int index = 1; index < 8; index++) {
					HungMan[index2][index] = ' ';
				}
			}
			for (int index2 = 4; index2 < HungMan.length; index2++) {
				for (int index = 9; index < HungMan.length; index++) {
					HungMan[index2][index] = ' ';
				}
			}
			for (int index = 0; index < HungMan.length; index++) {
				System.out.println(HungMan[index]);
			}
		} else if (nthGuess == 5) {
			HungMan[6][9] = ' ';
			HungMan[7][10] = ' ';
			HungMan[9][7] = ' ';
			HungMan[10][6] = ' ';
			HungMan[9][9] = ' ';
			HungMan[10][10] = ' ';
			for (int index = 0; index < HungMan.length; index++) {
				System.out.println(HungMan[index]);
			}
		} else if (nthGuess == 6) {
			HungMan[9][7] = ' ';
			HungMan[10][6] = ' ';
			HungMan[9][9] = ' ';
			HungMan[10][10] = ' ';
			for (int index = 0; index < HungMan.length; index++) {
				System.out.println(HungMan[index]);
			}
		} else if (nthGuess == 7) {
			HungMan[9][9] = ' ';
			HungMan[10][10] = ' ';
			for (int index = 0; index < HungMan.length; index++) {
				System.out.println(HungMan[index]);
			}
		} else if (nthGuess == 8) {
			for (int index = 0; index < HungMan.length; index++) {
				System.out.println(HungMan[index]);
			}
		}
	}

	/**
	 * Given the word (selected randomly from the '.txt' file) and a string
	 * containing the hidden alphabets (hangman word), return the total number
	 * of points for each player as per their individual performances in 
	 * guessing the word.
	 *
	 * @param newString,randomWord 
	 * 						Evaluate number of points as per every player's
	 * 						performance.
	 * 
	 * @return 				Number of points for each player
	 */
	public int GameBegins(String newString, String randomWord) {
		int counter = 1, flg = 0, flg2, points = 0;
		Scanner sc = new Scanner(System.in);
		while (counter <= 8) {
			flg2 = 0;
			System.out.println("\nWord: " + newString);
			System.out.println("Guess an alphabet in the word: ");
			char GuessByUser = sc.next().charAt(0);
			char[] randomWordCopyArray = randomWord.toCharArray();
			char[] newStringArray = newString.toCharArray();
			for (int index2 = 0; index2 < randomWordCopyArray.length; index2++) {
				if (GuessByUser == randomWordCopyArray[index2]) {
					if (newStringArray[index2] == '_') {
						newStringArray[index2] = GuessByUser;
						flg2 = 1;
						points += 10;
					}

				}
			}
			if (flg2 == 0) {
				System.out.println("\nWrong! " + "  Number of chances left: "
						+ (8 - counter));
				displayPartByPart(counter);
				points -= 5;
				counter++;
			} else {
				String stillIncompleteWord = String.valueOf(newStringArray);
				System.out.println("Correct!" + "\n" + stillIncompleteWord);
			}
			newString = String.valueOf(newStringArray);
			if (newString.equals(randomWord)) {
				flg = 1;
				break;
			}
		}
		if (flg == 1) {
			System.out.println("Congratulations!! You guessed the word right"
					+ "  Word: " + newString);
		} else {
			System.out.println("Sorry! You didn't get the word right."
					+ "  Word was: " + randomWord);
		}
		return (points);
	}

	/**
	 * Given the word (selected randomly from the '.txt' file), return a Hang-
	 * man word (i.e. a word with hidden alphabets) for the same.
	 *
	 * @param randomWord 
	 * 					Converting the randomly selected word into the Hangman
	 * 					word (i.e. random selected sword with hidden alphabets).
	 * 
	 * @return 			String with the hidden alphabets (Hangman word)
	 */
	public String CharReplace(String randomWord) {
		int index = 0;
		char[] newChar = randomWord.toCharArray();
		int LenOfRandomWord = randomWord.length();

		// If length is even
		if (LenOfRandomWord % 2 == 0) {
			// Put blanks in odd places
			while (index < LenOfRandomWord) {
				if (index % 2 != 0) {
					newChar[index] = '_'; 
											
				}
				index++;
			}
		} else {
			// Put blanks in even places
			while (index < LenOfRandomWord) {
				if (index % 2 != 0) {
					newChar[index] = '_'; 
											
				}
				index++;
			}
		}
		String newString = String.valueOf(newChar);
		return (newString);
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int[] findTopFour = new int[10];
		int counter = 1, players = 1, index = 0, index2, itr, playerLength, findTopFourLength, temp;
		int[] player = new int[20];
		int noOfPlayers, limit;
		Vector<String> v = new Vector<String>();
		Vector<String> playerNames= new Vector<String>();
		Random r = new Random();
		HangMan h = new HangMan();
		Scanner s = new Scanner(new File("words.txt"));
		while (s.hasNext()) {
			v.add(s.nextLine());
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of players: ");
		noOfPlayers = sc.nextInt();
		while (players < (noOfPlayers + 1)) {
			System.out.println("\nEnter your name: ");
			playerNames.add(sc.next());
			String randomWord = v.elementAt(r.nextInt(v.size()));
			String newString = h.CharReplace(randomWord);
			int points = h.GameBegins(newString, randomWord);
			player[index] = players;
			player[index + 1] = points;
			index += 2;
			players++;
		}
		System.out.println("\nResult Table:");
		System.out.println("Player Name " + "          Score ");
		playerLength = index;
		itr = 0;
		for (int j = 0; j < playerLength; j += 2) {
			System.out.println("  " + playerNames.elementAt(itr) + "\t\t\t" + player[j + 1]);
			findTopFour[itr++] = player[j + 1];
		}
		if(noOfPlayers>4){
		System.out.println("\nScores of TOP FOUR PLAYERS: ");
		} else {
		System.out.println("\nScores of TOP PLAYERS: ");
		}
		for (index = 0; index < noOfPlayers - 1; index++) {
			for (index2 = 0; index2 < (noOfPlayers - index - 1); index2++) {
				if (findTopFour[index2] > findTopFour[index2 + 1]) {
					temp = findTopFour[index2];
					findTopFour[index2] = findTopFour[index2 + 1];
					findTopFour[index2 + 1] = temp;
				}
			}
		}
		//System.out.println("\n" + "Player No. " + "    Score ");
		System.out.println("\n   Score ");
		index = 0;
		if (noOfPlayers < 4) {
			limit = 0;
		} else {
			limit = noOfPlayers - 4;
		}
		for (index2 = (noOfPlayers - 1); index2 >= limit; index2--) {
			System.out.println("    " + findTopFour[index2]);
			index += 2;
		}
	}
}