package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class NegativeTests {
	String url = "http://the-internet.herokuapp.com/login";
	static WebDriver driver;
	
	@BeforeClass
	public void initiateDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		//open link
		driver.get(url);
		// maximize driver window
		driver.manage().window().maximize();
	}
	
	
	@Test(priority=1)
	public void negativeUsernameTest() {
		//get username textbox and enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("incorrect");
		
		//get password textbox and enter password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		
		//get login button and click
		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();
		
		//assert that url is still the same as before
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(url, currentURL, "Actual page URL is not the same as expected");	
		
		//assert that the error msg is there
		WebElement message = driver.findElement(By.className("error"));
		Assert.assertTrue(message.getText().contains("Your username is invalid"), "Expected msg it not: Your password is invalid");
	}
	
	@Test(groups = { "smoke", "negativeTest" })
	public void negativePasswordTest() {
			
		//get username textbox and enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		//get password textbox and enter password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword");
		
		//get login button and click
		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();
		
		//assert that url is still the same as before
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(url, currentURL, "Actual page URL is not the same as expected");	
		
		//assert that the error msg is there
		WebElement message = driver.findElement(By.className("error"));
		Assert.assertTrue(message.getText().contains("Your password is invalid"), "Expected msg it not: Your password is invalid");
	}
	
	@Test
	public void negativeUsernameaAndPasswordTest() {
			
		//get username textbox and enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("incorrect");
		
		//get password textbox and enter password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword");
		
		//get login button and click
		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		button.click();
		
		//assert that url is still the same as before
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(url, currentURL, "Actual page URL is not the same as expected");	
		
		//assert that the error msg is there
		WebElement message = driver.findElement(By.className("error"));
		Assert.assertTrue(message.getText().contains("Your username is invalid"), "Expected msg it not: Your password is invalid");
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
}
