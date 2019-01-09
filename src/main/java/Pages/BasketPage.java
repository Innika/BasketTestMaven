package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public int getProductsQantity(){
        return Integer.parseInt(headerBasketQuantity.getText());
    }

    @FindBy(css = "[data-role='cart-quantity']")
    WebElement headerBasketQuantity;
}
