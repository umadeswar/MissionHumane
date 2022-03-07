

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import utils.ExcelUtils;

public class missionBackup {
	static WebDriver driver;
	static int j;
	static ExcelUtils xlUtils= new ExcelUtils("files/MissionHumane.xlsx");
	
	@Test
	public static void ScrapeWestBengal() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "/Users/eswar/Documents/chromedriver");

		driver = new ChromeDriver();
		driver.get("https://westbengal.covidsafe.in/");
		Thread.sleep(1000);
			
		JavascriptExecutor js = (JavascriptExecutor)driver;
		ArrayList<String> data = new ArrayList<String>();

		for(int j=1;j<=7;j++) {
			WebElement loadmore= driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
			js.executeScript("arguments[0].click();", loadmore);
		}
		
		clickAllRows(js);
		List<WebElement> row1 =driver.findElements(By.xpath("//table[@class='result-table table table-borderless table-hover']/tbody/tr"));
		writeData(1, row1.size());
		driver.close();

	}
	
	public static void clickAllRows(JavascriptExecutor js) {
		List<WebElement> allRows= driver.findElements(By.tagName("strong")); //.xpath("//div[@class='p-1']"));
		for(WebElement ele : allRows) {
			js.executeScript("arguments[0].click();", ele);
		}
	}
	
	public static void writeData(int start, int end) throws IOException {

		int currentRow = start;
		for (int i = start; i <= end; i++) {

			int FirstRow = 0;
			ArrayList<String> data = new ArrayList<String>();
			System.out.println(end);
			currentRow++;
			List<WebElement> cols = driver.findElements(By.xpath("//tr[1]/td[@class='text-center']"));
			System.out.println("Row:" + i);

			if ((i) % 2 != 0) {
				String row1Content = driver.findElement(By.xpath("//tr[" + i + "]/td/div[@class='p-1']")).getText();// row1.get(i).getText()
				data.add(row1Content);
				for (j = 1; j <= cols.size(); j++) {
					System.out.println("Size:" + cols.size());
					String a = driver.findElement(By.xpath("//tr[" + i + "]/td[@class='text-center'][" + j + "]"))
							.getText();
					System.out.println("Col Value:" + a);
					data.add(a);

				}
			}
			// System.out.println("Col:"+j);

			String b = driver.findElement(By
					.xpath("//table[@class='result-table table table-borderless table-hover']/tbody/tr[" + (i++) + "]"))
					.getText();
			System.out.println("Col Value:" + b);
			data.add(b);
			System.out.println("row inside col" + i);
			xlUtils.writeToExcel(data, "WestBengalSheet", currentRow);
		}
	}

}


//List<WebElement> allRows= driver.findElements(By.tagName("strong")); //.xpath("//div[@class='p-1']"));
//for(WebElement ele : allRows) {
//	js.executeScript("arguments[0].click();", ele);
//}
//List<WebElement> row1 =driver.findElements(By.xpath("//table[@class='result-table table table-borderless table-hover']/tbody/tr"));
//
//List <WebElement> col;
/*int currentRow = 0 ;
for(int i=1;i<=row1.size();i++) {
	
	int FirstRow = 0;
	
	System.out.println(row1.size());
	currentRow++;
	List<WebElement> cols = driver.findElements(By.xpath("//tr[1]/td[@class='text-center']"));
	System.out.println("Row:"+i);

		if((i)%2!=0) {
			String row1Content = driver.findElement(By.xpath("//tr["+i+"]/td/div[@class='p-1']")).getText();//row1.get(i).getText()
			data.add(row1Content);
		for(j=1;j<=cols.size();j++) {
			System.out.println("Size:"+cols.size());
			String a= driver.findElement(By.xpath("//tr["+i+"]/td[@class='text-center']["+j+"]")).getText();
			System.out.println("Col Value:"+a);
			data.add(a);
			
		}}
		//System.out.println("Col:"+j);
		
		String b=driver.findElement(By.xpath("//table[@class='result-table table table-borderless table-hover']/tbody/tr["+(i++)+"]")).getText();
		System.out.println("Col Value:"+b);
		data.add(b);
		System.out.println("row inside col"+i);

	
		xlUtils.writeToExcel(data,"WestBengalSheet",currentRow);

}*/
