package foodManagement;

/**
 * A SortedListOfImmutables represents a sorted collection of immutable objects 
 * that implement the Listable interface.  
 * 
 * An array of references to Listable objects is used internally to represent 
 * the list.  
 * 
 * The items in the list are always kept in alphabetical order based on the 
 * names of the items.  When a new item is added into the list, it is inserted 
 * into the correct position so that the list stays ordered alphabetically
 * by name.
 */
public class SortedListOfImmutables {

	/*
	 * STUDENTS:  You may NOT add any other instance variables or
	 * static variables to this class!
	 */
	private Listable[] items;

	/**
	 * This constructor creates an empty list by creating an internal array
	 * of size 0.  (Note that this is NOT the same thing as setting the internal
	 * instance variable to null.) 
	 */
	public SortedListOfImmutables() {
		this.items = new Listable [0];
	}

	/**
	 *  Copy constructor.  The current object will become a copy of the
	 *  list that the parameter refers to.  
	 *  
	 *  The copy must be made in such a way that future changes to
	 *  either of these two lists will not affect the other. In other words, 
	 *  after this constructor runs, adding or removing things from one of 
	 *  the lists must not have any effect on the other list.
	 *  
	 *  @param other the list that is to be copied
	 */
	public SortedListOfImmutables(SortedListOfImmutables other) {
		items = new Listable [other.getSize()];
		for(int i = 0; i < items.length; i++) {
			this.items[i] = other.items[i];
		}
	}

	/**
	 * Returns the number of items in the list.
	 * @return number of items in the list
	 */
	public int getSize() {
		return items.length;
	}

	/**
	 * Returns a reference to the item in the ith position in the list.  (Indexing
	 * is 0-based, so the first element is element 0).
	 * 
	 * @param i index of item requested
	 * @return reference to the ith item in the list
	 */
	public Listable get(int i) {
		return items[i];
	}

	/**
	 * Adds an item to the list.  This method assumes that the list is already
	 * sorted in alphabetical order based on the names of the items in the list.
	 * 
	 * The new item will be inserted into the list in the appropriate place so
	 * that the list will remain alphabetized by names.
	 * 
	 * In order to accomodate the new item, the internal array must be re-sized 
	 * so that it is one unit larger than it was before the call to this method.
	 *  
	 * @param itemToAdd refers to a Listable item to be added to this list
	 */
	public void add(Listable itemToAdd) {
		boolean empty = false; 
		Listable [] addedArr = new Listable [this.items.length + 1]; 
		for(int i = 0; i < items.length ; i++) { 
			addedArr[i]= items[i]; 
		} 
		if(items.length == 0) { 
			empty = true; 
		} 
		if(empty == true) { 
			addedArr[0] = itemToAdd; 
		}else{ 
			boolean added = false; 
			addedArr[addedArr.length - 1] = itemToAdd; 
			for(int i = 0; i < items.length; i++) { 
				String itemAdd = itemToAdd.toString();   
				String item = items[i].toString(); 
				if(itemAdd.compareTo(item) < 0 && added == false) { 
					addedArr[i] = itemToAdd; 
					added = true;
				}
				if(added == true) {
					addedArr[i + 1] = items [i]; 		
				}
			} 
		} 
		items = addedArr; 
	} 
	/**
	 * Adds an entire list of items to the current list, maintaining the 
	 * alphabetical ordering of the list by the names of the items.
	 * 
	 * @param listToAdd a list of items that are to be added to the current object
	 */
	public void add(SortedListOfImmutables listToAdd) {
		for(int i = 0; i < listToAdd.items.length; i++) {
			this.add(listToAdd.items[i]);
		}
	}

	/**
	 * Removes an item from the list.
	 * 
	 * If the list contains the same item that the parameter refers to, it will be 
	 * removed from the list.  
	 * 
	 * If the item appears in the list more than once, just one instance will be
	 * removed.
	 * 
	 * If the item does not appear on the list, then this method does nothing.
	 * 
	 * @param itemToRemove refers to the item that is to be removed from the list
	 */
	public void remove(Listable itemToRemove) {
		boolean appearsOnList = false;
		String itemRemove = itemToRemove.toString();
		for (int i = 0; i < items.length; i++) {
			String item = items[i].toString();
			if (item.equals(itemRemove)) {
				appearsOnList = true;
			}
		}
		if(appearsOnList == true) {
			boolean removeOnce = false;
			Listable [] remove = new Listable [items.length - 1];
			for(int i = 0; i < remove.length; i++) {
				if(items[i].toString().contentEquals(itemRemove)&& removeOnce == false) {
					remove[i] = items[i + 1];
					removeOnce = true;
				}
				if(removeOnce == true) {
					remove[i] = items[i + 1];
				}
				else { 
					remove[i] = items[i];

				}
			}
			items = remove;
		}
	}

	/**
	 * Removes an entire list of items from the current list.  Any items in the
	 * parameter that appear in the current list are removed; any items in the
	 * parameter that do not appear in the current list are ignored.
	 * 
	 * @param listToRemove list of items that are to be removed from this list
	 */
	public void remove(SortedListOfImmutables listToRemove) {
		for(int i = 0; i < listToRemove.items.length; i++) {
			this.remove(listToRemove.items[i]);
		}
	}

	/**
	 * Returns the sum of the wholesale costs of all items in the list.
	 * 
	 * @return sum of the wholesale costs of all items in the list
	 */
	public int getWholesaleCost() {
		int sum = 0;
		for(int i = 0; i < items.length; i++) {
			sum += items[i].getWholesaleCost();
		}
		return sum;
	}

	/**
	 * Returns the sum of the retail values of all items in the list.
	 * 
	 * @return sum of the retail values of all items in the list
	 */
	public int getRetailValue() {
		int sum = 0; 
		for(int i = 0; i < items.length; i++) {
			sum += items[i].getRetailValue();
		}
		return sum;
	}

	/**
	 * Checks to see if a particular item is in the list.
	 * 
	 * @param itemToFind item to look for
	 * @return true if the item is found in the list, false otherwise
	 */
	public boolean checkAvailability(Listable itemToFind) {
		boolean occursInList = false;
		for(int i = 0; i < items.length; i++) {
			if(items[i].equals(itemToFind)) {
				occursInList = true;
			}
		}
		return occursInList;
	}

	/**
	 * Checks if a list of items is contained in the current list.
	 * (In other words, this method will return true if ALL of the items in 
	 * the parameter are contained in the current list.  If anything is missing,
	 * then the return value will be false.)
	 * 
	 * @param listToCheck list of items that may or may not be a subset of the
	 * current list
	 * @return true if the parameter is a subset of the current list; false 
	 * otherwise
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		SortedListOfImmutables list = new SortedListOfImmutables (this);
		boolean inList = false;
		targetLoop:
			for(int i = 0; i < listToCheck.items.length; i++) {
				if(list.checkAvailability(listToCheck.items[i]) == true) {
					inList = true;
					for(int j = 0; j < list.items.length; j++) {
						if(list.items[j].equals(listToCheck.items[i])) {
							list.remove(list.items[j]);
							continue targetLoop;
						}
					}
				}
				else {
					inList = false;
					break;
				}
			}
		return inList;
	}


	/*
	 * We'll give you this one for free.  Do not modify this method or you
	 * will fail our tests!
	 */
	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
}