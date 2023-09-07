public class FileData {

    public String name;
    public String dir;
    public String lastModifiedDate;

    // TODO
    /*
     * FileData constructor
     * @param name of file
     * @param directory of file
     * @param modifiedDate of file
     */
    public FileData(String name, String directory, String modifiedDate) {
    	this.name = name;
    	this.dir = directory;
    	this.lastModifiedDate = modifiedDate;
    }

    // TODO
    /*
     * Method to get String representation
     * @return String representation
     */
    public String toString() {
    	return "{Name: " + name + ", Directory: " + dir +
    			", Modified Date: " + lastModifiedDate + "}";
    }
}