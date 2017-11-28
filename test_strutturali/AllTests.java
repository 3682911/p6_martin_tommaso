package test_strutturali;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AnalysisManagerTest.class, EllipseTest.class, OutputManagerTest.class, ReportAnalyzerTest.class,
		ReportDataTest.class, StimulusTest.class, TrialTest.class, VideoAnalyzerTest.class, VideoDataTest.class })
public class AllTests {

}
