package Validators;

import Models.Order;
import Pages.BasketPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import static Pages.BasePage.takeScreenshotOnSuccess;
import static org.assertj.core.api.Assertions.assertThat;

public class BasketPageValidator extends BasePageValidator {
    public BasketPageValidator(BasketPage currentPage) {
        super(currentPage);
    }

    @Step("Wait for products quantity changes in the basket. Verify the quantity")
    public BasketPage verifyProductsQuantityInBasket(int expectedQuantity) {
        Assert.assertEquals(expectedQuantity, BasketPage.getProductsQuantity());
        takeScreenshotOnSuccess();
        return (BasketPage) this.pageInstance;
    }

    @Step("Check if products' names, quantities, prices per piece, subPrices are correct. Verify if total price is correct")
    public BasketPage verifyOrder(Order expectedOrder, Order actualOrder) {
        assertThat(expectedOrder).isEqualToComparingFieldByFieldRecursively(actualOrder);
        takeScreenshotOnSuccess();
        return (BasketPage) this.pageInstance;
    }
}
