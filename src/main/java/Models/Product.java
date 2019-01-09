package Models;

import java.math.BigDecimal;

public class Product {
    public String name = "";
    public BigDecimal price;

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

}