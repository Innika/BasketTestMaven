import Models.Order;
import Models.Product;
import org.junit.jupiter.api.Test;

import static Pages.BasePage.getRandomIntInBoundaries;

public class BasketTest extends BaseTest {

    @Test   //"Add products to the basket on Allegro and verify the order correctness"
    public void basketTest() throws Throwable {

        Order expectedOrder = new Order();
        int singleTypeProductQuantity = 1;

        homePage.closeAgreementPopup();

        for (int i = 0; i < 1; i++) {
            homePage.navigateTo().navigateToRandomCategory("dzialy");

            Product productArticle = productsListPage.SelectRandomProductArticle();
            Product productItem = new Product();

            productItem.setName(productPage.getProductName()).setPrice(productPage.getPrice());
            productPage.validator.productSelectedMatсhesArticle(productArticle, productItem);

            if (productPage.isProductCouldBeAddedToBasket()) {
                int maxPossibleQuantity = Integer.parseInt(productPage.quantityInput.getAttribute("max"));
                singleTypeProductQuantity = (singleTypeProductQuantity > maxPossibleQuantity) ? maxPossibleQuantity : singleTypeProductQuantity;

                for (int j = 0; j < singleTypeProductQuantity; j++) {
                    productPage.addToBasket(expectedOrder, 1, false);
                    basketPage.validator.verifyProductsQuantityInBasket(expectedOrder.totalQuantity);
                }
                singleTypeProductQuantity = getRandomIntInBoundaries(1, 3);
            } else
                i--;
        }

        basketPage.navigateTo().validator.verifyOrder(expectedOrder, basketPage.getOrder())
                .navigateToDeliveryAndPayment();
    }
}
