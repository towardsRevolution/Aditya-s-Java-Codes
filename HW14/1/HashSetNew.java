/**
 * HashSetNew.java
 * @version $ID: HashSetNew.java, v 1.8 12/5/2015 7:02pm 
 * 
 * Revision: 14.11 12/7/2015 11:43am
 *
 */
 
import java.util.*;

/*
 * This class is my implementation of the class HashSet (inbuilt) of java. 
 * 
 * @author Aditya Pulekar, Mandar Badave
 */
public class HashSetNew extends HashSet {
	int MAX = 20000;
	double loadFactor = 0.75;
	int currentSize = 0;
	static int rehash = 2;   //rehashing factor
	Object[] SetArray = new Object[MAX];  //for storage of objects in a hash set
	
	/*
	 * Default constructor
	 *
	 */
	public HashSetNew() {
		
	}
	
	/*
	 * Parameterized constructor 1
	 * 
	 * @param MAX_Capacity Max number of buckets in a hash table
	 */
	public HashSetNew(int MAX_Capacity) {
		SetArray = new Object[MAX_Capacity];
	}
	
	/*
	 * Parameterized constructor 2
	 * 
	 * @param MAX_Capacity Max number of buckets in a hash table
	 * @param loadFactor ratio of the size to the table size
	 */
	public HashSetNew(int MAX_Capacity, float loadFactor) {
		SetArray = new Object[MAX_Capacity];
		this.loadFactor = loadFactor;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.HashSet#add(java.lang.Object)
	 */
	@Override
	public boolean add(Object o) {
		int flag = 0;
		long hash;
		if (currentSize == MAX) {
			System.out.println("Rehashing....");
			// Rehashing...
			int lenOfSetArray = MAX;
			MAX *= rehash;
			Object[] newSetArray = new Object[MAX];
			for (int i = 0; i < lenOfSetArray; i++) {
				if (SetArray[i] != null) {
					hash = o.hashCode();
					if (hash < 0) {
						hash *= (-1);
					}
					newSetArray[(int) hash % MAX] = SetArray[i];
				}
			}

			SetArray = newSetArray;
			rehash++;
			// return false;
		}
		hash = o.hashCode();
		if (hash < 0) {
			hash *= (-1);
		}
		if (!contains(o)) { // && SetArray[(int) (hash % MAX)] == null)
			if (SetArray[(int) (hash % MAX)] == null) {
				SetArray[(int) (hash % MAX)] = o;
				currentSize++;
			} else {
				// If the array doesn't contain the given object, then
				// place the object at first "null" position in the array

				// OPEN ADDRESSING
				int index = (int) (hash % MAX);
				int limitOfOpenAddr = (int) (hash % MAX);
				while (true) {
					if (SetArray[index] == null) {
						flag = 1;
						break;
					}
					index++;
					if (index == MAX) {
						index = 0;
					}
					if (index == limitOfOpenAddr) {
						// System.out.println("No, the object ain't inside the array");
						break;
					}
				}
				if (flag == 1) {
					// No need to iterate if this is the case. We need to put
					// the
					// value in the "hash % MAX"th location straight away
					SetArray[(int) (hash % MAX)] = o;
					currentSize++;
				} else {
					System.out
							.println("No more place in the array to add.....");
				}
			}
			return true;
		}
		return false;
		// }
	}

	// Note: If we were to override the equals method, then we would also have
	// to override the hashCode() method to maintain the general contract which
	// states that equal hashcodes.

	/*
	 * (non-Javadoc)
	 * @see java.util.HashSet#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		int index = 0, flag = 0;
		long hash = o.hashCode();
		if (hash < 0) {
			hash *= (-1);
		}
		// Imp note: Two objects with the same hashCode may be different (i.e.
		// not equal)
		if (SetArray[(int) (hash % MAX)] != null) {
			if (SetArray[(int) (hash % MAX)].equals(o)) {
				// This means it is a duplicate object
				return true;
			} else {
				// This means it is an object with the same hashcode as the
				// element at "SetArray[hash % MAX]" but is a different object.
				// So, now we have two options: 1. Either we define our own
				// hashCode()
				// method such that only duplicate objects will have the same
				// hashcode OR 2. We do open Addressing.
				// SO WE WILL TRY OPEN ADDRESSING
				index = (int) (hash % MAX);
				int limitOfOpenAddr = (int) (hash % MAX);

				// Make sure that SetArray[index] is not "null", else we
				// get a null pointer exception.
				while (true) {
					if (SetArray[index] != null) {
						if (SetArray[index].equals(o)) {
							flag = 1;
							break;
						}
					}
					index++;
					if (index == MAX) {
						index = 0;
					}
					if (index == limitOfOpenAddr) {
						// System.out.println("No, the object ain't inside the array");
						break;
					}
				}
				if (flag == 1) {
					return true;
				}else {
					return false;
				}
			}
		} else {
			//System.out.println("SetArray[(int) (hash % MAX)] == null...index: "+ (int) (hash % MAX));
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.HashSet#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		int flag = 0;
		int hash = o.hashCode();
		if (hash < 0) {
			hash *= (-1);
		}
		if (SetArray[hash % MAX] != null) {
			if (SetArray[hash % MAX].equals(o)) {
				SetArray[hash % MAX] = null;
				currentSize--;
				return true;
			} else {
				// OPEN ADDRESSING
				int index = (int) (hash % MAX);
				int limitOfOpenAddr = (int) (hash % MAX);
				while (true) {
					if (SetArray[index] != null) {
						if (SetArray[index].equals(o)) {
							flag = 1;
							break;
						}
					}
					index++;
					if (index == MAX) {
						index = 0;
					}
					if (index == limitOfOpenAddr) {
						// System.out.println("No, the object ain't inside the array");
						break;
					}
				}
				if (flag == 1) {
					// No need to iterate if this is the case. We need to remove
					// the value in the "hash % MAX"th location straight away
					SetArray[(int) (hash % MAX)] = null;
					currentSize--;
					return true;
				} else {
					/*System.out
							.println("The object has already been removed.....");*/
					return false;
				}
			}
		} else {
			//System.out.println("The object has been already removed!");
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.HashSet#size()
	 */
	@Override
	public int size() {
		return currentSize;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.HashSet#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (currentSize == 0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.HashSet#clear()
	 */
	@Override
	public void clear() {
		// Note: Such for loop only works on a list
		// Also, an iterator can only be used on a list and not on
		// an array object.
		/*
		 * for(Object obj : SetArray){ obj = null; } for (int index = 0;index <
		 * MAX; index++){ if(SetArray[index] != null){
		 * System.out.println("Error in clear().."); break; } }
		 */
		MAX = 20000;
		SetArray = new Object[MAX];
		currentSize = 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.HashSet#iterator()
	 */
	@Override
	public Iterator<Object> iterator() {
		Iterator<Object> itr = new IteratorNew();
		return itr;
	}
	
	/*
	 * My Iterator class (an inner class)
	 */
	public class IteratorNew implements Iterator<Object>{
		int currentIndex = 0;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (currentIndex < MAX && SetArray[currentIndex] != null);
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return SetArray[currentIndex++];
		}

	}
}


