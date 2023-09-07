import static org.junit.Assert.*;

import org.junit.*;

/*
 * FileDataTest
 * Test FileData class
 */
public class FileDataTest {

	/*
	 * Test FileData constructor
	 */
	@Test
	public void testFileData_Constructor() {
		FileData fileData = new FileData("file.txt", "/home", "05/12/2023");
		assertEquals("file.txt", fileData.name);
		assertEquals("/home", fileData.dir);
		assertEquals("05/12/2023", fileData.lastModifiedDate);
	}
	
	/*
	 * Test FileData toString() method
	 */
	@Test
	public void testFileData_toString() {
		FileData fileData = new FileData("file.txt", "/home", "05/12/2023");
		assertEquals("{Name: file.txt, Directory: /home, Modified Date: 05/12/2023}",
				fileData.toString());
	}
}
