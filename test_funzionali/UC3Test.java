package test_funzionali;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import application.AnalysisManager;

public class UC3Test {

	AnalysisManager analysisManager;
	
	@SuppressWarnings("unused")
	@Test
	public void test() {
		String destinationPath;
		String path = "Experiment";
		String sp = System.getProperty("file.separator");
		
		File returned = null;
	    if (returned != null) {
	    	destinationPath = "User selected path";
	    }
	    else {
	    	destinationPath = "Select a valid path";
	    }
	    assertTrue(destinationPath.equals("Select a valid path"));
	    
	    returned = new File(path + sp);
	    if (returned != null) {
	    	destinationPath = "User selected path";
	    }
	    else {
	    	destinationPath = "Select a valid path";
	    }
	    assertTrue(destinationPath.equals("User selected path"));
	}
}

