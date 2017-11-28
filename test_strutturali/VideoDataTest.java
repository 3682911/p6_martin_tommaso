package test_strutturali;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Point;

import application.Ellipse;
import application.VideoData;

public class VideoDataTest {

	VideoData v1;
	
	@Before
	public void setUp() throws Exception {
		v1 = new VideoData();
	}

	@Test
	public void testVideoData() {
		assertTrue(v1.getAllPupils().equals(new Vector<Ellipse>()));
	}

	@Test
	public void testGetAllPupils() {
		assertNotNull(v1.getAllPupils());
	}

	@Test
	public void testGetSetPupil() {
		v1.insertPupil(new Ellipse());
		v1.insertPupil(new Ellipse(10, 20, new Point(2, 4), 123));
	}
}
