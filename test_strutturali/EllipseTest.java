package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Point;

import application.Ellipse;

public class EllipseTest {
	
	Ellipse e1;
	Ellipse e2;

	@Before
	public void setUp() throws Exception {
		e1 = new Ellipse();
		e2 = new Ellipse(20, 10, new Point(5, 5), 123);
	}

	@Test
	public void testEllipse() {
		assertEquals(e1.getA(), 0);
		assertEquals(e1.getB(), 0);
		assertTrue(e1.getCenter().equals(new Point(0, 0)));
		assertEquals(e1.getFrame(), -1);
	}

	@Test
	public void testEllipseIntIntPointInt() {
		e2 = new Ellipse(1, 2, new Point(5, 10), 123);
		assertEquals(e2.getA(), 1);
		assertEquals(e2.getB(), 2);
		assertTrue(e2.getCenter().equals(new Point(5, 10)));
		assertEquals(e2.getFrame(), 123);
	}

	@Test
	public void testGetA() {
		assertEquals(e1.getA(), 0);
	}

	@Test
	public void testSetA() {
		e1.setA(23);
		assertEquals(e1.getA(), 23);
	}

	@Test
	public void testGetB() {
		assertEquals(e1.getB(), 0);
	}

	@Test
	public void testSetB() {
		e1.setB(32);
		assertEquals(e1.getB(), 32);
	}

	@Test
	public void testGetCenter() {
		assertTrue(e1.getCenter().equals(new Point(0, 0)));
	}

	@Test
	public void testSetCenter() {
		e1.setCenter(new Point(12, 34));
		assertTrue(e1.getCenter().equals(new Point(12, 34)));
	}

	@Test
	public void testGetFrame() {
		assertEquals(e1.getFrame(), -1);
	}

	@Test
	public void testSetFrame() {
		e1.setFrame(321);
		assertEquals(e1.getFrame(), 321);
	}

	@Test
	public void testCopy() {
		e1 = e2.copy();
		assertEquals(e1.getA(), e2.getA());
		assertEquals(e1.getB(), e2.getB());
		assertTrue(e1.getCenter().equals(e2.getCenter()));
		assertEquals(e1.getFrame(), e2.getFrame());
	}

}
