package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//1) Go to https://www.nykaa.com/  
		
	    WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();	
//2) Mouseover on Brands and Search L'Oreal Paris
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder=new Actions(driver);
		builder.moveToElement(brands).perform();
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		

//3) Click L'Oreal Paris
		driver.findElement(By.linkText("L'Oreal Paris")).click();
	
//	4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		System.out.println("The title is " + driver.getTitle());
//	5) Click sort By and select customer top rated
		driver.findElement(By.className("sort-name")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		Thread.sleep(2000);
//	6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("(//span[text()='Hair Care'])[2]")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
//	7) Click->Concern->Color Protection
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
	
//	8)check whether the Filter is applied with Shampoo
		String filter = driver.findElement(By.xpath("//span[text()='Shampoo']")).getText();
		System.out.println(filter);
		Thread.sleep(2000);
//	9) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[contains(text(),'Oreal Paris Colour Protect Shampoo')]")).click();

//	10) GO to the new window and select size as 175ml
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window=new ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));
		Thread.sleep(2000);
		
		WebElement dropdown = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select source=new Select(dropdown);
		source.selectByIndex(1);
//	11) Print the MRP of the product
		Thread.sleep(2000);
	    System.out.print(driver.findElement(By.xpath("//div[@class='css-1d0jf8e'][1]")).getText());
		
		  
//	12) Click on ADD to BAG
	    driver.findElement(By.xpath("(//span[text()='Add to Bag'] )[1]")).click();
		
//	13) Go to Shopping Bag 
		driver.findElement(By.xpath("//button[@type='button'][1]")).click();
//	14) Print the Grand Total amount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    WebElement findElement = driver.findElement(By.xpath("//span[text()='₹259']"));
	    String grand = findElement.getText();
		System.out.println("The grand total is:"+ grand);
//	15) Click Proceed
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
//	16) Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
//	17) Check if this grand total is the same in step 14
		Thread.sleep(2000);
		String total = driver.findElement(By.xpath("(//div[contains(@class,'payment-details-tbl')])[4]")).getText();
		System.out.println("The grand total is:"+ total);
		if(grand.equals(total)) {
			System.out.println("The grand total is same");
		}
		else {
			System.out.println("The grand total is not same");
		}
//	18) Close all windows
       driver.quit();
	}

}
