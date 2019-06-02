package com.class2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utils.CommonMethods;

public class softAndHardAssertExample extends CommonMethods{
	
	
	@BeforeClass
	
	public void setUp() {
		setUpDriver("chrome","https://opensource-demo.orangehrmlive.com/");
		
	}
	
	@Test
	public void orangehrmLoginScreen() {
//		String title=driver.getTitle();
//		String ecpectedTitle="OrangeHRM";
//		SoftAssert soft=new SoftAssert();
//		soft.assertEquals(title, ecpectedTitle);
//		System.out.println("soft assert");
		
//		Assert.assertEquals(title, ecpectedTitle);
//		System.out.println("after hard assert");	
		
	}
	
	@Test
	public void logo() {
		boolean logo=driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed();
		SoftAssert soft2= new SoftAssert();
		soft2.assertTrue(logo);
		System.out.println("soft assert");
		soft2.assertAll();
//		Assert.assertTrue(logo);
//		System.out.println("hard assert");
	}
	

}
