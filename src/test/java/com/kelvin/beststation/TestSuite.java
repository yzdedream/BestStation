package test.java.com.kelvin.beststation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PointTest.class,
        StationTest.class,
        BestStationTest.class,
        LazyCachedBestStationTest.class,
        DiligentCacheBestStationTest.class
})

public class TestSuite {

}