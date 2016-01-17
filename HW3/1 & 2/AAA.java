/**
 * AAA.java
 * @version $ID: AAA.java, v 1.8 09/09/2015 12:02pm
 * 
 * Revision: 3.11 09/13/2015 10:43am
 *
 */
 /**
 * This program explains the concept of inheritance and upcasting (i.e. casting an object of 
 * a subclass to an object of a superclass).
 * 
 * @author Aditya Pulekar
 *
 */
class AAA extends AA {

	int aInt = 1;

	/*
	 * On initiating an object of the class AAA, the control passes to the
	 * Superclass AA and executes the default constructor of class AA before
	 * executing the default constructor of class AAA
	 */
	AAA() {
		aInt = 11;
	}

	public int intPlusPlus() {
		return ++aInt;
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String args[]) {
		AAA aAAA = new AAA();
		AA aAA = (AA) aAAA;
		A aA = (A) aAA;
		/*
		 * Casting the object doesn't change the nature of the object. It only
		 * tells the compiler to treat 'aA' as if it was a class AA object and
		 * 'aAA' as if it was a class AAA object . But 'aA' remains to be an
		 * object of the class A and 'aAA' remains to be an object of the class
		 * AA. Hence, 'aA.intPlusPlus', 'aAA.intPlusPlus()' incremented the
		 * instance variable 'aInt' of the class AAA by 1 and value of 'aInt'
		 * for the classes A and AA remains untampered (i.e. 11).
		 */
		aAAA.intPlusPlus();
		aAA.intPlusPlus();
		aA.intPlusPlus();
		/*
		 * Note: If we specify a toString() method inside Class AAA, then it
		 * would print 14 for "SOP(aA), SOP(aAA), SOP(aAAA)". That means,
		 * control passes to the toString() method of the class A, wherein, the
		 * value of 'aInt' was defined as 11. Hence, we can conclude that if a
		 * class doesn't have a toString() method, it goes back to the
		 * superclass for object representation and the "this" reference changes
		 * to an instance of the superclass (i.e this.aInt will be 11, which is
		 * the value that it got from the superclass AA's constructor).
		 */
		System.out.println("aA:        " + aA);
		System.out.println("aAA:       " + aAA);
		/*
		 * For the statement below, the value printed on the console is the
		 * value of the instance variable 'aInt' of the object of class AA as
		 * class AAA does not have a 'toString()' method. Hence, the control
		 * passes to the superclass of class AAA (i.e class AA) and, though, the
		 * name of the class is printed as 'AAA', the value of the instance
		 * variable 'aInt' is that for the object of the class AA (i.e. 11).
		 */
		System.out.println("aAAA:      " + aAAA);
		/*
		 * By casting, the compiler was told to treat the objects 'aA' and 'aAA'
		 * as if it were the objects of the class AAA. The instance variable
		 * 'aInt' of the class AAA was incremented thrice since its method
		 * 'intPlusPlus' was called three times. Therefore, the final value in
		 * 'aInt' was 14
		 */
		System.out.println("aAAA:.aInt " + aAAA.aInt);
	}
}