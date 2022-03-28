package com.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void initialDelay(){
        //intentionally added this as chrome/firefox containers take few ms to register
        //to hub - test fails saying hub does not have node!!
        //very rare
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    }
    
    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException  {
    
    	// BROWSER => chrome / firefox
        // HUB_HOST => localhost / 10.0.1.3 / hostname

    	String host = "localhost";
    	MutableCapabilities dc;
    	 
    	    if(System.getProperty("BROWSER") != null &&
    	            System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
    	        dc = new FirefoxOptions();
    	    }else{
    	        dc = new ChromeOptions();
    	    }
    	 
    	    if(System.getProperty("HUB_HOST") != null){
    	        host = System.getProperty("HUB_HOST");
    	    }
    	 
    	    String testName = ctx.getCurrentXmlTest().getName();
    	 
    	    String completeUrl = "http://" + host + ":4444/wd/hub";
    	    dc.setCapability("name", testName);
    	    this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
 
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        driver.quit();
    }  
}
