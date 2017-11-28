package test_strutturali;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Point;

import application.Ellipse;
import application.OutputManager;
import application.ReportData;
import application.Stimulus;
import application.Trial;
import application.VideoData;

public class OutputManagerTest {
	
	OutputManager o1;
	String path;
	String sp;

	@Before
	public void setUp() throws Exception {
		path = "Experiment";
		File newFolder = new File(path);
		sp = System.getProperty("file.separator");
		newFolder.mkdir();
	}

	@Test
	public void testOutputManagerString() {
		o1 = new OutputManager("");
		assertNotNull(o1);
	}

	@Test
	public void testCreateOutputDataFile() throws FileNotFoundException, UnsupportedEncodingException {
		o1 = new OutputManager(path + sp);
		ReportData r1 = new ReportData();
		r1.insertStimulus(new Stimulus("asd", 1, 2, 3));
		r1.insertTrial(new Trial(new Stimulus("dsa", 4, 5, 6), 100));
		o1.createOutputDataFile(r1, "test sourcepath");
	}

	@Test
	public void testCreateOutputSizesFile() throws FileNotFoundException, UnsupportedEncodingException {
		o1 = new OutputManager(path + sp);
		VideoData v1 = new VideoData();
		v1.insertPupil(new Ellipse(10, 20, new Point(0, 0), 30));
		o1.createOutputSizesFile(v1, "test sourcepath");
	}

}
