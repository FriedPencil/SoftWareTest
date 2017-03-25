package com.selenium;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
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
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class WebDriverTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String number = null;
	private String email = null;
	
	public WebDriverTest(String number, String email){
		this.number = number;
		this.email = email;
	}

	@Before
	public void setUp() {
		System.setProperty("webdriver.firefox.bin", "D:/firefox/firefox.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://121.193.130.195:8080";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
    @Parameters
    public static Collection<Object[]> getData() throws IOException {
      Object[][] obj = new Object[117][];
  	  try {  
          BufferedReader reader = new BufferedReader(new FileReader("inputgit.csv"));
          reader.readLine();
          String line = null;
          int count = 0;
          while((line=reader.readLine())!=null){  
              String item[] = line.split(",");
              String first = item[0];  
              String last = item[item.length-1];
              obj[count] = new Object[]{first,last};
              count++; 
          }  
      } catch (Exception e) {  
          e.printStackTrace();  
      }
  	  return Arrays.asList(obj);
    }
    
	@Test
	public void testSelenium(){
	    driver.get(baseUrl + "/");
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys(this.number);
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys(this.number.substring(4));
	    driver.findElement(By.id("submit")).click();
	    assertEquals(this.email,driver.findElement(By.xpath(".//*[@id='table-main']/tr[3]/td[2]")).getText());
	}
	
	@After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
