package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests {
	
	String url = "http://the-internet.herokuapp.com/login";
	static WebDriver driver;
	
	@Parameters({"browser"})
	@BeforeSuite(alwaysRun = true)
	public void initiateDriver(String browser) {
		switch(browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		
		//open link
		driver.get(url);
		// maximize driver window
		driver.manage().window().maximize();
	}
		
	@Test(priority=1, groups = {"NegativeTest"})
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
	
	@Test(priority = 2, groups = {"NegativeTest"})
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
	
	@Test(priority = 3, groups = {"NegativeTest"})
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
	

	@Test(priority=4, groups = {"PositiveTest"})
	public void positiveLoginTest() {
		
		// enter username
		WebElement username = driver.findElement(By.id("username"));	
		username.sendKeys("tomsmith");

		// enter password
		WebElement password = driver.findElement(By.id("password"));	
		password.sendKeys("SuperSecretPassword!");

		// click login button
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();
		//sleep(3000);
				
		// verification of url
		String expectedURL = "http://the-internet.herokuapp.com/secure";
		String actualURL = driver.getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL, "Actual page URL is not the same as expected");		
		
		// logout button visible
		WebElement logOutButton = driver.findElement(By.xpath("//a[@href='/logout']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Logout button is not visible");
		
		// successful login message
		WebElement message = driver.findElement(By.cssSelector("#flash"));
		String msgText = "You logged into a secure area!";
		//Assert.assertEquals(message.getText(), msgText, "Login message was not the same as expected");
		Assert.assertTrue(message.getText().contains(msgText), "Actual message is not same as expected message");
	}

	@AfterSuite(alwaysRun= true)
	public void closeBrowser() {
		driver.quit();
	}
	
}
