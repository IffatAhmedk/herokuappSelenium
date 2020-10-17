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
	
	@Test
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

	@Test (priority = 1)
	public void dynamicControls() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://the-internet.herokuapp.com/dynamic_controls");
		driver.manage().window().maximize();
		
		WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
		WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(), 'Remove')]"));
		checkbox.click();
		removeButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		//Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(checkbox)), "Checkbox is still visible");
		//Assert.assertFalse(checkbox.isDisplayed());		
        
		Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkbox)));
		WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), 'Add')]"));
		
		addButton.click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@type='checkbox']")));
		
		checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
		
		Assert.assertTrue(checkbox.isDisplayed(), "Checkbox is not displayed, when it should be");
		
		driver.quit();
	}	

	@Test (priority = 1)
	public void disabledElementTest() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://the-internet.herokuapp.com/dynamic_controls");
		driver.manage().window().maximize();
		
		WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(), 'Enable')]"));
		WebElement textBox = driver.findElement(By.xpath("//input[@type='text']"));
		enableButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("message")));
		wait.until(ExpectedConditions.elementToBeClickable(textBox));
		
		textBox.sendKeys("Hello World");
		Assert.assertEquals(textBox.getAttribute("value"), "Hello World", "Field does not have text 'Hello World'");
				
		driver.quit();
	}

}
