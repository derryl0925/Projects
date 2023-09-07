import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {
	
	/* TODO: Add your own tests */
	/*@Test
	public void dummyTest() {
		
	}*/
	
	private DefaultMap<String, String> testBST; // use this for basic tests
	public static final String TEST_KEY = "Test Key";
	public static final String TEST_VAL = "Test Value";
	
	@Before
	public void setUp() {
		testBST = new BST<String,String>();
	}

	/**
	 * Test put() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPut_nullKey() {
		testBST.put(null, TEST_VAL);
	}

	/**
	 * Test keys() method with non-empty map
	 */
	@Test
	public void testKeys_nonEmptyMap() {
		// You don't have to use array list 
		// This test will work with any object that implements List
		List<String> expectedKeys = new ArrayList<>(5);
		for(int i = 0; i < 5; i++) {
			// key + i is used to differentiate keys since they must be unique
			testBST.put(TEST_KEY + i, TEST_VAL + i);
			expectedKeys.add(TEST_KEY + i);
		}
		List<String> resultKeys = testBST.keys();
		// we need to sort because hash map doesn't guarantee ordering
		Collections.sort(resultKeys);
		assertEquals(expectedKeys, resultKeys);
	}
	
	/**
	 * Test put() method with duplicate
	 */
	@Test
	public void testPut_duplicate() {
		assertTrue(testBST.put(TEST_KEY, TEST_VAL));
		assertFalse(testBST.put(TEST_KEY, TEST_VAL + TEST_VAL));
	}
	
	/**
	 * Test replace() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testReplace_nullKey() {
		testBST.replace(null, TEST_VAL);
	}
	
	/**
	 * Test replace() method
	 */
	@Test
	public void testReplace() {
		assertFalse(testBST.replace(TEST_KEY, TEST_VAL));
		testBST.put(TEST_KEY, TEST_VAL);
		assertTrue(testBST.replace(TEST_KEY, TEST_VAL + TEST_VAL));
		assertEquals(TEST_VAL + TEST_VAL, testBST.get(TEST_KEY));
	}
	
	/**
	 * Test remove() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemove_nullKey() {
		testBST.remove(null);
	}
	
	/**
	 * Test remove() method
	 */
	@Test
	public void testRemove() {
		assertFalse(testBST.remove(TEST_KEY));
		testBST.put(TEST_KEY, TEST_VAL);
		assertTrue(testBST.remove(TEST_KEY));
		assertFalse(testBST.remove(TEST_KEY));
	}
	
	/**
	 * Test set() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSet_nullKey() {
		testBST.set(null, TEST_VAL);
	}
	
	/**
	 * Test set() method
	 */
	@Test
	public void testSet() {
		assertNull(testBST.get(TEST_KEY));
		testBST.set(TEST_KEY, TEST_VAL);
		assertEquals(TEST_VAL, testBST.get(TEST_KEY));
		testBST.set(TEST_KEY, TEST_VAL + TEST_VAL);
		assertEquals(TEST_VAL + TEST_VAL, testBST.get(TEST_KEY));
	}
	
	/**
	 * Test get() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGet_nullKey() {
		testBST.get(null);
	}
	
	/**
	 * Test get() method
	 */
	@Test
	public void testGet() {
		assertNull(testBST.get(TEST_KEY));
		testBST.put(TEST_KEY, TEST_VAL);
		assertEquals(TEST_VAL, testBST.get(TEST_KEY));
	}
	
	/**
	 * Test size() method on non-empty map
	 */
	@Test
	public void testSize_nonEmptyMap() {
		for (int i = 0; i < 5; i++) {
			assertEquals(i, testBST.size());
			testBST.put(TEST_KEY + i, TEST_VAL + i);
			assertEquals(i + 1, testBST.size());
		}
	}
	
	/**
	 * Test isEmpty() method on non-empty map
	 */
	@Test
	public void testIsEmpty_nonEmptyMap() {
		assertTrue(testBST.isEmpty());
		for (int i = 0; i < 5; i++) {
			testBST.put(TEST_KEY + i, TEST_VAL + i);
			assertFalse(testBST.isEmpty());
		}
	}
	
	/**
	 * Test containsKey() method with null key
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testContainsKey_nullKey() {
		testBST.containsKey(null);
	}
	
	/**
	 * Test containsKey() method on non-empty map
	 */
	@Test
	public void testContainsKey_nonEmptyMap() {
		for (int i = 0; i < 5; i++) {
			assertFalse(testBST.containsKey(TEST_KEY + i));
			testBST.put(TEST_KEY + i, TEST_VAL + i);
			assertTrue(testBST.containsKey(TEST_KEY + i));
		}
	}
	
	/**
	 * Test keys() method on empty map
	 */
	@Test
	public void testKeys_empty() {
		assertEquals(testBST.keys(), new ArrayList<>(0));
	}
}
