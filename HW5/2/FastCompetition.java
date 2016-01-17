/**
 * FastCompetition.java
 * @version $ID: FastCompetition.java, v 1.8 09/26/2015 7:02pm 
 * 
 * Revision: 5.21 09/28/2015 10:43am
 *
 */
/**
 * This program constructs a data structure, on which, operations such as add(),
 * contain(), remove() and sort() are performed
 * 
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */

@SuppressWarnings("hiding")
public class FastCompetition<String> implements Competition<Object> {
	int ElementIndex = 0, countOfObjects = 0;
	int MaxSize;
	Node<Object> head = null;
	Node<Object> subHead = null;

	/*
	 * Parameterized constructor of the class FastComptition
	 */
	public FastCompetition(int size) {
		this.MaxSize = size;
	}

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

		// Objects added to 'head' directly

		// TODO Auto-generated method stub
		if (countOfObjects > MaxSize) {
			countOfObjects--;
			return false;
		} else {
			head = new Node<Object>(e, head, ++ElementIndex);
			countOfObjects++;
			return true;
		}
	}

	/**
	 * Given an object (String or an Integer), the method should check the
	 * presence of the element inside the data structure
	 *
	 * @param o
	 *            Checks whether the object (i.e element of the node) passed as
	 *            the argument is contained inside the data structure
	 * 
	 * @return returns a boolean value indicating whether or not the object (i.e
	 *         the element of the node) is contained inside the data structure
	 */
	@Override
	public boolean contains(Object o) {
		// No Altering of the linkedlist, only traversing
		// TODO Auto-generated method stub
		int flag = 0;
		subHead = head;
		while (subHead != null) {
			if (o.equals(subHead.element)) {
				flag = 1;
				break;
			}
			subHead = subHead.next;
		}
		if (flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Given an object (String or an Integer), the method should remove the
	 * given object from the data structure
	 *
	 * @param o
	 *            Removes the node from the data structure
	 * 
	 * @return returns a boolean value indicating whether or not the node has
	 *         been removed from the data structure
	 */
	@Override
	public boolean remove(Object o) {

		/*
		 * Altering of linkedlist involved, so subHead assigned back to head in
		 * the last line of this method
		 */

		// TODO Auto-generated method stub
		int flag = 0;
		Node<Object> previous = null;
		subHead = head;
		if (subHead == null) {
			return false;
		}

		// In case the first element is our object

		if (subHead.element.equals(o)) {
			head = subHead.next;
			flag = 2;
		} else {
			while (subHead != null) {
				if (subHead.element.equals(o)) {
					flag = 1;
					break;
				}
				previous = subHead;
				subHead = subHead.next;
			}
		}
		if (flag == 1) {
			previous.next = subHead.next;
			countOfObjects--;
			return true;
		} else if (flag == 2) {
			countOfObjects--;
			return true;
		} else {
			System.out.println("Object not found!");
			return false;
		}

	}

	/**
	 * The method should return the instance field 'element' of the node class
	 * at the location given in the arguments
	 * 
	 * @return returns the object (i.e. String or Integer)
	 *
	 */
	@Override
	public Object elementAt(int index) {
		// TODO Auto-generated method stub
		int itr = 1;
		subHead = head;
		while (itr < index) {
			subHead = subHead.next;
			itr++;
		}
		Object Element = subHead.element;
		return Element;
	}

	/**
	 * The method should sort the elements of the data structure
	 * 
	 * @return returns an object of the Interface Competition
	 *
	 */
	@Override
	public Competition<Object> sort() {
		// TODO Auto-generated method stub
		Node<Object> temp, temp2, previousToSwappedNode = null;
		Node<Object> toMaintainRefToFirstNode = null;
		int flag = 0;
		subHead = head;
		for (int index = 0; index < countOfObjects - 1; index++) {
			if (flag == 1) {
				subHead = toMaintainRefToFirstNode;
				flag = 0;
			}
			for (int index2 = 0; index2 < countOfObjects - index - 1; index2++) {
				if (subHead.next != null) {

					/*
					 * If we write (subHead!==null) we will get a null pointer
					 * error
					 */

					if (subHead.compareNodes(subHead.next) > 0) {
						temp = subHead;
						subHead = subHead.next;
						temp2 = subHead.next;
						subHead.next = temp;
						temp.next = temp2;
						if (index2 > 0) {
							previousToSwappedNode.next = subHead;
						}
					}
					if (index2 == 0) {
						toMaintainRefToFirstNode = subHead;
						flag = 1;
					}
					previousToSwappedNode = subHead;
					subHead = subHead.next;
				}
			}
		}
		head = toMaintainRefToFirstNode;
		return null;
	}

	/**
	 * The method should return the size of the data structure at any point of
	 * time
	 * 
	 * @return returns the size
	 *
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return countOfObjects;
	}

	/**
	 * The method should print the elements (i.e element field of the class
	 * node) of the data structure
	 *
	 */
	@Override
	public void printNode() {
		// TODO Auto-generated method stub
		Node<Object> iterateNode = head;
		System.out.println("Contents of the data structure: ");
		while (iterateNode != null) {
			System.out.println(iterateNode.element);
			iterateNode = iterateNode.next;
		}
	}
}
