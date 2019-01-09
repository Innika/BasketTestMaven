package Validators;

import Pages.BasketPage;
import org.testng.Assert;

public class BasketPageValidator extends BasePageValidator {
    public BasketPageValidator(BasketPage currentPage){
        super(currentPage);
    }

    public BasketPage verifyProductsQuantityInBasket(int expectedQuantity){
        Assert.assertEquals(expectedQuantity, BasketPage.getProductsQantity());
        return (BasketPage)this.pageInstance;
    }
}
