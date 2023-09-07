import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

/*
 * ElementFinder class
 */
public class ElementFinder {

	/*
	 * Method to find Kth largest/smallest element
	 * @param filename to read numbers from
	 * @param K element to find
	 * @param operation largest or smallest
	 * @return Kth largest/smallest element
	 */
	public static int Kth_finder(String filename, int K, String operation) {
		
		// Create a comparator depending upon the type of operation
		// Heap<Integer, Integer> heap = new Heap<Integer, Integer>(comparator);
		/** TODO **/
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(operation.equals("smallest")) {
					return o1 - o2;
				} else {
					return o2 - o1;
				}
			}
		};
		Heap<Integer, Integer> heap = new Heap<Integer, Integer>(comparator);
		try {
			Scanner file = new Scanner(new File(filename));
			while (file.hasNext()) {
				int n = file.nextInt();
				heap.add(n, n);
				if(heap.size() > K) {
					heap.poll();
				}
			}
			file.close();
			return heap.peek().getKey();
		} catch (FileNotFoundException e) {
			return -1;
		}
	}
}
