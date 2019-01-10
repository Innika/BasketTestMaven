package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePage {

    private static final int TIMEOUT = 20;
    private static final int POLLING = 100;

    public static WebDriver driver;
    private static WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(driver, this);
    }

    public static void waitForElementToAppear(WebElement element) {
        waitForJQuery();
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }

    protected void waitForElementToDisappear(WebElement element) {
        waitForJQuery();
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected static void waitForJQuery() {
        (new WebDriverWait(driver, TIMEOUT)).until((ExpectedCondition<Boolean>) d -> {
            JavascriptExecutor js = (JavascriptExecutor) d;
            return (Boolean) js.executeScript("return document.readyState").toString().equals("complete");
        });
    }

    public static BigDecimal getPriceFromText(String price) {
        price = price.replace(" ", "");
        price = price.replace(',', '.');

        Pattern p = Pattern.compile("[\\d.]+");
        Matcher m = p.matcher(price);
        m.find();
        String finalPrice = m.group();
        return new BigDecimal(finalPrice);
    }
}
