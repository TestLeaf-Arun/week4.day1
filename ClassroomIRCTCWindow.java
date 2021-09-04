package week4.day1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class ClassroomIRCTCWindow {
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option);
		driver.get("https://www.irctc.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	 // Click OK button in the dialog		
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		System.out.println(driver.getTitle());
		
	 // Click on FLIGHTS link		
		driver.findElement(By.xpath("//a[contains(text(),'FLIGHTS')]")).click();
		
	 // Convert the Set to List	
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		
	 // window Handle ---> Main window = 0; Child window = 1
		driver.switchTo().window(windowHandlesList.get(1)); // window handle of the child window
		System.out.println(driver.getTitle());
		
	 // Print the Customer care Email ID		
		String str = driver.findElement(By.xpath("(//a[@href='mailto:flights@irctc.co.in'])[1]")).getText();
		System.out.println("Customer care Email ID is " + str);
		
	 // Print the number of windows	
		System.out.println("Number of Windows:" + windowHandlesList.size());
		
		driver.switchTo().window(windowHandlesList.get(0)); // window handle of the main window
		System.out.println(driver.getTitle());
		driver.close(); // close only the 1st window
	}
}