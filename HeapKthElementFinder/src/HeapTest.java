// import static org.junit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

/*
 * HeapTest class implements tester that will test the methods from heap file
 */
public class HeapTest {

	/*
	 * Test add method
	 */
	@Test
	public void testAdd() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		assertEquals(8, heap.entries.size());
	}
	
	/*
	 * Test poll method
	 */
	@Test
	public void testPoll() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		assertEquals(50, (int) heap.poll().getKey());
		assertEquals(30, (int) heap.poll().getKey());
		assertEquals(20, (int) heap.poll().getKey());
		assertEquals(19, (int) heap.poll().getKey());
		assertEquals(15, (int) heap.poll().getKey());
		assertEquals(10, (int) heap.poll().getKey());
		assertEquals(5, (int) heap.poll().getKey());
		assertEquals(2, (int) heap.poll().getKey());
	}
	
	/*
	 * Test peek method
	 */
	@Test
	public void testPeek() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		assertEquals(19, (int) heap.peek().getKey());
		heap.add(50, "10");
		assertEquals(50, (int) heap.peek().getKey());
		heap.add(30, "10");
		assertEquals(50, (int) heap.peek().getKey());
		heap.add(15, "10");
		assertEquals(50, (int) heap.peek().getKey());
		heap.add(20, "10");
		assertEquals(50, (int) heap.peek().getKey());
		heap.add(10, "10");
		assertEquals(50, (int) heap.peek().getKey());
		heap.add(5, "");
		assertEquals(50, (int) heap.peek().getKey());
		heap.add(2, "");
		assertEquals(50, (int) heap.peek().getKey());
	}
	
	/*
	 * Test toArray method
	 */
	@Test
	public void testToArray() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		assertEquals(50, (int) heap.toArray().get(0).getKey());
		assertEquals(20, (int) heap.toArray().get(1).getKey());
		assertEquals(30, (int) heap.toArray().get(2).getKey());
		assertEquals(15, (int) heap.toArray().get(3).getKey());
		assertEquals(19, (int) heap.toArray().get(4).getKey());
		assertEquals(10, (int) heap.toArray().get(5).getKey());
		assertEquals(5, (int) heap.toArray().get(6).getKey());
		assertEquals(2, (int) heap.toArray().get(7).getKey());
	}
	
	/*
	 * Test isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		assertTrue(heap.isEmpty());
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		for (int i = 0; i < 8; i++) {
			assertFalse(heap.isEmpty());
			heap.poll();
		}
		assertTrue(heap.isEmpty());
	}

}