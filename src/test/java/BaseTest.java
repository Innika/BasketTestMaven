import Pages.BasketPage;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductsListPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.util.Date;

public class BaseTest {
    WebDriver driver;
    HomePage homePage;
    ProductsListPage productsListPage;
    ProductPage productPage;
    BasketPage basketPage;

    @BeforeClass
    public void setUp() throws Exception {
        deleteReportResources();

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point( -2000, 1));
        driver.manage().window().maximize();
        driver.get("https://allegro.pl");

        homePage = new HomePage(driver);
        productsListPage = new ProductsListPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
        createHtmlReport();
    }

    private void createHtmlReport() throws Exception {
        var file = new File(String.format(".\\target\\reports\\%s",
                new Date().toString().replace(':', '-')));
        file.mkdirs();

        var process = Runtime.getRuntime().exec(String.format("cmd.exe /c allure generate -o \"%s\"",
                file.getPath()));
        process.waitFor();

        if (file.list().length > 0)
            deleteReportResources();
    }

    private void deleteReportResources() throws Exception {
        var dir = new File("allure-results");
        if (dir.exists())
            FileUtils.cleanDirectory(dir);
    }
}
