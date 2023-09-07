import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * MyHashMap class
 * This class implements a HashMap data structure
 * @param <K> key type
 * @param <V> value type
 */
public class MyHashMap<K, V> implements DefaultMap<K, V> {
	public static final double DEFAULT_LOAD_FACTOR = 0.75;
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	public static final String ILLEGAL_ARG_CAPACITY = "Initial Capacity must be non-negative";
	public static final String ILLEGAL_ARG_LOAD_FACTOR = "Load Factor must be positive";
	public static final String ILLEGAL_ARG_NULL_KEY = "Keys must be non-null";
	
	private double loadFactor;
	private int capacity;
	private int size;

	// Use this instance variable for Separate Chaining conflict resolution
	private List<HashMapEntry<K, V>>[] buckets;  
	
	// Use this instance variable for Linear Probing
	// private HashMapEntry<K, V>[] entries; 	
	// ^ I'm not going to use linear probing

	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	/*
	 * MyHashMap constructor
	 * @param initialCapacity the initial capacity of this MyHashMap
	 * @param loadFactor the load factor for rehashing this MyHashMap
	 * @throws IllegalArgumentException if initialCapacity is negative or loadFactor not
	 * positive
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, double loadFactor) throws IllegalArgumentException {
		// TODO Finish initializing instance fields
		if(initialCapacity < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARG_CAPACITY);
		}
		if(loadFactor <= 0) {
			throw new IllegalArgumentException(ILLEGAL_ARG_LOAD_FACTOR);
		}
		this.loadFactor = loadFactor;
		this.capacity = initialCapacity;
		this.size = 0;

		// if you use Separate Chaining
		buckets = (List<HashMapEntry<K, V>>[]) new List<?>[capacity];
		for (int i = 0; i < capacity; i++) {
			buckets[i] = new ArrayList<HashMapEntry<K, V>>();
		}

		// if you use Linear Probing
		//entries = (HashMapEntry<K, V>[]) new HashMapEntry<?, ?>[initialCapacity];
		// not using linear probing
	}

	/*
	 * Adds the specified key, value pair to this MyHashMap
	 * Note: duplicate keys are not allowed
	 * 
	 * @return true if the key value pair was added to this MyHashMap
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		// can also use key.hashCode() assuming key is not null
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		if(containsKey(key)) {
			return false;
		}
		int keyHash = Objects.hashCode(key); 
		// TODO Auto-generated method stub
		int index = Math.abs(keyHash) % capacity;
		buckets[index].add(new HashMapEntry<K,V>(key, value));
		size++;
		if(size > capacity * loadFactor) {
			MyHashMap<K, V> map2 = new MyHashMap<K, V>(capacity * 2, loadFactor);
			for (K key2: keys()) {
				map2.put(key2, get(key2));
			}
			capacity = map2.capacity;
			buckets = map2.buckets;
		}
		return true;
	}

	/*
	 * Replaces the value that maps to the key if it is present
	 * @param key The key whose mapped value is being replaced
	 * @param newValue The value to replace the existing value with
	 * @return true if the key was in this MyHashMap
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		if(!containsKey(key)) {
			return false;
		}
		int keyHash = Objects.hashCode(key);
		int index = Math.abs(keyHash) % capacity;
		for (HashMapEntry<K, V> entry: buckets[index]) {
			if(entry.getKey().equals(key)) {
				entry.setValue(newValue);
				break;
			}
		}
		return true;
	}

	/*
	 * Remove the entry corresponding to the given key
	 * 
	 * @return true if an entry for the given key was removed
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		if(!containsKey(key)) {
			return false;
		}
		int keyHash = Objects.hashCode(key);
		int index = Math.abs(keyHash) % capacity;
		for (HashMapEntry<K, V> entry: buckets[index]) {
			if(entry.getKey().equals(key)) {
				buckets[index].remove(entry);
				size--;
				break;
			}
		}
		return true;
	}

	/*
	 * Adds the key, value pair to this MyHashMap if it is not present,
	 * otherwise, replaces the value with the given value
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		if(containsKey(key)) {
			replace(key, value);
		} else {
			put(key, value);
		}
		
	}

	/*
	 * @return the value corresponding to the specified key, null if key doesn't 
	 * exist in hash map
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		V value = null;
		int keyHash = Objects.hashCode(key);
		int index = Math.abs(keyHash) % capacity;
		for (HashMapEntry<K, V> entry: buckets[index]) {
			if(entry.getKey().equals(key)) {
				value = entry.getValue();
			}
		}
		return value;
	}

	/*
	 * @return The number of (key, value) pairs in this MyHashMap
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/*
	 * @return true iff this.size() == 0 is true
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}

	/*
	 * @return true if the specified key is in this MyHashMap
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		int keyHash = Objects.hashCode(key);
		int index = Math.abs(keyHash) % capacity;
		for (HashMapEntry<K, V> entry: buckets[index]) {
			if(entry.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * @return an array containing the keys of this MyHashMap. If this MyHashMap is
	 * empty, returns array of length zero. 
	 */
	@Override
	public List<K> keys() {
		// TODO Auto-generated method stub
		List<K> keys = new ArrayList<K>();
		for (int index = 0; index < capacity; index++) {
			for (HashMapEntry<K, V> entry: buckets[index]) {
				keys.add(entry.getKey());
			}
		}
		return keys;
	}
	
	/*
	 * HashMapEntry inner class
	 * Internal Representation of every (key, value) pair in this MyHashMap
	 *
	 * @param <K> key type
	 * @param <V> value type
	 */
	private static class HashMapEntry<K, V> implements DefaultMap.Entry<K, V> {
		
		K key;
		V value;
		
		/*
		 * HashMapEntry constructor
		 * @param key of entry
		 * @param value of entry
		 */
		private HashMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/*
		 * Method to get key of entry
		 * @return key of entry
		 */
		@Override
		public K getKey() {
			return key;
		}

		/*
		 * Method to get value of entry
		 * @return value of entry
		 */
		@Override
		public V getValue() {
			return value;
		}
		
		/*
		 * Method to set value of entry
		 * @param value of entry
		 */
		@Override
		public void setValue(V value) {
			this.value = value;
		}
	}
}
