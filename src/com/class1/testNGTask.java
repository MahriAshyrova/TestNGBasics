package com.class1;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;
public class testNGTask extends CommonMethods {
//	TC 1: Swag Lab Title and Login Verification
//	@BeforeMethod should contain navigation to the URL and title verification
//	@Test should contain steps to login and “Product” word verification
//	@AfterMethod should logOut from the application and close the browser
	
	@BeforeMethod
	public void setUp() {
		
		setUpDriver("chrome", "https://www.saucedemo.com/index.html");
	}
	
	@Test
	public void loginAndProductVerification() {
	
		sendText(driver.findElement(By.cssSelector("input#user-name")), "standard_user");
		sendText(driver.findElement(By.cssSelector("input#password")), "secret_sauce");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		
		boolean logo=driver.findElement(By.xpath("//div[contains(text(),'Products')]")).isDisplayed();

		if(logo) {
			System.out.println("Product is displayed");
		}else {
			System.out.println("Product is not displayed");
		}	
	}
	
	@AfterMethod
	public void close() {
		driver.findElement(By.xpath("//button[text()='Open Menu']")).click();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#logout_sidebar_link")));
		driver.findElement(By.cssSelector("a#logout_sidebar_link")).click();
		driver.close();
	}
	
}
