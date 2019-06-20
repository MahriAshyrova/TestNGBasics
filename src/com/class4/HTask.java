package com.class4;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class HTask extends CommonMethods{
	

	@Parameters({ "browserr", "urll" })
	@BeforeClass
	public void login(String browser, String url) {
		setUpDriver(browser, url);
	}
   @Parameters({"giris","sifre"})
	@Test
	public void Task1(String giriss, String sifree) {
		driver.findElement(By.cssSelector("#ctl00_MainContent_username")).sendKeys(giriss);
		sendText(driver.findElement(By.cssSelector("#ctl00_MainContent_password")), sifree);
		driver.findElement(By.cssSelector("#ctl00_MainContent_login_button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.login_info")).isDisplayed(), true);	
	}

   @DataProvider()
	public Object [][] arrayy(){
		Object[][] data=new Object[2][7];
		
		data[0][0]="Mustafa";
		data[0][1]="24 crossing way";
		data[0][2]="Wayne";
		data[0][3]="New Jersey";
		data[0][4]="07412";
		data[0][5]="456456138789";
		data[0][6]="07/22";

		data[1][0]="Hasan";
		data[1][1]="12 classic";
		data[1][2]="Totowa";
		data[1][3]="New Jersey";
		data[1][4]="07872";
		data[1][5]="78974156122";
		data[1][6]="09/20";
		return data;
	}
   @Test(alwaysRun=true ,dataProvider="arrayy")
   public void Task2(String name, String adres, String city, String state, String zip,String CardNo,String expDate) {
	     driver.findElement(By.xpath("//a[text()='Order']")).click();
	        
	        Select Products=new Select(driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_ddlProduct\"]")));
	        Products.selectByVisibleText("FamilyAlbum");
	        
	        WebElement Musti=driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtQuantity\"]"));
	        Musti.clear();
	        Musti.sendKeys("10");;
	        driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtUnitPrice\"]")).clear();
	        driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtUnitPrice\"]")).sendKeys("40");
	        driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtDiscount\"]")).clear();
	        driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtDiscount\"]")).sendKeys("30");
	        
	        driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtName\"]")).sendKeys(name);
	        sendText(driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox2\"]")), adres);
	        sendText(driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox3\"]")), city);
	        sendText(driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox4\"]")), state);
	        sendText(driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox5\"]")), zip);
	        
	        List<WebElement> banka=driver.findElements(By.xpath("//ol/li/table/tbody/tr/td"));
	        for(WebElement bankalar: banka) {
	            if(bankalar.getText().contains("MasterCard")) {
	                bankalar.click();     
	            }}
	            sendText(driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox6\"]")), CardNo);
	            sendText(driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox1\"]")), expDate);
	  
	            driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_InsertButton\"]")).click();
	            driver.findElement(By.xpath("//a[text()='View all orders']")).click();
   }
	     @Test
	     public void Task2changeAdress() throws InterruptedException { 
	            List<WebElement> rows=driver.findElements(By.xpath("//*[@id=\"ctl00_MainContent_orderGrid\"]/tbody/tr"));
	            
	            
	            for (int i=1; i<=rows.size(); i++) {
	    			String rowText=driver.findElement(By.xpath("//table[contains(@id, 'orderGrid')]/tbody/tr["+i+"]")).getText();
	    			
	    			if(rowText.contains("Mustafa")) {
	    				driver.findElement(By.xpath("//table[contains(@id, 'orderGrid')]/tbody/tr["+i+"]/td[13]")).click();
	    			break;
	    			}
	            }
	            Thread.sleep(1000);
	            driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox2\"]")).clear();
	           Thread.sleep(1000);
	            driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox2\"]")).sendKeys("45 washington street");
	            Thread.sleep(1000);
	            driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_UpdateButton\"]")).click();
   }
  
	     @Test
	     public void task3() throws Exception {
	    	 List<WebElement> people= driver.findElements(By.xpath("//*[@id='ctl00_MainContent_orderGrid']/tbody/tr"));
	    	 for(WebElement person: people) {
	    		 if (person.getText().contains("Hasan") || person.getText().contains("Mustafa")) {
	    			 System.out.println("Wallah there is Mustafa and Hasan");
	    		 break;
	    		 }}
	    	 
	    	 TakesScreenshot camera=(TakesScreenshot)driver;
	 		File selfire=camera.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(selfire, new File("C:\\Users\\Mustafa\\eclipse-workspace\\TestGrbasics\\resimler\\List.jpg"));
	    	 
	     }
	@AfterClass 
	public void quit() {
	driver.quit();

	}

}