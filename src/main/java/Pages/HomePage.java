package Pages;

import Controllers.TabsContainer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Controllers.TabsContainer.Tab.MainCategory.SecondaryCategory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage closeAgreementPopup() {
        this.closeAgreementPopupButton.click();
        waitForElementToDisappear(this.closeAgreementPopupButton);
        return this;
    }

    public HomePage navigateTo() {
        logo.click();
        waitForElementToAppear(categoriesContainer);
        return this;
    }

    public HomePage navigateToRandomCategory(String tabName) throws Exception {
        var tabContainer = new TabsContainer(categoriesContainer);

        var randomMainCategory = (TabsContainer.Tab.MainCategory) getRandomElementFromList(tabContainer.getTabByName(tabName).getMainCategories());
        var randomSecondaryCategory = (SecondaryCategory) getRandomElementFromList(randomMainCategory.getSecondaryCategories());

        SecondaryCategory.navigateTo(randomSecondaryCategory);

        waitForJQuery();
        return this;
    }

    @FindBy(xpath = "//i[@data-role='accept-consent']")
    WebElement closeAgreementPopupButton;

    @FindBy(css = "div[data-box-name='left column'] div[data-role='tabs-container']") //(xpath = "//div[@data-box-name='left column']//div[@data-role='tabs-container']")
            WebElement categoriesContainer;

    @FindBy(css = "[data-description='header logo']")
    WebElement logo;
}
