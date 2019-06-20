package com.class2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class Homework2 extends CommonMethods{
//
//	TC 2: OrangeHRM Add Employee
//	Step 1: Click on PIM link and Add Employee
//	Step 2: Provide Details and Save
//	Step 3: Verify Employee is added 
	
	
	@BeforeClass
	
	public void setUp() {
		
	setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com/");	
	}
	
	@Test
	
	public void clickLink() {
	WebElement link =driver.findElement(By.linkText("PIM"));
	}
	
	
	@AfterClass
	
	public void close() {
	//driver.quit();	
	}
	
	
	
	
}
