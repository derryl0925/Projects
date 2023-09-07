import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.*;

public class MyHashMapTest {
	
	private DefaultMap<String, String> testMap; // use this for basic tests
	private DefaultMap<String, String> mapWithCap; // use for testing proper rehashing
	public static final String TEST_KEY = "Test Key";
	public static final String TEST_VAL = "Test Value";
	
	@Before
	public void setUp() {
		testMap = new MyHashMap<>();
		mapWithCap = new MyHashMap<>(4, MyHashMap.DEFAULT_LOAD_FACTOR);
	}

	/*
	 * Test put() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPut_nullKey() {
		testMap.put(null, TEST_VAL);
	}

	/*
	 * Test keys() method with non-empty map
	 */
	@Test
	public void testKeys_nonEmptyMap() {
		// You don't have to use array list 
		// This test will work with any object that implements List
		List<String> expectedKeys = new ArrayList<>(5);
		for(int i = 0; i < 5; i++) {
			// key + i is used to differentiate keys since they must be unique
			testMap.put(TEST_KEY + i, TEST_VAL + i);
			expectedKeys.add(TEST_KEY + i);
		}
		List<String> resultKeys = testMap.keys();
		// we need to sort because hash map doesn't guarantee ordering
		Collections.sort(resultKeys);
		assertEquals(expectedKeys, resultKeys);
	}
	
	/* Add more of your tests below */
	
	/*
	 * Test keys() method on map with capacity
	 */
	@Test
	public void testKeys_mapWithCap() {
		// You don't have to use array list
		// This test will work with any object that implements List
		List<String> expectedKeys = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			// key + i is used to differentiate keys since they must be unique
			mapWithCap.put(TEST_KEY + i, TEST_VAL + i);
			expectedKeys.add(TEST_KEY + i);
		}
		List<String> resultKeys = mapWithCap.keys();
		// we need to sort because hash map doesn't guarantee ordering
		Collections.sort(resultKeys);
		assertEquals(expectedKeys, resultKeys);
	}
	
	/*
	 * Test put() method with duplicate
	 */
	@Test
	public void testPut_duplicate() {
		assertTrue(testMap.put(TEST_KEY, TEST_VAL));
		assertFalse(testMap.put(TEST_KEY, TEST_VAL + TEST_VAL));
	}
	
	/*
	 * Test constructor with negative initial capacity
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_negativeInitialCapacity() {
		new MyHashMap<>(-1, MyHashMap.DEFAULT_LOAD_FACTOR);
	}
	
	/*
	 * Test constructor with negative load factor
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_negativeLoadFactor() {
		new MyHashMap<>(4, -1);
	}
	
	/*
	 * Test constructor with zero load factor
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor_zeroLoadFactor() {
		new MyHashMap<>(4, 0);
	}
	
	/*
	 * Test replace() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testReplace_nullKey() {
		testMap.replace(null, TEST_VAL);
	}
	
	/*
	 * Test replace() method
	 */
	@Test
	public void testReplace() {
		assertFalse(testMap.replace(TEST_KEY, TEST_VAL));
		testMap.put(TEST_KEY, TEST_VAL);
		assertTrue(testMap.replace(TEST_KEY, TEST_VAL + TEST_VAL));
		assertEquals(TEST_VAL + TEST_VAL, testMap.get(TEST_KEY));
	}
	
	/*
	 * Test remove() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemove_nullKey() {
		testMap.remove(null);
	}
	
	/*
	 * Test remove() method
	 */
	@Test
	public void testRemove() {
		assertFalse(testMap.remove(TEST_KEY));
		testMap.put(TEST_KEY, TEST_VAL);
		assertTrue(testMap.remove(TEST_KEY));
		assertFalse(testMap.remove(TEST_KEY));
	}
	
	/*
	 * Test set() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSet_nullKey() {
		testMap.set(null, TEST_VAL);
	}
	
	/*
	 * Test set() method
	 */
	@Test
	public void testSet() {
		assertNull(testMap.get(TEST_KEY));
		testMap.set(TEST_KEY, TEST_VAL);
		assertEquals(TEST_VAL, testMap.get(TEST_KEY));
		testMap.set(TEST_KEY, TEST_VAL + TEST_VAL);
		assertEquals(TEST_VAL + TEST_VAL, testMap.get(TEST_KEY));
	}
	
	/*
	 * Test get() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGet_nullKey() {
		testMap.get(null);
	}
	
	/*
	 * Test get() method
	 */
	@Test
	public void testGet() {
		assertNull(testMap.get(TEST_KEY));
		testMap.put(TEST_KEY, TEST_VAL);
		assertEquals(TEST_VAL, testMap.get(TEST_KEY));
	}
	
	/*
	 * Test size() method on non-empty map
	 */
	@Test
	public void testSize_nonEmptyMap() {
		for (int i = 0; i < 5; i++) {
			assertEquals(i, testMap.size());
			testMap.put(TEST_KEY + i, TEST_VAL + i);
			assertEquals(i + 1, testMap.size());
		}
	}
	
	/*
	 * Test size() method on map with capacity
	 */
	@Test
	public void testSize_mapWithCap() {
		for (int i = 0; i < 5; i++) {
			assertEquals(i, mapWithCap.size());
			mapWithCap.put(TEST_KEY + i, TEST_VAL + i);
			assertEquals(i + 1, mapWithCap.size());
		}
	}
	
	/*
	 * Test isEmpty() method on non-empty map
	 */
	@Test
	public void testIsEmpty_nonEmptyMap() {
		assertTrue(testMap.isEmpty());
		for (int i = 0; i < 5; i++) {
			testMap.put(TEST_KEY + i, TEST_VAL + i);
			assertFalse(testMap.isEmpty());
		}
	}
	
	/*
	 * Test isEmpty() method on map with capacity
	 */
	@Test
	public void testIsEmpty_mapWithCap() {
		assertTrue(mapWithCap.isEmpty());
		for (int i = 0; i < 5; i++) {
			mapWithCap.put(TEST_KEY + i, TEST_VAL + i);
			assertFalse(mapWithCap.isEmpty());
		}
	}
	
	/*
	 * Test containsKey() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testContainsKey_nullKey() {
		testMap.containsKey(null);
	}
	
	/*
	 * Test containsKey() method on non-empty map
	 */
	@Test
	public void testContainsKey_nonEmptyMap() {
		for (int i = 0; i < 5; i++) {
			assertFalse(testMap.containsKey(TEST_KEY + i));
			testMap.put(TEST_KEY + i, TEST_VAL + i);
			assertTrue(testMap.containsKey(TEST_KEY + i));
		}
	}
	
	/*
	 * Test containsKey() method on map with capacity
	 */
	@Test
	public void testContainsKey_mapWithCap() {
		for (int i = 0; i < 5; i++) {
			assertFalse(mapWithCap.containsKey(TEST_KEY + i));
			mapWithCap.put(TEST_KEY + i, TEST_VAL + i);
			assertTrue(mapWithCap.containsKey(TEST_KEY + i));
		}
	}
	
	/*
	 * Test keys() method on empty map
	 */
	@Test
	public void testKeys_empty() {
		assertEquals(testMap.keys(), new ArrayList<>(0));
	}
	
}