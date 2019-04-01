package lab2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

//from selenium.webdriver.support.wait import WebDriverWait
//from selenium.webdriver.support import expected_conditions as EC

@RunWith(Parameterized.class)
public class Lab2 {
	private String stuId;
	private String github;
	private String passwd;
	private String stuName;
	private WebDriver driver;
	private String baseUrl;
	private WebDriverWait wait = null;

	public Lab2(String id, String passwd, String name, String git) {
		this.stuId = id;
		this.passwd = passwd;
		this.stuName = name;
		this.github = git;
	}

	@Before
	public void setUp() throws Exception {
		String driverPath = "D:/c++/workspace/lab2/src/src/resource/driver/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		baseUrl = "http://121.193.130.195:8800";
		// 121.193.130.195:8800
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 59);
	}

	@Parameters
	public static Collection<Object[]> getData() throws BiffException, IOException {

		File f = new File("D:/软件测试名单.xls");
		Workbook book = Workbook.getWorkbook(f);//
		Sheet sheet = book.getSheet(0); 
		Object[][] objects = new Object[142][4];
		for (int i = 2; i < sheet.getRows(); i++) {			
			objects[i-2][0] = sheet.getCell(1, i).getContents();//id
			objects[i-2][1] = sheet.getCell(1, i).getContents().substring(4);//password
			objects[i-2][2] = sheet.getCell(2, i).getContents();//name
			objects[i-2][3] = sheet.getCell(3, i).getContents();//gitUrl
		}
		
		List<Object[]> strlist = Arrays.asList(objects);
		return strlist;
	}

	@Test
	public void lab2() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.name("id")).clear();
		driver.findElement(By.name("id")).sendKeys(this.stuId);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(this.passwd);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_login")));
			driver.findElement(By.id("btn_login")).click();
		} catch (Exception e) {
			Thread.sleep(20000);
			driver.navigate().refresh();
			Thread.sleep(20000);
		} finally {
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("student-id")));
			WebElement checkId = driver.findElement(By.id("student-id"));
			assertEquals(this.stuId, checkId.getText());
			WebElement checkName = driver.findElement(By.id("student-name"));
			assertEquals(this.stuName, checkName.getText());
			WebElement checkUrl = driver.findElement(By.id("student-git"));
			assertEquals(this.github, checkUrl.getText());
		}
	}

	@After
	public void tearDown() throws Exception {
		// driver.quit();
		// String verificationErrorString = verificationErrors.toString();
		// if (!"".equals(verificationErrorString)) {
		// fail(verificationErrorString);
		// }
	}
}