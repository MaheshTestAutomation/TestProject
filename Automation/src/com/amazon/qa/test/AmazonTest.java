 package com.amazon.qa.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonTest {

	
	WebDriver driver
    WebElement menu;
    WebElement submenu;
    WebElement list;
    WebElement sortBY;
	
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/maheshbiradar/eclipse-workspace/amazonAutomation/driver/chromedriver");
		driver=new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	}
	
	@Test
		public void launchAppliation() throws InterruptedException, Exception {
			driver.get("https://www.amazon.com");
			String captchaVal = JOptionPane.showInputDialog("Please enter the captcha value:");
			driver.findElement(By.id("captchacharacters")).sendKeys(captchaVal);
			driver.findElement(By.className("a-button-text")).click();
			driver.findElement(By.xpath("//select[@id='searchDropdownBox']")).click();
			menu=driver.findElement(By.xpath("//option[contains(text(),'Electronics')]"));
			Actions action=new Actions(driver);
			action.moveToElement(menu).perform();
			driver.findElement(By.xpath("//option[contains(text(),'Electronics')]")).click();
			driver.findElement(By.xpath("//div[contains(@class,'nav-search-submit nav-sprite')]//input[contains(@class,'nav-input')]")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Television & Video')]")).click();
			driver.findElement(By.xpath("//span[@class='a-size-base a-color-base'][contains(text(),'Televisions')]")).click();
			list=driver.findElement(By.xpath("//div[@id='filters']//ul[1]"));
			list.findElement(By.xpath("//span[contains(text(),'32 Inches & Under')]")).click();
		//Selecting Options for sorting by price high to low
			WebElement element = driver.findElement(By.xpath("//select[@id='s-result-sort-select']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", element);
		    sortBY = driver.findElement(By.xpath("//option[contains(text(),'Price: High to Low')]"));
			Actions action1=new Actions(driver);
			action.moveToElement(sortBY).perform();
			driver.findElement(By.xpath("//option[contains(text(),'Price: High to Low')]")).click();
			List <WebElement> elements = driver.findElements(By.xpath("//*[@class='s-result-list s-search-results sg-row']/div"));
						
//return the first ‘N’ item prices and the item names as a list, based on the above search
		for(int i=1;i<elements.size();i++) {
			int x=i;
			String var1="//div[@class='s-result-list s-search-results sg-row']/div[";
			String var2="]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span";
			String xpath=var1+x+var2;
			String Name=driver.findElement(By.xpath(xpath)).getText();
			System.out.println(Name); //Prints the product names for the above search
			
			String var3="//div[@class='s-result-list s-search-results sg-row']/div[";
			String var4="]/div/span/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/a/span[1]";
			String xpath1=var3+x+var4;
			
		    if(driver.findElements(By.xpath(xpath1)).isEmpty()) {
		    	System.out.println("No pricing displayed for this " +i+ " product in the search results");
		    }
		    else {
		    	String Price=driver.findElement(By.xpath(xpath1)).getText();
				System.out.println(Price); 
			}
			}
		//For selecting the first product displayed in the search result
	    int z=1;
		String var5="//div[@class='s-result-list s-search-results sg-row']/div[";
		String var6="]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span";
		String xpath2=var5+z+var6;
		
	    driver.findElement(By.xpath(xpath2)).click();
//Selects the first element of the search results
			//driver.findElement(By.xpath("//span[contains(text(),'Soulaca 22 inches Smart Magic Mirror Android Frame')]")).click();	
//Sign in page title is verified			
			driver.findElement(By.xpath("//input[@id='add-to-wishlist-button-submit']")).click();
			String s1=driver.getTitle();
			System.out.println("Sign in page is displayed with title as " + s1);
			driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("amazontestpurpose@gmail.com");
			driver.findElement(By.xpath("//input[@id='continue']")).click();
			driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Apple@109");
			driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();	
			
	}
	
//End the test
@AfterMethod
public void tearDown() {
	driver.quit();
	
}
}
