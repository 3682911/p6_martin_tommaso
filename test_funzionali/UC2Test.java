package test_funzionali;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import application.AnalysisManager;

public class UC2Test {

	AnalysisManager analysisManager;
	
	@SuppressWarnings("unused")
	@Test
	public void test() {
		String sourcePath;
		String destinationPath;
		String path = "Experiment";
		String sp = System.getProperty("file.separator");
		
		/*
		 * Il file returned simula il file restituito dal dialog di ricerca
		 */
		File returned = null;
	    if (returned != null) {
	    	destinationPath = "User selected path";
	    	sourcePath = "User selected path";
	    }
	    else {
	    	destinationPath = "Select a valid path";
	    	sourcePath = "Select a valid path";
	    }
	    assertTrue(destinationPath.equals("Select a valid path"));
	    assertTrue(sourcePath.equals("Select a valid path"));
	    
	    returned = new File(path + sp);
	    if (returned != null) {
	    	destinationPath = "User selected path";
	    	sourcePath = "User selected path";
	    }
	    else {
	    	destinationPath = "Select a valid path";
	    	sourcePath = "Select a valid path";
	    }
	    assertTrue(destinationPath.equals("User selected path"));
	    assertTrue(sourcePath.equals("User selected path"));
	}

}
