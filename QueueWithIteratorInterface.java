
import java.util.Iterator;
public interface QueueWithIteratorInterface<T> extends InterfaceQueue<T>,
                                                      Iterable<T>
{
	public Iterator<T> getIterator();
}
