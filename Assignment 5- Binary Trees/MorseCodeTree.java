import java.util.ArrayList;

/**
 * Utilizes the TreeNode object to implement a morse code tree
 * @author Hana Fatima Shaikh
 */
public class MorseCodeTree {
	TreeNode<String> root;
	
	/**
	 * Constructor that calls buildTree
	 */
	public MorseCodeTree() {
		buildTree();
	}
	
	/**
	 * Retrieves the root of a MorseCodeTree
	 * @return the root TreeNode
	 */
	public TreeNode<String> getRoot(){
		return root;
	}
	
	/**
	 * Sets the root
	 * @param newNode the new value of the root 
	 */
	public void setRoot(TreeNode<String> newNode) {
		root = new TreeNode<String>(newNode);
	}
	
	/**
	 * Inserts a node in the tree
	 * @param code the morse code
	 * @param letter the data of the added TreeNode
	 */
	public void insert(String code, String letter) {
		addNode(root, code, letter);
	}
	
	/**
	 * Adds a node to the tree
	 * @param root the node under which the node will be added
	 * @param code the morse code to reach the correct place in the tree
	 * @param letter the data of the added TreeNode
	 */
	public void addNode(TreeNode<String> root, String code, String letter) {
		int length = code.length();		
		
		if(length == 1) {			
			TreeNode<String> add = new TreeNode<String>(letter);
			
			if(code.equals(".")) {
				add.setLeft(root.getLeft());
				root.setLeft(add);
			
			}
			else if(code.equals("-")) {
				add.setRight(root.getRight());
				root.setRight(add);
			}
			
			return;
		}
		
		TreeNode<String> newRoot = null;
		if(code.charAt(0)=='.') {
			newRoot = root.getLeft();	
		}
		else if(code.charAt(0) == '-') {
			newRoot = root.getRight();	
		}
		
		String newCode = code.substring(1);
		addNode(newRoot, newCode, letter);
	}
	
	/**
	 * Finds the data based on a morse code
	 * @param code the morse code which leads to the node in the tree
	 * @return the data stored in the TreeNode the morse code leads to
	 */
	public String fetch(String code) {
		return fetchNode(root, code);
	}
	
	/**
	 * Finds the data based on a morse code
	 * @param root the node under which the node is 
	 * @param code the morse code which leads to the node in the tree
	 * @return the data stored in the TreeNode the morse code leads to
	 */
	public String fetchNode(TreeNode<String> root, String code) {
		if(code.charAt(0) == '.'){
			if(code.length() == 1) {
				return root.getLeft().getData();
			}	

			return fetchNode(root.getLeft(), code.substring(1));
		}
		else if(code.charAt(0) == '-'){
			if(code.length() == 1) {
				return root.getRight().getData();
			}	
			
			return fetchNode(root.getRight(), code.substring(1));
		}
		
		return null;
	}
	
	/**
	 * Unsupported
	 * @param data
	 * @return
	 * @throws UnsupportedOperationException
	 */
	public MorseCodeTree delete(String data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Unsupported
	 * @return
	 * @throws UnsupportedOperationException
	 */
	public MorseCodeTree update() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Builds a tree with the different morse code nodes
	 */
	public void buildTree() {
		TreeNode<String> r = new TreeNode<String>("");
		setRoot(r);
		
		addNode(root, ".", "e");
		addNode(root, "-", "t");
		addNode(root, "..", "i");
				
		addNode(root, ".-", "a");
		addNode(root, "-.", "n");
		addNode(root, "--", "m");
		addNode(root, "...", "s");
		addNode(root, "..-", "u");
		addNode(root, ".-.", "r");
		addNode(root, ".--", "w");
		addNode(root, "-..", "d");
		addNode(root, "-.-", "k");
		addNode(root, "--.", "g");
		addNode(root, "---", "o");
		addNode(root, "....", "h");
		addNode(root, "...-", "v");
		addNode(root, "..-.", "f");
		addNode(root, ".-..", "l");
		addNode(root, ".--.", "p");
		addNode(root, ".---", "j");
		addNode(root, "-...", "b");
		addNode(root, "-..-", "x");
		addNode(root, "-.-.", "c");
		addNode(root, "-.--", "y");
		addNode(root, "--..", "z");
		addNode(root, "--.-", "q");
	}
	
	/**
	 * Converts the morse code tree to an arraylist
	 * @return the arraylist which holds the morse code tree
	 */
	public ArrayList<String> toArrayList(){
		ArrayList<String> converted = new ArrayList<String>();
		LNROutputTraversal(root, converted);
		return converted;
	}
	
	/**
	 * Traverses the tree in-order
	 * @param root the root of the tree
	 * @param list the arraylist in which to store the morse code tree
	 */
	public void LNROutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root != null) {
			LNROutputTraversal(root.getLeft(), list);
			if(root.getData() != null) {
				list.add(root.getData());
			}
			LNROutputTraversal(root.getRight(), list);
		}
	}
}
