package frameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkDesign.utilMethods.AbstractClass;

public class ProductCataloguePage extends AbstractClass {
	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productsList;

	By productsBy = By.cssSelector(".mb-3");
	By productNameBy = By.tagName("b");
	By addToCartBy = By.xpath("//div[@class='card-body']/button[2]");
	By toastContainerBy = By.cssSelector("#toast-container");
	By toasterContainerTextBy = By.cssSelector("#toast-container");
	By animatingBy = By.cssSelector(".ng-animating");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return productsList;
	}

	public WebElement getProductByName(String productName) {
		WebElement prodName = getProductList().stream()
				.filter(product -> product.findElement(productNameBy).getText().equals(productName)).findFirst()
				.orElse(null);
		return prodName;
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName).findElement(addToCartBy);
		prod.click();
		waitForElementToAppear(toastContainerBy);
		waitForElementToDisappear(animatingBy);
	}

}
