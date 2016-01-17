/**
 * StringZipOutputStream.java
 * @version $ID: StringZipOutputStream.java, v 1.8 10/8/2015 7:28pm 
 * 
 * Revision: 7.2 10/12/2015 3:30pm
 *
 */
import java.io.*;

/**
 * This program compresses the input file using the Lempel Ziv Welch (LZW)
 * Algorithm
 * 
 * @author Aditya Pulekar
 *
 */
public class StringZipOutputStream {
	int dictElementIndex; // It will keep track of the indices of the dictionary
							// (above 65535)
	String[] dict = new String[655360]; // Keep the dictionary here now....only
										// then we will get a compressed size
	int index2 = 0;
	OutputStream out;
	InputStream in;

	/*
	 * Parameterized Constructor
	 * 
	 * @param out writes the OutputStream
	 */
	public StringZipOutputStream(OutputStream out) throws IOException {
		this.out = out;
	}

	/**
	 * The method should write the encoded data (derived from the input String)
	 * onto the file
	 * 
	 * @param aWord
	 *            a String (containing) a line from the input file
	 *
	 */
	public void write(String aWord) throws IOException {
		int toCheckNext = 0, iterator = 0;
		int current, next, encodeCount = 0;
		int[] encodedChars = new int[655360];
		char[] line = aWord.toCharArray();
		int charCount = 1;
		out = new DataOutputStream(out);
		try {
			if (line.length > 0) {
				next = line[0];
				while (charCount < line.length) {
					current = next;
					toCheckNext = line[charCount];
					next = toCheckNext;
					if (!(searchDict(current, next))) { // If the word is not in
														// the dictionary
						AddToDictENC(current, next, index2);
						index2++;
						encodedChars[encodeCount++] = current;
					} else { // If the word is in the dictionary
						next = dictElementIndex;
					}
					charCount++;
				}
				encodedChars[encodeCount++] = line[charCount - 1];
				index2++;
				// We have to include the two statements below since we have to
				// read line by line
				encodedChars[encodeCount++] = 13;
				encodedChars[encodeCount++] = 10;
			} else {
				encodedChars[encodeCount++] = 13;
				encodedChars[encodeCount++] = 10;
			}
			while (iterator < encodeCount) {
				((DataOutputStream) out).writeBytes((String
						.valueOf(encodedChars[iterator++]) + " "));
			}
		} finally {
			out.flush();
		}
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
	 * Traverses the dictionary in order to check for a String
	 * 
	 * @param current
	 *            Unicode value of the character being considered
	 * @param next
	 *            Unicode value of the subsequent character
	 * 
	 * @return boolean value return a boolean indicating whether the word exists
	 *         in the dictionary or not
	 */
	public boolean searchDict(int current, int next) {
		int index = 0, flag = 0;
		String s = null;
		if (current > 65535) {
			s = dict[current - 65536];
			s = s + new StringBuffer().append((char) next).toString();
		} else {
			s = new StringBuffer().append((char) current).append((char) next)
					.toString();
		}
		while (dict[index] != null) {
			if (dict[index].equals(s)) {
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
	public void AddToDictENC(int current, int next, int index) {
		String concatenatedChars;
		// If the index in 'current' is greater than 65535
		if (current > 65535) {
			concatenatedChars = dict[current - 65536];
			concatenatedChars = concatenatedChars
					+ new StringBuffer().append((char) next).toString();
		} else {
			concatenatedChars = new StringBuffer().append((char) current)
					.append((char) next).toString();
		}
		dict[index] = concatenatedChars;
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// new StringZipOutputStream(new
		// FileOutputStream("compressedData.txt"));
	}

}
