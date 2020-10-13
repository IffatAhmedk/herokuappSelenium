package com.herokuapp.theinternet;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumWait {

	//String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
	static WebDriver driver;
	
	@Test (enabled=false)
	public void waitForHelloWorld() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
		driver.manage().window().maximize();
		
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		WebElement finishText = driver.findElement(By.cssSelector("#finish > h4"));
		
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
			wait.until(ExpectedConditions.visibilityOf(finishText));
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception catched" + e.getMessage());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
        String text = finishText.getText();
        
		Assert.assertTrue(text.contains("Hello World"), "Finish Text:" + text);
		
		driver.quit();
	}

	@Test
	public void waitForHelloWorld2() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().window().maximize();
		
		WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
		startButton.click();

		//WebElement finishText = driver.findElement(By.cssSelector("#finish > h4"));
		
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
			WebElement finishText = driver.findElement(By.cssSelector("#finish > h4"));
			String text = finishText.getText();
	        
			Assert.assertTrue(text.contains("Hello World"), "Finish Text:" + text);
			
			} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception catched" + e.getMessage());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
        
        //String text = finishText.getText();
        
		//Assert.assertTrue(text.contains("Hello World"), "Finish Text:" + text);
		
		driver.quit();
	}
}
