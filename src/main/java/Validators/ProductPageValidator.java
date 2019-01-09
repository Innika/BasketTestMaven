package Validators;

import Models.Product;
import Pages.BasePage;
import org.testng.Assert;

public class ProductPageValidator extends BasePageValidator {
    public ProductPageValidator(BasePage currentPageInstance) {
        super(currentPageInstance);
    }

    public BasePage productSelectedMatсhesArticle(Product productArticle, Product selectedProduct){
        Assert.assertEquals(productArticle.name, selectedProduct.name);
        return this.pageInstance;
    }
}
