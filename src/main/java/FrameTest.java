import com.mysql.Supplier;
import com.mysql.SupplierDaoImp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FrameTest {
    WebDriver driver;

    @BeforeTest
    public void openChrome(){
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void frameTest(){
        driver.get("http://localhost:81/general/ERP/LOGIN/");//打开登录页面

        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("admin");//登录用户名
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/table/tbody/tr/td[3]/table[4]/tbody/tr[1]/td[1]/input")).click();//点击登录

        driver.switchTo().frame("function_panel_index");//左侧一级
        driver.switchTo().frame("menu_main");//二级

        driver.findElement(By.xpath("/html/body/table[7]/tbody/tr/td[3]/a")).click();//点击供应商管理
        driver.findElement(By.xpath("//*[@id=\"MENU_66d\"]/tbody/tr/td/table[1]/tbody/tr/td[4]/a")).click();//点击供应商

        driver.switchTo().defaultContent();//退出Frame

        driver.switchTo().frame("table_index");//右侧一级
        driver.switchTo().frame("table_main");//二级

        driver.findElement(By.xpath("//*[@id=\"table\"]/thead[1]/tr/td/table/thead/tr/td[1]/input[1]")).click();//点击新建
        driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[4]/td[2]/input")).sendKeys("实力肯定就发");//输入供应商

        WebElement element = driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[5]/td[2]/select"));//得到下拉框
        Select select = new Select(element);
        select.selectByValue("13");//选择个体经济

        String handle = driver.getWindowHandle();//得到主页面Handle
        System.out.println("主页面Handle = "+handle);
        driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[14]/td[2]/a[1]")).click();//点击选择采购员,跳转新页面

        //找到新窗口，并进入
        for(String handles : driver.getWindowHandles()){
            if(handles != handle){
                driver.switchTo().window(handles);
            }
        }

        driver.switchTo().frame("dept");//左侧Frame
        driver.findElement(By.xpath("//*[@id=\"treeDemo_2_check\"]")).click();//选择总经办
        driver.switchTo().defaultContent();

        driver.switchTo().frame("user");//右侧Frame
        driver.findElement(By.xpath("//*[@id=\"admin\"]")).click();//选择管理员

        driver.switchTo().window(handle);//返回主页面

        driver.switchTo().frame("table_index");//右侧一级
        driver.switchTo().frame("table_main");//二级
        driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[9]/td[2]/input")).sendKeys("15564336808");//输入手机号
        driver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[17]/td/div/input[1]")).click();//点击保存


    }

    @Test
    public void checkTest() throws Exception {
        driver.get("http://localhost:81/general/ERP/LOGIN/");//打开登录页面

        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("admin");//登录用户名
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/table/tbody/tr/td[3]/table[4]/tbody/tr[1]/td[1]/input")).click();//点击登录

        driver.switchTo().frame("function_panel_index");//左侧一级
        driver.switchTo().frame("menu_main");//二级

        driver.findElement(By.xpath("/html/body/table[7]/tbody/tr/td[3]/a")).click();//点击供应商管理
        driver.findElement(By.xpath("//*[@id=\"MENU_66d\"]/tbody/tr/td/table[1]/tbody/tr/td[4]/a")).click();//点击供应商

        driver.switchTo().defaultContent();//退出Frame

        driver.switchTo().frame("table_index");//右侧一级
        driver.switchTo().frame("table_main");//二级

        //界面校验
        String name = driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td[2]")).getText();
        System.out.println("第一行供应商名称："+name);
        Assert.assertEquals(name,"实力肯定就发");

        //数据库校验
        SupplierDaoImp sdt = new SupplierDaoImp();
        Supplier s = sdt.getSupplierByName(name);
        Assert.assertNotNull(s);//数据库有信息则 Not Null

    }

    @AfterTest
    public void closedchrome()throws InterruptedException{
        Thread.sleep(2800);//等待
        driver.quit();//完全退出浏览器
    }
}
