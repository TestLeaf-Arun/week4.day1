package week4.day1;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class LeafgroundFrames {
	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");		
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");		
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	//  Take Screenshot of Click me button of 1st frame
		WebElement frame1 = driver.findElement(By.xpath("//div[@id='wrapframe']/iframe"));
		driver.switchTo().frame(frame1);
		WebElement clickmebutton = driver.findElement(By.xpath("//button[@id='Click']"));
		File src = clickmebutton.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/clickmebutton.png");
		FileUtils.copyFile(src, dst);
		driver.switchTo().defaultContent();
		
	//  Find the number of frames visible to the main page (excluded nested frames count)
		List<WebElement> framelist = driver.findElements(By.tagName("iframe"));
		int numberofframes = framelist.size();
		System.out.println("Number of Frames visible to the main page: " + numberofframes);
	}
}