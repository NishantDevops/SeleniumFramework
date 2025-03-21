package frameworkDesign.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
	WebDriver driver;

	@FindBy(css = "[placeholder*='Country']")
	WebElement countryValue;

	@FindBy(css = ".ta-results button")
	List<WebElement> countrySuggestions;

	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterCountry(String country) {
		countryValue.sendKeys(country);
	}

	public void selectCountryFromSuggestions() {

		countrySuggestions.stream().filter(c -> c.getText().trim().equalsIgnoreCase("India")).findFirst()
				.ifPresent(WebElement::click);
	}

	public ThankYouPage placeOrder(String country) {
		enterCountry(country);
		selectCountryFromSuggestions();
		placeOrderButton.click();
		ThankYouPage ty = new ThankYouPage(driver);
		return ty;
	}
}
