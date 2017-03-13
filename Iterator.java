public interface Iterator<T> {
	 /** Detects whether there is something next to it afterwards.
    @return  True if it is not empty, or false otherwise. */
   public boolean hasNext();
   /**  Retrieves the entry that is is next.
   @return  The object where the next is at. */
   public T next();
   /** Removes entry where next is at. */
   public void remove();
}
