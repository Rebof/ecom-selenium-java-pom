package ecom.TestComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import ecom.common.CommonComponents;
import ecom.resources.ExtentReportsNG;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReportsNG.getReportObject();
    ExtentTest test;
    private static final Logger logger = LogManager.getLogger(Listeners.class);
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String displayName = result.getMethod().getMethodName();

        test = extent.createTest(displayName);
        extentTest.set(test);
        logger.info("Starting test: " + displayName);
    }

    @Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());//
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		String filePath = null;
		try {
			
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
				
		
	}
    
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test skipped");
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        logger.info("All tests finished. Reports generated.");
    }
}
