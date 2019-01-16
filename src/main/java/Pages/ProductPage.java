package Pages;

import Models.Order;
import Models.Product;
import Validators.ProductPageValidator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.List;

public class ProductPage extends BasePage {
    public ProductPageValidator validator;

    public ProductPage(WebDriver driver) {
        super(driver);
        validator = new ProductPageValidator(this);
    }

    public String getProductName() {
        waitForElementToAppear(nameLabel);
        return nameLabel.getText();
    }

    public BigDecimal getPrice() {
        waitForElementToAppear(priceLabel);

        return getPriceFromText(priceLabel.getText());
    }

    @Step("Add the product to basket")
    public ProductPage addToBasket(Order order, Integer quantity, Boolean goToCart) {
        Product product = new Product().setName(getProductName()).setPrice(getPrice());

        //TODO: handnle cases when there's only one unit of product available

        if (quantity != 1) {
            quantityInput.clear();
            quantityInput.sendKeys(quantity.toString());
        }

        addToCartButton.click();
        waitForElementToAppear(goToCartButton);

        if (goToCart)
            goToCartButton.click();
        else
            continueShoppingButton.click();

        order.addProductToOrder(
                new Order.SingleProductTypeOrder(product, quantity));
        waitForJQuery();
        takeScreenshotOnSuccess();
        return this;
    }

    public Boolean isProductCouldBeAddedToBasket(){
        return exists(addToCartButtonNoWait) && addToCartButton.isEnabled();
    }

    @FindBy(css = "[itemscope]>div>h1")
    WebElement nameLabel;

    @FindBy(xpath = "//*[@itemscope]/div/div[span[contains(., 'zł')]]")
    WebElement priceLabel;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;

    @FindBy(id = "add-to-cart-button")
    List<WebElement> addToCartButtonNoWait;

    @FindBy(id = "add-to-cart-si-precart")
    WebElement goToCartButton;

    @FindBy(xpath = "//button[contains(., 'kontynuuj zakupy')]")
    WebElement continueShoppingButton;

    @FindBy(css = "input[name='quantity']")
    public WebElement quantityInput;
}
