import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilizes the MorseCodeTree to convert morse code into english
 * @author Hana Fatima Shaikh
 */
public class MorseCodeConverter {
	private static MorseCodeTree tree = new MorseCodeTree();;
	
	/**
	 * Constructs a MorseCodeTree for use in following functions
	 */
	public MorseCodeConverter() {
		tree = new MorseCodeTree();
	}
	
	/**
	 * Compiles the contents of a morse code tree
	 * @return a string containing a morse code trees nodes data
	 */
	public static String printTree() {
		ArrayList<String> treeAL = tree.toArrayList();
		String treeString = "";
		
		for(int i = 0; i < treeAL.size(); i++) {
			treeString += treeAL.get(i) + " ";
		}
		
		return treeString.substring(0, treeString.length()-1);
	}
	
	/**
	 * Converts a morse code to English
	 * @param code the code to convert
	 * @return the code in English
	 */
	public static String convertToEnglish(String code) {
		String english = "";
		String[] words = code.split(" / ");
		
		for(int i = 0; i < words.length; i++) {
			String[] letters = words[i].split(" ");
			for(int j = 0; j < letters.length; j++) {
				english += tree.fetch(letters[j]);
			}
			english+=" ";
		}
		
		return english.substring(0, english.length()-1);
	}
	
	/**
	 * Converts a file of morse codes into English
	 * @param codeFile the file to convert
	 * @return the code in English
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		try {
			Scanner scanner = new Scanner(codeFile);
			String english = "";

			while (scanner.hasNextLine()) {
				
				String[] words = scanner.nextLine().split(" / ");
				
				for(int i = 0; i < words.length; i++) {
					String[] letters = words[i].split(" ");
					for(int j = 0; j < letters.length; j++) {
						english += tree.fetch(letters[j]);
					}
					english+=" ";
				}
				english = english.substring(0, english.length()-1);
			}

			scanner.close();
			return english;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
	}
}
