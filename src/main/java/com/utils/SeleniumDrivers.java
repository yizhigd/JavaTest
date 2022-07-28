package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDrivers {
    public static WebDriver driver;

    /**
     *
     * 用户调用这个类中的方法
     * 传入 chrome  firefox IE 等不同的参数，可以得到不同的浏览器驱动对象
     *
     *
     */
    public static WebDriver openBrowse(String brower) throws Exception {

        if(brower.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
            driver=new ChromeDriver();
        }else if(brower.equals("firefox")){
            System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            driver=new ChromeDriver();
        }else{
            throw new Exception("未知浏览器");

        }

        return driver;
    }

    public static void closed(){
        driver.quit();
    }
}
