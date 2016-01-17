// this implementation implements the methods,
// but the methods are null methods;
public class StringStackOld implements StackInterfaceOld {
	Integer i = new Integer(0);

	public void push(Object item) {
	}

	public Object pop() {
		return "hello";
	}

	public Object peek() {
		return "hello";
	}

	public boolean isEmpty() {
		return true;
	}

	public static void main(String args[]) {
		StackInterfaceOld aStackInterfaceOld = new StringStackOld();
		@SuppressWarnings("unused")
		StringStackOld s = new StringStackOld();
		/*
		 * The Object class is in the java.lang package. Every class inherits
		 * the object class by default. A String class is a subclass to the
		 * Object class. If you notice, the push() method has an incoming Object
		 * parameter. In the main, we pass a String "hello" which is stored in
		 * the Object item. Now, when we define the parameter object, being at
		 * the top of the class hierarchy, a String, Integer, Array etc can be
		 * passed into it. Hence, an object of String is successfully stored in
		 * the argument 'item' and the compiler doesn't give an error or
		 * warning.
		 */
		aStackInterfaceOld.push("hello"); // why is here no warning?

		/*
		 * While popping an element from the stack, an object is being returned
		 * from the stack and we are storing it into a String without casting.
		 * Now, the compiler analyzes an object to be of the class Integer,
		 * String, Array etc. Hence, it finds a discrepancy in assigning an
		 * object to a String, unless clearly specified by casting. Therefore,
		 * the compiler gives the error during pop() operation. Note: compiler
		 * won't mind, if the pop() method was to return a String and it was to
		 * be stored in an object. So, cases that are valid: (LHS-RHS)
		 * Object-Object Object-String String-String String-String (or any other
		 * object) is only valid with casting
		 */
		/*
		 * The cast tells the compiler that the object returned from the pop()
		 * method is to considered as a String.
		 */
		String aString = (String) aStackInterfaceOld.pop();
		@SuppressWarnings("unused")
		Object aInt = aStackInterfaceOld.pop();
		System.out.println(aString);

	}
	/*
	 * javac StringStackOld.java // explain this error StringStackOld.java:11:
	 * incompatible types // explain what a cast would do found :
	 * java.lang.Object // regarding possible compiler error detection required:
	 * java.lang.String String aString = aStackInterfaceOld.pop(); ^ 1 error
	 */

}