package Controllers;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class TabsContainer {
    static WebElement tabContainerElement;

    public TabsContainer(WebElement tabContainerElement) throws Exception {
        if (tabContainerElement.getAttribute("data-role").equals("tabs-container"))
            this.tabContainerElement = tabContainerElement;
        else
            throw new Exception("The element isn't a TabsContainer type");
    }

    public List<Tab> GetTabs() {
        List<Tab> tabs = new ArrayList();
        var elements = this.tabContainerElement.findElements(By.cssSelector("div[data-role='navigation-item]"));

        for (var el : elements) {
            tabs.add(new Tab(el));
        }
        return tabs;
    }

    public Tab getTabByName(String tabName) {
        return new Tab(this.tabContainerElement.findElement(
                By.cssSelector(String.format("a[data-role='navigation-item'][href='#%s']", tabName))));
    }

    public static class Tab {
        WebElement tabElement;

        public Tab(WebElement tabElement) {
            this.tabElement = tabElement;
        }

        public List<MainCategory> getMainCategories() {
            List<MainCategory> categories = new ArrayList();
            String tabName = GetTabName(this.tabElement);

            var elements = TabsContainer.tabContainerElement.findElements(By.xpath(
                    String.format(".//*[@data-opbox-tab-link='%s']//div[a[@data-description='navigation-layers category link']]", tabName)));

            for (var el : elements) {
                categories.add(new MainCategory(el, this.tabElement));
            }
            return categories;
        }

        private String GetTabName(WebElement element) {
            String linkText = element.getAttribute("href");
            return linkText.substring(linkText.lastIndexOf('#') + 1);
        }

        public static class MainCategory {
            WebElement mainCategoryElement;
            WebElement tabElement;

            public MainCategory(WebElement mainCategoryElement, WebElement tabElement) {
                this.mainCategoryElement = mainCategoryElement;
                this.tabElement = tabElement;
            }

            public List<SecondaryCategory> getSecondaryCategories() {
                List<SecondaryCategory> categories = new ArrayList();

                var elements = this.mainCategoryElement.findElements(By.xpath(".//li[count(*)=1]/a"));

                for (var el : elements) {
                    categories.add(new SecondaryCategory(el, this.mainCategoryElement, this.tabElement));
                }

                return categories;
            }

            public static class SecondaryCategory {
                WebElement secondaryCategoryElement;
                WebElement mainCategoryElement;
                WebElement tabElement;

                public SecondaryCategory(WebElement secondaryCategoryElement,
                                         WebElement mainCategoryElement, WebElement tabElement) {
                    if (secondaryCategoryElement != null)
                        this.secondaryCategoryElement = secondaryCategoryElement;
                    this.mainCategoryElement = mainCategoryElement;
                    this.tabElement = tabElement;
                }

                public static void navigateTo(SecondaryCategory category) {
                    category.tabElement.click();
                    Actions action = new Actions(BasePage.driver);
                    action.moveToElement(category.mainCategoryElement).build().perform();

                    BasePage.waitForElementToAppear(category.secondaryCategoryElement);
                    category.secondaryCategoryElement.click();
                }
            }
        }
    }
}