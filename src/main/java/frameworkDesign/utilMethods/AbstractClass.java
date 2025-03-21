package frameworkDesign.utilMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameworkDesign.pageObjects.CartPage;
import frameworkDesign.pageObjects.OrderPage;

public class AbstractClass {
	WebDriver driver;

	@FindBy(css = "[routerlink*=cart]")
	WebElement cartLink;

	@FindBy(css = "[routerlink*=myorders]")
	WebElement orderPageLink;

	public AbstractClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForWebElementToAppear(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public void waitForElementToDisappear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator)));
	}

	public CartPage goToCartPage() {
		cartLink.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}

	public OrderPage goToOrderPage() {
		orderPageLink.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}

	public void windowScrollBy() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
	}

	public void windowScrollTo() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)");
		Thread.sleep(2000);
	}
}
