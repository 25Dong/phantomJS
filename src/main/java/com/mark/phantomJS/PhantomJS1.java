package com.mark.phantomJS;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomJS1 {

	private static final String check_usert_agent_URL = "http://service.spiritsoft.cn/ua.html";
	private static final String INDEX_URL = "https://www.gslltd.com.hk/";
	private static final String INDEX_URL1 = "http://www.zim.com/pages/default.aspx";
	private static final String INDEX_URL2 = "http://www.goldstarline.com/p2p.aspx?hidSearch=true&hidFromHomePage=false&hidSearchType=4&id=166&l=4&txtPOL=INKRI&txtPOD=IDJKT&rb=Dep&txtDateFrom=23/08/2017&txtDateTo=07/12/2019";;
	private static final String INDEX_URL3 = "http://www.zim.com/pages/findyourroute.aspx?origincode=HKHKG%3b10&origincodetext=Hong+Kong+--+Hong+Kong&destcode=SGSIN%3b10&destcodetext=Singapore+--+Singapore&fromdate=23%2f11%2f2017&mode=1&searchdimention=0&todate=04%2f01%2f2018&schedule=view0";
	
	public static void main(String[] args) throws InterruptedException {
		DesiredCapabilities dcsps = new DesiredCapabilities();
		dcsps.setCapability("acceptSslCerts", true);
		dcsps.setCapability("takesScreenshot", true);
		dcsps.setCapability("cssSelectorsEnabled", true);
		dcsps.setJavascriptEnabled(true);
		dcsps.setCapability("phantomjs.page.settings.resourceTimeout", 1000);
		//不在加载图片
		dcsps.setCapability("phantomjs.page.settings.loadImages", false);
		dcsps.setCapability("phantomjs.page.settings.localToRemoteUrlAccessEnabled", true);
		//设置uer-agent
		dcsps.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.94 Safari/537.4");
		//
		dcsps.setCapability("phantomjs.page.settings.loadImages.Accept-Language","zh-CN,zh;q=0.9");
		//设置驱动路径
		dcsps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\aaMARK\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
		
		WebDriver indexDriver = new PhantomJSDriver(dcsps);
		indexDriver.get(INDEX_URL2);
		Thread.sleep(10000);
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

		Thread.sleep(10000);
		
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
