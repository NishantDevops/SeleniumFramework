package frameworkDesign.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankYouPage {

	WebDriver driver;
	@FindBy(css = ".hero-primary")
	WebElement thankYouMessage;

	@FindBy(css = "label.ng-star-inserted")
	WebElement orderText;

	public ThankYouPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String getConfirmationMessage() {
		return thankYouMessage.getText();

	}

	public void getOrderId() {
		String orderID = orderText.getText().split(" ")[1].trim();
		//System.out.println("Order ID is :" + orderID);

	}

}
