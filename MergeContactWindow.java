package week4.day1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
public class MergeContactWindow {
	public static void main(String[] args) throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");		
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");		
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");  
		driver.findElement(By.id("password")).sendKeys("crmsfa");			 
		driver.findElement(By.className("decorativeSubmit")).click();		 
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();				   // Click Contacts
		driver.findElement(By.linkText("Merge Contacts")).click();			   // Click Merge Contacts
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();     // Click Widget of From Contact
		
	//  Click 1st Resulting Contact
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		String windowHandles = windowHandlesList.get(1);    // get the child window
		driver.switchTo().window(windowHandles);            // switching to child window
		driver.findElement(By.xpath("(//table[@class='x-grid3-row-table']//tr/td)[1]//a[1]")).click();
		driver.switchTo().window(windowHandlesList.get(0)); //switching back to main window from child window
		
	//  Click Widget of To Contact	
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		
	//  Click 2nd Resulting Contact
		Set<String> windowHandlesSet1 = driver.getWindowHandles();
		List<String> windowHandlesList1 = new ArrayList<String>(windowHandlesSet1);
		driver.switchTo().window(windowHandlesList1.get(1));
		driver.findElement(By.xpath("((//table[@class='x-grid3-row-table'])[2]//tr/td)[1]//a")).click();
		driver.switchTo().window(windowHandlesList1.get(0));  // switching back to main window
		
	//  Click Merge button	
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		
   	 // Switch the control to the current active dialog
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		
     //	Verify Title of the page - View Contact | opentaps CRM
		String title ="View Contact | opentaps CRM";
		String resultingTitle = driver.getTitle();
		if (title.equals(resultingTitle)) {
			System.out.println("In the View Contact page");
		} else
			System.out.println("Not in the View Contact page");
	}
}