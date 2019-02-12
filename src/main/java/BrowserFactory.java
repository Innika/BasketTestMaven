import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory{
    public static WebDriver InitBrowser(String browserName) {
        switch (browserName) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                return new ChromeDriver();
            case "Firefox":
                System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
                return new FirefoxDriver();
            default:
                break;
        }
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        return new ChromeDriver();
    }
}
