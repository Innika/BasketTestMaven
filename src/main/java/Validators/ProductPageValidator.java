package Validators;

import Models.Product;
import Pages.ProductPage;
import io.qameta.allure.Step;

import static Pages.BasePage.takeScreenshotOnSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductPageValidator extends BasePageValidator {
    public ProductPageValidator(ProductPage currentPageInstance) {
        super(currentPageInstance);
    }

    @Step("Verify if product opened is the same as the one we clicked on before")
    public ProductPage productSelectedMatсhesArticle(Product productArticle, Product selectedProduct) {
        assertEquals(productArticle.name, selectedProduct.name);
        takeScreenshotOnSuccess();
        return (ProductPage) this.pageInstance;
    }
}
