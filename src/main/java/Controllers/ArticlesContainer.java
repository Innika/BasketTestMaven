package Controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ArticlesContainer {
    //TODO: manage pagination
    WebElement articlesContainerElement;

    public ArticlesContainer(WebElement articlesContainerElement) throws Exception {
        if (articlesContainerElement.getAttribute("data-box-name").equals("items container"))
            this.articlesContainerElement = articlesContainerElement;
        else
            throw new Exception("The element isn't a ArticlesContainer type");
    }

    public List<Article> getArticles() {
        List<Article> articles = new ArrayList();
        var elements = this.articlesContainerElement.findElements(By.xpath(".//article[not(contains(@class, 'carousel-item'))]"));

        for (var el : elements) {
            articles.add(new Article(el));
        }
        return articles;
    }

    public class Article {
        public WebElement articleElement;
        public String name;

        public Article(WebElement articleElement) {
            this.articleElement = articleElement;
        }

        public String getName() {
            this.name = this.articleElement.findElement(By.xpath(".//h2/a[@title]")).getText();
            return name;
        }
    }
}
