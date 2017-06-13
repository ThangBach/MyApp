package com.example.bqt.myapp.Model.ObjectClass;

import java.math.BigDecimal;

/**
 * Created by BQT on 6/8/2017.
 */

public class ProductPrice {
    int ProductID;
    BigDecimal Price;

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }
}
