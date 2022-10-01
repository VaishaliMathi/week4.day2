package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundDrag {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				WebDriverManager.chromedriver().setup();
				ChromeDriver driver=new ChromeDriver();
				driver.get("https://www.leafground.com/drag.xhtml");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				//1.draggable
				WebElement drag = driver.findElement(By.xpath("//div[contains(@class,'ui-panel-titlebar ui')][1]"));
				System.out.println(drag.getLocation());
				Actions builder=new Actions(driver);
				builder.dragAndDropBy(drag, 250,0).perform();
				System.out.println(drag.getLocation());
				//2.droppable
				WebElement drop = driver.findElement(By.xpath("//span[text()='Droppable Target']"));
				WebElement target = driver.findElement(By.xpath("(//div[contains(@class,'ui-panel-content ')])[3]"));
				builder.dragAndDrop(target, drop).perform();
				//3.draggable column
				WebElement name = driver.findElement(By.xpath("(//table[@role='grid'])[1]//thead//tr//th[1]"));
				WebElement quality= driver.findElement(By.xpath(" (//table[@role='grid'])[1]//thead//tr//th[3]"));
				builder.dragAndDrop(name, quality).perform();
				String text = driver.findElement(By.xpath("//span[text()='Columns reordered']")).getText();
				System.out.println(text);
				//4.draggable row
				Thread.sleep(10000);
				WebElement row2 = driver.findElement(By.xpath("(//table[@role='grid'])[2]//tr[2]"));
				WebElement row8 = driver.findElement(By.xpath("(//table[@role='grid'])[2]//tr[8]"));
				builder.dragAndDrop(row2, row8).perform();
			//5.resizable
			    WebElement drag1 = driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-e']"));
			    builder.dragAndDropBy(drag1, 20, 0).perform();
			    //6.progress bar
			    String text2 = driver.findElement(By.className("ui-progressbar-label")).getText();
			    System.out.println("The inital progress is "+text2);
			    driver.findElement(By.xpath("//span[text()='Start']")).click();
			    Thread.sleep(4000);
			    //7.range slide
			    WebElement slide = driver.findElement(By.xpath("//span[contains(@class,'ui-slider-handle')][1]"));
			    builder.dragAndDropBy(slide, 70, 0).perform();
			   
	}

}
