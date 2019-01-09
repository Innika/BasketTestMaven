package Validators;

import Models.Product;
import Pages.BasePage;
import Pages.ProductPage;
import org.testng.Assert;

public class ProductPageValidator extends BasePageValidator {
    public ProductPageValidator(ProductPage currentPageInstance) {
        super(currentPageInstance);
    }

    public ProductPage productSelectedMatсhesArticle(Product productArticle, Product selectedProduct){
        Assert.assertEquals(productArticle.name, selectedProduct.name);
        return (ProductPage)this.pageInstance;
    }
}
