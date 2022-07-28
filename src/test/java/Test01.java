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
        driver.get("http://localhost:81/general/ERP/LOGIN/");

        a.sendKeys(By.xpath("//*[@id=\"username\"]"), "admin");
        a.click(By.xpath("//*[@id=\"loginForm\"]/table/tbody/tr/td[3]/table[4]/tbody/tr[1]/td[1]/input"));

    }

    @AfterTest
    public void closed() throws InterruptedException {
        Thread.sleep(2700);
        sd.closed();
    }

}
