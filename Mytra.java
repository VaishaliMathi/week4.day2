package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.compress.archivers.dump.DumpArchiveEntry.TYPE;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Mytra {

	public static void main(String[] args) throws IOException, Throwable {
		// TODO Auto-generated method stub
		//1) Open https://www.myntra.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	//		2) Mouse hover on MeN 
		WebElement men = driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		Actions builder=new Actions(driver);
		builder.moveToElement(men).perform();
	//		3) Click Jackets 
		driver.findElement(By.xpath("(//a[text()='Jackets'])[1]")).click();
	//		4) Find the total count of item 
		WebElement count = driver.findElement(By.xpath("//span[@class='title-count']"));
		System.out.println("The total count of item is "+count.getText());
		Thread.sleep(2000);
	//		6) Check jackets
		driver.findElement(By.xpath("(//label[@class='common-customCheckbox vertical-filters-label'])[1]")).click();
	//		7) Click + More option under BRAND
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		Thread.sleep(2000);
	//		8) Type Duke and click checkbox
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']")).sendKeys("Duke");
		driver.findElement(By.xpath("(//label[@class=' common-customCheckbox'])[1]")).click();
	//		9) Close the pop-up x
		driver.findElement(By.xpath("//span[contains(@class,'myntraweb-sprite FilterDirectory')]")).click();
		Thread.sleep(2000);
	//		10) Confirm all the Coats are of brand Duke
	//		    Hint : use List 
		List<WebElement> brand = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		for (int i = 0; i < brand.size(); i++) {
			System.out.println(brand.get(i).getText());
		}
	//		11) Sort by Better Discount
		Thread.sleep(2000);
		driver.findElement(By.className("sort-sortBy")).click();
		driver.findElement(By.xpath("(//label[@class='sort-label '])[4]")).click();
		
	//		12) Find the price of first displayed item
		driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]")).getText();
	//		Click on the first product
		driver.findElement(By.xpath("//img[@alt='Duke Men Green Bomber Jacket']")).click();
		Thread.sleep(2000);
	//		13) Take a screen shot
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> newWindow=new ArrayList<String>(windowHandles);
		driver.switchTo().window(newWindow.get(1));
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destin=new File("./snapsh/myntra.png");
		FileUtils.copyFile(source, destin);
	//		14) Click on WishList Now
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
	//		15) Close Browser
        driver.close();
	}

}
