import static org.junit.Assert.*;

import org.junit.*;

/*
 * FileSystemTest
 * Test FileSystem class
 */
public class FileSystemTest {

private FileSystem fileSystem;
	
	@Before
	public void setUp() {
		fileSystem = new FileSystem("input.txt");
	}
	
	/*
	 * Test add() method of FileSystem with duplicate (version 1)
	 */
	@Test
	public void testAddDuplicate1() {
		FileSystem fileSystem = new FileSystem();
		fileSystem.add("test.txt", "/home", "2021/01/01");
		assertEquals("{Name: test.txt, Directory: /home, Modified Date: 2021/01/01}",
				fileSystem.nameTree.get("test.txt").toString());
		fileSystem.add("test.txt", "/home", "2021/02/01");
		assertEquals("{Name: test.txt, Directory: /home, Modified Date: 2021/02/01}",
				fileSystem.nameTree.get("test.txt").toString());
		fileSystem.add("test.txt", "/home", "2021/01/01");
		assertEquals("{Name: test.txt, Directory: /home, Modified Date: 2021/02/01}",
				fileSystem.nameTree.get("test.txt").toString());
	}
	
	/*
	 * Test add() method of FileSystem with duplicate (version 2)
	 */
	@Test
	public void testAddDuplicate2() {
		FileSystem fileSystem = new FileSystem();
		fileSystem.add("mySample1.txt", "/root", "2021/02/01");
		assertEquals("{Name: mySample1.txt, Directory: /root, Modified Date: 2021/02/01}",
				fileSystem.nameTree.get("mySample1.txt").toString());
		fileSystem.add("mySample1.txt", "/root", "2023/02/01");
		assertEquals("{Name: mySample1.txt, Directory: /root, Modified Date: 2023/02/01}",
				fileSystem.nameTree.get("mySample1.txt").toString());
		fileSystem.add("mySample1.txt", "/root", "2021/02/01");
		assertEquals("{Name: mySample1.txt, Directory: /root, Modified Date: 2023/02/01}",
				fileSystem.nameTree.get("mySample1.txt").toString());
	}
	
	/*
	 * Test findFileNamesByDate() method of FileSystem
	 */
	@Test
	public void testFindFileNamesByDate() {
		assertEquals(2, fileSystem.findFileNamesByDate("2021/02/01").size());
		assertEquals("mySample.txt", fileSystem.findFileNamesByDate("2021/02/01").get(0));
		assertEquals("mySample1.txt", fileSystem.findFileNamesByDate("2021/02/01").get(1));
		assertEquals(1, fileSystem.findFileNamesByDate("2021/02/06").size());
		assertEquals("mySample2.txt", fileSystem.findFileNamesByDate("2021/02/06").get(0));
		
	}
	
	/*
	 * Test findFileNamesByDate() method of FileSystem with null date
	 */
	@Test
	public void testFindFileNamesByDateNull() {
		assertNull(fileSystem.findFileNamesByDate(null));
	}
	
	/*
	 * Test filter() method of FileSystem with start and end dates
	 */
	@Test
	public void testFilterDate() {
		assertEquals(0, fileSystem.filter("2021/01/31", "2021/02/01").nameTree.size());
		assertEquals(2, fileSystem.filter("2021/02/01", "2021/02/02").nameTree.size());
		assertEquals(3, fileSystem.filter("2021/02/01", "2021/02/07").nameTree.size());
		assertEquals(1, fileSystem.filter("2021/02/06", "2021/02/07").nameTree.size());
		assertEquals(0, fileSystem.filter("2021/02/07", "2021/02/08").nameTree.size());
	}
	
	/*
	 * Test filter() method of FileSystem with wildcard of name
	 */
	@Test
	public void testFilterWildcard() {
		assertEquals(3, fileSystem.filter("mySample").nameTree.size());
		assertEquals(1, fileSystem.filter("mySample1").nameTree.size());
		assertEquals(0, fileSystem.filter("mySomple").nameTree.size());
	}
	
	/*
	 * Test outputNameTree() method of FileSystem
	 */
	@Test
	public void testOutputNameTree() {
		assertEquals(3, fileSystem.outputNameTree().size());
		assertEquals("mySample.txt: {Name: mySample.txt, Directory: /home, Modified Date: 2021/02/01}",
				fileSystem.outputNameTree().get(0));
		assertEquals("mySample1.txt: {Name: mySample1.txt, Directory: /root, Modified Date: 2021/02/01}",
				fileSystem.outputNameTree().get(1));
		assertEquals("mySample2.txt: {Name: mySample2.txt, Directory: /user, Modified Date: 2021/02/06}",
				fileSystem.outputNameTree().get(2));
		
	}
	
	/*
	 * Test outputDateTree() method of FileSystem
	 */
	@Test
	public void testOutputDateTree() {
		assertEquals(3, fileSystem.outputDateTree().size());
		assertEquals("2021/02/06: {Name: mySample2.txt, Directory: /user, Modified Date: 2021/02/06}",
				fileSystem.outputDateTree().get(0));
		assertEquals("2021/02/01: {Name: mySample1.txt, Directory: /root, Modified Date: 2021/02/01}",
				fileSystem.outputDateTree().get(1));
		assertEquals("2021/02/01: {Name: mySample.txt, Directory: /home, Modified Date: 2021/02/01}",
				fileSystem.outputDateTree().get(2));
		
	}
}