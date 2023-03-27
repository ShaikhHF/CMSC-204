import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Utilizes the CourseDBElement to implement a hashtable with buckets
 * @author Hana Fatima Shaikh
 */
public class CourseDBStructure implements CourseDBStructureInterface{
	public CourseDBElement[] hashtable;
	
	/**
	 * Constructs a linked list to hold courses based on finding the smallest 4k+3 prime greater than the parameter divided by 1.5
	 * @param courseNum is the estimated number of courses
	 */
	CourseDBStructure(int courseNum){
		int least = (int) (courseNum / 1.5);
		int k = 1;
		int possiblePrime = 4*k+3;
		boolean notFound = true;
		
		while(notFound) {  
			possiblePrime = 4*k + 3;
			
			if(least >= possiblePrime){
				k++;
				continue;
			}
      
			boolean notPrime = false;
			
			for (int i = 2; i <= possiblePrime / 2; ++i) {
				if (possiblePrime % i == 0) {
					notPrime = true;
					break;
				}
			}	
		    
			if(notPrime) {
				k++;
				continue;
			}
			else{
				notFound = false;
				break;
			}
		}  
		
		hashtable = new CourseDBElement[possiblePrime];
	}
	
	/**
	 * Constructs a linked list based on parameters
	 * @param s is used to differentiate the two constructors
	 * @param i is the length to set the linked list to
	 */
	CourseDBStructure(String s, int i){
		hashtable = new CourseDBElement[i];
	}

	/**
	 * Adds an element to the linked list 
	 * @param element is the CourseDBElement to add
	 */
	public void add(CourseDBElement element) {
		element.setHashcode(element.getCRN()%hashtable.length);
		boolean inTable = false;
		CourseDBElement bucket = hashtable[element.getCRN()%hashtable.length];
		
		if(bucket == null) { // entire bucket for the hashcode is empty
			element.setHashcode(element.getCRN()%hashtable.length);
			hashtable[element.getHashcode()] = element;
			inTable = true;
		}
		
		while(bucket != null && !inTable) { // checks if the element is already in the array or if theres only one element and adds it in that case
			if(element.compareTo(bucket) == 1) { // same element
				
				element.setPrev(bucket.getPrev());
				element.setNext(bucket.getNext());
				
				CourseDBElement bucketPrev = bucket.getPrev();
				CourseDBElement bucketNext = bucket.getNext();
				
				if(bucketPrev != null) {
					bucketPrev.setNext(element);
				}
				else {
					hashtable[element.getHashcode()] = element;
				}
				if(bucketNext != null) {
					bucketNext.setPrev(element);
				}

				inTable = true;
				break;
			}
			
			if(bucket.getNext() == null) { // last item of the linked list, added				
				
				CourseDBElement bucketPrev = bucket.getPrev();
				
				if(bucketPrev != null) {
					bucketPrev.setNext(element);
				}
				else {
					hashtable[element.getHashcode()] = element;
				}
				
				bucket.setPrev(element);
				element.setNext(bucket);
				
				inTable = true;
				break;
			}
			
			bucket = bucket.getNext();
			
		}
		
		if(!inTable) {
			element.setHashcode(element.getCRN()%hashtable.length);
			hashtable[element.getHashcode()] = element;
		}
	}

	/**
	 * Retrieves an element from the linked list based on index
	 * @param crn is the crn of the object you are trying to retrieve
	 * @return the CourseDBElement based on the passed crn
	 * @throws IOException the crn passed is not there
	 */
	public CourseDBElement get(int crn) throws IOException {
		CourseDBElement course = hashtable[crn%hashtable.length];
		if(course == null) {
			throw new IOException();
		}
		
		if(crn == course.getCRN()) {
			return course;
		}
		
		while(course != null) {
			if(crn == course.getCRN()) {
				return course;
			}
			
			course = course.getNext();
		}
		
		throw new IOException();
	}

	/**
	 * converts the CourseDBStructure object into a string
	 * @return an arraylist of strings 
	 */
	public ArrayList<String> showAll() {
		ArrayList<String> hashtableString = new ArrayList<>();

		for(int i = 0; i < hashtable.length; i++) {
			CourseDBElement temp = hashtable[i];
			while(temp != null) {
				hashtableString.add(temp.toString());
				temp = temp.getNext();
			}
		}

		return hashtableString;
	}

	/**
	 * Retrieves the size of the linked list
	 * @return the size attribute
	 */
	public int getTableSize() {
		return hashtable.length;
	}
	
}
