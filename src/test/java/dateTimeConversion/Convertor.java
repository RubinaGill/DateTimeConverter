package dateTimeConversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Convertor {

	WebDriver driver;

	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//a[text()='English']")).click();
	}

	public String getCurrentDateTime() {
		WebElement searchTextBox = driver.findElement(By.xpath("//input[@name='q']"));
		searchTextBox.sendKeys("current date and Time in Abu Dhabi" + Keys.ENTER);
		String currentTime = driver.findElement(By.xpath("(//div[contains(@class, 'card-section')])[1]/div[1]"))
				.getText();
		String currentDate = driver.findElement(By.xpath("(//div[contains(@class, 'card-section')])[1]/div[2]"))
				.getText();
		return currentDate + currentTime;
	}

	public void printFormattedDate(String currentTimestamp) {
		DateFormat inputFormat = new SimpleDateFormat("E, dd MMMM yyyy (z)HH:mm a");
		DateFormat outputFormat = new SimpleDateFormat("dd-MMM-yy HH:mm a");
		try {
			System.out.println(outputFormat.format(inputFormat.parse(currentTimestamp)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void closeBrowser() {
		driver.quit();
	}

	@Test
	public void getConvertedTimestamp() {
		openBrowser();
		printFormattedDate(getCurrentDateTime());		
		closeBrowser();
	}

}
