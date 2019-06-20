package utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

	public static WebDriver driver;

	public static void setUpDriver(String browser, String url) {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver");
			driver = new ChromeDriver();
			// for windows
			// System.setProperty("webdriver.chrome.driver",
			// "src/drivers/chromedriver.exe");

		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/drivers/geckodriver");
			driver = new FirefoxDriver();
		} else {
			System.out.println("The invalid browser!");
		}

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get(url);
	}

	public static void waitForElementBeVisible(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void waitForElementBeVisible(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public static void waitForElementBeClickable(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void waitForElementBeClickable(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	/**
	 * A method that will click on the specified element
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {
		element.click();

	}

	/**
	 * A method that will check if radio buttons are enabled and select the
	 * specified radio button value
	 * 
	 * @param element
	 * @param buttonToSelect
	 * @throws InterruptedException
	 */

	public static void radioButtonToSelect(List<WebElement> element, String buttonToSelect) {
		List<WebElement> radioList = element;
		for (WebElement radioButton : radioList) {
			if (radioButton.isEnabled()) {
				String text = radioButton.getText();
				if (text.equals(buttonToSelect)) {
					if (!radioButton.isSelected()) {
						radioButton.click();
						break;
					} else {
						System.out.println("Radio button is selected by default");
					}
				}
			} else {
				System.out.println("The radio buttons are not enabled");
			}
		}
	}

	/**
	 * A method that will select the specified month and verify that it was entered
	 * succesfully
	 * 
	 * @param element
	 * @param dateToSelect
	 */

	public static void getDate(List<WebElement> element, String dateToSelect) {
		boolean isSelected = false;
		List<WebElement> cells = element;
		for (WebElement cell : cells) {
			String cellText = cell.getText();
			if (cellText.equals(dateToSelect)) {
				cell.click();
				System.out.println("The date has been entered succesfully");
				isSelected = true;
				break;
			}
		}
		if (!isSelected) {
			System.out.println("The date has not been entered succesfully");
		}
	}

	/**
	 * A method that will check if check boxes are enabled, selected and select the
	 * specified value
	 * 
	 * @param element
	 * @param attribute
	 * @param checkBoxValueToSelect
	 * @throws InterruptedException
	 */

	public static void checkBoxToSelect(List<WebElement> element, String attribute, String checkBoxValueToSelect) {
		List<WebElement> checkBoxes = element;
		for (WebElement checkbox : checkBoxes) {
			if (checkbox.isEnabled()) {
				if (checkbox.getAttribute(attribute).equals(checkBoxValueToSelect)) {
					if (!checkbox.isSelected()) {
						checkbox.click();
						break;
					} else {
						System.out.println("Checkbox is selected by default");
					}
				}
			} else {
				System.out.println("Checkbox is not enabled");
			}
		}

	}

	/**
	 * A method that will pick the specified date from a calendar
	 * 
	 * @param element
	 * @param dateToChoose
	 */

	/**
	 * A method that will submit the button
	 * 
	 * @param element
	 */
	public static void submit(WebElement element) {
		element.submit();

	}

	/**
	 * Method that will verify if actualValue equals expectedValue
	 * 
	 * @param actualValue
	 * @param expectedValue
	 */
	public static void verify(String actualValue, String expectedValue) {
		if (!actualValue.equals(expectedValue)) {
			System.out.println("Item is displayed");
		} else {
			System.out.println("Item is not displayed");
		}
	}

	/**
	 * Method that will Method that will check if radio buttons are enabled and
	 * select the spedified radio buttons by value
	 * 
	 * @param String nameValue, String attribute, String radioValueToSelect
	 */
	public static void radioButtonToSelect(List<WebElement> element, String attribute, String radioButtonToSelect) {
		List<WebElement> radioButtonList = element;
		for (WebElement radioButton : radioButtonList) {
			if (radioButton.isEnabled()) {
				if (radioButton.getAttribute(attribute).equals(radioButtonToSelect)) {
					if (!radioButton.isSelected()) {
						radioButton.click();
						break;
					} else {
						System.out.println("Radio button is selected by default");
					}
				}
			} else {
				System.out.println("Radio button is not enabled");
			}
		}

	}

	/**
	 * Method that will Method that will check if check boxes are enabled and select
	 * the spedified check box
	 * 
	 * @param String xpathLocator, String attribute, String checkBoxValueToSelect
	 */

	public static void selectValueFromDD(WebElement element, String text) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		boolean isSelected = false;
		for (WebElement option : options) {
			String optionText = option.getText();
			if (optionText.equals(text)) {
				select.selectByVisibleText(text);
				System.out.println("Option with text " + text + " is selected");
				isSelected = true;
				break;
			}
		}
		if (!isSelected) {
			System.out.println("Option with text " + text + " is not available");
		}
	}

	public static void selectValueFromDD(WebElement element, int index) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		if (options.size() > index) {
			select.selectByIndex(index);
		} else {
			System.out.println("Invalid index has been passed");
		}
	}

	public static void sendText(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * Method will accept alert
	 * 
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
	 * 
	 * @throws NoAlertPresentException if alert is not present
	 */
	public static void dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
		}
	}

	/**
	 * Method will get text of an alert
	 * 
	 * @throws NoAlertPresentException if alert is not present
	 * @return String text
	 */
	public static String getAlertText() {

		try {
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
			return null;
		}

	}

	/**
	 * Method that will switch control to the specified frame
	 * 
	 * @param frame id or frame name
	 */
	public static void switchToFrame(String idOrName) {
		try {
			driver.switchTo().frame(idOrName);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}

	/**
	 * Method that will switch control to the specified frame
	 * 
	 * @param frame element
	 */
	public static void switchToFrame(WebElement element) {
		try {
			driver.switchTo().frame(element);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}

	/**
	 * Method that will switch control to the specified frame
	 * 
	 * @param frame index
	 */
	public static void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}

	public static void takeScreenshot(String folderName, String fileName) {
		TakesScreenshot camera = (TakesScreenshot) driver;
		File src = camera.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("screenshots/" + folderName + "/" + fileName + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Selfie not taken");
		}
	}

	public static void scrollDown(int pixels) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, " + pixels + ")");
	}

	public static void scrollUp(int pixels) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -" + pixels + ")");
	}

	public static void javaScriptClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}

}
