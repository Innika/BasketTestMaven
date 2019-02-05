package stepsDefinition;

import Listener.AllureListener;
import Models.Order;
import Models.Product;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Pages.BasePage.getRandomIntInBoundaries;

@Listeners(AllureListener.class)
public class BasketTest extends BaseTest {
    Order expectedOrder;

    @Test(description = "Add products to the basket on Allegro and verify the order correctness")
    @Given("When nese jajo")
    public void basketTest() throws Throwable {
        expectedOrder = new Order();
        int singleTypeProductQuantity = 1;

        homePage.closeAgreementPopup();

        for (int i = 0; i < 4; i++) {
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
    }

    @Then("You are able to buy different products")
    public void verifyResult() throws Exception{
        basketPage.navigateTo().validator.verifyOrder(expectedOrder, basketPage.getOrder())
                .navigateToDeliveryAndPayment();
    }
}
