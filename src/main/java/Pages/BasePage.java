package Pages;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
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
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }

    protected void waitForElementToDisappear(List<WebElement> elementsList) {
        waitForJQuery();
        wait.until((ExpectedCondition<Boolean>) driver -> exists(elementsList) == false);
    }

    protected void waitForElementToBecomeInvisible(WebElement element) {
        waitForJQuery();
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected static void waitForJQuery() {
        (new WebDriverWait(driver, TIMEOUT)).until((ExpectedCondition<Boolean>) d -> {
            JavascriptExecutor js = (JavascriptExecutor) d;
            return (Boolean) js.executeScript("return document.readyState").toString().equals("complete");
        });
    }

    private static byte[] takeScreenshot() {
        getCurrentUrl();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Success", type = "image/png")
    public static byte[] takeScreenshotOnSuccess() {
        return takeScreenshot();
    }

    @Attachment(value = "Fail", type = "image/png")
    public static byte[] takeScreenshotOnFail() {
        return takeScreenshot();
    }

    @Attachment(value = "URL")
    private static String getCurrentUrl() {
        return driver.getCurrentUrl();
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

    public Object getRandomElementFromList(List<?> listToSelectFrom) {
        int index = new Random().nextInt(listToSelectFrom.size() - 1);
        return listToSelectFrom.get(index);
    }

    public static int getRandomIntInBoundaries(int startInt, int endInt) {
        return startInt + new Random().nextInt(endInt - startInt);
    }

    public static Boolean exists(List<WebElement> elementNoWait) {
        return elementNoWait.size() > 0;
    }
}
