
import java.util.Iterator;
public interface StackWithIteratorInterface<T> extends StackInterface<T>,
                                                      Iterable<T>
{
	public Iterator<T> getIterator();
}
