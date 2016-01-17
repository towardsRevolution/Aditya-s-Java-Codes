/**
 * A.java
 * @version $ID: A.java, v 1.8 09/09/2015 11:00am 
 * 
 * Revision: 3.11 09/13/2015 10:43am
 *
 */
/**
 * This program explains the concept of inheritance and upcasting (i.e. casting an object of a
 * subclass to an object of a superclass).
 * 
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */
class A {

	int aInt = 1;

	A() {
		aInt = 11;
	}

	public int intPlusPlus() {
		return ++aInt;
	}

	public String toString() {
		return this.getClass().getName() + ": " + aInt;
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String args[]) {
		/*
		 * The default constructor of class A is used to instantiate an object
		 * of class A
		 */
		A aA = new A();
		/*
		 * Increments the value of the instance variable 'aInt' of the object
		 * 'aA' by 1, thereby making the value of 'aInt' as 12
		 */
		aA.intPlusPlus();
		System.out.println(aA);
	}
}