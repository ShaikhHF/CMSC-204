import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_STUDENT_Test {
	CourseDBManager manager;
	
	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = new CourseDBManager(3);
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		manager = null;
	}
	
	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			manager.add("CMSC203",30504,4,"SC450","Joey Bag-O-Donuts");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
		ArrayList<String> al = manager.showAll();
		assertEquals(1, al.size());
	}
	
	/**
	 * Test showAll method and updates
	 */
	@Test
	public void testShowAll() {
		assertEquals(7, manager.getTableSize());
		manager.add("id1",11111,4,"SC450","abc");
		manager.add("id2",22222,4,"SC450","bca");
		manager.add("id3",22222,5,"SC450","cab");
		ArrayList<String> list = manager.showAll();
		assertEquals(list.get(0),"\nCourse:id1 CRN:11111 Credits:4 Instructor:abc Room:SC450");
	 	assertEquals(list.get(1),"\nCourse:id3 CRN:22222 Credits:5 Instructor:cab Room:SC450");
	}
	
	/**
	 * Test read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("TestStudent.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("LING200 23232 3 HT400 Short Name");
			inFile.print("CMSC204 30503 2 SC450 LongName LongestName LongerName");
			
			inFile.close();
			manager.readFile(inputFile);
			assertEquals("LING200",manager.get(23232).getID());
			assertEquals("CMSC204",manager.get(30503).getID());
			assertEquals("HT400",manager.get(23232).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
