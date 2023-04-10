
/**
 * Creates a base object to hold morse code nodes
 * @author Hana Fatima Shaikh
 */
public class TreeNode<T> {
	TreeNode<T> right;
	TreeNode<T> left;
	T data;
	
	/**
	 * Constructs a node based parameter
	 * @param dataNode the value saved for the nodes data
	 */
	TreeNode(T dataNode){
		right = null;
		left = null;
		data = dataNode;
	}
	
	/**
	 * Constructs a deep copy of a TreeNode
	 * @param node the node that is being copied
	 */
	TreeNode(TreeNode<T> node){
		if(node == null) {
			data = null;
			right = null;
			left = null;
			return;
		}
		data = node.getData();
		
		if(node.getRight() == null) {
			right = null;
		}
		else {
			right = node.getRight();
		}
		
		if(node.getLeft() == null) {
			left = null;
		}
		else {
			left = node.getLeft();
		}
	}
	
	/**
	 * Retrieves the data of the node
	 * @return the data attribute
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Retrieves the right node
	 * @return the rightNode attribute
	 */
	public TreeNode<T> getRight() {
		if(right == null) {
			return null;
		}
		return right;
	}
	
	/**
	 * Retrieves the left node
	 * @return the leftNode attribute
	 */
	public TreeNode<T> getLeft() {
		if(left == null) {
			return null;
		}
		return left;
	}
	
	/**
	 * Sets the right node
	 * @param newRight the new value for the rightNode attribute
	 */
	public void setRight(TreeNode<T> newRight) {
		right = new TreeNode<T>(newRight);
	}
	
	/**
	 * Sets the left node
	 * @param newLeft the new value for the leftNode attribute
	 */
	public void setLeft(TreeNode<T> newLeft) {
		left = new TreeNode<T>(newLeft);
	}
}
