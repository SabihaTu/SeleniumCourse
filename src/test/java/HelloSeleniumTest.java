import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
        driver.quit();
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
        driver.findElement(By.id("email_create")).sendKeys("stukulija@gmail.com");
        driver.findElement(By.name("SubmitCreate")).click();

        driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("Sabiha");
        driver.findElement(By.id("customer_lastname")).sendKeys("Tukulija");
        driver.findElement(By.id("passwd")).sendKeys("123123");

        Select days = new Select(driver.findElement(By.id("days")));
        days.selectByValue("24");

        Select months = new Select(driver.findElement(By.id("months")));
        months.selectByIndex(6);


        Select years = new Select(driver.findElement(By.id("years")));
        years.selectByValue("1978");


        driver.findElement(By.id("firstname")).sendKeys("Sabiha");
        driver.findElement(By.id("lastname")).sendKeys("Tukulija");
        driver.findElement(By.id("company")).sendKeys("Endava");
        driver.findElement(By.id("address1")).sendKeys("Envera Sehovica 11");
        driver.findElement(By.id("city")).sendKeys("Sarajevo");

        Select states = new Select(driver.findElement(By.id("id_state")));
        states.selectByIndex(3);
        driver.findElement(By.id("postcode")).sendKeys("71000");

        Select countries = new Select(driver.findElement(By.id("id_country")));
        countries.selectByIndex(1);

        driver.findElement(By.id("other")).sendKeys("Napomena 1");
        driver.findElement(By.id("phone_mobile")).sendKeys("+38761228784");
        driver.findElement(By.id("alias")).sendKeys("moja adresa");

        // register
        driver.findElement(By.id("submitAccount")).click();

        // verify sign out
        Assert.assertTrue(driver.findElement(By.className("logout")).isDisplayed());
    }
    @Test
    public void popularBestsellerCategoryTest()
    {
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com");


        List<WebElement> popularList = driver.findElements(By.cssSelector("ul.homefeatured li"));

        List<WebElement> bestSellerList = driver.findElements(By.cssSelector("ul.blockbestsellers li"));

        Assert.assertEquals(popularList.size(), 7);
        Assert.assertEquals(bestSellerList.size(), 7);
    }

    @Test
    public void printedDressTest() {

        String dressesListText = "";
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com");

        WebElement element = driver.findElement(By.id("search_query_top"));
        element.sendKeys("Printed dresses");
        driver.findElement(By.name("submit_search")).click();

        List<WebElement> dressesList = driver.findElements(By.xpath("//div[@id='center_column']/ul/li"));
        for (WebElement dress : dressesList) {
            String justTitle = dress.getText().substring(0, dress.getText().indexOf('$'));
            dressesListText  = dressesListText + justTitle ;
          //  System.out.println(dress.getText());
        }
        writeToTxtFile(dressesListText);
    }

    @Test
    public void printedSummerDressTest() {

        String dressesListText = "";
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com");

        WebElement element = driver.findElement(By.id("search_query_top"));
        element.sendKeys("Printed dresses");
        driver.findElement(By.name("submit_search")).click();

        List<WebElement> dressesList = driver.findElements(By.xpath("//div[@id='center_column']/ul/li"));

        WebElement firstDress = dressesList.get(0);
        firstDress.click();

        Assert.assertTrue(driver.findElement(By.className("btn-twitter")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("btn-facebook")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("btn-google-plus")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("btn-pinterest")).isDisplayed());

        //-5%
        Assert.assertEquals(driver.findElement(By.id("reduction_percent_display")).getText(), "-5%");

        // change details
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys("3");
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByIndex(1);

        driver.findElement(By.id("color_14")).click();


        driver.findElement(By.id("add_to_cart")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("layer_cart_product_quantity"))));

        int quantity1 = Integer.parseInt(driver.findElement(By.id("layer_cart_product_quantity")).getText());

        Assert.assertEquals(3, quantity1);
        String colorSize = driver.findElement(By.id("layer_cart_product_attributes")).getText();

        int commaIndex = colorSize.indexOf(',');
        String color = colorSize.substring(0, commaIndex);
        String sizeM = colorSize.substring(commaIndex+1);

        Assert.assertEquals("Blue", color);
        Assert.assertEquals("M", sizeM.trim());

        // proceed
        driver.findElement(By.className("button-medium")).click();

        driver.findElement(By.cssSelector(".cart_navigation clearfix"));
   /*     WebDriverWait wait2 = new WebDriverWait(driver, 10);
        wait2.until(ExpectedConditions.elementToBeClickable(driver.findElement
                (By.cssSelector(".cart_navigation clearfix"))));*/

     //   driver.findElement(By.className("button-medium")).click();

    }

    public void writeToTxtFile(String dressesText)
    {
        // upis u txt file
        try
        {
            FileWriter myWriter = new FileWriter("printedDresses.txt");
            myWriter.write(dressesText);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
