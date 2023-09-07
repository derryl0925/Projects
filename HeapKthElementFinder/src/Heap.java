import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

/*
 * Heap class
 * 
 * @param <K> key type
 * @param <V> value type
 */
public class Heap<K, V> implements PriorityQueue<K, V> {
	
	public List<Entry<K, V>> entries;
	public Comparator<K> comparator;
	
	/*
	 * Heap constructor
	 * @param comparator of Heap
	 */
	public Heap(Comparator<K> comparator) {
		this.entries = new ArrayList<Entry<K, V>>();
		this.comparator = comparator;
	}

	/*
	 * Method to add key, value to Heap
	 * @param k key
	 * @param v value
	 */
	@Override
	public void add(K k, V v) {
		entries.add(new Entry<K, V>(k, v));
		bubbleUp(entries.size() - 1);
	}

	/*
	 * Method to poll Heap
	 * @return entry at root of Heap
	 */
	@Override
	public Entry<K, V> poll() {
		if(isEmpty()) {
			throw new NoSuchElementException("Error: Heap is empty");
		}
		Entry<K, V> result = entries.remove(0);
		if(!entries.isEmpty()) {
			entries.add(0, entries.remove(entries.size() - 1));
			bubbleDown(0);
		}
		return result;
	}

	/*
	 * Method to peek Heap
	 * @return entry at root of Heap
	 */
	@Override
	public Entry<K, V> peek() {
		if(isEmpty()) {
			throw new NoSuchElementException("Error: Heap is empty");
		}
		return entries.get(0);
	}

	/*
	 * Method to get array of entries
	 * @return array of entries
	 */
	@Override
	public List<Entry<K, V>>toArray() {
		List<Entry<K, V>> array = new ArrayList<Entry<K, V>>(entries);
		return array;
	}

	/*
	 * Method to get if Heap is empty
	 * @return if Heap is empty
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/*
	 * Method to get parent index
	 * @param index to start
	 * @return parent index
	 */
	public int parent(int index) {
		if(index == 0) {
			return -1;
		} else {
			return (index - 1) / 2;
		}
	}
	
	/*
	 * Method to get left child index
	 * @param index to start
	 * @return left child index
	 */
	public int left(int index) {
		return index * 2 + 1;
	}
	
	/*
	 * Method to get right child index
	 * @param index to start
	 * @return right child index
	 */
	public int right(int index) {
		return index * 2 + 2;
	}
	
	/*
	 * Method to swap values at indices
	 * @param i1 first index
	 * @param i2 second index
	 */
	public void swap(int i1, int i2) {
		Entry<K, V> temp = entries.get(i1);
		entries.set(i1, entries.get(i2));
		entries.set(i2, temp);
	}
	
	/*
	 * Method to recursively bubble up from index
	 * @param index to start
	 */
	public void bubbleUp(int index) {
		if(existsAndGreater(index, parent(index))) {
			swap(index, parent(index));
			bubbleUp(parent(index));
		}
	}
	
	/*
	 * Method to recursively bubble down from index
	 * @param index to start
	 */
	public void bubbleDown(int index) {
		if(existsAndGreater(left(index), index) &&
				!existsAndGreater(right(index), left(index))) {
			swap(index, left(index));
			bubbleDown(left(index));
		} else if (existsAndGreater(right(index), index)) {
			swap(index, right(index));
			bubbleDown(right(index));
		}
		
	}
	
	/*
	 * Method to get if value exists and is greater between indices
	 * @param index1 first index
	 * @param index2 second index
	 * @return if value exists and is greater between indices
	 */
	public boolean existsAndGreater(int index1, int index2) {
		return index1 >= 0 && index1 < entries.size() &&
				index2 >= 0 && index2 < entries.size() &&
				comparator.compare(entries.get(index1).getKey(),
						entries.get(index2).getKey()) > 0;
	}

	/*
	 * Method to get size of Heap
	 * @return size of Heap
	 */
	public int size() {
		return entries.size();
	}
	
	/*
	 * Method to get String representation of entries
	 * @return String representation of entries
	 */
	public String toString() {
		return toArray().toString();
	}
}
