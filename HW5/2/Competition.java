public interface Competition<E>	{
	// Appends the specified element to this storage.
	// Returns true if the element could be added to this storage
	boolean	add(E e);
	// Returns true if this storage contains the specified element.
	boolean	contains(Object o);	
	// Removes the first occurrence of the specified element in this storage.
	// If the storage does not contain the element, it is unchanged.
	// Returns true if the element could be removed from this storage
	boolean	remove(Object o);	
	// Returns the component at the specified index.
	E elementAt(int index);	
	// Sorts the storage
	// Returns the sorted storage
	Competition<E>	sort();	
	// Returns the number of components in this storage.
	int size();
	//Print all the elements
	public void printNode();
	//For comparisonof object
}