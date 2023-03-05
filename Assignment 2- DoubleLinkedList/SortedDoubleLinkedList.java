import java.util.Comparator;
import java.util.ListIterator;

/**
 * functionality for a sorted double linked list
 * @author Hana Fatima Shaikh
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	Comparator<T> comp;
	
	/**
	 * instantiates a SortedDoubleLinkedList
	 * @param compareableObject is the value set for the comp variable
	 */
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		super();
		comp = compareableObject;
	}
	
	/**
	 * add a data value based on where it belongs in the array
	 * @param data what is used to compare the Node to the linked list
	 */
	public void add(T data) {
		Node hold = new Node(data);

		if(size == 0) { //works
			head = hold;
			tail = hold;
			size++;
			return;
		}
		
		Node current = head;
		int index = 0;
		
		while(index < size) {
			int comparison = comp.compare(data, current.getData());
			if(comparison < 0) {
				if(index == 0) { // first element
					hold.setNextNode(head);
					head.setPrevNode(hold);
					head = hold;			
					size++;
				}
				else { // in middle
					hold.setNextNode(current);
					hold.setPrevNode(current.getPrevNode());
					current.getPrevNode().setNextNode(hold);
					current.setPrevNode(hold);
					size++;
				}
				break;
			}
			else{
				if(comparison > 0 && index == size-1) { //case of last element
					current.setNextNode(hold);
					hold.setPrevNode(current);
					tail = hold;
					size++;
					return;
				}
				
				current = current.getNextNode();
			}
			
			index++;
		}
	}

	/**
	 * Not supported for a SortedDoubleLinkedList
	 */
	public void addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	/**
	 * Not supported for a SortedDoubleLinkedList
	 */
	public void addToEnd(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	/**
	 * instantiates an iterator based on BasicDouibleLinkedList iterator function
	 */
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	/**
	 * removes a data element from the linked list based on BasicDoubleLinkedList remove function
	 */
	public Node remove(T data, Comparator<T> comparator) {
		return super.remove(data, comparator);
	}
}
