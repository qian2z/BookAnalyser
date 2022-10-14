import java.util.HashMap;
import java.util.Map;
/**
 * <b>WordList</b>
 * WordList is used to store every word in the text file which is after pre-processing.
 * WordList is mainly using a linked list implementation.
 * 
 * @author <qian2z>
 * @version 1.0
 * final submission 26 Nov 2021
 */
public class WordList {
	
	private class Node {
		private String value;
		private Node nextNode;

		public Node(String i) {
			value = i;
			nextNode = null; 
		}

		// returns the value stored in the node
		public String getValue() {
			return value;
		}

		public String toString() {
			return getValue();
		}
	} // End of Node Class implementation
	
	private Node headNode;
	private UniqueWordList uniqueList = new UniqueWordList();
	private HashMap<String, Integer> hash = new HashMap<String, Integer>();
	
	// constructor
	public WordList() {
		headNode = null;
	}
	
	// add new word into the WordList
	public void add(String n) {
		Node newNode = new Node(n);
		if(headNode == null) {
			headNode = newNode;
		}
		else {
			Node last = headNode;
			while(last.nextNode != null) {
				last = last.nextNode;
			}
			last.nextNode = newNode;
		}
	}
	
	// calculate the total number of words in the WordList
	public int count() {
		Node temp = headNode;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.nextNode;
        }
        return count;
	}
	
	// get the word of the specified index in the WordList
	public String get(int idx) {
		if(headNode == null) {
			return null;
		}
		else {
			Node getNode = headNode;
			int i = 0;
			while(i != idx) {
				getNode = getNode.nextNode;
				i++;
			}
			return getNode.getValue();
		}
	}
	
	// sort the words in the WordList in an ascending order - sort() for linked list
	public void sort() {
		Node current = headNode;
		Node index = null;
		
		String temp;
		if(current == null) {
			return;
		}
		else {
			while(current != null) {
				index = current.nextNode;
				while(index != null) {
					if((current.value).compareTo(index.value) > 0) {
						temp = current.value;
						current.value = index.value;
						index.value = temp;
					}
					index = index.nextNode;
				}
				current = current.nextNode;
			}
		}
	}
	
	// private binarySearch method
	private boolean find(int first, int last, String s) {
		if(first > last) {
			return false;
		}
		else {
			int middle = (first + last) / 2;
			int res = s.compareTo(get(middle));
			if(res == 0) {
				return true;
			}
			else if(res < 0) {
				return find(first, (middle - 1), s);
			}
			else {
				return find((middle + 1), last, s);
			}
		}
	}
	
	// main find() method
	// returns whether a particular query word is available in the book
	public boolean find(String s) {
		sort();
		return find(0, (count() - 1), s);
	}
	
	// calculate the occurrence of each word in the WordList
	// each word is stored in the HashMap as key, while the occurrence is stored in the HashMap as value
	// find the unique words (which occurrence == 1) in the WordList
	// add the unique words into UniqueWordList
	public void calculateOccurrenceOfEachWord() {
		sort();
		Node temp = headNode;
		
		while(temp != null) {
			if(hash.containsKey(temp.value)) {
				hash.put(temp.value, (hash.get(temp.value) + 1));
			}
			else {
				hash.put(temp.value, 1);
			}
			temp = temp.nextNode;
		}
		
	}
	
	// add the words with occurrence = 1 into UniqueWordList
	public void findUnique() {
		for(Node tmp = headNode; tmp != null; tmp = tmp.nextNode) {
			if(hash.get(tmp.value) == 1) {
				uniqueList.add(tmp.value);
			}
		}
	}
	
	// show the total number of unique words in the text file
	// print all the unique words in the UniqueWordList
	public void printFindUnique() {
		System.out.println("There are " + uniqueList.count() + " unique words in the book.");
		System.out.println("The list of unique words in alphabetical order: ");
		uniqueList.display();
	}
	
	// bubble sorting for array in a descending order, most frequent words are in the front of the array
	public void arrayBubbleSortDesc(String[] arr1, int[] arr2) {
		int n = arr1.length;
		for(int i = 0; i < (n - 1); i++) {
			for(int j = 0; j < (n - i - 1); j++) {
				if(arr2[j] < arr2[j + 1]) {
					// swap between integer values
					int temp1 = arr2[j];
					arr2[j] = arr2[j + 1];
					arr2[j + 1] = temp1;
					// swap between strings following the integer array
					String temp2 = arr1[j];
					arr1[j] = arr1[j + 1];
					arr1[j + 1] = temp2;
				}
			}
		}
	}
	
	// returns the top K most frequently appearing words in the text file
	public void topKWords(int k) {
		// create two arrays to store every String key and integer value from the HashMap
		String[] strArr = new String[hash.size()];
		int[] intArr = new int[hash.size()];
		
		// insert all String keys and integer values into respective array
		// HashMap to array
		int index = 0;
		for(Map.Entry<String, Integer> element : hash.entrySet()) {
			strArr[index] = element.getKey();
			intArr[index] = element.getValue();
			index++;
		}
		
		// bubbleSort both arrays in descending order
		arrayBubbleSortDesc(strArr, intArr);
		
		// print top K most frequent words
		System.out.println("The list of the top " + k + " most frequent words: ");
		for(int i = 0; i < k; i++) {
			System.out.println(strArr[i] + ": " + intArr[i] + " times");
		}
	}
	
	// Type Token Ratio : total number of unique words / total number of words
	public double ttr() {
		return ((double) uniqueList.count() / count()) * 100;
	}
	
	// print all the words in the WordList
	public void display() {
		Node tmp = headNode;
		while(tmp != null) {
			System.out.println(tmp);
			tmp = tmp.nextNode;
		}
	}
	
}
