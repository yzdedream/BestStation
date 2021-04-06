Install & run
----
1. install Java 8
   ```
   https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
   ```
2. Run
   ```
   java -cp output/production/BestStation com.kelvin.beststation.App
   ```
3. Test 
   ```
   java -cp output/test/BestStation:output/production/BestStation:output/test/BestStation/junit-4.13.2.jar:output/test/BestStation/hamcrest-core-1.3.jar org.junit.runner.JUnitCore com.kelvin.beststation.test.TestSuite
   ```

Thoughts
--------
* This solution includes 3 methods, basic, lazily cached and diligently cached 
* BestStationFinder is an abstract base class for all methods.
* BasicBestStationFinder uses the naive way to find the best station for each given device.
   * For n stations, query time complexity is O(n)
   
* CachedBestStationFinder is an abstract class for two cached methods
* LazyCachedBestStationFinder calculates best station result only when it is needed.
   * For n stations, time complexity for a new query is O(n)
   * For cached result time complexity is O(1)
* DiligentCachedBestStationFinder calculates results within the coverage of a newly added/removed station
   * coverage is a rectangle defined by station's coordinates +/- reach
   * Adding a station with reach of r to existing n stations, time complexity is O(n * r^2)
   * For devices within coverage, query time complexity is O(1)
   * For devices outside of coverage, query time complexity is O(n)
   
* Methods
   * Basic finder is suitable for small scale demo purposes
   * Lazy finder is suitable for frequent add/delete station scenarios
   * Diligent finder is suitable for infrequent station changes
