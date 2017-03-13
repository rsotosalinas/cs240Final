import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDictionary<K,V> implements DictionaryInterface<K,V> {
	private Entry<K,V>[] dictionary;
	private int numberOfEntries;
	private boolean initialized = false;
	private final static int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 1000;
	
	public ArrayDictionary()
	{
		this(DEFAULT_CAPACITY);
	}
	public ArrayDictionary(int initialCapacity){
		@SuppressWarnings("unchecked")
		Entry<K,V>[] tempDictionary = (Entry<K,V>[])new Entry[initialCapacity];
		dictionary = tempDictionary;
		numberOfEntries = 0;
		initialized = true;
	}
	private class Entry<S,T>
	{
		private S key;
		private T value;
		private Entry(S searchKey, T dataValue)
		{
			key = searchKey;
			value = dataValue;
		}
		private S getKey()
		{
			return key;
		}
		private T getValue()
		{
			return value;
		}
		private void setValue( T newValue)
		{
			value = newValue;
		}
		
	}

	public V add(K key, V value) {
		if((key == null) || (value == null))
			throw new IllegalArgumentException();
		else 
		{
			V result = null;
			int keyIndex = locateIndex(key);
			if(keyIndex < numberOfEntries)
			{
				result = dictionary[keyIndex].getValue();
				dictionary[keyIndex].setValue(value);
			}
			else 
			{
				dictionary[numberOfEntries] = new Entry<>(key, value);
				numberOfEntries++;
			}
			return result;
		}
	}
	
	private int locateIndex(K key)
	{
		int index = 0 ;
		while((index < numberOfEntries) && 
				!key.equals(dictionary[index].getKey()))
			index++;
		return index;
	}

	public V remove(K key) {
		V result = null;
		int keyIndex = locateIndex(key);
		if(keyIndex < numberOfEntries)
		{
			result = dictionary[keyIndex].getValue();
			dictionary[keyIndex] = dictionary[numberOfEntries-1];
			dictionary[numberOfEntries -1] = null;
			numberOfEntries--;
		}
		return result;
	}

	public V getValue(K key) {
		int keyIndex = locateIndex(key);
		
		return dictionary[keyIndex].getValue();
		
	}

	public boolean contains(K key) 
	{
		boolean found = false;
		int index = 0 ;
		while((index < numberOfEntries) && 
				!key.equals(dictionary[index].getKey())){
			index++;
			if(key.equals(dictionary[index].getKey())){
				found = true;
			}
		}
		return found;
	}

	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	public int getSize() {
		return numberOfEntries;
	}

	public void clear() {
		while(!isEmpty()){
			dictionary[numberOfEntries] = null;
			numberOfEntries--;
		}
	}
	private class IteratorForArrayList<T> implements Iterator<T>
	{
		private int nextIndex;
		private boolean wasNextCalled;

		
		private IteratorForArrayList()  
		{
			nextIndex = 1;
			wasNextCalled= false;
		}
		
		public boolean hasNext()
		{
			return nextIndex <= numberOfEntries;
		}
		

		public T next() {
			if(hasNext())
			{
				wasNextCalled = true;
				T nextEntry= (T) dictionary[nextIndex];
				nextIndex++;
				return nextEntry;
			}
			else
			{
				throw new NoSuchElementException("illegal call to next()");
			}
		}
		public void remove()
		{
			
			
				throw new UnsupportedOperationException() ;
			
		}
		
	}


}
