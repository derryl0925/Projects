import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * FileSystem class
 * This class implements a file system structure
 */
public class FileSystem {

    MyHashMap<String, ArrayList<FileData>> nameMap;
    MyHashMap<String, ArrayList<FileData>> dateMap;

    // can do /** instead of /* to make @param and @return highlight a diff color!
    
    // TODO
    /*
     * FileSystem constructor
     */
    public FileSystem() {
    	nameMap = new MyHashMap<String, ArrayList<FileData>>();
    	dateMap = new MyHashMap<String, ArrayList<FileData>>();
    }

    // TODO
    /*
     * FileSystem constructor with input file parameter
     * @param inputFile to load files from
     */
    public FileSystem(String inputFile) {
        // Add your code here
    	nameMap = new MyHashMap<String, ArrayList<FileData>>();
    	dateMap = new MyHashMap<String, ArrayList<FileData>>();
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                // Add your code here
                if(data.length == 3) {
                	String fileName = data[0];
                	String directory = data[1];
                	String modifiedDate = data[2];
                	add(fileName, directory, modifiedDate);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    // TODO
    /*
     * Method to add file to FileSystem
     * @param fileName of file
     * @param directory of file
     * @param modifiedDate of file
     * @return boolean if file added to FileSystem
     */
    public boolean add(String fileName, String directory, String modifiedDate) {
    	if(fileName == null) { 
    		fileName = "";
    	}
    	if(directory == null) {
    		directory = "/";
    	}
    	if(modifiedDate == null) {
    		modifiedDate = "01/01/2021";
    	}
    	if(!nameMap.containsKey(fileName)) {
    		nameMap.put(fileName, new ArrayList<FileData>());
    	}
    	if(!dateMap.containsKey(modifiedDate)) {
    		dateMap.put(modifiedDate, new ArrayList<FileData>());
    	}
    	for (FileData fileData: nameMap.get(fileName)) {
    		if(fileData.name.equals(fileName) && fileData.dir.equals(directory)) {
    			return false;
    		}
    	}
    	FileData fileData = new FileData(fileName, directory, modifiedDate);
    	nameMap.get(fileName).add(fileData);
    	dateMap.get(modifiedDate).add(fileData);
    	return true;
    }

    // TODO
    /*
     * Method to find file in FileSystem
     * @param name of file
     * @param directory of file
     * @return file in FileSystem
     */
    public FileData findFile(String name, String directory) {
    	if(!nameMap.containsKey(name)) {
    		return null;
    	}
    	for (FileData fileData: nameMap.get(name)) {
    		if(fileData.name.equals(name) && fileData.dir.equals(directory)) {
    			return fileData;
    		}
    	}
    	return null;
    }

    // TODO
    /*
     * Method to get list of all filenames in FileSystem
     * @return list of all filenames in FileSystem
     */
    public ArrayList<String> findAllFilesName() {
    	ArrayList<String> names = new ArrayList<String>();
    	for (String name: nameMap.keys()) {
    		if(!nameMap.get(name).isEmpty()) {
    			names.add(name);
    		}
    	}
    	return names;
    }

    // TODO
    /*
     * Method to get list of all files in FileSystem with name
     * @param name of files
     * @return list of all files in FileSystem with name
     */
    public ArrayList<FileData> findFilesByName(String name) {
    	if(!nameMap.containsKey(name)) {
    		return new ArrayList<FileData>();
    	}
    	return nameMap.get(name);
    }

    // TODO
    /*
     * Method to get list of all files in FileSystem with modifiedDate
     * @param modifiedDate of files
     * @return list of all files in FileSystem with modifiedDate
     */
    public ArrayList<FileData> findFilesByDate(String modifiedDate) {
    	if(!dateMap.containsKey(modifiedDate)) {
    		return new ArrayList<FileData>();
    	}
    	return dateMap.get(modifiedDate);
    }

    // TODO
    /*
     * Method to get list of all multiple files in FileSystem with modifiedDate
     * @param modifiedDate of files
     * @return list of all multiple files in FileSystem with modifiedDate
     */
    public ArrayList<FileData> findFilesInMultDir(String modifiedDate) {
    	if(!dateMap.containsKey(modifiedDate)) {
    		return new ArrayList<FileData>();
    	}
    	ArrayList<FileData> files = new ArrayList<FileData>();
    	for (FileData fileData: dateMap.get(modifiedDate)) {
    		if(nameMap.get(fileData.name).size() > 1) {
    			files.add(fileData);
    		}
    	}
    	return files;
    }

    /*
     * Method to remove all files by name
     * @param name of files
     * @return if files are removed
     */
    public boolean removeByName(String name) {
    	if(!nameMap.containsKey(name)) {
    		return false;
    	}
    	for (FileData fileData: nameMap.get(name)) {
    		dateMap.get(fileData.lastModifiedDate).remove(fileData);
    	}
    	nameMap.remove(name);
    	return true;
    }

    // TODO
    /*
     * Method to remove file with name and directory
     * @param name of file
     * @param directory of file
     * @return if file removed
     */
    public boolean removeFile(String name, String directory) {
    	if(!nameMap.containsKey(name)) {
    		return false;
    	}
    	for (FileData fileData: nameMap.get(name)) {
    		if(fileData.dir.equals(directory)) {
    			dateMap.get(fileData.lastModifiedDate).remove(fileData);
    			nameMap.get(name).remove(fileData);
    			return true;
    		}
    	}
    	return false;
    }

}
