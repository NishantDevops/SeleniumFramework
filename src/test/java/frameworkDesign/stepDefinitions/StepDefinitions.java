package frameworkDesign.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import frameworkDesign.TestComponents.BaseTest;
import frameworkDesign.pageObjects.CartPage;
import frameworkDesign.pageObjects.LandingPage;
import frameworkDesign.pageObjects.PaymentPage;
import frameworkDesign.pageObjects.ProductCataloguePage;
import frameworkDesign.pageObjects.ThankYouPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends BaseTest {

	public LandingPage lp;
	public ProductCataloguePage pc;
	public CartPage cp;
	public PaymentPage pp;
	public ThankYouPage ty;

	@Given("^I landed on the ECommerce Page$")
	public void I_landed_on_the_ECommerce_Page() throws IOException {
		lp = launchApplicationURL();

	}

	@Given("^I have logged in with (.+) and (.+)$")
	public void I_have_logged_in_with_username_and_password(String username, String password) {
		pc = lp.loginApplication(username, password);
	}

	@When("^I add the product (.+) to the cart$")
	public void I_add_the_product_productName_to_the_cart(String productName) {
		List<WebElement> productsList = pc.getProductList();
		pc.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException {

		cp = pc.goToCartPage();
		Assert.assertTrue(cp.verifyProductTitles(productName));
		cp.windowScrollBy();
		pp = cp.goToCheckout();
	}

	@Then("Select {string} and {string} message is displayed on the confirmation page")
	public void confirm_ThankYou_Message(String string1, String string2) throws InterruptedException {

		ty = pp.placeOrder(string1);
		Thread.sleep(2000);
		cp.windowScrollTo();
		String confirmationMessage = ty.getConfirmationMessage();
		AssertJUnit.assertEquals(confirmationMessage, string2);
		driver.close();
	}

	@Then("{string} message is displayed on the page")
	public void verify_IncorrectMessage(String string) {
		lp.getErrorMessage();
		AssertJUnit.assertEquals(lp.getErrorMessage(), string);
		driver.close();
	}

}
