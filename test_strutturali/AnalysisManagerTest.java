package test_strutturali;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.videoio.VideoWriter;

import application.AnalysisManager;
import application.Main;

public class AnalysisManagerTest {
	
	AnalysisManager a1;
	String path;
	String sp;

	/*
	 * Prima di effettuare un test devo generare dei file dal formato compatibile con
	 * quello richiesto dall'applicazione per funzionare
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws FileNotFoundException, UnsupportedEncodingException {
		//Load the opencv .dll library
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Win")) {
			try {
				InputStream in = null;
				File fileOut = null;

				int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));

				if (bitness == 64) {
					in = Main.class.getResourceAsStream("/libraries/opencv/build/java/x64/opencv_java320.dll");
					fileOut = File.createTempFile("lib", ".dll");
				} else {
					//if OS is 32 bit
					in = Main.class.getResourceAsStream("/libraries/opencv/build/java/x86/opencv_java320.dll");
					fileOut = File.createTempFile("lib", ".dll");
				}

				OutputStream out = FileUtils.openOutputStream(fileOut);
				IOUtils.copy(in, out);
				in.close();
				out.close();

				System.load(fileOut.toString());

			} catch (Exception e) {
				throw new RuntimeException("Failed to load opencv native library", e);
			}
		}
		
		String path = "Experiment";
		File newFolder = new File(path);
		String sp = System.getProperty("file.separator");
		newFolder.mkdir();
		
		PrintWriter writer = null;
		writer = new PrintWriter(path + sp + "Report.txt", "UTF-8");
		writer.println("testa 123 h 3 s 1 v");
		writer.println("testb 321 ert 2 a 3 g");
		writer.println();
		writer.println("a 456");
		writer.println("b 654");
		writer.close();
		
		/*
		 * Genero un video composto da 2 frame con 2 rettangoli
		 */
		Mat testFrame = new Mat(320, 240, CvType.CV_8UC3, new Scalar(255, 230, 240));
		for (int i=100; i<120; i++) {
		    for (int j=30; j<80; j++) {
		    	double[] data = testFrame.get(i, j);
		    	for(int k=0; k<testFrame.channels(); k++) {
		    		data[k] = k*2;
		    	}
		    	testFrame.put(i, j, data);
		    }
		}
		for (int i=160; i<210; i++) {
		    for (int j=90; j<180; j++) {
		    	double[] data = testFrame.get(i, j);
		    	for(int k=0; k<testFrame.channels(); k++) {
		    		data[k] = (5-k)*20;
		    	}
		    	testFrame.put(i, j, data);
		    }
		}
		VideoWriter videoWriter = new VideoWriter();
	    videoWriter.open(path + sp + "Video.avi", VideoWriter.fourcc('M', 'S', 'V', 'C'), 10, testFrame.size(), true); //WHAM MSVC CRAM
	    videoWriter.write(testFrame);
		videoWriter.release();
	}
	
	@Before
	public void setUp() throws Exception {
		path = "Experiment";
		File newFolder = new File(path);
		sp = System.getProperty("file.separator");
		newFolder.mkdir();
		a1 = new AnalysisManager(path + sp, path + sp, 20, "testshold");
	}

	@Test
	public void testAnalysisManager() {
		assertNotNull(a1);
	}

	@Test
	public void testAnalyze() throws IOException {
		a1.analyze();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AnalysisManager.closeAnalysis();
		a1.analyze();
	}
	
	@Test
	public void testValidPath() {
		a1 = new AnalysisManager("valid path Test", "valid path Test", 20, "testshold");
		assertFalse(a1.arePathsValid());
		a1 = new AnalysisManager(path, path, 20, "testshold");
		assertTrue(a1.arePathsValid());
	}
}
