package ecom.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int count = 0;
    private static final int MAX_RETRY = 3; // Maximum number of retry attempts

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) { // Check if test failed
            if (count < MAX_RETRY) {
                count++;
                result.setStatus(ITestResult.FAILURE); // Mark test as failed
                System.out.println("Retrying test " + result.getName() + " for the " + count + " time(s).");
                return true; // Tells TestNG to re-run the test
            } else {
                result.setStatus(ITestResult.FAILURE); // Max retries reached, mark as failed
            }
        } else {
            result.setStatus(ITestResult.SUCCESS); // Test passed, mark as success
        }
        return false; // Tells TestNG not to re-run the test
    }
}