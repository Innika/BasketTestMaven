package Pages;

import Controllers.TabsContainer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

import static Controllers.TabsContainer.Tab.MainCategory.SecondaryCategory.*;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage closeAgreementPopup() {
        this.closeAgreementPopupButton.click();
        waitForElementToDisappear(this.closeAgreementPopupButton);
        return this;
    }

    public HomePage navigateToRandomCategory(String tabName) throws Exception {
        var tabContainer = new TabsContainer(categoriesContainer);

        Random rand = new Random();
        int mainCategoryIndex = rand.nextInt(tabContainer.getTabByName(tabName).getMainCategories().size());
        int secondaryCategoryIndex = rand.nextInt(
                tabContainer.getTabByName(tabName).getMainCategories()
                        .get(mainCategoryIndex).getSecondaryCategories().size());

        navigateTo(tabContainer.getTabByName(tabName)
                .getMainCategories().get(mainCategoryIndex).getSecondaryCategories().get(secondaryCategoryIndex));

        waitForJQuery();
        return this;
    }

    @FindBy(xpath = "//i[@data-role='accept-consent']")
    WebElement closeAgreementPopupButton;

    @FindBy (css = "div[data-box-name='left column'] div[data-role='tabs-container']") //(xpath = "//div[@data-box-name='left column']//div[@data-role='tabs-container']")
    WebElement categoriesContainer;
}
