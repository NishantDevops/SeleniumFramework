package frameworkDesign.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.utilMethods.AbstractClass;

public class LandingPage extends AbstractClass {
	WebDriver driver;

	// Constructor
	public LandingPage(WebDriver driver) {
		super(driver);
		/*
		 * Assigns the WebDriver instance (passed as an argument) to the class-level
		 * driver variable. This ensures that the LandingPage class has access to the
		 * WebDriver instance for performing actions.
		 */
		this.driver = driver;

		/*
		 * PageFactory initializes all the WebElements declared using @FindBy
		 * annotations within this class. It also ensures that elements are located only
		 * when they are accessed (Lazy Initialization).
		 */
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginButton;

	@FindBy(css = ".toast-message")
	WebElement errorMessage;

	public void goToURL() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCataloguePage loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		ProductCataloguePage pc = new ProductCataloguePage(driver);
		return pc;
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
