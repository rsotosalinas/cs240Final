public class LinkedStackIterator<T> implements StackWithIteratorInterface<T>
{
	private Node topNode;
	private int numberOfEntries = 0;
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
	public void push(T newEntry){
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
		numberOfEntries++;
	}

	   
	public T pop(){
		T first = peek();
		
		   first = topNode.data;
		   topNode = topNode.next;
		   numberOfEntries--;
	   return first;
	}
	 
	public T peek(){
	   if(isEmpty()){
		  throw new EmptyStackException();
	   }
	   else
		   return topNode.data;
	}
	
    public boolean isEmpty(){
    	return topNode==null;
	}
	  
	public void clear(){
		   topNode = null;
	}
	public T[] lookAll() {
		@SuppressWarnings("unchecked")
		T[] array = (T[])new Object[numberOfEntries];
		int index = 0;
		Node temp = topNode;
		while(index < numberOfEntries && temp != null) {
			array[index] = temp.data;
			temp = temp.next;

			index++;
			
		}
			return array;
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
			nextNode = topNode;
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
			System.out.println(pop());
		}
	}
} // end StackInterface
