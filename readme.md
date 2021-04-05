Install & run
----
1. install Java 8
   https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
   
2. Run
   ```
   java -cp output/production/BestStation main.java.com.kelvin.beststation.App
   ```
3. Test 
   ```
   java -cp output/production/BestStation:output/production/BestStation/junit-4.13.2.jar:output/production/BestStation/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.java.com.kelvin.beststation.TestSuite
   ```

