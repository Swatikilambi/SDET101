package com.sdet101.covid;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;

public class CovidSafe {
	
	public static void getHospitalData() throws InterruptedException {
		
		ChromeOptions co = new ChromeOptions();
		co.setAcceptInsecureCerts(true);
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		
		JavascriptExecutor je = (JavascriptExecutor)driver;
		driver.get("https://westbengal.covidsafe.in/");
		
		int i = 1;
		
		
		
		do
		{
			
		int numberOfHospitals = driver.findElements(By.xpath("//tbody/tr")).size();
		
		for(;i<=numberOfHospitals; i++) {
			
		
			
		WebElement hospital = driver.findElement(By.xpath("//tbody/tr[" +i+ "]//strong"));
		String hospName = hospital.getText();
		
		hospital.click();
		Thread.sleep(1000);
		
		WebElement phoneElement = driver.findElement(By.xpath("//span[contains(text(),'Phone: ')] "));
		String phone = phoneElement.getText();
		
		WebElement addressElement = driver.findElement(By.xpath("//span[contains(text(),'Address: ')]"));
		String address = addressElement.getText();
		
		WebElement pinElement = driver.findElement(By.xpath("//span[contains(text(),'Pincode: ')]"));
		String pincode = pinElement.getText();
		
		WebElement withoutOxygenElement = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]/span"));
		String withoutOxygen = withoutOxygenElement.getText();
		
		WebElement withOxygenElement = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[3]/span"));
		String withOxygen = withOxygenElement.getText();
		
		WebElement withoutVentilatorElement = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[4]/span"));
		String withoutVentilator = withoutVentilatorElement.getText();
		
		WebElement withVentilatorElement = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[5]/span"));
		String withVentilator = withVentilatorElement.getText();
		
		int Totalbed = Integer.parseInt(withVentilator)+Integer.parseInt(withoutVentilator)+Integer.parseInt(withOxygen)+Integer.parseInt(withoutOxygen);
		
		System.out.println("Hospital name: "+hospName );
		System.out.println(phone);
		System.out.println(address);
		System.out.println(pincode);
		System.out.println("Total number of beds: "+ Totalbed);
		System.out.println("Beds without oxygen "+withoutOxygen );
		System.out.println("Beds with oxygen: "+withOxygen );
		System.out.println("Beds without ventilator: "+withoutVentilator );
		System.out.println("Beds with ventilator: "+withVentilator );
		
		
		
		hospital.click();
		
		Actions act = new Actions(driver);
		act.scrollByAmount(0, 120).perform();
		Thread.sleep(1000);
		
					
		
		}
		
		}while(clickLoadMore(driver));		
		
	}
	public static boolean clickLoadMore(WebDriver driver) throws InterruptedException {
		
		try
		{
			WebElement loadButton = driver.findElement(By.xpath("//button[text()='Load next 20']"));
		if(loadButton!=null) {
			loadButton.click();
			Thread.sleep(3000);
			return true;
		}
		return false;
		}
		catch(org.openqa.selenium.NoSuchElementException ex) {
			return false;
		}
	}
	
	
	public static void main(String args[])throws InterruptedException {
		
		getHospitalData();
	}
	
	

}
