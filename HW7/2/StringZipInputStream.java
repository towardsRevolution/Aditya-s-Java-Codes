/**
 * StringZipInputStream.java
 * @version $ID: StringZipInputStream.java, v 1.8 10/8/2015 7:28pm 
 * 
 * Revision: 7.2 10/12/2015 3:30pm
 *
 */
import java.io.*;

/**
 * This program decompresses the encoded file using the Lempel Ziv Welch (LZW)
 * Algorithm
 * 
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */
public class StringZipInputStream {
	int dictElementIndex; // It will keep track of the indices of the dictionary
							// (above 65535)
	int toCheckNext = 0;
	String[] dictDEC = new String[655360];
	InputStream in;
	OutputStream out;

	/*
	 * Parameterized Constructor
	 * 
	 * @param in reads the OutputStream
	 */
	public StringZipInputStream(InputStream in) throws IOException {
		this.in = in;
	}

	/**
	 * The method should write the encoded data (derived from the input String)
	 * onto the file
	 * 
	 * @return s a String (containing) the data from the compressed file
	 *
	 */
	public String read() throws IOException {
		int index3 = 0;
		int current, next, iterator = 0;
		int[] decodedChars = new int[655360];
		String s = "";
		/*
		 * DATA DECODING
		 */
		try {
			int dictIndex = 0;
			String c = "";
			int reading = in.read();
			if (toCheckNext == -1) {
				return null;
			}
			while (reading != 32) {
				c = (String) (c + (char) reading);
				reading = in.read();
			}
			next = Integer.parseInt(c);
			while (toCheckNext != -1) {
				current = next;
				reading = in.read();
				if (reading != -1) {
					c = "";
					while (reading != 32) {
						c = (String) (c + (char) reading);
						reading = in.read();
					}
					toCheckNext = Integer.parseInt(c);
				} else {
					toCheckNext = -1;
				}
				next = toCheckNext;
				if (!(searchDictDEC(current, next))) { // If the word is not in
														// the dictionary
					AddToDictDEC(current, next, dictIndex);

					if (current > 65535) {
						int itr = 0, indicator = 0;
						while (dictDEC[itr] != null) {
							if ((65536 + itr) == current) {
								indicator = 1;
								break;
							}
							itr++;
						}
						if (indicator == 1) {
							String outputStr = "";
							int charIndex = 0;
							char[] InDict = dictDEC[itr].toCharArray();
							while (charIndex < InDict.length) {

								if (InDict[charIndex] != ' ') {
									outputStr += String
											.valueOf(InDict[charIndex]);
								} else {
									decodedChars[index3++] = Integer
											.parseInt(outputStr);
									outputStr = "";
								}
								charIndex++;
							}
							if (charIndex == InDict.length) {
								decodedChars[index3++] = Integer
										.parseInt(outputStr);
							}
						}
					} else {
						decodedChars[index3++] = current;
					}
				} else { // If the word is in the dictionary
					next = dictElementIndex;
				}
				dictIndex++;
			}
		} finally {
			while (iterator < index3) {
				s = s + String.valueOf((char) decodedChars[iterator++]);
			}
		}
		return s;
	}

	/**
	 * Closes the input and the output stream
	 * 
	 * @param aWord
	 *            a String (containing) a line from the input file
	 *
	 */
	public void close() throws IOException {
		if (in != null) {
			in.close();
		}
		if (out != null) {
			out.close();
		}
	}

	/**
	 * Traverses the dictionary in order to check for a string
	 * 
	 * @param current
	 *            Unicode value of the character being considered
	 * @param next
	 *            Unicode value of the subsequent character
	 * 
	 * @return boolean value return a boolean indicating whether the word exists
	 *         in the dictionary or not
	 */
	public boolean searchDictDEC(int current, int next) {
		int index = 0, flag = 0;
		String s = null;
		if (current > 65535) {
			s = dictDEC[current - 65536];
			if (next < 65535) {
				s = s
						+ new StringBuffer().append(" " + String.valueOf(next))
								.toString();
			} else {
				int flagNext = 0, indexNext = 0;
				String sNext = dictDEC[next - 65536];
				while (dictDEC[indexNext] != null) {
					if (dictDEC[indexNext].equals(sNext)) {
						flagNext = 1;
						break;
					}
					indexNext++;
				}
				if (flagNext == 1) {
					// Chose the part before " " in dictDEC[indexNext] and
					// concatenate it to s
					char[] InDictFirst = dictDEC[indexNext].toCharArray();
					int itr = 0;
					while (itr < InDictFirst.length) {
						if (InDictFirst[itr] == ' ') {
							break;
						}
						itr++;
					}
					s = s
							+ new StringBuffer().append(
									" " + dictDEC[indexNext].substring(0, itr))
									.toString();
				}
			}
		} else {
			if (next < 65535) {
				s = s
						+ new StringBuffer().append(String.valueOf(current))
								.append(String.valueOf(next)).toString();
			} else {
				int flagNext = 0, indexNext = 0;
				String sNext = dictDEC[next - 65536];
				while (dictDEC[indexNext] != null) {
					if (dictDEC[indexNext].equals(sNext)) {
						flagNext = 1;
						break;
					}
					indexNext++;
				}
				if (flagNext == 1) {
					// Chose the part before " " in dictDEC[indexNext] and
					// concatenate it to s
					char[] InDictFirst = dictDEC[indexNext].toCharArray();
					int itr = 0;
					while (itr < InDictFirst.length) {
						if (InDictFirst[itr] == ' ') {
							break;
						}
						itr++;
					}
					s = s
							+ new StringBuffer().append(
									" " + dictDEC[indexNext].substring(0, itr))
									.toString();
				}
			}
		}
		while (dictDEC[index] != null) {
			if (dictDEC[index].equals(s)) {
				flag = 1;
				break;
			}
			index++;
		}
		if (flag == 1) {
			this.dictElementIndex = 65536 + index;
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Adds the string to the dictionary
	 * 
	 * @param current
	 *            Unicode value of the character being considered
	 * @param next
	 *            Unicode value of the subsequent character
	 * @param index
	 *            Index at which the string is to be added to the dictionary
	 * 
	 * @return boolean value return a boolean indicating whether the word exists
	 *         in the dictionary or not
	 */
	public void AddToDictDEC(int current, int next, int index) {
		String concatenatedChars = null;
		if (current > 65535) {
			concatenatedChars = dictDEC[current - 65536];
			if (next > 65535) {
				int flagNext = 0, indexNext = 0;
				String sNext = dictDEC[next - 65536];
				while (dictDEC[indexNext] != null) {
					if (dictDEC[indexNext].equals(sNext)) {
						flagNext = 1;
						break;
					}
					indexNext++;
				}
				if (flagNext == 1) {
					// Chose the part before " " in dictDEC[indexNext] and
					// concatenate it to s
					char[] InDictFirst = dictDEC[indexNext].toCharArray();
					int itr = 0;
					while (itr < InDictFirst.length) {
						if (InDictFirst[itr] == ' ') {
							break;
						}
						itr++;
					}
					concatenatedChars = concatenatedChars
							+ new StringBuffer().append(
									" " + dictDEC[indexNext].substring(0, itr))
									.toString();
				}
			} else {
				concatenatedChars = concatenatedChars
						+ new StringBuffer().append(" " + String.valueOf(next))
								.toString();
			}
		} else {
			if (next > 65535) {
				int flagNext = 0, indexNext = 0;
				String sNext = dictDEC[next - 65536];
				while (dictDEC[indexNext] != null) {
					if (dictDEC[indexNext].equals(sNext)) {
						flagNext = 1;
						break;
					}
					indexNext++;
				}
				if (flagNext == 1) {
					concatenatedChars = new StringBuffer()
							.append(String.valueOf(current))
							.append(" " + dictDEC[indexNext]).toString();
				}
			} else {
				concatenatedChars = new StringBuffer()
						.append(String.valueOf(current))
						.append(" " + String.valueOf(next)).toString();
			}
		}
		dictDEC[index] = concatenatedChars;
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// StringZipInputStream aStringZipInput = new StringZipInputStream(new
		// DataInputStream(new FileInputStream("charOutFinalAdi.txt")));
		// aStringZipInput.read();

	}

}
