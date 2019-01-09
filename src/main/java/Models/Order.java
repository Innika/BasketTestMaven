package Models;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    List<SingleProductTypeOrder> singleProductTypeOrders = new ArrayList<>();
    BigDecimal subTotalPrice;
    BigDecimal deliveryPrice;
    BigDecimal totalPrice;

    public Order setProductQuantity(List<SingleProductTypeOrder> singleProductTypeOrders) {
        this.singleProductTypeOrders = singleProductTypeOrders;
        return this;
    }

    public Order setSubTotalPrice(BigDecimal subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
        return this;
    }

    public Order setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
        return this;
    }

    public Order setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Order addProductToOrder(SingleProductTypeOrder singleProductTypeOrder) {
        SingleProductTypeOrder existingProduct = this.singleProductTypeOrders.stream()
                .filter(order -> order.product.name == singleProductTypeOrder.product.name)
                .findFirst()
                .orElse(null);

        if (existingProduct != null) {
            existingProduct.quantity += singleProductTypeOrder.quantity;
            existingProduct.totalPrice = existingProduct.totalPrice.add(
                    existingProduct.product.price
                    .multiply(new BigDecimal(singleProductTypeOrder.quantity)));
        }
        else
            this.singleProductTypeOrders.add(singleProductTypeOrder);

        return this;
    }

    public static class SingleProductTypeOrder {
        public Product product;
        public Integer quantity;
        public BigDecimal totalPrice;

        public SingleProductTypeOrder(Product product, Integer quantity) {
            this.product = product;
            this.quantity = quantity;
            this.totalPrice = product.price.multiply(new BigDecimal(quantity));
        }
    }
}
