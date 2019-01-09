package Pages;

import Controllers.ArticlesContainer;
import Models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class ProductsListPage extends BasePage {
    public ProductsListPage(WebDriver driver) {super(driver);}

    public Product SelectRandomProductArticle() throws Exception{
        var productArticle = new Product();

        var productList = new ArticlesContainer(productListContainer);
        var articlesList = productList.getArticles();

        int randomIndex = new Random().nextInt(articlesList.size());
        productArticle.setName(articlesList.get(randomIndex).getName());

        articlesList.get(randomIndex).articleElement.click();
        waitForJQuery();

        return productArticle;
    }

    @FindBy(xpath = "//div[@data-box-name='items container']")
    WebElement productListContainer;
}
