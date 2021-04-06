package com.kelvin.beststation.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PointTest.class,
        StationTest.class,
        BasicBestStationFinderTest.class,
        LazyCachedBestStationFinderTest.class,
        DiligentCacheBestStationFinderTest.class
})

public class TestSuite {

}
