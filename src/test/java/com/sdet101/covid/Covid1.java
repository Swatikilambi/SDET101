package com.sdet101.covid;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Covid1 {
	
	WebDriver driver;
	@Test(priority=1)
	public void openurl() throws InterruptedException
	{
		System.setProperty("WebDriver.chrome.driver", "C:\\Users\\swati\\eclipse-workspace\\SDET101_Selenium\\src\\test\\resources\\drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://westbengal.covidsafe.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		hospital_data(driver);
	}
	
	public void hospital_data(WebDriver driver) throws InterruptedException 
	{
		Actions actions= new Actions(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement load20= driver.findElement(By.xpath("//button[text()='Load next 20']"));
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		while(load20!=null)
		{
			Thread.sleep(1000);
			load20.click();
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}
		js.executeScript("window.scrollBy(document.body.scrollHeight,0)");
	
		int size = driver.findElements(By.xpath("//tbody/tr")).size();
			System.out.println("Size: "+size);
			for(int i=1;i<=size;i++)
			{
			WebElement hospital=driver.findElement(By.xpath("//tbody//tr["+i+"]//strong"));
			String hospitalname= hospital.getText();
			
			hospital.click();

			System.out.println("Hospital Name : "+ hospitalname);
			
			WebElement contact= driver.findElement(By.xpath("//span[contains(text(),'Phone: ')]"));
			String contactnumber=contact.getText();
			System.out.println("Contact Number : "+ contactnumber);
			
			WebElement address= driver.findElement(By.xpath("//span[contains(text(),'Address: ')]"));
			String addressdetails=address.getText();
			System.out.println("Address : "+ addressdetails);
			
			WebElement oxyzen1= driver.findElement(By.xpath("//tbody//tr["+i+"]//td[3]//span/span"));
			String withoxyzen=oxyzen1.getText();
			System.out.println("With Oxyzen beds : "+ withoxyzen);
			
			WebElement oxyzen2= driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]//span/span"));
			String withoutoxyzen=oxyzen2.getText();
			//System.out.println("Without Oxyzen Beds : "+ withoutoxyzen);
			
			WebElement icu1= driver.findElement(By.xpath("//tbody//tr["+i+"]//td[4]//span/span"));
			String withoutventilator=icu1.getText();
			//System.out.println("Without Ventilator Beds : "+ withoutventilator);
			
			WebElement icu2= driver.findElement(By.xpath("//tbody//tr["+i+"]//td[5]//span/span"));
			String withventilator=icu2.getText();
			System.out.println("With Ventilator Beds : "+ withventilator);
			
			int totalbeds= Integer.parseInt(withoxyzen)+Integer.parseInt(withoutoxyzen)+Integer.parseInt(withoutventilator)+Integer.parseInt(withventilator);
			System.out.println("Total Beds : "+ totalbeds);
		
		    hospital.click();
		    
		    actions.scrollByAmount(0, 120).perform();	
			}
		}
		
	//}
}

