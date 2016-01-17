import java.util.Scanner;

public class StorageTest {

	// Suppress warnings added for unused objects
	public static void main(String[] args) {

		/*
		 * Testing StorageFixed.java by adding Strings to the data structure
		 */
		Storage<String, String> aStorageString = new StorageFixed<String, String>();
		@SuppressWarnings("unused")
		Storage<Integer, String> aStorageInteger = new StorageFixed<Integer, String>();
		System.out
				.println("Enter the number of String elements you want to store: ");
		Scanner sc = new Scanner(System.in);
		int numOfStrings = sc.nextInt();
		System.out.println("Enter the elements for storage: ");
		for (int index = 0; index < numOfStrings; index++) {
			String valueOfElement = sc.next();
			if (!aStorageString.add(valueOfElement)) {
				System.out
						.println("No more elements can be added!! Out of storage space.");
				break;
			}
		}
		System.out.println("Printing all the elements: ");
		aStorageString.printNode();
		System.out
				.println("Enter a position where you want a String to be added along with the String: ");
		int pos = sc.nextInt();
		String s = sc.next();
		aStorageString.add(pos, s);
		System.out.println("Printing all the elements: ");
		aStorageString.printNode();
		System.out.println("First Element: " + aStorageString.firstElement());
		System.out.println("Last Element: " + aStorageString.lastElement());
		aStorageString.clear();
		System.out.println("Printing all the elements: ");
		aStorageString.printNode();

		/*
		 * Testing StorageDynamic.java by adding Integers to the data structure
		 */
		@SuppressWarnings("unused")
		Storage<String, String> aSString = new StorageDynamic<String, String>();
		Storage<Integer, String> aSInteger = new StorageDynamic<Integer, String>();
		System.out
				.println("\n\nEnter the number of Integer elements you want to store: ");
		Scanner scan = new Scanner(System.in);
		int numOfIntegers = scan.nextInt();
		System.out.println("Enter the elements for storage: ");
		for (int index = 0; index < numOfIntegers; index++) {
			Integer valueOfElement = scan.nextInt();
			if (!aSInteger.add(valueOfElement)) {
				System.out
						.println("No more elements can be added!! Out of storage space.");
				break;
			}
		}
		System.out.println("Printing all the elements: ");
		aSInteger.printNode();
		System.out
				.println("Enter a position where you want a number to be added along with the number: ");
		int pos1 = scan.nextInt();
		Integer num = scan.nextInt();
		aSInteger.add(pos1, num);
		System.out.println("Printing all the elements: ");
		aSInteger.printNode();
		System.out.println("First Element: " + aSInteger.firstElement());
		System.out.println("Last Element: " + aSInteger.lastElement());
		aSInteger.clear();
		System.out.println("Printing all the elements: ");
		aSInteger.printNode();
		scan.close();
		sc.close();
	}
}
