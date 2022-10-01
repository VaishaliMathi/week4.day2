package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Jquery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://jqueryui.com/draggable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//1.draggable
		driver.switchTo().frame(0);
		WebElement target = driver.findElement(By.xpath("//div[contains(@class,'ui-widget-content ui')]"));
		System.out.println("The original loctions is "+  target.getLocation());
		Actions builder=new Actions(driver);
		builder.dragAndDropBy(target, 120, 60).perform();
		System.out.println("The dragged locations is "+target.getLocation());
		//2.droppable
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Droppable")).click();
		driver.switchTo().frame(0);
		WebElement target1 = driver.findElement(By.xpath("//div[contains(@class,'ui-widget-content ui')]"));
		WebElement destination = driver.findElement(By.xpath("//div[contains(@class,'ui-widget-header')]"));
		builder.dragAndDrop(target1, destination).perform();
		//3.resizable
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Resizable")).click();
		driver.switchTo().frame(0);
		WebElement resize = driver.findElement(By.xpath("//div[contains(@class,'ui-resizable-handle')][1]"));
		builder.dragAndDropBy(resize, 120, 0).perform();
		//4.selectable
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Selectable")).click();
		driver.switchTo().frame(0);
	    WebElement select1= driver.findElement(By.xpath("//li[contains(@class,'ui-widget-content ui')][1]"));
	    WebElement select2 = driver.findElement(By.xpath("//li[contains(@class,'ui-widget-content ui')][6] "));
	    builder.clickAndHold(select1).clickAndHold(select2).release().perform();
	    //5.sortable
	    driver.switchTo().defaultContent();
	    driver.findElement(By.linkText("Sortable")).click();
	    driver.switchTo().frame(0);
	    WebElement item1 = driver.findElement(By.xpath("//li[contains(@class,'ui-state-default ui')]"));
	    WebElement item5 = driver.findElement(By.xpath("//li[contains(@class,'ui-state-default ui')][5]"));
	    WebElement item7 = driver.findElement(By.xpath("//li[contains(@class,'ui-state-default ui')][7]"));
	    builder.dragAndDrop(item5, item1).dragAndDrop(item7, item5).perform();
	}

}
