import java.util.ArrayList;
import java.util.List;

/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */
public class BST<K extends Comparable<? super K>, V> implements DefaultMap<K, V> {
	
	private Node<K,V> root = null;
	private int size = 0;

	/**
	 * Adds the specified key, value pair to this BST
	 * Note: duplicate keys are not allowed
	 * @return true if the key value pair was added to this BST
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException("Error: key is null");
		}
		if(size == 0) {
			root = new Node<K,V>(key, value);
			size++;
			return true;
		} else {
			Node<K,V> current = root;
			while (true) {
				if(key.compareTo(current.getKey()) < 0) {
					if(current.left == null) {
						current.left = new Node<K, V>(key, value);
						size++;
						return true;
					} else {
						current = current.left;
					}
				} else if(key.compareTo(current.getKey()) > 0) {
					if(current.right == null) {
						current.right = new Node<K, V>(key, value);
						size++;
						return true;
					} else {
						current = current.right;
					}
				} else {
					return false;
				}
			}
		}
	}

	/**
	 * Replaces the value that maps to the key if it is present
	 * @param key The key whose mapped value is being replaced
	 * @param newValue The value to replace the existing value with
	 * @return true if the key was in this MyHashMap
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException("Error: key is null");
		}
		if (size == 0) {
			return false;
		} else {
			Node<K,V> current = root;
			while (true) {
				if (key.compareTo(current.getKey()) < 0) {
					if (current.left == null)
						return false;
					else
						current = current.left;
				} else if (key.compareTo(current.getKey()) > 0) {
					if (current.right == null)
						return false;
					else
						current = current.right;
				} else {
					current.setValue(newValue);
					return true;
				}
			}
		}
	}

	/**
	 * Remove the entry corresponding to the given key
	 * @return true if an entry for the given key was removed
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		if(key == null)
			throw new IllegalArgumentException("Error: key is null");
		if (!containsKey(key))
			return false;
		root = removeRecursive(root, key);
		size--;
		return true;
	}
	
	/**
	 * Remove the entry below node corresponding to the given key, recursive
	 * @return true if an entry for the given key was removed
	 * @throws IllegalArgument exception if the key is null
	 */
	private Node<K,V> removeRecursive(Node<K,V> node, K key) {
		if(node == null) {
			return node;
		}
		if(key.compareTo(node.getKey()) < 0) {
			node.left = removeRecursive(node.left, key);
		} else if (key.compareTo(node.getKey()) > 0) {
			node.right = removeRecursive(node.right, key);
		} else {
			if (node.left == null)
				return node.right;
			else if (node.right == null)
				return node.left;
			K minKey = getMinKey(node.right);
			node.value = get(minKey);
			node.key = minKey;
			node.right = removeRecursive(node.right, minKey);
		}
		return node;
	}
	
	/**
	 * Get minimum key below node
	 * @return minimum key below node
	 */
	private K getMinKey(Node<K,V> node) {
		K minKey = node.getKey();
		while (node.left != null) {
			minKey = node.left.getKey();
			node = node.left;
		}
		return minKey;
	}

	/**
	 * Adds the key, value pair to this BST if it is not present,
	 * otherwise, replaces the value with the given value
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		if(containsKey(key)) {
			replace(key, value);
		} else {
			put(key, value);
		}
	}

	/**
	 * Get value corresponding to key in BST
	 * @return the value corresponding to the specified key, null if key doesn't 
	 * exist in BST
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException("Error: key is null");
		}
		if(size == 0) {
			return null;
		} else {
			Node<K,V> current = root;
			while (true) {
				if (key.compareTo(current.getKey()) < 0) {
					if (current.left == null)
						return null;
					else
						current = current.left;
				} else if (key.compareTo(current.getKey()) > 0) {
					if (current.right == null)
						return null;
					else
						current = current.right;
				} else {
					return current.getValue();
				}
			}
		}
	}

	/**
	 * Get size of BST
	 * @return The number of (key, value) pairs in this BST
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Get if BST is empty
	 * @return true iff this.size() == 0 is true
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Get if BST contains key
	 * @return true if the specified key is in this BST
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		if(key == null)
			throw new IllegalArgumentException("Error: key is null");
		if(size == 0) {
			return false;
		} else {
			Node<K,V> current = root;
			while (true) {
				if (key.compareTo(current.getKey()) < 0) {
					if (current.left == null)
						return false;
					else
						current = current.left;
				} else if (key.compareTo(current.getKey()) > 0) {
					if (current.right == null)
						return false;
					else
						current = current.right;
				} else {
					return true;
				}
			}
		}
	}

	/**
	 * Get keys of BST
	 * @return an array containing the keys of this MyHashMap. If this MyHashMap is
	 * empty, returns array of length zero. 
	 */
	// Keys must be in ascending sorted order
	// You CANNOT use Collections.sort() or any other sorting implementations
	// You must do in order traversal of the tree
	@Override
	public List<K> keys() {
		List<K> keys = new ArrayList<K>();
		keysRecursive(root, keys);
		return keys;
	}
	
	private void keysRecursive(Node<K, V> node, List<K> keys) {
		if (node != null) {
			keysRecursive(node.left, keys);
			keys.add(node.getKey());
			keysRecursive(node.right, keys);
		}
	}
	
	private static class Node<K extends Comparable<? super K>, V> 
								implements DefaultMap.Entry<K, V> {
		private K key;
		private V value;
		private Node<K, V> left;
		private Node<K, V> right;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public void setValue(V value) {
			this.value = value;
		}
		
		
	}
	 
}