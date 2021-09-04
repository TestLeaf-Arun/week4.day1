package week4.day1;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class ServiceNowFrames {
	public static void main(String[] args) throws Throwable {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");		
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://dev113545.service-now.com/");		
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	//  Enter User name		
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		
	//  Enter Password	
		driver.findElement(By.id("user_password")).sendKeys("w6hnF2FRhwLC");
		
	//  Click Login	
		driver.findElement(By.id("sysverb_login")).click();
		
	// Search "incident" Filter Navigator	
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident");
		Thread.sleep(2000);
		
	//  Click All	
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='All'])[2]")).click();
		
	//  Click New button	
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();
		
	//  Select a value for Caller & enter value for short description
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		System.out.println(driver.getTitle());
		System.out.println("Number of Windows:" + windowHandlesList.size());
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		driver.switchTo().window(windowHandlesList.get(0));
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		driver.switchTo().frame(frame2);
		driver.findElement(By.id("incident.short_description")).sendKeys("Description is important for an incident");
		
	//  Read the incident number and Save it as a variable
		String incidentnumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println("Incident number:" + incidentnumber);
		
	//  Click Submit button
		driver.findElement(By.id("sysverb_insert")).click();
		
	//  Search the same incident number in the next search screen
		WebElement searchtext = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		searchtext.sendKeys(incidentnumber);
		searchtext.sendKeys(Keys.ENTER);
		
	//  Verify the incident is created successfully
		String resultincident = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
			if (incidentnumber.equalsIgnoreCase(resultincident)) {
				System.out.println("Incident is created successfully");
			} else {
				System.out.println("Incident is not created");
			}
		System.out.println(driver.getTitle());
		
	//  Snapshot of created incident
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/incident.png");
		FileUtils.copyFile(src, dst);
	}
}