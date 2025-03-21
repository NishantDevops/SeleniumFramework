package frameworkDesign.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import frameworkDesign.TestComponents.BaseTest;
import frameworkDesign.pageObjects.CartPage;
import frameworkDesign.pageObjects.OrderPage;
import frameworkDesign.pageObjects.PaymentPage;
import frameworkDesign.pageObjects.ProductCataloguePage;
import frameworkDesign.pageObjects.ThankYouPage;

public class StandAloneTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void standAloneTest(HashMap<String, String> hm) throws InterruptedException, IOException {

		ProductCataloguePage pc = lp.loginApplication(hm.get("email"), hm.get("password"));
		List<WebElement> productsList = pc.getProductList();
		// System.out.println(productsList.size());

		pc.addProductToCart(hm.get("productName"));
		Thread.sleep(500);
		/*
		 * String toaster =
		 * driver.findElement(By.cssSelector("#toast-container")).getText();
		 * Assert.assertEquals(toaster, "Product Added To Cart");
		 */

		CartPage cp = pc.goToCartPage();
		Assert.assertTrue(cp.verifyProductTitles(hm.get("productName")));
		cp.windowScrollBy();

		PaymentPage pp = cp.goToCheckout();
		ThankYouPage ty = pp.placeOrder(hm.get("country"));
		Thread.sleep(2000);

		cp.windowScrollTo();
		String confirmationMessage = ty.getConfirmationMessage();
		AssertJUnit.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
		ty.getOrderId();

	}

	@Test(dependsOnMethods = { "standAloneTest" }, dataProvider = "getData")
	public void checkOrderHistory(HashMap<String, String> hm) {

		ProductCataloguePage pc = lp.loginApplication(hm.get("email"), hm.get("password"));
		OrderPage op = pc.goToOrderPage();
		Assert.assertTrue(op.verifyOrderText(hm.get("productName")), hm.get("productName"));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToHashMap(
				System.getProperty("user.dir") + "//src//test//java//frameworkDesign//data//testData.json");

		return new Object[][] { { data.get(0) } };

		/*
		 * HashMap<String, String> hm = new HashMap<String, String>();
		 * 
		 * hm.put("email", "nick.verma.krish@gmail.com"); hm.put("password",
		 * "Mi4i@2015"); hm.put("productName", "ZARA COAT 3"); hm.put("country", "Ind");
		 * 
		 * // return new Object[][] { { "nick.verma.krish@gmail.com", "Mi4i@2015",
		 * "ZARA", "Ind"}} // COAT 3", "Ind" }}; return new Object[][] { { hm } };
		 */
	}

}
