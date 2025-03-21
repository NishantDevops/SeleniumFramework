package frameworkDesign.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frameworkDesign.pageObjects.LandingPage;

public class StandAloneTest_E2E {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		String country = "Ind";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage lp = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("nick.verma.krish@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mi4i@2015");
		driver.findElement(By.id("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		String toaster = driver.findElement(By.cssSelector("#toast-container")).getText();

		Assert.assertEquals(toaster, "Product Added To Cart");
		System.out.println("Assertion : Product Added To Cart");

		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

		List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean pMatch = cartProduct.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(pMatch);
		System.out.println("Assertion : Correct Product Added");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys(country);
		Thread.sleep(2000);
		List<WebElement> cs = driver.findElements(By.cssSelector(".ta-results button"));
		cs.stream().filter(c -> c.getText().trim().equalsIgnoreCase("India")).findFirst().ifPresent(WebElement::click);

		driver.findElement(By.cssSelector(".action__submit")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector(".toast-title")).getText(), "Order Placed Successfully");
		System.out.println("Assertion : Order placed successfully");

		js.executeScript("window.scrollTo(0,0)");
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
		String orderText = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
		String orderID = orderText.split(" ")[1].trim();

		System.out.println("Order ID is :" + orderID);

		driver.close();

	}

}
