import java.util.List;

/*
 * PriorityQueue interface
 *
 * @param <K> key type
 * @param <V> value type
 */
public interface PriorityQueue<K, V> {
	/*
	 * Method to add to PriorityQueue
	 * @param k key
	 * @param v value
	 */
	void add(K k, V v);

	/*
	 * Method to poll PriorityQueue
	 * @return highest priority entry
	 */
	Entry<K, V> poll();

	/*
	 * Method to peek PriorityQueue
	 * @return highest priority entry
	 */
	Entry<K, V> peek();

	/*
	 * Method to get array of entries
	 * @return array of entries
	 */
	List<Entry<K, V>> toArray();

	/*
	 * Method to get if PriorityQueue is empty
	 * @return if PriorityQueue is empty
	 */
	boolean isEmpty();

}
