package test_funzionali;

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
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.videoio.VideoWriter;

import application.AnalysisManager;
import application.Main;

public class UC1Test {
	
	AnalysisManager analysisManager;

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
		/*
		 * Genero una nuova cartella internamente al progetto in modo da non avere
		 * problemi di path
		 */
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
		    	for (int k=0; k<testFrame.channels(); k++) {
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
	
	@Test
	public void test() throws IOException {
		String path = "Experiment";
		File newFolder = new File(path);
		String sp = System.getProperty("file.separator");
		if (!newFolder.isDirectory()) {
			newFolder.mkdir();
		}
		
		/*
		 * Input corretti -> avvio analisi
		 */
		analysisManager = new AnalysisManager(path + sp, path + sp, 20, "testshold");
		assertTrue(analysisManager.arePathsValid());
		if (analysisManager.arePathsValid()) {
			analysisManager.analyze();
		}
		
		/*
		 * Input non corretti -> non avvio l'analisi
		 */
		analysisManager = new AnalysisManager("Fail sourcepath", path + sp, 20, "testshold");
		assertFalse(analysisManager.arePathsValid());
		analysisManager = new AnalysisManager(path + sp, "Fail sourcepath", 20, "testshold");
		assertFalse(analysisManager.arePathsValid());
	}

}
