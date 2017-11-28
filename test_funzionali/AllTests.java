package test_funzionali;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UC1Test.class, UC2Test.class, UC3Test.class })
public class AllTests {

}
