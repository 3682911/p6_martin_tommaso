package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.Stimulus;

public class StimulusTest {

	Stimulus s1;
	Stimulus s2;

	@Before
	public void setUp() throws Exception {
		s2 = new Stimulus("Stimulus s2", 440, 10, 5);
	}

	@Test
	public void testStimulus() {
		s1 = new Stimulus();
		assertTrue(s1.getName().equals("Stimulus with no parameters set"));
		assertEquals(0, s1.getFrequence(), 0.0);
		assertEquals(0, s1.getDuration(), 0.0);
		assertEquals(0, s1.getVolume(), 0.0);
	}

	@Test
	public void testStimulusStringFloatFloatFloat() {
		s1 = new Stimulus("Constructor test", 1, 2, 3);
		assertTrue(s1.getName().equals("Constructor test"));
		assertEquals(1, s1.getFrequence(), 0.0);
		assertEquals(2, s1.getDuration(), 0.0);
		assertEquals(3, s1.getVolume(), 0.0);
	}

	@Test
	public void testGetName() {
		assertTrue(s2.getName().equals("Stimulus s2"));
	}

	@Test
	public void testSetName() {
		s2.setName("testSetName");
		assertTrue(s2.getName().equals("testSetName"));
	}

	@Test
	public void testGetFrequence() {
		assertEquals(440, s2.getFrequence(), 0.0);
	}

	@Test
	public void testSetFrequence() {
		s2.setFrequence(220);
		assertEquals(220, s2.getFrequence(), 0.0);
	}

	@Test
	public void testGetDuration() {
		assertEquals(10, s2.getDuration(), 0.0);
	}

	@Test
	public void testSetDuration() {
		s2.setDuration(20);
		assertEquals(20, s2.getDuration(), 0.0);
	}

	@Test
	public void testGetVolume() {
		assertEquals(5, s2.getVolume(), 0.0);
	}

	@Test
	public void testSetVolume() {
		s2.setVolume(2);
		assertEquals(2, s2.getVolume(), 0.0);
	}

	@Test
	public void testCopy() {
		s1 = s2.copy();
		assertTrue(s1.getName().equals(s2.getName()));
		assertEquals(s1.getFrequence(), s2.getFrequence(), 0.0);
		assertEquals(s1.getDuration(), s2.getDuration(), 0.0);
		assertEquals(s1.getVolume(), s2.getVolume(), 0.0);
	}

	@Test
	public void testEqualsStimulus() {
		s1 = s2.copy();
		assertTrue(s1.equals(s2));
		assertFalse(s1.equals(new Stimulus("FailEquals", 330, 2, 1)));
	}

}
