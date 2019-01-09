import Pages.BasketPage;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductsListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    WebDriver driver;
    HomePage homePage;
    ProductsListPage productsListPage;
    ProductPage productPage;
    BasketPage basketPage;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://allegro.pl");

        //TODO: add reporting
        homePage = new HomePage(driver);
        productsListPage = new ProductsListPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    @AfterClass
    public void tearDown() throws Exception{
        driver.quit();

        //TODO: stop reporting
    }
}
