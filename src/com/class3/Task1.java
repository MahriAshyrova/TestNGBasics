package com.class3;

import org.openqa.selenium.By;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class Task1 extends CommonMethods {
	
	@BeforeGroups ({"Smoke", "Regression1", "Regression2"})
	public void setUp() {
	String url="https://www.saucedemo.com/";
	setUpDriver("chrome", url);
		
	}
	
	@Test (groups="Smoke")
	public void userOne() {
		
		sendText(driver.findElement(By.cssSelector("input#user-name")), "standard_user");
		sendText(driver.findElement(By.cssSelector("input.form_input")), "secret_sauce");
		driver.findElement(By.cssSelector("input.btn_action")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}
	
	
	@Test (groups="Regression1")
	public void userTwo() {
		
		sendText(driver.findElement(By.cssSelector("input#user-name")), "problem_user");
		sendText(driver.findElement(By.cssSelector("input.form_input")), "secret_sauce");
		driver.findElement(By.cssSelector("input.btn_action")).click();
	}
	
	
	@Test (groups="Regression2")
	public void userThree() {
		
		sendText(driver.findElement(By.cssSelector("input#user-name")), "performance_glitch_user");
		sendText(driver.findElement(By.cssSelector("input.form_input")), "secret_sauce");
		driver.findElement(By.cssSelector("input.btn_action")).click();
	}
	@AfterGroups 
	public void tearDown() {
	driver.quit();	
		
	}
	
	

}
