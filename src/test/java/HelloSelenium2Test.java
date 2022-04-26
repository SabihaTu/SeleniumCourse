import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloSelenium2Test
{
  /*
    @BeforeClass
    public static void setupClass()
    {
         WebDriverManager.chromedriver().setup();
    }

    @Test
    public void smartTest()
    {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.quit();
    }
*/

/*    @BeforeClass
    public static void setupClass()
    {
        WebDriverManager.edgedriver().setup();
    }

    @Test
    public void smartTest()
    {

        WebDriver driver = new EdgeDriver();
        driver.get("https://www.saucedemo.com");
        driver.quit();
    }
    */

    @BeforeClass
    public static void setupClass()
    {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void smartTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com");
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void cypressTest() throws InterruptedException {

        WebDriver driver = new FirefoxDriver();
        driver.get("https://example.cypress.io");
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void shoppingChartTest() throws InterruptedException {

        WebDriver driver = new FirefoxDriver();
        driver.get("https://react-shopping-cart-67954.firebaseapp.com");
        Thread.sleep(3000);
        driver.quit();
    }


}
