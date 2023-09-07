/*
 * Entry class
 *
 * @param <K> key type
 * @param <V> value type
 */
public class Entry<K, V> {
	K key; // aka the priority
	V value;

	/*
	 * Entry constructor
	 * @param k key
	 * @param v value
	 */
	public Entry(K k, V v) {
		this.key = k;
		this.value = v;
	}

	/*
	 * Method to get String representation
	 * @return String representation
	 */
	public String toString() {
		return key + ": " + value;
	}

	/*
	 * Method to get key
	 * @return key
	 */
	public K getKey() {
		return key;
	}
}