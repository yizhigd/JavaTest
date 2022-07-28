
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

public class OpenChrome {
    WebDriver driver;

    @BeforeTest
    public void openChrome(){
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test(){
        driver.get("https://www.baidu.com");

        WebElement element = driver.findElement(By.id("kw"));
        element.sendKeys("007");
        driver.findElement(By.id("su")).click();

//        是否显示
//        Boolean bl = driver.findElement(By.id("su")).isDisplayed();
//        System.out.println("百度按钮是否存在：" + bl);
//        Assert.assertTrue(bl);

        //是否可以点击
//        Boolean bl = driver.findElement(By.id("su")).isEnabled();
//        System.out.println("能否点击：" + bl);
//        Assert.assertTrue(bl);

    }

    @Test
    public void screenShotFile(){
        driver.get("https://www.baidu.com");
        //截图并保存
        File picture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
//            FileUtils.copyFile(picture,new File("D:/p.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //循环输出
    @Test
    public void test02(){
        driver.get("https://www.baidu.com");
        //如何获取xpath,在chrome浏览器中定位到该位置代码，然后右键点击copy->copy xpath
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"s-top-left\"]"));
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getText());
            System.out.println(list.get(i).getTagName());
        }
    }

    @Test
    public void dataTest() throws Exception{
        driver.get("http://192.168.4.119:8080/DagShop06/");

        driver.findElement(By.xpath("/html/body/div/div[1]/div[3]/ol/li[2]/a")).click();
        Thread.sleep(2000);

        WebElement yonghu=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form/div[1]/div/input"));
        yonghu.sendKeys("aaaa");
        WebElement passw=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form/div[2]/div/input"));
        passw.sendKeys("222222");
        WebElement passw1=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form/div[3]/div/input"));
        passw1.sendKeys("222222");

        WebElement data = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form/div[7]/div/input"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].value='2022-07-25';",data);

        WebElement yzm=driver.findElement(By.xpath(" /html/body/div[3]/div/div[2]/form/div[8]/div[1]/input"));
        yzm.sendKeys("zxck");

    }

    //选择页面，页面切换
    @Test
    public void selectWindows() throws Exception{
        driver.get("http://www.baidu.com");
        //打开新闻页面
        driver.findElement(By.xpath("//*[@id=\"s-top-left\"]/a[1]")).click();
        Thread.sleep(1500);
//        //打开贴吧页面
//        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div/div[3]/div[1]/div/ul/li[1]/strong/a")).click();

        //获取当前页面的句柄
        String handle = driver.getWindowHandle();
        //获取所有页面的句柄
        for(String handles : driver.getWindowHandles()){
            System.out.println("当前页面句柄：" + handles);
            if(handles.equals(handle))
                continue;
            driver.switchTo().window(handles);
        }

        //选择原来的句柄，回到原来的页面
        System.out.println("原来页面句柄："+handle);
        driver.switchTo().window(handle);

    }
    @AfterTest
    public void closedchrome()throws InterruptedException{
        Thread.sleep(3000);//等待
        driver.quit();//完全退出浏览器
    }
}