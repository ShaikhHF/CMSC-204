
/**
 * represents a course with properties similar to a node
 * @author Hana Fatima Shaikh
 */
public class CourseDBElement implements Comparable{
	private String courseID;
	private int crn;
	private int creditNum;
	private String roomNum;
	private String instructor;
	private CourseDBElement next = null;
	private CourseDBElement prev = null;
	
	private int hashcode; 
	
	/**
	 * Base Constructor
	 */
	CourseDBElement(){
	}
	
	/**
	 * Constructor with parameters initializing the object
	 * @param id is the course id
	 * @param crn is the course crn 
	 * @param credits is the number of credits the course is worth
	 * @param roomNum is the room number for the class
	 * @param instructor is the courses instructors name
	 */
	CourseDBElement(String id, int crn, int credits, String roomNum, String instructor){
		this.courseID = id;
		this.crn = crn;
		this.creditNum = credits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	}
	
	/**
	 * retrieves the course crn 
	 * @return the course crn 
	 */
	public int getCRN() {
		return this.crn;
	}
	
	/**
	 * retrieves the hashcode of the object
	 * @return the hashcode
	 */
	public int getHashcode() {
		return hashcode;		
	}
	
	/**
	 * converts the object into a string
	 * @return the string version of the object
	 */
	public String toString() {
		return "\nCourse:"+courseID+" CRN:"+crn+" Credits:"+creditNum+" Instructor:"+instructor+" Room:"+roomNum;
	}

	/**
	 * sets the objects crn 
	 * @param parseInt is the new crn
	 */
	public void setCRN(int parseInt) {
		this.crn = parseInt;
	}
	
	/**
	 * sets the objects hashcode
	 * @param hc is the new hashcode
	 */
	public void setHashcode(int hc) {
		hashcode = hc;
	}

	/**
	 * retrieves the course id
	 * @return the course id
	 */
	public String getID() {
		return courseID;
	}

	/**
	 * retrieves the room number
	 * @return the room number
	 */
	public String getRoomNum() {
		return roomNum;
	}

	/**
	 * compares two CourseDBElements based on crn
	 * @param o the other CourseDBElement used in the comparison
	 * @return 1 if they are the same course, 0 if they are different
	 */
	public int compareTo(CourseDBElement o) {

		if(this.crn == o.getCRN()) {
			return 1;
		}
		return 0;
	}

	/**
	 * necessary for the Comparable interface
	 */
	public int compareTo(Object o) {
		return 0;
	}
	
	/**
	 * retrieves the next CourseDBElement
	 * @return the next attribute
	 */
	public CourseDBElement getNext(){
		return next;
	}
	
	/**
	 * set the next CourseDBElement
	 * @param next is the new next attribute
	 */
	public void setNext(CourseDBElement next) {
		this.next = next;
	}
	
	/*
	 * retrieves the prev CourseDBElement 
	 */
	public CourseDBElement getPrev(){
    	return prev;
    }
	
	/**
	 * set the prev CourseDBElement
	 * @param prev is the new prev attribute
	 */
	public void setPrev(CourseDBElement prev) {
		this.prev = prev;
	}
}
