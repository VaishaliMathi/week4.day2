package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, Throwable {
		// TODO Auto-generated method stub
		//    1.Load the uRL https://www.amazon.in/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//	2.search as oneplus 9 pro 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		//	3.Get the price of the first product
		Thread.sleep(2000);
		WebElement mobile = driver.findElement(By.xpath("(//span[text()='54,999'])[1]"));
		System.out.println("The price is "+ mobile.getText());
		//	4. Print the number of customer ratings for the first displayed product
		WebElement number = driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])[3]"));
		System.out.println("The number of customer ratings is "+ number.getText());
		Thread.sleep(2000);
		//	5. Mouse Hover on the stars
		WebElement star = driver.findElement(By.xpath("(//i[contains(@class,'a-icon a-icon-star')])[3]"));
		Actions builder=new Actions(driver);
		builder.moveToElement(star).perform();
		//	6. Get the percentage of ratings for the 5 star.
	    String text = driver.findElement(By.xpath("//table[@id='histogramTable']//tr[1]//td[3]")).getText();
	    System.out.println(text);
		Thread.sleep(2000);
		//	7. Click the first text link of the first image
		driver.findElement(By.xpath("(//img[@class='s-image'])[3]")).click();
		//	8. Take a screen shot of the product displayed
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File destin=new File("./amazon/snapsh/screenshot.img");
		FileUtils.copyFile(screenshotAs, destin);
		Thread.sleep(2000);
		//	9. Click 'Add to Cart' button
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window=new ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));
		driver.findElement(By.id("add-to-cart-button")).click();
		//	10. Get the cart subtotal and verify if it is correct.
		WebElement cartTotal = driver.findElement(By.xpath("//span[text()='54,999']"));
		System.out.println("The cart sub total is "+ cartTotal.getText());
	}

}
