package Pages;

import Controllers.BasketItemsContainer;
import Models.Order;
import Validators.BasketPageValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage {
    public BasketPageValidator validator;

    public BasketPage(WebDriver driver) {
        super(driver);
        validator = new BasketPageValidator(this);
    }

    public static int getProductsQuantity() {
        return Integer.parseInt(headerBasketQuantity.getText());
    }

    public BasketPage navigateTo() {
        headerBasketQuantity.click();
        waitForElementToAppear(basketForm);
        return this;
    }

    public Order getOrder() throws Exception {
        var basketItemsList = new BasketItemsContainer(basketForm);

        return new Order().setProductsAndQuantities(basketItemsList.getSubOrders())
                .setSubTotalPrice(basketItemsList.getSubTotalPrice());
    }

    @FindBy(css = "[data-role='cart-quantity']")
    static WebElement headerBasketQuantity;

    @FindBy(css = "[id='goToDeskForm']")
    static WebElement basketForm;
}
