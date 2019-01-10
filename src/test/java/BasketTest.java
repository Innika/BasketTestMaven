import Models.Order;
import Models.Product;
import org.testng.annotations.Test;

import java.util.Random;

public class BasketTest extends BaseTest {

    @Test
    public void basketTest() throws Throwable {
        Order expectedOrder = new Order();
        int singleTypeProductQuantity = 1;
        homePage.closeAgreementPopup();

        for (int i = 0; i < 4; i++) {
            homePage.navigateTo().navigateToRandomCategory("dzialy");

            Product productArticle = productsListPage.SelectRandomProductArticle();
            Product productItem = new Product();

            productItem.setName(productPage.getProductName()).setPrice(productPage.getPrice());
            productPage.validator.productSelectedMatсhesArticle(productArticle, productItem);

            for (int j = 0; j < singleTypeProductQuantity; j++) {
                //TODO: add handler licytacji
                productPage.addToBasket(expectedOrder, 1, false);
                basketPage.validator.verifyProductsQuantityInBasket(expectedOrder.totalQuantity);
            }
            singleTypeProductQuantity = 1 + new Random().nextInt(2);
        }

        basketPage.navigateTo().validator.verifyOrder(expectedOrder, basketPage.getOrder());
    }
}
