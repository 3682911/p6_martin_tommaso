package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.Stimulus;
import application.Trial;

public class TrialTest {
	
	Trial t1;
	Trial t2;
	
	@Before
	public void setUp() throws Exception {
		t1 = new Trial();
		t2 = new Trial(new Stimulus("Stimolo1", (float)440.0, (float)2.0, (float)5.0), 123);
	}

	@Test
	public void testTrial() {
		assertNotNull(t1);
		assertTrue(t1.getShape().equals(new Stimulus()));
		assertEquals(0,t1.getRepTime());
	}

	@Test
	public void testTrialStimulusInt() {
		assertNotNull(t2);
		assertTrue(t2.getShape().equals(new Stimulus("Stimolo1", (float)440.0, (float)2.0, (float)5.0)));
		assertEquals(123,t2.getRepTime());
	}

	@Test
	public void testGetShape() {
		assertTrue(t2.getShape().equals(new Stimulus("Stimolo1", (float)440.0, (float)2.0, (float)5.0)));
	}

	@Test
	public void testSetShape() {
		t1.setShape(new Stimulus("Stimolo1", (float)440.0, (float)2.0, (float)5.0));
		assertTrue(t1.getShape().equals(new Stimulus("Stimolo1", (float)440.0, (float)2.0, (float)5.0)));
	}

	@Test
	public void testGetRepTime() {
		assertEquals(123, t2.getRepTime());
	}

	@Test
	public void testSetRepTime() {
		t1.setRepTime(321);
		assertEquals(321, t1.getRepTime());
	}
}
