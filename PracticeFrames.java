package week4.day1;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
public class PracticeFrames {
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");		
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");		
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	//  Frame1 to enter the Topic text box
		WebElement nestedframe1 = driver.findElement(By.xpath("//iframe[@id='frame1']"));
		driver.switchTo().frame(nestedframe1);
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Not a friendly topic");
		
	//  Frame3 to click the check box	
		driver.switchTo().frame("frame3");
		driver.findElement(By.xpath("//input[@id='a']")).click();
		driver.switchTo().parentFrame();    // comes out of frame3 & goes to parent frame(frame1)
		WebElement topictext = driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input"));
		topictext.clear();
		topictext.sendKeys("Sorry, it is friendly");
		driver.switchTo().defaultContent();
		
	//  Frame2 to select the dropdown option
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='frame2']"));
		driver.switchTo().frame(frame2);
		WebElement drop1 = driver.findElement(By.xpath("//select[@id='animals']"));
		Select drpdwn1 = new Select(drop1);
		drpdwn1.selectByValue("big baby cat");
		driver.switchTo().defaultContent();
	}
}