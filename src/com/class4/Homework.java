package com.class4;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.CommonMethods;

//Identify Priority of Test Cases
//
//http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx
//
//TC 1: WebOrder Verify Successful Login ( )
//Step 1: Open browser and navigate to WebOrder
//Step 2: Enter valid username, enter valid password and click on the  login button
//Step 3: Verify user successfully logged in
//
//TC 2: WebOrder Creating and verifying Users( )
//Step 1: Create Two users and fill out all the required fields
// Step 2: Verify that user one name appears within the table 
// Step 3: Edit user one  and update user one’s State, assert the new updated State is displayed and save the changes.
// Step 4: Verify that user two name appears within the table 
// Step 5: Edit user two and update user two’s City and assert the new updated City is displayed and save the changes.
//
//TC 3: WebOrder verifying Users( )
// Step 1 : Assert Both User one and User Two are displayed
//
//Note: Create BeforeClass and AfterClass annotations to open browser and logout from the application. 
//The creation of users two should depend on the creation of users one.
//The validation both users should depend on the creation of both users. Create xml file and share a screenshot of the test.

public class Homework extends CommonMethods{
	
	public static WebDriver driver;
	
	@Parameters({"browser", "URL"})
	@BeforeClass
	public void setUp(String brow, String url) {
		setUpDriver(brow, url);
	}
	
	
	@DataProvider
	public Object[][] setData() {
		
		Object[][] data=new Object[2][10];
		
		data[0][0]="FamilyAlbum";
		data[0][1]="1";
		data[0][2]="Jane";
		data[0][3]="123 Lemon street";
		data[0][4]="Austin";
		data[0][5]="TX";
		data[0][6]="124357";
		data[0][7]="MasterCard";
		data[0][8]="9876543213";
		data[0][9]="04/20";

		data[1][0]="ScreenSaver";
		data[1][1]="2";
		data[1][2]="Mary";
		data[1][3]="345 Strawberry street";
		data[1][4]="Fairax";
		data[1][5]="VA";
		data[1][6]="234567";
		data[1][7]="Visa";
		data[1][8]="3456789023";
		data[1][9]="10/20";
		return data;	
	}
	
	@Parameters ({"userName", "password"})
	@Test (priority=0)
	public void loginAndVerify(String uName, String pwd) {
		
		sendText(driver.findElement(By.cssSelector("input[id*='MainContent_username']")), uName);
		sendText(driver.findElement(By.cssSelector("input[id*='MainContent_password']")), pwd);
		click(driver.findElement(By.cssSelector("input[id*='MainContent_login_button']")));
		
		boolean textIsDisplayed=driver.findElement(By.xpath("//h1[text()='Web Orders']")).isDisplayed();
		Assert.assertTrue(textIsDisplayed);	
	}
		
	
	 
	@Test (priority=1, dataProvider="setData")
	public void getData(String pr, String qt, String cN, String st, String c, String s, String z, String cName, String cNum, String ex) {
		
		WebElement order=driver.findElement(By.linkText("Order"));
		WebElement product=driver.findElement(By.xpath("//select[contains(@id,'MainContent_fmwOrder_ddlProduct')]"));
		WebElement quantity=driver.findElement(By.xpath("//input[contains(@id,'MainContent_fmwOrder_txtQuantity')]"));
		WebElement cusName=driver.findElement(By.xpath("//input[contains(@id,'MainContent_fmwOrder_txtName')]"));
		WebElement street=driver.findElement(By.xpath("//input[contains(@id,'MainContent_fmwOrder_TextBox2')]"));
		WebElement city=driver.findElement(By.xpath("//input[contains(@id,'MainContent_fmwOrder_TextBox3')]"));
		WebElement state=driver.findElement(By.xpath("//input[contains(@id,'MainContent_fmwOrder_TextBox4')]"));
		WebElement zip=driver.findElement(By.xpath("//input[contains(@id,'MainContent_fmwOrder_TextBox5')]"));
		List<WebElement> radioButton=driver.findElements(By.xpath("//table[contains(@id,'MainContent_fmwOrder_cardList')]"));
		WebElement cardNum=driver.findElement(By.xpath("//input[contains(@id,'MainContent_fmwOrder_TextBox6')]"));
		WebElement expDate=driver.findElement(By.xpath("//input[contains(@id,'MainContent_fmwOrder_TextBox1')]"));
		WebElement process=driver.findElement(By.linkText("Process"));
		
		click(order);
		selectValueFromDD(product, pr);
		selectValueFromDD(quantity, qt);
		sendText(cusName, cN);
		sendText(street,  st);
		sendText(city, c);
		sendText(state, s);
		sendText(zip, z);
		radioButtonToSelect(radioButton, cName);
		sendText(cardNum, cNum);
		sendText(expDate, ex);
		click(process);		
	}
		
	@Test (priority=2, dependsOnMethods = "getData")
	public void verifyAndEdit() {
		boolean isDisplayed=false;
		List<WebElement> rows =driver.findElements(By.xpath("//table[contains(@id,'MainContent_orderGrid')]/tbody/tr"));
		for(int i=0; i<=rows.size(); i++) {
			String actualText=driver.findElement(By.xpath("//table[contains(@id,'MainContent_orderGrid')]/tbody/tr["+i+"]")).getText();
			if(actualText.contains("Jane")) {
				driver.findElement(By.xpath("//table[contains(@id,'MainContent_orderGrid')]/tbody/tr/td[13]")).click();
				System.out.println("The order with Jane is displayed");
				isDisplayed=true;
				break;
			}
		}
		if(!isDisplayed) {
			System.out.println("The order with Jane is not displayed");
		}
		WebElement state=driver.findElement(By.xpath("//input[contains(@id,'MainContent_fmwOrder_TextBox4')]"));
		String actualText=state.getText();
		sendText(state, "NY");
		driver.findElement(By.linkText("Process")).click();
		String expectedText="NY";
		verify(actualText, expectedText);
		
		
		for(int i=0; i<=rows.size(); i++) {
			String actualText2=driver.findElement(By.xpath("//table[contains(@id,'MainContent_orderGrid')]/tbody/tr["+i+"]")).getText();
			if(actualText2.contains("Mary")) {
				driver.findElement(By.xpath("//table[contains(@id,'MainContent_orderGrid')]/tbody/tr/td[13]")).click();
				System.out.println("The order with Mary is displayed");
				isDisplayed=true;
				break;
			}
		}
		if(!isDisplayed) {
			System.out.println("The order with Mary is not displayed");
		}
		WebElement city=driver.findElement(By.xpath("//input[contains(@id,'MainContent_fmwOrder_TextBox3')]"));
		String actualCity=city.getText();
		sendText(city, "Reston");
		driver.findElement(By.linkText("Process")).click();
		String expectedCity="Reston";
		verify(actualText, expectedCity);	
		
		
		for(WebElement row:rows) {
			String rowText=row.getText();
			if(rowText.contains("Jane") && rowText.contains("Mary")) {
				System.out.println("Jane and Mary are displayed");
				isDisplayed=true;
				break;
			}
		}
		if(!isDisplayed) {
			System.out.println("Jane and Mary are not displayed");
		}	
	}
	

	@AfterClass (alwaysRun=true)
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}