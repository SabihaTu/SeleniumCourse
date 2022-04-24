import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSeleniumTest
{
    public void firstTest()
    {
        System.setProperty("webriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
    }
}
