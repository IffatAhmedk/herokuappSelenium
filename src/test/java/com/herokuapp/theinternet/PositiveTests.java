package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	String url = "http://the-internet.herokuapp.com/login";

	@Test
	public void loginTest() {
		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// maximize driver window
		driver.manage().window().maximize();
		//sleep(3000);
		
		// Open test page
		driver.get(url);
		System.out.println("Page is open");
		//sleep(2000);

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
		
		//Close browser
		driver.quit();

	}

	private void sleep(long n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
