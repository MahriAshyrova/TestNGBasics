package com.class2;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utils.CommonMethods;

//Identify Priority of Test Cases
//
//TC 1: OrangeHRM Verify Successful Login
//Step 1: Open browser and navigate to OrangeHRM
//Step 2: Enter valid UID and valid PWD and click login button
//Step 3: Verify user successfully logged in

//TC 2: OrangeHRM Add Employee
//Step 1: Click on PIM link and Add Employee
//Step 2: Provide Details and Save
//Step 3: Verify Employee is added 

//TC 3: OrangeHRM Verify Employee Details
//Step 1: Click on PIM link and Employee List
//Step 2: Search for employee by it id
//Step 3: Verify Employee details are displayed 
public class Homework1 extends CommonMethods {
	
	public static String employeeName = "Marysss Poppinssss";
	public static String employeeId = "777777";
	public static String employeeStatus = "Full-Time Contract";
	public static String include = "Current Employees Only";
	public static String supervisorName ="Lisasss Smith";
	public static String employeeJobTitle ="IT Executive";
	public static String subUnit ="IT";
	public static String expectedName = "Marysss";
	public static String expectedLastName = "Poppinssss";
	public static String firstName = "Marysss";
	public static String lastName = "Poppinssss";
	public static String UsName = "Marysss Poppinssss";
	public static String pswrd = "P1234cvcvcvxz#";
	public static String cnfmPass = "P1234cvcvcvxz#";
	public static String stText = "Enabled";
	Properties prop;
	
	@BeforeClass
	public void seUp() {
		setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com/");
	}

	//Step 1: Open browser and navigate to OrangeHRM
	//Step 2: Enter valid UID and valid PWD and click login button
	//Step 3: Verify user successfully logged in
	
	@Test(priority = 1)
	public void login() {
		String userN = "Admin";
		String passw = "admin123";
		WebElement userName = driver.findElement(By.xpath("//span[text()='Username']/preceding-sibling::input"));
		WebElement password = driver.findElement(By.xpath("//div[@id='divPassword']/input"));
		WebElement login = driver.findElement(By.xpath("//div[@id='divLoginButton']/input"));

		sendText(userName, userN);
		sendText(password, passw);
		login.click();
	}
	@Test(priority = 2)
	public void loginVerify() {

		String expectedText = "Dashboard";
		String actualText = driver.findElement(By.xpath("//div[@class='head']/h1")).getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualText, expectedText);
	}

	//Step 1: Click on PIM link and Add Employee
	//Step 2: Provide Details and Save
	//Step 3: Verify Employee is added 
	@Test(priority = 2)
	public void clickLink() {
		WebElement link = driver.findElement(By.linkText("PIM"));
		link.click();
	}

	@Test(priority = 3)
	public void addEmployee() {
		
		WebElement empName = driver.findElement(By.cssSelector("input#empsearch_employee_name_empName"));
		WebElement empID = driver.findElement(By.cssSelector("input#empsearch_id"));
		WebElement supervisorN = driver.findElement(By.cssSelector("input#empsearch_supervisor_name"));
		WebElement jTitle = driver.findElement(By.cssSelector("select#empsearch_job_title"));
		WebElement empStatus = driver.findElement(By.cssSelector("select#empsearch_employee_status"));
		WebElement incld = driver.findElement(By.cssSelector("select#empsearch_termination"));
		WebElement sUnit = driver.findElement(By.cssSelector("select#empsearch_sub_unit"));
		WebElement add = driver.findElement(By.cssSelector("input#btnAdd"));

		sendText(empName, employeeName);
		sendText(empID, employeeId);
		sendText(supervisorN, supervisorName);
		selectValueFromDD(jTitle, employeeJobTitle);
		selectValueFromDD(empStatus, employeeStatus);
		selectValueFromDD(incld, include);
		selectValueFromDD(sUnit, subUnit);
		add.click();
	}

	@Test(priority = 4)
	public void detailsSave() throws InterruptedException {

		WebElement fName = driver.findElement(By.id("firstName"));
		WebElement lName = driver.findElement(By.id("lastName"));
		WebElement eID = driver.findElement(By.id("employeeId"));
		WebElement file = driver.findElement(By.id("photofile"));
		WebElement checkbox = driver.findElement(By.id("chkLogin"));
		WebElement save = driver.findElement(By.xpath("//input[@id='btnSave']"));
		String filePath = "/Users/mahri/Desktop/image.png";

		sendText(fName, firstName);
		sendText(lName, lastName);
		sendText(eID, employeeId);
		file.click();
		file.sendKeys(filePath);
		checkbox.click();
		save.click();

		WebElement uName = driver.findElement(By.id("user_name"));
		WebElement pass = driver.findElement(By.id("user_password"));
		WebElement confPass = driver.findElement(By.id("re_password"));
		WebElement status = driver.findElement(By.xpath("//select[@id='status']"));

		save.submit();
		sendText(uName, UsName);
		sendText(pass, pswrd);
		sendText(confPass, cnfmPass);
		selectValueFromDD(status, stText);
		save.submit();
		Thread.sleep(2000);

		String actualName = driver.findElement(By.cssSelector("input[id='personal_txtEmpFirstName']")).getText();
		String actualLastName = driver.findElement(By.id("personal_txtEmpLastName")).getText();
		String actualID = driver.findElement(By.id("personal_txtEmployeeId")).getText();

		SoftAssert soft1 = new SoftAssert();
		soft1.assertEquals(actualName, expectedName);
		soft1.assertEquals(actualLastName, expectedLastName);
		soft1.assertEquals(actualID, employeeId);
	}
//		Step 1: Click on PIM link and Employee List
//		Step 2: Search for employee by it id
//		Step 3: Verify Employee details are displayed 

	@Test (priority=5)
	public void clickPimAndEmployeeList() {

		WebElement link = driver.findElement(By.linkText("PIM"));
		link.click();
		WebElement eList = driver.findElement(By.linkText("Employee List"));
		eList.click();
	}
	
	@Test (priority=6)
	public void searchEmployeeIDAndVerify() {
		WebElement empID = driver.findElement(By.cssSelector("input#empsearch_id"));
		sendText(empID, employeeId);
		WebElement search = driver.findElement(By.cssSelector("input[id='searchBtn']"));
		search.click();
		
		WebElement result=driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr"));
		result.getText();
		
		SoftAssert soft2= new SoftAssert();
		soft2.assertEquals(employeeId, result);
	}
	
	@AfterClass
	public void close() {
	driver.quit();
	}
}
