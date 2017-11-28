package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.ReportData;
import application.Stimulus;
import application.Trial;

public class ReportDataTest {
	
	ReportData r1;

	@Before
	public void setUp() throws Exception {
		r1 = new ReportData();
	}

	@Test
	public void testReportData() {
		assertNotNull(r1);
	}

	@Test
	public void testGetAllStimuli() {
		assertNotNull(r1.getAllStimuli());
	}

	@Test
	public void testGetAllTrials() {
		assertNotNull(r1.getAllTrials());
	}

	@Test
	public void testGetInsertStimulus() {
		r1.insertStimulus(new Stimulus("testname", 1, 2, 3));
		assertTrue(r1.getStimulus("testname").getName().equals("testname"));
		assertTrue(r1.getStimulus("fail").getName().equals("Stimulus not found"));
	}

	@Test
	public void testInsertTrial() {
		r1.insertTrial(new Trial());
		assertFalse(r1.getAllTrials().isEmpty());
	}

}
