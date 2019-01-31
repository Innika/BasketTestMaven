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

    public BasketItemsContainer(WebElement itemsContainerFormElement) throws Exception {
        if (itemsContainerFormElement.findElements(By.cssSelector(".m-card")).size() > 0) {
            this.itemsContainerFormElement = itemsContainerFormElement;
        } else
            throw new Exception("The element isn't a BasketItemsContainer type");
}

    public List<Order.SingleProductTypeOrder> getSubOrders() {
        List<Order.SingleProductTypeOrder> subOrders = new ArrayList<>();

        var productSections = this.itemsContainerFormElement
                .findElements(By.cssSelector("m-offer-row"));

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
        String pricePerPiece = subOrder.getAttribute("ng-reflect-m-amount"); //TODO: review if it will fail for items without the price per unit

        if (pricePerPiece != null && pricePerPiece != "")
            return getPriceFromText(pricePerPiece);

        else return getSubOrderPrice(subOrder);
    }

    private BigDecimal getSubOrderPrice(WebElement subOrder) {
        return getPriceFromText(subOrder.getAttribute("ng-reflect-m-total-discounted-price"));
    }

    private int getProductQuantity(WebElement subOrder) {
        return Integer.parseInt(subOrder.getAttribute("ng-reflect-m-quantity"));
    }

    public BigDecimal getSubTotalPrice() {
        return getPriceFromText(
                this.itemsContainerFormElement.findElement(
                        By.cssSelector(".summation .m-price"))
                        .getText());
    }
}
