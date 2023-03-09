package Covid_assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Covid {
	
	WebDriver driver;
	@Test(priority=1)
	public void openurl() throws InterruptedException
	{
		//											   C:\workspace\SDET101_Selenium\src\test\resources\drivers
		System.setProperty("WebDriver.chrome.driver", "C:\\workspace\\SDET101_Selenium\\src\\test\\resources\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(options);
		
		driver.get("https://westbengal.covidsafe.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		hospital_data(driver);
	}
	
	public void hospital_data(WebDriver driver) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		int newListSize=0;
		WebElement load20= null;
		do 
		{
			System.out.println("Starting......................");
			int size = driver.findElements(By.xpath("//tbody/tr")).size();
			System.out.println("Size: "+size);
			System.out.println("newListSize"+newListSize);
			if(size!=20) {
				newListSize=size-20;
				
			}
			for(int i=newListSize+1;i<=size;i++)
			{
			Actions actions= new Actions(driver);
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
		   // Thread.sleep(500);
		    
		    actions.scrollByAmount(0, 120).perform();	
		}
			try {
			load20= driver.findElement(By.xpath("//button[text()='Load next 20']"));
			
			
			if(null!=load20)
			load20.click();
			}catch(org.openqa.selenium.NoSuchElementException e) {
				System.out.println("Reached end of the page");
				load20=null;
			}
			
			
		}while(load20!=null);
		
			
	}
}
