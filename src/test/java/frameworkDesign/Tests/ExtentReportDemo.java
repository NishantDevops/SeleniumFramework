package frameworkDesign.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo {
	public ExtentReports extent;

	@BeforeMethod
	public void extentReportConfig() {
		// ExtentSparkReporter & ExtentReports

		/*
		 * ExtentSparkReporter - Responsible for generating an HTML report. Produces a -
		 * rich, interactive report with pie charts, logs, and screenshots.
		 */

		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

		reporter.config().setReportName("Automation Reports");
		reporter.config().setDocumentTitle("Test Report");

		/*
		 * ExtentReports - Main class that manages the entire reporting process.
		 * Collects, - maintains, and generates the final test report. Used to log test
		 * steps, - failures, screenshots, etc.
		 */

		// ExtentReports extent = new ExtentReports();
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester Name", "Nishant");

	}

	@Test
	public void initialDemo() {

		/*
		 * ExtentTest (Represents Each Test Case) 🔹 What it does: Used to log steps,
		 * assertions, and status (Pass/Fail/Skip). Helps track individual test cases in
		 * reports.
		 */

		ExtentTest test = extent.createTest("Initia Demo");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		driver.close();

		//test.fail("Results do not match"); // Explicitly failing the test

		/*
		 * extent.flush() - Why is it needed? 🔹 extent.flush() is used to write all
		 * test execution data into the report file. 🔹 Without calling .flush(), the
		 * report will not be saved, and you won’t see any results in the HTML
		 */
		extent.flush();
	}
}
