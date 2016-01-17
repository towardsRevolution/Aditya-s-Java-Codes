/**
 * StorageDynamic.java
 * @version $ID: StorageDynamic.java, v 1.8 09/24/2015 7:02pm 
 * 
 * Revision: 5.11 09/28/2015 10:43am
 *
 */
/**
 * This program constructs a dynamic data structure using a linked list, which
 * is constructed using a Node class. The data structure has no upper limit for
 * addition of nodes.
 * 
 * @author Aditya Pulekar
 *
 */
@SuppressWarnings("hiding")
public class StorageDynamic<Object, String> implements Storage<Object, String> {
	/*
	 * // Note: We get a warning
	 * "The type parameter Object is hiding in the type parameter Object" when
	 * we specify generics as <Object, String> for both, the class and the
	 * interface in the above statement. It means that there is a class or
	 * actual type "T" declared somewhere in the classpath (or inner class). If
	 * we remove the generics from the class then we do not get one. However,
	 * Storage<String, String> aStorageString = new
	 * StorageFixed<String,String>(); gives an error in StorageTest.java, if we
	 * remove it.
	 */
	static int ElementIndex = 0;
	public Node<Object> head = null;

	/**
	 * Given an object (String or an Integer), the method should add it to the
	 * instance field 'element' of the node and then add the node at the end of
	 * the data structure
	 *
	 * @param e
	 *            Adds the object in the node and then adds the node to the data
	 *            structure
	 * 
	 * @return returns a boolean value indicating whether the node can be added
	 *         to the data structure or not
	 */
	@Override
	public boolean add(Object e) {
		// TODO Auto-generated method stub
		try {
			head = new Node<Object>(e, head, ++ElementIndex);
		} catch (StackOverflowError s) {
			System.out.println("StackOverflow Exception is caught!");
			s.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public Object clone() {
		return null;

	}

	/**
	 * Given an object (String or an Integer) and the location of addition, the
	 * method should add it to the instance field 'element' of the node and then
	 * add the node at the location specified in the arguments
	 *
	 * @param e
	 *            Adds the object in the node and then adds the node to the data
	 *            structure at the given location
	 */
	@Override
	public void add(int index, Object element) {
		// TODO Auto-generated method stub
		Node<Object> iteratorNode = head;
		Node<Object> storeReference, IntermediateNode;
		int itr = 1;
		if (index == 1) {
			IntermediateNode = new Node<Object>(element, head, ++ElementIndex);
			IntermediateNode.next = iteratorNode;
			head = IntermediateNode;
		} else {
			while (itr < index - 1) {
				iteratorNode = iteratorNode.next;
				itr++;
			}
			// System.out.println("2nd element: " + iteratorNode.element);
			storeReference = iteratorNode.next;
			IntermediateNode = new Node<Object>(element, iteratorNode,
					++ElementIndex);
			iteratorNode.next = IntermediateNode;
			IntermediateNode.next = storeReference;
			/*
			 * System.out.println("IntermediateNode.next.element: " +
			 * IntermediateNode.element);
			 */
		}

	}

	@Override
	public void addElement(Object obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addElement(Object obj, String elem) {
		// TODO Auto-generated method stub

	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return 1;
	}

	/**
	 * The method should clear the data structure created.
	 *
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head = null;

	}

	/**
	 * The method should return the first element of the data structure
	 * 
	 * @return returns the object (i.e. String or Integer)
	 *
	 */
	@Override
	public Object firstElement() {
		// TODO Auto-generated method stub
		return head.element;
	}

	/**
	 * The method should return the element (i.e element field of the class
	 * node) at the specified location of the data structure
	 * 
	 * @param index
	 *            traverses the data structure to find the element at the given
	 *            index
	 * 
	 * @return returns the object (i.e. String or Integer)
	 *
	 */
	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		int itr = 1;
		while (itr < index) {
			head = head.next;
			itr++;
		}
		Object Element = head.element;
		// head=head.next;
		return Element;
	}

	/**
	 * The method should return the last element of the data structure
	 * 
	 * @return returns the object (i.e. String or Integer)
	 *
	 */
	@Override
	public Object lastElement() {
		// TODO Auto-generated method stub
		Node<Object> iterateNode = head;
		while (iterateNode.next != null) {
			iterateNode = iterateNode.next;
		}
		return iterateNode.element;
	}

	/**
	 * The method should print the elements (i.e element field of the class
	 * node) of the data structure
	 *
	 */
	public void printNode() {
		Node<Object> iterateNode = head;
		System.out.println("Values in the Data Structure: ");
		if (iterateNode == null) {
			System.out.println("The data structure has been cleared!");
		}
		while (iterateNode != null) {
			System.out.println(iterateNode.element);
			iterateNode = iterateNode.next;
		}
	}
}
