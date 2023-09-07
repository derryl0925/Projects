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
	
	/**
	 * Test findFile() method of FileSystem
	 */
	@Test
	public void testFindFile() {
		assertNull(fileSystem.findFile("", ""));
		assertNull(fileSystem.findFile("mySample.txt", ""));
		assertNull(fileSystem.findFile("", "/home"));
		assertEquals("{Name: mySample.txt, Directory: /home, Modified Date: 02/01/2021}",
				fileSystem.findFile("mySample.txt", "/home").toString());
		assertEquals("{Name: mySample.txt, Directory: /root, Modified Date: 02/01/2021}",
				fileSystem.findFile("mySample.txt", "/root").toString());
		assertEquals("{Name: mySample.txt, Directory: /user, Modified Date: 02/06/2021}",
				fileSystem.findFile("mySample.txt", "/user").toString());
	}

	/**
	 * Test findAllFilesName() method of FileSystem
	 */
	@Test
	public void testFindAllFilesName() {
		assertEquals(1, fileSystem.findAllFilesName().size());
		assertEquals("mySample.txt", fileSystem.findAllFilesName().get(0));
	}
	
	/**
	 * Test findFilesByDate() method of FileSystem
	 */
	@Test
	public void testFindFilesByDate() {
		assertEquals(0, fileSystem.findFilesByDate("02/02/2021").size());
		assertEquals(1, fileSystem.findFilesByDate("02/06/2021").size());
		assertEquals("{Name: mySample.txt, Directory: /user, Modified Date: 02/06/2021}",
				fileSystem.findFilesByDate("02/06/2021").get(0).toString());
		assertEquals(2, fileSystem.findFilesByDate("02/01/2021").size());
		assertEquals("{Name: mySample.txt, Directory: /home, Modified Date: 02/01/2021}",
				fileSystem.findFilesByDate("02/01/2021").get(0).toString());
		assertEquals("{Name: mySample.txt, Directory: /root, Modified Date: 02/01/2021}",
				fileSystem.findFilesByDate("02/01/2021").get(1).toString());
	}
	
	/**
	 * Test findFilesInMultDir() method of FileSystem
	 */
	@Test
	public void testFindFilesInMultDir() {
		fileSystem.add("mySample2.txt", "/home", "02/06/2021");
		assertEquals(0, fileSystem.findFilesInMultDir("02/02/2021").size());
		assertEquals(1, fileSystem.findFilesInMultDir("02/06/2021").size());
		assertEquals("{Name: mySample.txt, Directory: /user, Modified Date: 02/06/2021}",
				fileSystem.findFilesInMultDir("02/06/2021").get(0).toString());
		assertEquals(2, fileSystem.findFilesInMultDir("02/01/2021").size());
		assertEquals("{Name: mySample.txt, Directory: /home, Modified Date: 02/01/2021}",
				fileSystem.findFilesInMultDir("02/01/2021").get(0).toString());
		assertEquals("{Name: mySample.txt, Directory: /root, Modified Date: 02/01/2021}",
				fileSystem.findFilesInMultDir("02/01/2021").get(1).toString());
	}
	
	/**
	 * Test removeByName() method of FileSystem
	 */
	@Test
	public void testRemoveByName() {
		assertFalse(fileSystem.removeByName("mySample2.txt"));
		assertEquals(1, fileSystem.findAllFilesName().size());
		assertTrue(fileSystem.removeByName("mySample.txt"));
		assertEquals(0, fileSystem.findAllFilesName().size());
	}
	
	/**
	 * Test removeFile() method of FileSystem
	 */
	@Test
	public void testRemoveFile() {
		assertFalse(fileSystem.removeFile("mySample.txt", ""));
		assertEquals(1, fileSystem.findAllFilesName().size());
		assertTrue(fileSystem.removeFile("mySample.txt", "/home"));
		assertEquals(1, fileSystem.findAllFilesName().size());
		assertTrue(fileSystem.removeFile("mySample.txt", "/root"));
		assertEquals(1, fileSystem.findAllFilesName().size());
		assertTrue(fileSystem.removeFile("mySample.txt", "/user"));
		assertEquals(0, fileSystem.findAllFilesName().size());
	}
}
