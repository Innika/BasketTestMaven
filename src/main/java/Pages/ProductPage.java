package Pages;

import Models.Order;
import Models.Product;
import Validators.ProductPageValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String price = priceLabel.getText();
        price = price.replace(" ", "");
        price = price.replace(',', '.');

        Pattern p = Pattern.compile("[\\d.]+");
        Matcher m = p.matcher(price);
        m.find();
        String finalPrice = m.group();

        return new BigDecimal(finalPrice);
    }

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
        return this;
    }

    @FindBy(css = "[itemscope]>div>h1")
    WebElement nameLabel;

    @FindBy(xpath = "//*[@itemscope]/div/div[span[contains(., 'zł')]]")
    WebElement priceLabel;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;

    @FindBy(id = "add-to-cart-si-precart")
    WebElement goToCartButton;

    @FindBy(xpath = "//button[contains(., 'kontynuuj zakupy')]")
    WebElement continueShoppingButton;

    @FindBy(css = "input[name='quantity']")
    WebElement quantityInput;
}
