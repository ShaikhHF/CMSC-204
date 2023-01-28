import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class GradebookTester {
	GradeBook a, b;

	@Before
	void setUp() throws Exception {
		a = new GradeBook(8);
		b = new GradeBook(7);
		a.addScore(45);
		b.addScore(89);
		a.addScore(76);
	}

	@After
	void tearDown() throws Exception {
		a = null;
		b = null;
	}

	@Test
	void addScoreTest() {
		assertTrue("45.0 76.0 ".equals(a.toString()));
		assertTrue("89.0 ".equals(b.toString()));
		assertEquals(2.0, a.getScoreSize());
	}
	
	@Test
	void sumTest() {
		assertEquals(a.sum(), 121.0);
		assertEquals(b.sum(), 89.0);
	}
	
	@Test
	void minimumTest() {
		assertEquals(45, a.minimum());
		assertEquals(89, b.minimum());
	}
	
	@Test
	void finalScore() {
		assertEquals(a.finalScore(), 76);
		assertEquals(b.finalScore(), 89);
	}
	
}
