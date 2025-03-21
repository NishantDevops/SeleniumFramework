package frameworkDesign.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import frameworkDesign.utilMethods.AbstractClass;

public class CartPage extends AbstractClass {

	WebDriver driver;
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProductsList;

	@FindBy(css = ".totalRow button")
	WebElement checkOutButton;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public Boolean verifyProductTitles(String productName) {
		Boolean titleMatch = cartProductsList.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return titleMatch;

	}

	public PaymentPage goToCheckout() {
		checkOutButton.click();
		PaymentPage pp = new PaymentPage(driver);
		return pp;
	}

}
