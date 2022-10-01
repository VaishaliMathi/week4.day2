package week4.day2;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.utils.FileUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class SnapDeal {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		//    1. Launch https://www.snapdeal.com/
		WebDriverManager.chromedriver().setup();
		ChromeOptions ch=new ChromeOptions();
		ch.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(ch);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();	
		//	2. Go to Mens Fashion
		WebElement findElement = driver.findElement(By.xpath("//a[contains(@class,'menuLinks leftCate')]/span[2]"));
		Actions builder=new Actions(driver);
		builder.moveToElement(findElement).perform();
		//	3. Go to Sports Shoes
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click();
		//	4. Get the count of the sports shoes
	String count = driver.findElement(By.xpath("(//a[contains(@class,'child-cat-node')]/div[2])[2]")).getText();
	System.out.println("The count of sports shoe is"+  count);
		//	5. Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		//	6. Sort by Low to High
		driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
		driver.findElement(By.xpath("//li[@class='search-li'][2]")).click();
		//	7. Check if the items displayed are sorted correctly
		String text = driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).getText();
		System.out.println(text);
		if(text.contains("Low to High")) {
			System.out.println("item displayed correctly");
		}
		else {
			System.out.println("item not displayed correctly");
		}
		//	8.Select the price range (900-1200)
		WebElement priceFrom = driver.findElement(By.name("fromVal"));
		priceFrom.clear();
		priceFrom.sendKeys("900");
		WebElement priceTo = driver.findElement(By.name("toVal"));
		priceTo.clear();
		priceTo.sendKeys("1200");
		
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn btn')] ")).click();
		//	10 verify the all applied filters 
		String text2 = driver.findElement(By.xpath("//div[@class='filters']")).getText();
        System.out.println(text2);
		//	11. Mouse Hover on first resulting Training shoes
		WebElement shoe = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		builder.moveToElement(shoe).perform();
		//	12. click QuickView button
		driver.findElement(By.xpath("//div[contains(@class,'center quick-view')]")).click();
		//	13. Print the cost and the discount percentage
		System.out.println(driver.findElement(By.xpath("//div[@class=' pdp-e-i-PAY clearfix']")).getText());
		//	14. Take the snapshot of the shoes.
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File destin=new File("./snapsh/screenshot.png");
		FileUtils.copyFile(screenshotAs, destin);
		//	15. Close the current window
		driver.close();
		//	16. Close the main window
		driver.quit();
	}

}
