package frameworkDesign.Tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import frameworkDesign.TestComponents.BaseTest;
import frameworkDesign.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReportNG.extentReportConfig();
	ExtentTest test;

	/*
	 * To handle the concurrency issue when tests are run in parallel On
	 * "onTestStart" multiple tests can access the same "test" object which can
	 * cause this issue thereby causing inconsistency in the Extent reports
	 */
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {

		// extent.createTest(result.getTestName());
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // Assigns a unique threadID to each test

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// test.fail(result.getThrowable());

		extentTest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {

			e.printStackTrace();
		}

		String screenshotPath = null;
		try {
			screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(screenshotPath, result.getTestName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {

		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
