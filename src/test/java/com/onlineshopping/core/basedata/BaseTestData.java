package com.onlineshopping.core.basedata;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.onlineshopping.util.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;

import java.io.IOException;
 
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
 
import org.openqa.selenium.TakesScreenshot;

public class BaseTestData {

	public WebDriver driver;
	public Properties prop;	
	public Logger APPLICATION_LOGS;
		
	
	public ExtentReports rep=ExtentManager.getInstance();
	public ExtentTest test;
	
	public void openBrowser(String browserType)   {
						
		            if(prop==null){
			prop=new Properties();
			
			try {
				FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//OR.properties");
				prop.load(fs);
				
			} catch (Exception e) {
				
				e.printStackTrace();
						
			}			
			
			 APPLICATION_LOGS=Logger.getLogger("devpinoyLogger");
			 APPLICATION_LOGS.debug("Starting");
			 APPLICATION_LOGS.debug("Exutting");
			 APPLICATION_LOGS.debug("Ending");
			 
		}
		
		            
		            if(browserType.equals("Mozilla")){
		    			driver=new FirefoxDriver();
		    		}
		    		
		    		else if(browserType.equals("Chrome")){
		    			System.setProperty("webdriver.chrome.driver",(System.getProperty("user.dir")+"//src//test//resources//chromedriver.exe"));			
		    			driver=new ChromeDriver();
		    		}
		    		
		    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		            driver.manage().window().maximize();
		            
	}
	
	
    public void navigate(String url){    	
       driver.get(prop.getProperty(url));
    }

     public void click(String Xpath){	
    	 driver.findElement(By.xpath(prop.getProperty(Xpath))).click();
    	 
   }

     public WebElement getElement(String locatorKey){
 		WebElement e=null;
 		try{
 		if(locatorKey.endsWith("_id"))
 			e = driver.findElement(By.id(prop.getProperty(locatorKey)));
 		else if(locatorKey.endsWith("_name"))
 			e = driver.findElement(By.name(prop.getProperty(locatorKey)));
 		else if(locatorKey.endsWith("_xpath"))
 			e = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
 		else{
 			reportFailure("Locator not correct - " + locatorKey);
 			Assert.fail("Locator not correct - " + locatorKey);
 		}
 		
 		}catch(Exception ex){
 			// fail the test and report the error
 			reportFailure(ex.getMessage());
 			ex.printStackTrace();
 			Assert.fail("Failed the test - "+ex.getMessage());
 		}
 		return e;
 	}
     
     
     
     
     public void typeData(String Xpath , String data){
    	 driver.findElement(By.xpath(prop.getProperty(Xpath))).clear();
    	 driver.findElement(By.xpath(prop.getProperty(Xpath))).sendKeys(prop.getProperty(data));
    	 
    	 
}

   public void selectData(String day_Xpath ,String day,String month_xpath,String month,String year_Xpath , String year){
	   driver.findElement(By.xpath(prop.getProperty(day_Xpath))).sendKeys(prop.getProperty(day));
	   driver.findElement(By.xpath(prop.getProperty(month_xpath))).sendKeys(prop.getProperty(month));
	   driver.findElement(By.xpath(prop.getProperty(year_Xpath))).sendKeys(prop.getProperty(year));
	   
	
}
   public void selectData(String Xpath ,String value){
	   driver.findElement(By.xpath(prop.getProperty(Xpath))).sendKeys(prop.getProperty(value));
	   
	   
   }
   
   
      public void moveOverMouse(String Xpath){
    	  Actions action = new Actions(driver);
    	  WebElement mainMenu = driver.findElement(By.xpath(prop.getProperty(Xpath)));    	  
    	  action.moveToElement(mainMenu).build().perform(); 
	   
   }
   
      public void handlingFrames(String iFrametag){
    	List<WebElement> frames = driver.findElements(By.tagName(iFrametag));
  		System.out.println("Total frames -> "+frames.size());
    	  
      }
       
   
   /**********************************Validations******************************/
   
   public boolean  verifyTitle(){
	   return false;
   }
   
   public boolean  isElementPresent(String locatorKey){
	   List<WebElement> elementList=null;
	   
	   if(locatorKey.endsWith("_id"))
		   elementList = driver.findElements(By.id(prop.getProperty(locatorKey)));
		else if(locatorKey.endsWith("_name"))
			elementList = driver.findElements(By.name(prop.getProperty(locatorKey)));
		else if(locatorKey.endsWith("_xpath"))
			elementList= driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		else{
			reportFailure("Locator not correct - " + locatorKey);
			Assert.fail("Locator not correct - " + locatorKey);
		}
	   if (elementList.size()==0)
	   return false;
	   else
	   return true;  
   }
   
   public boolean  validateMessage(String test,String expectedTextKey) {
	   String actualTest=driver.findElement(By.xpath(prop.getProperty(test))).getText();	   
	   String expectedTest=prop.getProperty(expectedTextKey);
	   if (actualTest.equals(expectedTest))
		   
	       return true;	   
	   else		
		   reportFailure("Data not Matching");
	   
	   return false;
	   
   }
   
   public boolean  textDisplayed(String locatorkey){
	  boolean result= driver.findElement(By.xpath(prop.getProperty(locatorkey))).isDisplayed();
	  	  
	  if (result==true){		  
		  System.out.println("Account Created");
		  return true;
	  }
	      
	   else
		
	   return false;	   
   }
   
   
   
   /**********************************Reporting******************************/
   
   
   public void reportPass(String msg){
	   
	   test.log(LogStatus.PASS, msg);
	   
   }
   
   public void reportFailure(String msg){	   
	   test.log(LogStatus.FAIL, msg);	   
	   Assert.fail(msg);
   }
 
  public void takeScreenshot(){
	  
	  Date d=new Date();
		String screenshotfile=d.toString().replace(":", "_").replace(" ", "_")+".png";
	  File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 
	  try {
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"Screenshots//"+screenshotfile));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	  test.log(LogStatus.PASS,"Screenshot->"+test.addScreenCapture("c:\\test.png"));
   }
 

   
}
