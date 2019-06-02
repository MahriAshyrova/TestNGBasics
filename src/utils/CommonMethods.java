package utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CommonMethods {
	
	public static WebDriver driver;
	   public static void setUpDriver(String browser, String url) {
		   
		   if(browser.equalsIgnoreCase("chrome")) {
		  System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver");
		  	driver = new ChromeDriver();
		  	// for windows 
		  	// System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");

		   }else if (browser.equalsIgnoreCase("firefox")) {
		  System.setProperty("webdriver.gecko.driver", "src/drivers/geckodriver");
		   driver= new FirefoxDriver();
		   }else {
			   System.out.println("The invalid browser!");
		   }
		   
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get(url);  
	   }
	   
	   /**
		 * Method that will check the specified date in a calendar
		 * @param String calendarCellsXpath, String dateToChoose
		 */
		
		public static void calendarDateToPick(String calendarCellsXpath, String dateToChoose) {

		//geting all cells
				List <WebElement> cells=driver.findElements(By.xpath(calendarCellsXpath));
				for (WebElement cell: cells) {
					//geting text of each cell
				String cellText=cell.getText();
					if (cellText.equals(dateToChoose)) {
						cell.click();
				}}}
		// A method that will click on
		public static void click(WebElement element) {
		element.click();
		}
		// A method that will submit the button	
		public static void submit(WebElement element) {
		element.submit();

		}	
		
	public static void radioButtonToSelect(List<WebElement> element, String buttonToSelect)	{
		
		List<WebElement>radioList=element;
		System.out.println("The total number of radio buttons are: "+radioList.size());	
		for(WebElement radioButton:radioList) {
			if(radioButton.isEnabled()) {
			String value=radioButton.getText();	
				if(value.equalsIgnoreCase(buttonToSelect))	{
					radioButton.click();
				System.out.println(buttonToSelect+" has been succesfully selected");
			}else {
				System.out.println(buttonToSelect+" has been succesfully selected");
				}			
		}
	}
}
			
	   /**
		 * Method that will check if radio buttons are enabled and select the spedified radio button by index
		 * @param String nameValue, boolean radioValue, int index 
		 */
	   public static void radioButtonToSelect(String nameValue, boolean radioValue, int index) {
		List<WebElement>radioList=driver.findElements(By.name(nameValue));
		 //label[contains(text(),'Martial Status:')] 
		for(WebElement radioButton:radioList) {
			if(radioButton.isEnabled()) {
				radioButton.click();	
			}}
		radioValue = radioList.get(0).isSelected();
		if(radioValue==true) {
			radioList.get(1).click();
			}else {
				radioList.get(index).click();
			}
	   }
	   /**
		 * Method that will Method that will check if radio buttons are enabled and select the spedified radio buttons by value
		 * @param String nameValue, String attribute, String radioValueToSelect
		 */
	   public static void radioButtonToSelect (String xPath, String attributeValue, String radioValueToSelect) {
		 //input[contains(@name, 'cardList')] 
	   List<WebElement>cardList=driver.findElements(By.xpath(xPath));		
		for(WebElement card:cardList) {
			if(card.isEnabled()) {
				String value=card.getAttribute(attributeValue);
				//System.out.println("Radio button values are: "+value);
				if(value.equals(radioValueToSelect)) {
					card.click();
			}}}}	
	   
	   
	   /**
		 * Method that will Method that will check if check boxes are enabled and select the spedified check box
		 * @param String xpathLocator, String attribute, String checkBoxValueToSelect
		 */
	   public static void checkBoxToSelect(List<WebElement> element, String attribute, String checkBoxValueToSelect) {	
		   List<WebElement>checkBoxList=element;	
		   for(WebElement checkBox:checkBoxList) {
			   if(checkBox.isEnabled()) {
				   checkBox.click();	
				}
				String checkBoxValue=checkBox.getAttribute(attribute);
				System.out.println("The check box values are: "+checkBoxValue);
				if(!checkBoxValue.equals(checkBoxValueToSelect)) {
					checkBox.click();	
				}}	}


//   public static void radioIsEnabled (String nameValue) {
//		   
//		   List<WebElement>radioList=driver.findElements(By.name(nameValue));
//			System.out.println("The total number of radio buttons are: "+radioList.size());
//			boolean IsEnabled =false;
//			for(WebElement radio:radioList) {
//			if(radio.isEnabled()) {
//					radio.click();
//				}} }	
//   
	 public static void selectValueFromDD(WebElement element, String text) {
	        Select select = new Select(element);
	        List<WebElement> options = select.getOptions();
	        boolean isSelected=false;
	        for (WebElement option : options) {
	            String optionText = option.getText();
	            if (optionText.equals(text)) {
	                select.selectByVisibleText(text);
		        	System.out.println("Option with text "+text+" is selected");
	                isSelected=true;
	                break;
	            }
	        }
	        if(!isSelected) {
	        	System.out.println("Option with text "+text+" is not available");
	        }
	    }

	    public static void selectValueFromDD(WebElement element, int index) {
	        Select select = new Select(element);
	        List<WebElement> options = select.getOptions();
	        if (options.size() > index) {
	            select.selectByIndex(index);
	        }else {
	            System.out.println("Invalid index has been passed");
	        }
	    }
	    
	    public static void sendText(WebElement element, String value) {
	        element.clear();
	        element.sendKeys(value);
	    }

	    /**
		 * Method will accept alert
		 * @throws NoAlertPresentException if alert is not present
		 */
			public static void acceptAlert() {
				try {
					Alert alert = driver.switchTo().alert();
					alert.accept();
				} catch (NoAlertPresentException e) {
					System.out.println("Alert was not present");
				}
			}
			/**
			 * Method will dismiss alert
			 * @throws NoAlertPresentException if alert is not present
			 */
			public static void dismissAlert() {
				try {
					Alert alert=driver.switchTo().alert();
					alert.dismiss();
				}catch (NoAlertPresentException e) {
					System.out.println("Alert was not present");
				}
			}
			/**
			 * Method will get text of an alert
			 * @throws NoAlertPresentException if alert is not present
			 * @return String text
			 */
			public static String getAlertText() {
		
				try {
					Alert alert=driver.switchTo().alert();
					return alert.getText();
				}catch (NoAlertPresentException e) {
					System.out.println("Alert was not present");
					return null;
				}
		
			}
			/**
			 * Method that will switch control to the specified frame
			 * @param frame id or frame name
			 */
			public static void switchToFrame(String idOrName) {
				try {
					driver.switchTo().frame(idOrName);
				}catch(NoSuchFrameException e) {
					System.out.println("Frame is not present");
				}
			}
			/**
			 * Method that will switch control to the specified frame
			 * @param frame element
			 */
			public static void switchToFrame(WebElement element) {
				try {
					driver.switchTo().frame(element);
				}catch(NoSuchFrameException e) {
					System.out.println("Frame is not present");
				}
			}
			/**
			 * Method that will switch control to the specified frame
			 * @param frame index
			 */
			public static void switchToFrame(int index) {
				try {
					driver.switchTo().frame(index);
				}catch(NoSuchFrameException e) {
					System.out.println("Frame is not present");
				}}  
} 
	    
