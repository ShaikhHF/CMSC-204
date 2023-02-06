
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords;
	
	
	@Before
	public void setUp() throws Exception {
		passwords = new ArrayList<String>();
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			PasswordCheckerUtility.isValidLength("hello");
		}
		catch(LengthException e) {
			assertTrue(true);
		}
		
		try {
			PasswordCheckerUtility.isValidLength("hellohello");
		}
		catch(Exception e) {
			assertFalse(true);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			PasswordCheckerUtility.hasUpperAlpha("hello");
		}
		catch(NoUpperAlphaException e) {
			assertTrue(true);
		}
		
		try {
			PasswordCheckerUtility.hasUpperAlpha("HelloHello");
		}
		catch(NoUpperAlphaException e) {
			assertFalse(true);
		}	
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			PasswordCheckerUtility.hasLowerAlpha("HELLO");
		}
		catch(NoLowerAlphaException e) {
			assertTrue(true);
		}
		
		try {
			PasswordCheckerUtility.hasLowerAlpha("HelloHello");
		}
		catch(NoLowerAlphaException e) {
			assertFalse(true);
		}	
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			PasswordCheckerUtility.isWeakPassword("hello");
		}
		catch(WeakPasswordException e) {
			assertTrue(true);
		}
		
		try {
			PasswordCheckerUtility.isWeakPassword("HelloHelloHello");
		}
		catch(WeakPasswordException e) {
			assertFalse(true);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			PasswordCheckerUtility.NoSameCharInSequence("hello");
		}
		catch(InvalidSequenceException e) {
			assertTrue(true);
		}
		
		try {
			PasswordCheckerUtility.NoSameCharInSequence("HeloHelo");
		}
		catch(InvalidSequenceException e) {
			assertFalse(true);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			PasswordCheckerUtility.hasDigit("hello");
		}
		catch(NoDigitException e) {
			assertTrue(true);
		}
		
		try {
			PasswordCheckerUtility.hasDigit("HeloHelo123");
		}
		catch(NoDigitException e) {
			assertFalse(true);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			PasswordCheckerUtility.NoSameCharInSequence("helllo");
		}
		catch(InvalidSequenceException e) {
			assertTrue(true);
		}
		
		try {
			PasswordCheckerUtility.NoSameCharInSequence("HeloHelo");
		}
		catch(InvalidSequenceException e) {
			assertFalse(true);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> invalid = null;
		try {
			passwords.add("check123!");
			passwords.add("bad");
			passwords.add("bad123");
			invalid = PasswordCheckerUtility.getInvalidPasswords(passwords);
		}
		catch(Exception e) {
			assertTrue(invalid.get(1).equals("The password must be at least 6 characters long"));
			assertTrue(invalid.get(2).equals("The password must contain at least one uppercase alphabetic character"));
		}
	}
	
}
