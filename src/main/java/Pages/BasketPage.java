package Pages;

import Controllers.BasketItemsContainer;
import Models.Order;
import Validators.BasketPageValidator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BasketPage extends BasePage {
    public BasketPageValidator validator;

    public BasketPage(WebDriver driver) {
        super(driver);
        validator = new BasketPageValidator(this);
    }

    public static int getProductsQuantity() {
        return Integer.parseInt(headerBasketQuantity.getText());
    }

    @Step("Navigate to the basket")
    public BasketPage navigateTo() {
        headerBasketQuantity.click();
        waitForElementToAppear(basketForm);
        takeScreenshotOnSuccess();
        return this;
    }

    public Order getOrder() throws Exception {
        var basketItemsList = new BasketItemsContainer(basketForm);

        return new Order().setProductsAndQuantities(basketItemsList.getSubOrders())
                .setSubTotalPrice(basketItemsList.getSubTotalPrice());
    }

    @Step("Go to Delivery and Payment")
    public BasketPage navigateToDeliveryAndPayment(){
        deliveryAndPaymentButton.click();
        waitForJQuery();
        waitForElementToDisappear(deliveryAndPaymentButtonNoWait);
        takeScreenshotOnSuccess();
        return this;
    }

    @FindBy(css = "[data-role='cart-quantity']")
    static WebElement headerBasketQuantity;

    @FindBy(css = "[id='goToDapfForm']")
    static WebElement basketForm;

    @FindBy(xpath = "//button[contains(., 'dostawa')]")
    static WebElement deliveryAndPaymentButton;

    @FindBy(xpath = "//button[contains(., 'dostawa')]")
    List<WebElement> deliveryAndPaymentButtonNoWait;
}
