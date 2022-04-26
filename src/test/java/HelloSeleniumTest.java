import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class HelloSeleniumTest
{

    WebDriver driver;
    @BeforeClass
    public static void setupClass()
    {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp()
    {
        driver = new ChromeDriver();
    }
    @After
    public void quit()
    {
     //   driver.quit();
    }

    @Test
    public void amelaTest()
    {
        //5%
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com");

        //20%
        driver.findElement(By.className("login")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("email_create")).sendKeys("sabihatukulija@gmail.com");
        driver.findElement(By.name("SubmitCreate")).click();

        driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("Sabiha");
        driver.findElement(By.id("customer_lastname")).sendKeys("Tukulija");
        driver.findElement(By.id("passwd")).sendKeys("123123");

    }

    @Test
    public void locatorExam()
    {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // navigate to the url
        driver.get("https://www.saucedemo.com/");

        //use css selectors

        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        // use css/Xpath
        //*[@id=\"user-name\"]"
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();

        // use the best locator
        driver.findElement(By.id("first-name")).sendKeys("first name");
        driver.findElement(By.id("last-name")).sendKeys("last name");
        driver.findElement(By.id("postal-code")).sendKeys("zip");

        // click
        driver.findElement(By.cssSelector(".btn_primary.cart_button")).click();

        // finish
        driver.findElement(By.id("finish")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("#checkout_complete_container")).isDisplayed());
    }
    @Test
    public void firstTest()
    {
      //  System.setProperty("webriver.chrome.driver", "resources/chromedriver.exe");
     //   WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement element;

        //ID
        element = driver.findElement(By.id("login-button"));
        String s = element.getAttribute("id");


        //Name
        element = driver.findElement(By.name("user-name"));

        //Class name
        element = driver.findElement(By.className("login_logo"));

        // tag name
        element = driver.findElement(By.tagName("input"));

        // css
        element = driver.findElement(By.cssSelector("#password"));
        String cssS = element.getCssValue("password");

        //XPath
        element = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));

        //driver.quit();
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation");

        // link text
        driver.findElement(By.linkText("Click me using this link text!"));

        // partial link text

        driver.findElement(By.partialLinkText("link text"));


    }

    @Test
    public void elementQuiz1()
    {
        // navigate to the url
        driver.get("http://www.saucedemo.com");
        //find the element. track the state
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
        // Take action.Record the result
        assertTrue(element.isDisplayed());
    }


}
