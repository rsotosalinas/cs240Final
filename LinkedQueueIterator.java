import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;




public class LinkedQueueIterator<T> implements QueueWithIteratorInterface<T>
{
	private Node front;
	private Node back;
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
	public LinkedQueueIterator(){
		front = null;
		back = null;
		numberOfEntries = 0;
	}
	
	public void enqueue(T newEntry){
		Node newNode = new Node(newEntry, null);
		if(isEmpty())
			front  = newNode;
		else
			back.next = newNode;
		back = newNode;
		numberOfEntries++;
	}

    public T dequeue(){
    	T first = getFront();
    //	first = front.data;
    	front.data  = null;
    	front = front.next; 
    	if (front ==null){
    		back = null;
    	}
    	numberOfEntries--;
    	
    	return first;
    }

    public T getFront(){
           if(isEmpty())
        	   throw new EmptyStackException();
           else 
        	   return front.data;
    }

    public boolean isEmpty(){
    	return (front ==null) &&(back == null) ;
    }

   public void clear(){
	   front = null;
	   back = null;
   }
	public T[] lookAll() {
		@SuppressWarnings("unchecked")
		T[] array = (T[])new Object[numberOfEntries];
		int index = 0;
		Node temp = front;
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
			nextNode = back;
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
			dequeue();
		}
	}
}
