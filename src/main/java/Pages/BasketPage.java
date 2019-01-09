package Pages;

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

    public BasketPage NavigateTo(){
        headerBasketQuantity.click();
        waitForElementToAppear(basketForm);
        return  this;
    }

    public static int getProductsQantity() {
        return Integer.parseInt(headerBasketQuantity.getText());
    }

    @FindBy(css = "[data-role='cart-quantity']")
    static WebElement headerBasketQuantity;

    @FindBy(css = "[id='goToDeskForm']")
    static WebElement basketForm;
}
