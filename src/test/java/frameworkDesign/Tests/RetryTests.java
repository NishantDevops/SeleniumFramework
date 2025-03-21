package frameworkDesign.Tests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTests implements IRetryAnalyzer {

	int count = 0;
	int retryValue = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (count < retryValue) {
			count++;
			return true;
		}

		return false;
	}

}
