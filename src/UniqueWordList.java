/**
 * <b>UniqueWordList</b>
 * UniqueWordList is used to store every unique word in the WordList.
 * UniqueWordList is mainly using a linked list implementation.
 * 
 * @author <qian2z>
 * @version 1.0
 * final submission 20 Nov 2021
 */
public class UniqueWordList {
	
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

		// adds Node n to the tail of the list
		public void addNodeAtTail(Node n) {
			if (this.nextNode == null) {
				this.nextNode = n;
			} else {
				this.nextNode.addNodeAtTail(n);
			}
		}
		
		public String toString() {
			return getValue();
		}
	} // End of Node Class implementation
	
	private Node headNode;
	
	// constructor
	public UniqueWordList() {
		headNode = null;
	}
	
	// add new unique word into the UniqueWordList
	public void add(String n) {
		Node newNode = new Node(n);
		if(headNode == null) {
			headNode = newNode;
		}
		else {
			headNode.addNodeAtTail(newNode);
		}
	}
	
	// calculate the total number of unique words in the UniqueWordList
	public int count() {
		Node temp = headNode;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.nextNode;
        }
        return count;
	}
	
	// print all the unique words in the UniqueWordList
	public void display() {
		Node tmp = headNode;
		while(tmp != null) {
			System.out.println(tmp);
			tmp = tmp.nextNode;
		}
	}
}
