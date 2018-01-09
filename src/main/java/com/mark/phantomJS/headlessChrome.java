package com.mark.phantomJS;

import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class headlessChrome {

	private static final String INDEX_URL = "https://www.gslltd.com.hk/";
	private static final String INDEX_URL1 = "http://www.zim.com/pages/default.aspx";
	private static final String INDEX_URL2 = "http://www.goldstarline.com/p2p.aspx?hidSearch=true&hidFromHomePage=false&hidSearchType=4&id=166&l=4&txtPOL=INKRI&txtPOD=IDJKT&rb=Dep&txtDateFrom=23/08/2017&txtDateTo=07/12/2019";;
	private static final String INDEX_URL3 = "http://www.zim.com/pages/findyourroute.aspx?origincode=HKHKG%3b10&origincodetext=Hong+Kong+--+Hong+Kong&destcode=SGSIN%3b10&destcodetext=Singapore+--+Singapore&fromdate=23%2f11%2f2017&mode=1&searchdimention=0&todate=04%2f01%2f2018&schedule=view0";
	
	public static void main(String[] args) {
		
		DesiredCapabilities dcsps =DesiredCapabilities.chrome();
		
		
		
		
		
		dcsps.setCapability("acceptSslCerts", true);
		dcsps.setCapability("takesScreenshot", true);
		dcsps.setCapability("cssSelectorsEnabled", true);
		dcsps.setJavascriptEnabled(true);
		dcsps.setCapability("phantomjs.page.settings.resourceTimeout", 1000);
		dcsps.setCapability("phantomjs.page.settings.loadImages", true);
		dcsps.setCapability("phantomjs.page.settings.localToRemoteUrlAccessEnabled", true);

		dcsps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\aaMARK\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		
		WebDriver indexDriver = new PhantomJSDriver(dcsps);
		indexDriver.get(INDEX_URL2);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(indexDriver.getPageSource());
		
		String fromLocation="HONG KONG--HONG KONG(HKHKG)";
		String toLocation = "SINGAPORE--SINGAPORE(SGSIN)";
		
		String fromInputValueJs = "var input=document.getElementById('from_pt_tags');input.value='" +
				fromLocation + "'";
		((JavascriptExecutor) indexDriver).executeScript(fromInputValueJs);

		String toInputValueJs = "var input=document.getElementById('to_pt_tags');input.value='" +
				toLocation + "'";
		((JavascriptExecutor) indexDriver).executeScript(toInputValueJs);

		indexDriver.findElement(By.id("button3")).click();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriver resultDriver = null;
		String currentWindow = indexDriver.getWindowHandle();
		Set<String> handles = indexDriver.getWindowHandles();
		for (String handle : handles) {
			if (handle != null) {
				if (!handle.equals(currentWindow)) {
					resultDriver = indexDriver.switchTo().window(handle);
				}
			}
		}

		System.out.println(resultDriver.getPageSource());


	}

}
