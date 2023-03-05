import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * functionality for a double linked list
 * @author Hana Fatima Shaikh
 */
public class BasicDoubleLinkedList<T> implements Iterable<T>{
	protected Node head, tail;
	protected int size;
	protected Node hold;
	
	/**
	 * creates a basic node with three variables, data, next, and prev
	 * @author Hana Fatima Shaikh
	 */
	protected class Node {
		private T data;
		private Node prev, next;
		
		/**
		 * instantiates a basic Node object, sets next and prev to null
		 * @param dataNode is the value set for the data variable
		 */
		protected Node(T dataNode) {
			data = dataNode;
			prev = null;
			next = null;
		}
		
		/**
		 * instantiates a Node object, sets prev to null
		 * @param dataNode is the value set for the data variable
		 * @param nextNode is the value set for the next variable
		 */
		protected Node(T dataNode, Node nextNode) {
			data = dataNode;
			prev = null;
			next = nextNode;
		}
		
		/**
		 * instantiates a Node object
		 * @param dataNode is the value set for the data variable
		 * @param prevNode is the value set for the next variable
		 * @param nextNode is the value set for the prev variable
		 */
		protected Node(T dataNode, Node prevNode, Node nextNode) {
			data = dataNode;
			prev = prevNode;
			next = nextNode;
		}
		
		/**
		 * retrieves the data variable
		 * @return the data variable
		 */
		protected T getData() {
			return data;
		}
		
		/**
		 * sets the data variable
		 * @param newData is value set for the data variable
		 */
		protected void setData(T newData) {
			data = newData;
		}
		
		/**
		 * retrieves the next variable
		 * @return the next variable
		 */
		protected Node getNextNode() {
			return next;
		}
		
		/**
		 * retrieves the prev variable
		 * @return the prev variable
		 */
		protected Node getPrevNode(){
			return prev;
		}
		
		/**
		 * sets the next variable
		 * @param nextNode is the value set for the next variable
		 */
		protected void setNextNode(Node nextNode) {
			next = nextNode;
		}
		
		/**
		 * sets the prev variable
		 * @param prevNode is the value set for the prev variable
		 */
		protected void setPrevNode(Node prevNode) {
			prev = prevNode;
		}
	}

	/**
	 * creates an iterator that can traverse both forward and backward through the linked list
	 * @author Hana Fatima Shaikh
	 */
	protected class DoubleLinkedListIterator implements ListIterator<T>{
		protected Node current = null;
		protected int cursor;
		
		/**
		 * instantiates a basic iterator
		 * @param set is the value for the current variable
		 */
		private DoubleLinkedListIterator(Node set) {
			Node hold = new Node(null);
			hold.next = set;
			current = hold;
			cursor = -1;
		}
		
		/**
		 * advances the cursor forward
		 * @return the current value of the element the cursor is pointing to
		 */
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			T nextData = current.getNextNode().getData();
			current = current.next;
			cursor++;
			return nextData;
		}
		
		/**
		 * determines whether there is a next value for the current node
		 * @return true if there is a value in the next variable of the current node
		 */
		public boolean hasNext() {
			if(current.getNextNode() != null) {
				return true;
			}
			
			return false;
		}
		
		/**
		 * advances the cursor backward
		 * @return the current value of the element the cursor is pointing to
		 */
		public T previous() {
			if(!hasPrevious() && cursor != 0) {//the only case is if the first element is removed
				throw new NoSuchElementException();
			}
			
			T prevData = current.getData();			
			current = current.getPrevNode();
			cursor--;
			return prevData;
		}
		
		/**
		 * determines whether there is a prev value for the current node
		 * @return true if there is a value in the prev variable of the current node
		 */
		public boolean hasPrevious(){
			if(cursor == -1) {
				throw new NoSuchElementException();
			}
			if(current.getPrevNode() != null) {
				return true;
			}
			
			return false;
		}

		/**
		 * not supported for a DoubleLinkedListIterator
		 */
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		
		/**
		 * not supported for a DoubleLinkedListIterator
		 */
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		/**
		 * not supported for a DoubleLinkedListIterator
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/**
		 * not supported for a DoubleLinkedListIterator
		 */
		public void set(T e) {
			throw new UnsupportedOperationException();
		}

		/**
		 * not supported for a DoubleLinkedListIterator
		 */
		public void add(T e) {
			throw new UnsupportedOperationException();
		}
	}


	/**
	 * instantiate a new ListIterator
	 * @return a DoubleLinkedListIterator corresponding to the abstract data type  
	 */
	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator(head);
	}

	/**
	 * instantiate a basic BasicDoubleLinkedList to empty values
	 */
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * retrieve the size of the linked list
	 * @return the size variable
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * add a node to the front of the linked list
	 * @param data will be added to the front of the linked list
	 */
	public void addToEnd(T data) {
		hold = new Node(data);
		if(size == 0) {
			tail = hold;
			head = hold;
		}
		else {
			tail.next = hold;
			hold.prev = tail;
			tail = hold;
		}
			
		size++;
	}
	
	/**
	 * add a node to the back of the linked list
	 * @param data will be added to the back of the linked list
	 */
	public void addToFront(T data) {
		hold = new Node(data);
		if(size == 0) {
			head = hold;
			tail = hold;
		}
		else {
			hold.next = head;
			head.prev = hold;
			head = hold;
		}
		
		size++;
	}
	
	/**
	 * retrieve the data of the first node in the linked list
	 * @return the data of the first node
	 */
	public T getFirst() {
		if(size == 0) {
			return null;
		}
		return head.getData();
	}
	
	/**
	 * retrieve the data of the last node in the linked list
	 * @return the data of the last node
	 */
	public T getLast() {
		if(size == 0) {
			return null;
		}
		
		return tail.getData();
	}
	
	/**
	 * removes a specific data element
	 * @param targetData is the data that is being removed
	 * @param comparator is used to compare the data in nodes
	 * @return the node object that was removed
	 */
	public Node remove(T targetData, Comparator<T> comparator) {
		int index = 0;
		
		Node current = head;
		
		if(comparator.compare(current.getData(), targetData) == 0) {
			head = current.next;
			current.prev = null;
			size--;
			return current;
		}
		
		while(index < size) {
			current = current.next;
			index++;
			if(comparator.compare(current.getData(), targetData) == 0) {
				if(index == size-1) {
					tail = current.prev;
					current.prev.next = current.next; 
					size--;
					return current;
				}
				current.prev.next = current.next; 
				current.next.prev = current.prev;
				size--;
				return current;
			}
		}
		
		return null;
	}
	
	/**
	 * retrieve the first element of the list and remove it
	 * @return the data from the removed element
	 */
	public T retrieveFirstElement() {
		if(size == 0) {
			return null;
		}
		else if(size == 1) {
			Node first = head;
			head = null;
			tail = null;
			return first.getData();
		}

		Node first = head;
		head = head.next;
		head.prev = null;
		size--;
		return first.getData();		
	}
	
	/**
	 * retrieve the last element of the list and remove it
	 * @return the data from the removed element
	 */
	public T retrieveLastElement() {
		if(size == 0) {
			return null;
		}
		else if(size == 1) {
			Node last = tail;
			tail = null;
			head = null;
			return last.getData();
		}
		
		T last = tail.getData();
		
		tail = tail.prev;
		tail.next = null;
		size--;		
		return last;
	}
	
	/**
	 * create an arraylist of the linked list
	 * @return the arraylist version of the linked list
	 */
	public  ArrayList<T> toArrayList(){
		ArrayList<T> a = new ArrayList<T>();
		
		Node current = head;
		while(current != null) {
			a.add(current.getData());
			current = current.next;
		}
		
		return a;
	}
}
