package Validators;

import Models.Product;
import Pages.ProductPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import static Pages.BasePage.takeScreenshotOnSuccess;

public class ProductPageValidator extends BasePageValidator {
    public ProductPageValidator(ProductPage currentPageInstance) {
        super(currentPageInstance);
    }

    @Step("Verify if product opened is the same as the one we clicked on before")
    public ProductPage productSelectedMatсhesArticle(Product productArticle, Product selectedProduct) {
        Assert.assertEquals(productArticle.name, selectedProduct.name);
        takeScreenshotOnSuccess();
        return (ProductPage) this.pageInstance;
    }
}
