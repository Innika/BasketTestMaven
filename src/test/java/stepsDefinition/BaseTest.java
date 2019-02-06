package stepsDefinition;

import Pages.BasketPage;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductsListPage;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseTest  {
    WebDriver driver;
    HomePage homePage;
    ProductsListPage productsListPage;
    ProductPage productPage;
    BasketPage basketPage;

    //@BeforeClass
    public void setUp() throws Exception {
        deleteReportResources();

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(-2000, 1));
        driver.manage().window().maximize();
        driver.get("https://allegro.pl");

        homePage = new HomePage(driver);
        productsListPage = new ProductsListPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    //@AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void createHtmlReport() throws Exception {
        var file = new File(String.format(".\\target\\reports\\%s",
                new Date().toString().replace(':', '-')));
        file.mkdirs();

        var process = Runtime.getRuntime().exec(String.format("cmd.exe /c allure generate -o \"%s\"",
                file.getPath()));
        process.waitFor();
    }

    private void deleteReportResources() throws Exception {
        var dir = new File("allure-results");
        if (dir.exists())
            FileUtils.cleanDirectory(dir);
    }

    @AfterTest
    public void configureCucumberHtmlReport() throws Exception {
        File reportOutputDirectory = new File("target");
        String projectName = "cucumberProject";

        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber.json");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, new Configuration(reportOutputDirectory, projectName));
        reportBuilder.generateReports();

        createHtmlReport();
    }
}
