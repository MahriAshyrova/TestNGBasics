package com.class4;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.CommonMethods;

//https://www.saucedemo.com/
//
//TC 1: Saucedemo Username1(parameters - username and password)
//Step 1: Open browser and navigate to Saucedemo
//Step 2: Enter username standard_user and enter password secret_sauce and click login button
//Step 3: Verify user successfully logged in
//
//TC 2: Saucedemo Username2(parameters - username and password)
//Step 1: Open browser and navigate to Saucedemo
//Step 2: Enter username problem_user and enter password secret_sauce and click login button
//Step 3: Verify user successfully logged in
//
//
//Note: Create BeforeMethod and AfterMethod annotations to open browser and logout from the application. 
// Create a xml file with parameters username and password .

public class SauceDemoParameterTask extends CommonMethods {
	
	@Parameters({ "browser", "url" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser, String url) {
		setUpDriver(browser, url);
	}

	@Parameters({ "userName", "password" })
	@Test(groups = "Smoke")
	public void UsernameOne(String userName, String password) {
		sendText(driver.findElement(By.cssSelector("input#user-name")), userName);
		sendText(driver.findElement(By.cssSelector("input#password")), password);
		driver.findElement(By.cssSelector("input.btn_action")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='product_label']")).getText(), "Products");
	}

	@Parameters({ "userName2", "password2" })
	@Test(groups = "Regression")
	public void UsernameTwo(String userName, String password) {
		sendText(driver.findElement(By.cssSelector("input#user-name")), userName);
		sendText(driver.findElement(By.cssSelector("input#password")), password);
		driver.findElement(By.cssSelector("input.btn_action")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='product_label']")).getText(), "Products");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
}
//	
//	@BeforeMethod (alwaysRun=true, groups="Smoke")
//	public void setUp() {
//		setUpDriver("chrome", "https://www.saucedemo.com/");
//	}
//	
//	@Test (groups="Smoke")
//	public void userName1() {
//		
//		sendText(driver.findElement(By.id("user-name")), "standard_user");
//		sendText(driver.findElement(By.id("password")), "secret_sauce");
//		driver.findElement(By.cssSelector("input.btn_action")).click();
//
//		String actual=driver.findElement(By.xpath("//div[text()='Products']")).getText();
//		Assert.assertEquals(actual, "Products");	
//	}
//	
//	@Test (groups="Regression")
//	public void userName2() {
//		sendText(driver.findElement(By.id("user-name")), "problem_user");
//		sendText(driver.findElement(By.id("password")), "secret_sauce");
//		driver.findElement(By.cssSelector("input.btn_action")).click();
//
//		String actual=driver.findElement(By.xpath("//div[text()='Products']")).getText();
//		Assert.assertEquals(actual, "Products");
//	}
//	
//	
//	@AfterMethod (alwaysRun=true, groups="Smoke")
//	public void tearDown() throws InterruptedException {
//		Thread.sleep(3000);
//		driver.quit();
//		
//	}
