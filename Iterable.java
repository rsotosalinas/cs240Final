import java.util.Iterator;
public interface Iterable<T> {
	//returns an iterator for collection of objects of type T.
	Iterator<T> iterator();

}
