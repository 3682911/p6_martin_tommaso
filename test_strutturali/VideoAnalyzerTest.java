package test_strutturali;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Core;

import application.AnalysisManager;
import application.VideoAnalyzer;

public class VideoAnalyzerTest {

	VideoAnalyzer v1;
	VideoAnalyzer v2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	@Before
	public void setUp() throws Exception {
		String path = "Experiment";
		int threshold = 50;
		String outputType = new String("");
		v1 = new VideoAnalyzer(path, path, threshold, outputType);
		v2 = new VideoAnalyzer(path, path, threshold, "Thresholded");
	}

	@Test
	public void testVideoAnalyzer() {
		assertNotNull(v1);
	}

	@Test
	public void testAnalyze() throws IOException {
		v1.analyze();
		v2.analyze();
		synchronized(AnalysisManager.lock) {
			AnalysisManager.analysisIsRunning = false;
		}
		v2.analyze();
	}

}
