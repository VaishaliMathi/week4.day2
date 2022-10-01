package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws Throwable {

// 1. Launch URL "http://leaftaps.com/opentaps/control/login"
	    WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().window().maximize();
//2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("DemoCSR");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
 //3. Click on Login Button using Class Locator
    	driver.findElement(By.className("decorativeSubmit")).click();
 //4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
 //5. Click on contacts Button
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();  	
// 6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click(); 
// 7. Click on Widget of From Contact
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();  
// 8. Click on First Resulting Contact
	    Set<String> windowHandles = driver.getWindowHandles();
		List<String> window1=new ArrayList<String>(windowHandles);
		driver.switchTo().window(window1.get(1));
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("vaishali");
        driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
        driver.findElement(By.xpath("(//a[@class='linktext'])[1]")).click();
 //9. Click on Widget of To Contact
      driver.switchTo().window(window1.get(0));
	  driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
//  10. Click on Second Resulting Contact
	    Set<String> windowHandles2 = driver.getWindowHandles();
	    List<String> window2=new ArrayList<String>(windowHandles2);
	    driver.switchTo().window(window2.get(1));
		Thread.sleep(2000);
		 driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("vaishu");
		 driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
		 driver.findElement(By.xpath("(//a[@class='linktext'])[1]")).click();
//  11. Click on Merge button using Xpath Locator
		 driver.switchTo().window(window2.get(0));
		 driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
 // 12. Accept the Alert
		 Alert alert = driver.switchTo().alert();
		alert.accept();
// 13. Verify the title of the page
		 String text = driver.findElement(By.xpath("//div[text()='View Contact']")).getText();
		 System.out.println(text);
	}

}
