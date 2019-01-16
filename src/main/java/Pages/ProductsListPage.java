package Pages;

import Controllers.ArticlesContainer;
import Models.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsListPage extends BasePage {
    public ProductsListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select random product")
    public Product SelectRandomProductArticle() throws Exception {
        var productArticle = new Product();

        var productList = new ArticlesContainer(productListContainer);
        var articlesList = productList.getArticles();

        var randomArticle = (ArticlesContainer.Article) getRandomElementFromList(articlesList);
        productArticle.setName(randomArticle.getName());

        randomArticle.articleElement.click();
        waitForJQuery();

        takeScreenshotOnSuccess();
        return productArticle;
    }

    @FindBy(xpath = "//div[@data-box-name='items container']")
    WebElement productListContainer;
}
