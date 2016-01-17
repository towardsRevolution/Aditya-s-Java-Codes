/**
 * AA.java
 * @version $ID: AA.java, v 1.8 09/09/2015 11:00am 
 * 
 * Revision: 3.11 09/13/2015 10:43am
 *
 */
/**
 * This program explains the concept of inheritance and upcasting (i.e. casting an object of 
 * a subclass to an object of a superclass).
 * 
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */
class AA extends A {

	int aInt = 1;

	/*
	 * On initiating an object of the class AA, the control passes to the
	 * Superclass A and executes the default constructor of class A before
	 * executing the default constructor of class AA
	 */
	AA() {
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
		 * Current instance object is of the class AA, hence, 'this.' will be
		 * used to indicate an the instance object of the class AA.
		 */
		AA aAA = new AA();
		/*
		 * Note: A new instance of class A is not being created. Hence, 'this.'
		 * won't refer to an instance object of the class A. And SOP(aA) will
		 * print the state of object aAA of class AA.
		 */
		A aA = (A) aAA;
		// AA aA=aAA;
		// A aA= new A();
		/*
		 * Casting the object doesn't change the nature of the object. It only
		 * tells the compiler to treat 'aA' as if it was a class AA object. But
		 * 'aA' remains to be an object of the class A.
		 */
		aAA.intPlusPlus();
		aA.intPlusPlus();
		System.out.println(aA);
		System.out.println(aAA);
		/*
		 * Since 'aA' is an object of the class A, the value of the instance
		 * variable 'aInt' remains 11 as it wasn't tampered throughout the
		 * course of the main function. The value of the instance variable
		 * 'aInt' of the class AA was changed from 11 to 13
		 */
		System.out.println("aA: " + aA.aInt);
	}
}