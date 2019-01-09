import Models.Order;
import Models.Product;
import Pages.ProductPage;
import org.testng.annotations.Test;

public class BasketTest extends BaseTest {

    @Test
    public void basketTest() throws Throwable {

        homePage.closeAgreementPopup()
                .navigateToRandomCategory("dzialy");

        Product productArticle = productsListPage.SelectRandomProductArticle();

        Product productItem = new Product();
        Order expectedOrder = new Order();

        productItem.setName(productPage.getProductName()).setPrice(productPage.getPrice());
        ((ProductPage)productPage.pageValidator.productSelectedMatсhesArticle(productArticle, productItem))
                .addToCart(expectedOrder, 3, false);
    }
}
