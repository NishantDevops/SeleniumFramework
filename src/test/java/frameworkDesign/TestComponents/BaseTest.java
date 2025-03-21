package frameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import frameworkDesign.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;

	public WebDriver initializeDriver() throws IOException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//frameworkDesign//resources//GlobalData.properties");
		Properties prop = new Properties();
		prop.load(fis);

		/*
		 * Ternary Operator to check if system is sending some value for the browser or
		 * not through Maven command If yes - use the first option
		 * i.e.System.getProperty("browser") , else use the second option
		 * i.e.prop.getProperty("browser");
		 */
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {

			ChromeOptions options = new ChromeOptions();

			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);
			
			//Use below to launch in fullscreen in case any flakyness is observed and scripts fail
			//driver.manage().window().setSize(new Dimension(1440, 900));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplicationURL() throws IOException {

		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goToURL();
		return lp;
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException {

		// 1. Read json file data to String
		String jsonData = Files.readString(Paths.get(filePath));
		// System.out.println(jsonData);

		// 2. Convert String data to HashMap using dependency Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<>() {
		});

		return data;
	}

	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "//screenshots//" + testCaseName + ".png";
		FileUtils.copyFile(src, new File(destination));

		return destination;
	}

}
