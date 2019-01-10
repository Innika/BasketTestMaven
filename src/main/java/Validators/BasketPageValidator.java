package Validators;

import Models.Order;
import Pages.BasketPage;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketPageValidator extends BasePageValidator {
    public BasketPageValidator(BasketPage currentPage) {
        super(currentPage);
    }

    public BasketPage verifyProductsQuantityInBasket(int expectedQuantity) {
        Assert.assertEquals(expectedQuantity, BasketPage.getProductsQuantity());
        return (BasketPage) this.pageInstance;
    }

    public BasketPage verifyOrder(Order expectedOrder, Order actualOrder) {
        assertThat(expectedOrder).isEqualToComparingFieldByFieldRecursively(actualOrder);
        return (BasketPage) this.pageInstance;
    }
}
