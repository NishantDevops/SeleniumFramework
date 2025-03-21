package frameworkDesign.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import frameworkDesign.TestComponents.BaseTest;
import frameworkDesign.pageObjects.CartPage;
import frameworkDesign.pageObjects.ProductCataloguePage;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = RetryTests.class)
	public void loginErrorValidation() {

		lp.loginApplication("nick.verma.krish@gmail.com", "Mi@2015");
		lp.getErrorMessage();
		AssertJUnit.assertEquals(lp.getErrorMessage(), "Incorrect email or password.");

	}

	@Test
	public void productErrorValidation() throws InterruptedException, IOException {

		String productName = "ZARA COAT 3";

		ProductCataloguePage pc = lp.loginApplication("nick.verma.krish@gmail.com", "Mi4i@2015");
		List<WebElement> productsList = pc.getProductList();
		// System.out.println(productsList.size());

		pc.addProductToCart(productName);
		Thread.sleep(500);
		/*
		 * String toaster =
		 * driver.findElement(By.cssSelector("#toast-container")).getText();
		 * Assert.assertEquals(toaster, "Product Added To Cart");
		 */

		CartPage cp = pc.goToCartPage();
		Assert.assertFalse(cp.verifyProductTitles("ZARA COAT 33"));

	}
}
