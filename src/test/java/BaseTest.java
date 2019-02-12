import Pages.BasketPage;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductsListPage;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Date;

public class BaseTest {
    WebDriver driver;
    HomePage homePage;
    ProductsListPage productsListPage;
    ProductPage productPage;
    BasketPage basketPage;

    @BeforeEach
    public void setUp() throws Exception {
        deleteReportResources();

        String browserName = System.getProperty("driver");
        printBrowserName(browserName);

        driver = BrowserFactory.InitBrowser(browserName);
        driver.manage().window().setPosition(new Point(-2000, 1));
        driver.manage().window().maximize();
        driver.get("https://allegro.pl");

        homePage = new HomePage(driver);
        productsListPage = new ProductsListPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
    }

    @AfterAll
    public static void createHtmlReport() throws Exception {
        var file = new File(String.format(".\\target\\reports\\%s",
                new Date().toString().replace(':', '-')));
        file.mkdirs();

        var process = Runtime.getRuntime().exec(String.format("cmd.exe /c allure generate -o \"%s\"",
                file.getPath()));
        process.waitFor();
    }

    private static void deleteReportResources() throws Exception {
        var dir = new File("allure-results");
        if (dir.exists())
            FileUtils.cleanDirectory(dir);
    }

    @Step
    public String printBrowserName(String browserName) {
        return browserName;
    }
}
