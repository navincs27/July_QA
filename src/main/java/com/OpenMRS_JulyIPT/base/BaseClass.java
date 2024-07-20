package com.OpenMRS_JulyIPT.base;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {

 
	public static WebDriver driver;
	public static ExtentReports extentReports;
	public static File file;
	public static void browserLaunch(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("incognito");
			driver = new ChromeDriver(opt);
		}else if(browserName.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public static void UrlLaunch(String Url) {
		try {
			driver.get(Url);
		} catch (Exception e) {
			//System.out.println("Error Occurs:Line 27- Launch Url");
			e.printStackTrace();
		}
	}
	protected static void inputValues(WebDriver driver,WebElement Element, String Value ) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10)) // same as WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			.until(ExpectedConditions.visibilityOf(Element))// same as wait.until(ExpectedConditions.visibilityOf(Elements));
			.sendKeys(Value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void clickElement(WebElement Elements) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(15)) // same as WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			.until(ExpectedConditions.visibilityOf(Elements))// same as wait.until(ExpectedConditions.visibilityOf(Elements));
			.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void screenShot(String ImageName) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File Source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination  = new File("F:\\Java_Workspace\\AmazonTestNG\\Screenimage" + ImageName + "_ "+ timestamp+ ".png");
		try {
			FileHandler.copy(Source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void scrollBy(WebDriver driver, int xaxis, int yaxis) {
		((JavascriptExecutor)driver).executeScript("window.scrollBy("+ xaxis+", "+ yaxis+")");		
	}
	public static void clickjs(WebDriver driver,WebElement Elements ) {
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",Elements);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public static void dropDown(WebElement Elements, String ref, String value) {
		try {
			Select s = new Select(Elements);
			if(ref.equals("text")) {
				s.selectByVisibleText("text");
			}else if(ref.equals("value")) {
				s.selectByValue(value);
			} else if(ref.equals("index")) {
				s.selectByIndex(Integer.parseInt(value));	
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public static void extentReportStart(String location) {
		extentReports = new ExtentReports();
		File file = new File(location);
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	public void extentReportTearDown(String location) throws IOException {
		extentReports.flush();
		Object file = new File(location);
		Desktop.getDesktop().browse(((File) file).toURI());
	}

	public String takeScreenshot() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File scrfile = screenshot.getScreenshotAs(OutputType.FILE);
		File destfile = new File("Screenshort\\.png" + "_" + timeStamp + ".png");
		FileUtils.copyFile(scrfile, destfile);
		return destfile.getAbsolutePath();
	}

	public static void staticWait(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void exitBrowser(WebDriver driver) {
		driver.quit();

	}

	

}