package Models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Order {
    public List<SingleProductTypeOrder> singleProductTypeOrders = new ArrayList<>();
    public int totalQuantity = 0;
    public BigDecimal subTotalPrice = new BigDecimal(0);
    public BigDecimal deliveryPrice = new BigDecimal(0);
    public BigDecimal totalPrice = new BigDecimal(0);

    public Order setProductsAndQuantities(List<SingleProductTypeOrder> singleProductTypeOrders) {
        this.singleProductTypeOrders = singleProductTypeOrders;
        Collections.sort(this.singleProductTypeOrders, Comparator.comparing(o -> o.product.name));
        for (var product : this.singleProductTypeOrders) {
            this.totalQuantity += product.quantity;
        }
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

    public Order setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
        return this;
    }

    public Order addProductToOrder(SingleProductTypeOrder singleProductTypeOrder) {
        SingleProductTypeOrder existingProduct = this.singleProductTypeOrders.stream()
                .filter(order -> order.product.name.equals(singleProductTypeOrder.product.name))
                .findAny()
                .orElse(null);

        BigDecimal priceDelta = singleProductTypeOrder.product.price
                .multiply(new BigDecimal(singleProductTypeOrder.quantity));

        if (existingProduct != null) {
            existingProduct.quantity += singleProductTypeOrder.quantity;
            existingProduct.totalPrice = existingProduct.totalPrice.add(priceDelta);
        } else {
            this.singleProductTypeOrders.add(singleProductTypeOrder);
            Collections.sort(this.singleProductTypeOrders, Comparator.comparing(o -> o.product.name));
        }
        setSubTotalPrice(this.subTotalPrice.add(priceDelta));
        setTotalQuantity(this.totalQuantity + singleProductTypeOrder.quantity);
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
