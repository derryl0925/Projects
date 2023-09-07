import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * FileSystem class
 * This class implements a file system structure
 */
public class FileSystem {

    BST<String, FileData> nameTree;
    BST<String, ArrayList<FileData>> dateTree;
    
    // TODO
    /*
     * FileSystem constructor
     */
    public FileSystem() {
    	nameTree = new BST<String, FileData>();
    	dateTree = new BST<String, ArrayList<FileData>>();
    }


    // TODO
    /*
     * FileSystem constructor with input file parameter
     * @param inputFile to load files from
     */
    public FileSystem(String inputFile) {
    	nameTree = new BST<String, FileData>();
    	dateTree = new BST<String, ArrayList<FileData>>();
    	// Add your code here
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                // Add your code here
                if (data.length == 3) {
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


    /*
     * Method to add file to FileSystem
     * @param name of file
     * @param dir of file
     * @param date of file
     */
    public void add(String name, String dir, String date) {
    	if (name == null || dir == null || date == null)
    		return;
    	FileData file = new FileData(name, dir, date);
    	if (nameTree.containsKey(name)) {
    		file = nameTree.get(name);
    		if (date.compareTo(file.lastModifiedDate) == 0)
    			return;
    		nameTree.remove(name);
    		dateTree.get(file.lastModifiedDate).remove(file);
    		file.dir = dir;
    		if (date.compareTo(file.lastModifiedDate) > 0)
    			file.lastModifiedDate = date;
    		date = file.lastModifiedDate;
    	}
    	nameTree.put(name, file);
		if (!dateTree.containsKey(date))
			dateTree.put(date, new ArrayList<FileData>());
		dateTree.get(date).add(file);
    }


    /*
     * Find all file names by date
     * @param date to search
     * @return all file names by date
     */
    public ArrayList<String> findFileNamesByDate(String date) {
    	if (date == null)
    		return null;
    	ArrayList<String> names = new ArrayList<String>();
    	if (dateTree.containsKey(date)) {
    		for (FileData file: dateTree.get(date))
    			names.add(file.name);
    	}
    	return names;
    }


    /*
     * Filter all files from start date to end date
     * @param startDate at beginning of filter
     * @param endDate at end of filter
     * @return FileSystem containing files from start date to end date
     */
    public FileSystem filter(String startDate, String endDate) {
    	FileSystem filtered = new FileSystem();
    	for (String date: dateTree.keys()) {
    		if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) < 0) {
    			for (FileData file: dateTree.get(date))
    				filtered.add(file.name, file.dir, file.lastModifiedDate);
    		}
    	}
    	return filtered;
    }
    
    
    /*
     * Filter all files containing wildcard in name
     * @param wildCard to check
     * @return FileSystem containing files containing wildcard in name
     */
    public FileSystem filter(String wildCard) {
    	FileSystem filtered = new FileSystem();
    	for (String name: nameTree.keys()) {
    		if (name.contains(wildCard))
    			filtered.add(name, nameTree.get(name).dir, nameTree.get(name).lastModifiedDate);
    	}
    	return filtered;
    }
    
    
    /*
     * Output list of names of files
     * @return list of names of files
     */
    public List<String> outputNameTree(){
    	List<String> fileStrings = new ArrayList<String>();
    	for (String name: nameTree.keys())
    		fileStrings.add(name + ": " + nameTree.get(name).toString());
    	return fileStrings;
    }
    
    
    /*
     * Output list of dates of files
     * @return list of dates of files
     */
    public List<String> outputDateTree(){
    	List<String> fileStrings = new ArrayList<String>();
    	for (String date: dateTree.keys()) {
    		for (FileData file: dateTree.get(date))
    			fileStrings.add(0, date + ": " + file.toString());
    	}
    	return fileStrings;
    }
    

}

