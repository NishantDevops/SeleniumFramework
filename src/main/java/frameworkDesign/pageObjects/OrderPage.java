package frameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import frameworkDesign.utilMethods.AbstractClass;

public class OrderPage extends AbstractClass {
	WebDriver driver;

	@FindBy(xpath = "//tbody/tr/td[2]")
	List<WebElement> orderProductsList;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public Boolean verifyOrderText(String productName) {
		Boolean titleMatch = orderProductsList.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return titleMatch;

	}

}
