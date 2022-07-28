import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class FireFox {

    WebDriver driver;
    @Test
    public void openFireFox() throws Exception{
        System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        driver = new FirefoxDriver();

        driver.get("http://192.168.4.119:8080/DagShop06/");

        driver.findElement(By.xpath("/html/body/div/div[1]/div[3]/ol/li[2]/a")).click();
        Thread.sleep(2000);

        WebElement data = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form/div[7]/div/input"));
        ((JavascriptExecutor) data).executeScript("arguments[0].value='2022-7-25';",data);

    }

    @AfterTest
    public void closeFireFox() throws Exception{
        Thread.sleep(3000);
        driver.quit();
    }
}
