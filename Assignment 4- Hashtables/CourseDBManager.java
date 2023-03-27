import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilizes the CourseDBStructure class to stores courses and interact with files
 * @author Hana Fatima Shaikh
 */
public class CourseDBManager implements CourseDBManagerInterface{
	private CourseDBStructure courseStruct;
	
	/**
	 * Base Constructor
	 */
	CourseDBManager(){
		courseStruct = new CourseDBStructure(25);
	}
	
	/**
	 * Constructs based on passed value
	 * @param estimateNumCourses is the estimated number of courses stored over the course of the program
	 */
	CourseDBManager(int estimateNumCourses){
		courseStruct = new CourseDBStructure(estimateNumCourses);
	}
	
	/**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id is the course id 
	 * @param crn is the course crn
	 * @param credits is the number of credits
	 * @param roomNum is the course room number
	 * @param instructor is the name of the instructor
	 */
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		courseStruct.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}

	/**
	 * finds CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
	public CourseDBElement get(int crn) {
		try {
			return courseStruct.get(crn);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Reads the information of courses from a test file and adds them
	 * to the CourseDBStructure data structure
	 * @param input input file 
	 * @throws FileNotFoundException if file does not exists
	 */
	public void readFile(File input) throws FileNotFoundException {
		Scanner file = new Scanner(input);
		
		while(file.hasNextLine()) {
			String[] line = file.nextLine().split(" ");
			String courseId = line[0];
			int  crn = Integer.valueOf(line[1]);
			int credits = Integer.valueOf(line[2]);
			String roomNumber = line[3];
			String instructor = line[4];
			
			CourseDBElement add = new CourseDBElement(courseId, crn, credits, roomNumber, instructor);
			
			courseStruct.add(add);
		}
		
		file.close();
	}

	/**
	 * converts the object into a string
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	public ArrayList<String> showAll() {
		return courseStruct.showAll();
	}
	
	/**
	 * For testing purposes
	 * @return the length of the courseDBStructure attribute
	 */
	public int getTableSize() {
		return courseStruct.getTableSize();
	}
}
