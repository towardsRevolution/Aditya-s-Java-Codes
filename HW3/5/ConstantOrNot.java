/**
 * ConstantOrNot.java
 * @version $ID: ConstantOrNot.java, v 1.8 09/10/2015 12:18am 
 * 
 * Revision: 3.51 09/13/2015 10:43pm
 *
 */
import java.util.Vector; // what does this line do?
/*
 * The line above imports the Vector class from the 'java.util.*' package. The 
 * java.util.Vector class implements a dynamic array of objects.
 */

/**
 * This program explains the usage of the keyword 'final' and the access specifier
 * 'private' and the scope on declaring an Integer, a String and a vector as 
 * 'private final'.
 * 
 * @author Aditya Pulekar
 *
 */
class ConstantOrNot {
	/*
	 * A final variable may be assigned only once. If a final variable contains
	 * a value, then the value cannot be further changed. If a final variable
	 * contains a reference to an object, then the state of the object may be
	 * changed but the variable will always refer to the same object. Marking a
	 * field as private makes it accessible only within its own class.
	 */
	private final int aInt = 1;
	/*
	 * String is an object in java. A final string contains a reference to an
	 * object and the reference will always be the same (i.e. the string will,
	 * henceforth, refer to the same object).
	 */
	private final String aString = "abc";
	private final Vector aVector = new Vector();

	public void doTheJob() {
		/*
		 * Since 'aInt' is a 'private final int', it can be assigned only once.
		 * Since, it has already been assigned a value '1' initially, it cannot
		 * be changed further. Hence, the assignment 'aInt=3' would fail.
		 */
		// aInt = 3; why would this fail?

		/*
		 * When concatenating 'aString' with "abc", we get "abcabc" which is a
		 * different literal. Hence, it creates a new string object with a
		 * different reference. Since, a 'private final String' should always
		 * refer to the same object, the concatenation of a 'private final
		 * String' won't be permitted and the compiler would throw an error.
		 * Hence, aString = aString + "abc" would fail.
		 */
		// aString = aString + "abc"; //why would this fail?

		/*
		 * A Vector is an object. Like strings, Vectors are mutable. So,
		 * 'private final Vector aVector = new Vector()' creates a new instance
		 * (or object) of the vector. As mentioned before, if a final variable
		 * contains a reference to an object, then the state of the object may
		 * be changed but the variable will always refer to the same object.
		 * Unlike the strings, the vector contains the same reference value on
		 * adding data to it. Therefore, 'aVector.add("abc")' works.
		 */
		aVector.add("abc"); // why does this work?

	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String args[]) {
		new ConstantOrNot().doTheJob();

	}
}