package Controllers;

import Models.Order;
import Models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static Pages.BasePage.getPriceFromText;

public class BasketItemsContainer {
    WebElement itemsContainerFormElement;
    WebElement itemsContainerElement;

    public BasketItemsContainer(WebElement itemsContainerFormElement) throws Exception {
        var itemsContainerFormElements = itemsContainerFormElement.findElements(By.cssSelector(".cart-items"));
        if (itemsContainerFormElements.size() > 0) {
            this.itemsContainerFormElement = itemsContainerFormElement;
            this.itemsContainerElement = itemsContainerFormElements.get(0);
        } else
            throw new Exception("The element isn't a BasketItemsContainer type");
    }

    public List<Order.SingleProductTypeOrder> getSubOrders() {
        List<Order.SingleProductTypeOrder> subOrders = new ArrayList<>();

        var productSections = this.itemsContainerElement
                .findElements(By.cssSelector(".seller-items.ng-scope"));

        for (var productSection : productSections) {
            subOrders.add(new Order.SingleProductTypeOrder(getProductFromSubOrder(productSection),
                    getProductQuantity(productSection)));
        }
        return subOrders;
    }

    private Product getProductFromSubOrder(WebElement subOrderElement) {
        var product = new Product();
        return product.setName(getProductName(subOrderElement)).setPrice(getProductPricePerPiece(subOrderElement));
    }

    private String getProductName(WebElement subOrder) {
        return subOrder.findElement(By.cssSelector(".offer-title__title a")).getText();
    }

    private BigDecimal getProductPricePerPiece(WebElement subOrder) {
        List<WebElement> pricePerPiece = subOrder.findElements(By.xpath(".//per-piece/following-sibling::m-price"));

        if (pricePerPiece.size() > 0)
            return getPriceFromText(pricePerPiece.get(0).getText());

        else return getSubOrderPrice(subOrder);
    }

    private BigDecimal getSubOrderPrice(WebElement subOrder) {
        return getPriceFromText(subOrder.findElement(
                By.xpath(".//div[not(per-piece)]/m-price")).getText());
    }

    private int getProductQuantity(WebElement subOrder) {
        return Integer.parseInt(subOrder.findElement(
                By.cssSelector("[type='number']")).getAttribute("value"));
    }

    public BigDecimal getSubTotalPrice() {
        return getPriceFromText(
                this.itemsContainerFormElement.findElement(
                        By.cssSelector(".summation .m-price"))
                        .getText());
    }
}
