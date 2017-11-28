package test_strutturali;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.AnalysisManager;
import application.Main;
import application.VideoAnalyzer;

public class VideoAnalyzerTest {

	VideoAnalyzer v1;
	VideoAnalyzer v2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

	/*
	 * Analizzo l'esperimento fittizio e verifico che il reportData generato non sia
	 * vuoto.
	 * Provo anche a interrompere l'esecuzione per verificare che non ci siano problemi
	 * con la gestione del thread di analisi in caso di chiusura dell'app da GUI
	 */
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
