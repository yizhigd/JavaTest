import com.utils.Action;
import com.utils.SeleniumDrivers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test01 {
    SeleniumDrivers sd = new SeleniumDrivers();
    WebDriver driver;
    Action a;

    @BeforeTest
    public void openBrowser() throws Exception {
        driver = sd.openBrowse("chrome");
    }
    @Test
    public void testg() throws Exception {
        driver.get("http://www.baidu.com");

        a.sendKeys(By.id("kw"), "python");
        a.click(By.id("su"));


    }

    @AfterTest
    public void closed() throws InterruptedException {
        Thread.sleep(2700);
        sd.closed();
    }

}
