@SuppressWarnings("hiding")
public class Node<Object> {
	Object element = null;
	Node<Object> next;
	int ElementIndex;

	/*
	 * Parameterized constructor of the class Node
	 */
	public Node(Object element, Node<Object> next, int ElementIndex) {
		// We don't mention generics in a constructor
		this.element = element;
		this.next = next;
		this.ElementIndex = ElementIndex;
	}

	/**
	 * Given a node, the method should compare the String element of the current
	 * node object and the node object passed in the arguments. The method
	 * should return a value 1 if ,lexicographically, the String of current node
	 * object is greater than the string of the node object passed in the
	 * arguments, -1 if ,lexicographically, the String of current node object is
	 * lesser than the string of the node object passed in the arguments and 0
	 * if both the strings are equal
	 *
	 * @param o
	 *            Compares the Strings lexicographically
	 * 
	 * @return returns the comparison result
	 */
	public int compareNodes(Node<Object> o) {
		// TODO Auto-generated method stub
		String swapElement1 = (String) this.element;
		String swapElement2 = (String) o.element;
		if (swapElement1.compareTo(swapElement2) > 0) {
			return 1;
		} else if (swapElement1.compareTo(swapElement2) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}
