package frameworkDesign.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public static ExtentReports extentReportConfig() {

		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

		reporter.config().setReportName("Automation Reports");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester Name", "Nishant");
		extent.setSystemInfo("Operating System", "Windows");
		extent.setSystemInfo("Hostname", "Asus");

		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		extent.setSystemInfo("Timestamp", timestamp);

		return extent;

	}
}
