import Models.Order;
import Models.Product;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Pages.BasePage.getRandomIntInBoundaries;

@Listeners(AllureListener.class)
public class BasketTest extends BaseTest {

    @Test(description = "Add products to the basket on Allegro and verify the order correctness")
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
            singleTypeProductQuantity = getRandomIntInBoundaries(1, 3);
        }

        basketPage.navigateTo().validator.verifyOrder(expectedOrder, basketPage.getOrder())
                .navigateToDeliveryAndPayment();
    }
}
