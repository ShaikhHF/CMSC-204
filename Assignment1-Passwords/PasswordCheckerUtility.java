import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {
	
	/**
	 * compares two strings based on if they are equal
	 * @param password - the password that is being checked if it is the same as the other parameter
	 * @param passwordConfirm - the other password used to compare the two parameters
	 * @throws UnmatchedException - if the two parameters are not equal
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException();
		}
	}
	
	/**
	 * compares two strings based on if they are equal, returns boolean
	 * @param password - the password that is being checked if it is the same as the other parameter
	 * @param passwordConfirm - the other password used to compare the two parameters
	 * @return a boolean on whether the two parameters are equal
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		if (password.equals(passwordConfirm)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * checks if the string is 6 characters or longer in length, returns boolean
	 * @param password - the string being checked 
	 * @return a boolean on whether the string parameter is 6 characters or longer in length
	 * @throws LengthException - if the string parameter is not 6 characters or longer in length 
	 */
	public static boolean isValidLength(String password) throws LengthException {
		password = password.replace("\n", "").replace("\r", "");

		if(!(password.length() >= 6)) {
			throw new LengthException();
		}
		return true;
	}
	
	/**
	 * checks if the string contains an uppercase alpha character, returns boolean
	 * @param password - the string being checked
	 * @return a boolean on whether there is an uppercase alpha character in the string parameter
	 * @throws NoUpperAlphaException - if there is not an uppercase alpha character 
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		boolean present = false;
		for(int i = 0; i < password.length(); i++) {
			if(Character.isUpperCase(password.charAt(i))) {
				present = true;
			}
		}
		
		if(!present) {
			throw new NoUpperAlphaException();
		}
		
		return true;
	}
	
	/**
	 * checks if the string contains a lowercase alpha character, returns boolean
	 * @param password - the string being checked
	 * @return a boolean on whether there is a lowercase alpha character in the string parameter
	 * @throws NoLowerAlphaException - if there is not a lowercase alpha character
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		boolean present = false;
		for(int i = 0; i < password.length(); i++) {
			if(Character.isLowerCase(password.charAt(i))) {
				present = true;
			}
		}
		
		if(!present) {
			throw new NoLowerAlphaException();
		}
		
		return true;
	}
	
	/**
	 * checks if the string contains a digit, returns boolean 
	 * @param password - the string being checked
	 * @return a boolean whether there is a digit in the string parameter 
	 * @throws NoDigitException - if there is not a digit
	 */
	public static boolean hasDigit(String password) throws NoDigitException {
		boolean present = false;
		for(int i = 0; i < password.length(); i++) {
			if(Character.isDigit(password.charAt(i))) {
				present = true;
			}
		}
		
		if(!present) {
			throw new NoDigitException();
		}
		
		return true;
	}
	
	/**
	 * checks if the string contains a special character, returns boolean
	 * @param password - the string being checked
	 * @return a boolean on whether there is a special character in the string parameter
	 * @throws NoSpecialCharacterException - if there is not a special character
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(password);
	
		boolean containsChar = matcher.find();
        if(containsChar) {
        	return true;
        }
        else {    
			throw new NoSpecialCharacterException();
		}
	}
	
	
	//Based on the word document there cannot be a sequence of the same character 3 times in a row and I followed that 
	/**
	 * checks if the string has the same character 3 times in a row, returns boolean
	 * @param password - the string being checked
	 * @return a boolean on whether there is a sequence of the same character 3 times in a row in the string parameter
	 * @throws InvalidSequenceException - if there is a sequence of the same character 3 times in a row
	 */
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {
		char passCharArr[] = password.toCharArray();  
        
	   for(int i = 0; i <passCharArr.length-2; i++) {  
        	if(password.charAt(i) == password.charAt(i+1) && password.charAt(i) == password.charAt(i+2)) {
        		throw new InvalidSequenceException();
        	}
        }  
        
        return true;
	}
	
	/**
	 * checks if the string is a valid password, returns boolean
	 * @param password - the string being checked
	 * @return a boolean on whether the password is valid
	 * @throws LengthException - if the string parameter is not 6 characters or longer in length 
	 * @throws NoUpperAlphaException - if there is not an uppercase alpha character 
	 * @throws NoLowerAlphaException - if there is not a lowercase alpha character
	 * @throws NoDigitException - if there is not a digit
	 * @throws NoSpecialCharacterException - if there is not a special character
	 * @throws InvalidSequenceException - if there is a sequence of the same character 3 times in a row
	 */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		isValidLength(password);
		hasUpperAlpha(password);
		hasLowerAlpha(password);
		hasDigit(password);
		hasSpecialChar(password);
		NoSameCharInSequence(password);
				
		return true;
	}
	
	/**
	 * checks if the string is between 6 to 9 characters, returns boolean
	 * @param password - the string being checked
	 * @return a boolean on whether the string parameter length is between 6 and 9
	 */
	public static boolean hasBetweenSixAndNineChars(String password) {
		if(password.length() >= 6 && password.length() <= 9) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * checks if the string is a weak password, returns boolean
	 * @param password - the string being checked
	 * @return a boolean on whether the string parameter is weak
	 * @throws WeakPasswordException - if the string parameter is weak
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		if(hasBetweenSixAndNineChars(password)) {
			throw new WeakPasswordException();
		}
		
		return false;
	}
	
	
	/**
	 * getInvalidPasswords
	 * @param passwords - an arraylist containing strings to be checked
	 * @return an arraylist of strings and the error messages of the strings in the parameter arraylist
	 */
	public static java.util.ArrayList<java.lang.String> getInvalidPasswords(java.util.ArrayList<java.lang.String> passwords) {
		ArrayList<String> invalid = new ArrayList<String>();
		for(int i = 0; i < passwords.size(); i++) {
			try {
				isValidPassword(passwords.get(i));
			}
			catch(Exception e){
				invalid.add(passwords.get(i) + " " + e.getMessage());
				
			}
		}
		
		return invalid;
	}
	
}
