package test_strutturali;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.ReportAnalyzer;

public class ReportAnalyzerTest {
	
	ReportAnalyzer r1;
		
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		String path = "Experiment";
		r1 = new ReportAnalyzer(path);
	}

	@Test
	public void testReportAnalyzer() {
		assertNotNull(r1);
	}

	@Test
	public void testAnalyze() throws IOException {
		r1.analyze();
	}

}
