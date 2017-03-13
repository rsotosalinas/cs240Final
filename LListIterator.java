import java.util.Iterator;
import java.util.NoSuchElementException;

public class LListIterator<T> implements ListWithIteratorInterface<T> { 
	private Node firstNode;
	private Node lastNode;
	private int numberOfEntries;
	
	private class Node{
		private T data; 
		private Node next;
		
		private Node(T data){
			this(data, null);
		}
		private Node(T data, Node nextNode){
			this.data = data;
			next= nextNode;
		}
	}
	public LListIterator(){
		firstNode = null;
		numberOfEntries = 0;
	}

	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		if(isEmpty())
		   firstNode = newNode;
		else{
			lastNode = getNodeAt(numberOfEntries);
			lastNode.next = newNode;
		}
		numberOfEntries++;
		
	}

	public void add(int newPosition, T newEntry) {
		if((newPosition >= 1) && (newPosition <= numberOfEntries)){
		    Node newNode = new Node(newEntry);
		    if(newPosition ==1){
		    	newNode.next = firstNode;
		    	firstNode = newNode;
		    }
		    else{
		    	Node nodeBefore = getNodeAt(newPosition -1);
		    	Node nodeAfter = nodeBefore.next;
		    	newNode.next = nodeAfter;
		    	nodeBefore.next = newNode;
		    	
		    }
		    numberOfEntries++;

		}
		else
			throw new IndexOutOfBoundsException(
		    		"illegal position given");
	}

	public T remove(int givenPosition) {
		T result = null;
		if((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
			assert !isEmpty();
			if(givenPosition == 1){
				result = firstNode.data;
				firstNode = firstNode.next;
			}
			else {
				Node nodeBefore = getNodeAt(givenPosition -1);
				Node nodeToRemove = nodeBefore.next;
				result = nodeToRemove.data;
				Node nodeAfter = nodeToRemove.next;
				nodeBefore.next = nodeAfter;
			}
			numberOfEntries--;
			return result;
		}
		else
			throw new IndexOutOfBoundsException(
		    		"illegal position given");

	}

	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
		
	}

	public T replace(int givenPosition, T newEntry) {
		if((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
			assert !isEmpty();
			Node desiredNode = getNodeAt(givenPosition);
			T originalEntry = desiredNode.data;
			desiredNode.data = newEntry;
			return originalEntry;
			
		}
		else
			throw new IndexOutOfBoundsException(
		    		"illegal position given");
	}

	public T getEntry(int givenPosition) {
		if((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
			assert !isEmpty();
			return getNodeAt(givenPosition).data;
		}
		else
			throw new IndexOutOfBoundsException(
		    		"illegal position given");
	}

	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] list = (T[])new Object[numberOfEntries];
		int index = 0;
		Node temp = firstNode;
		while(index < numberOfEntries && temp != null){
			list[index] = temp.data;
			temp = temp.next;
			index++;
		}
		return list;
	}

	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode= firstNode;
		while(!found && currentNode!= null){
			if(anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		}
		return false;
	}

	public int getLength() {
		return numberOfEntries;
	}

	public boolean isEmpty() {
		
		return numberOfEntries == 0;
	}
	private Node getNodeAt(int getPosition){
		assert (1 <= getPosition && getPosition <= numberOfEntries);
		Node temp = firstNode;
		int index = 1;
		while(index < getPosition){
			temp = temp.next;
			index++;
		}
		
		return temp;
	}
    public Iterator<T> iterator()
    {
    	return new IteratorForLinkedList();
    }


	public Iterator<T> getIterator() {
		return iterator();
	}
	private class IteratorForLinkedList implements Iterator<T>
	{
		private Node nextNode;
		int number;
		private IteratorForLinkedList()
		{
			nextNode = firstNode;
			number = 1;
			
		}
		public boolean hasNext() 
		{
			return nextNode != null;
		}
		public T next() 
		{
			if(hasNext())
			{
				Node returnNode = nextNode;
				nextNode = nextNode.next;
				number++;
				return returnNode.data;
				
			}
			else
			{
				throw new NoSuchElementException("Illegal call to next");
			}
		}
		public void remove(){
			T data;
			data = LListIterator.this.remove(number);
			System.out.println(data);
		}
	}

}
