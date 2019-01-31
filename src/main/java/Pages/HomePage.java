package Pages;

import Controllers.TabsContainer;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Controllers.TabsContainer.Tab.MainCategory.SecondaryCategory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Navigate to http://allegro.pl")
    public HomePage closeAgreementPopup() {
        takeScreenshotOnSuccess();
        this.closeAgreementPopupButton.click();
        waitForElementToBecomeInvisible(this.closeAgreementPopupButton);
        takeScreenshotOnSuccess();
        return this;
    }

    public HomePage navigateTo() {
        logo.click();
        waitForElementToAppear(categoriesContainer);
        return this;
    }

    @Step("Select random category")
    public HomePage navigateToRandomCategory(String tabName) throws Exception {
        var tabContainer = new TabsContainer(categoriesContainer);

        var randomMainCategory = (TabsContainer.Tab.MainCategory) getRandomElementFromList(tabContainer.getTabByName(tabName).getMainCategories());
        var randomSecondaryCategory = (SecondaryCategory) getRandomElementFromList(randomMainCategory.getSecondaryCategories());

        SecondaryCategory.navigateTo(randomSecondaryCategory);
        waitForJQuery();

        takeScreenshotOnSuccess();
        return this;
    }

    @FindBy(xpath = "//i[@data-role='accept-consent']")
    WebElement closeAgreementPopupButton;

    @FindBy(css = "div[data-box-name='tabs']")
    WebElement categoriesContainer;

    @FindBy(css = "[data-description='header logo']")
    WebElement logo;
}
