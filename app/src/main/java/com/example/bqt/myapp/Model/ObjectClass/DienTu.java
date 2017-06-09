package com.example.bqt.myapp.Model.ObjectClass;

import java.util.List;

/**
 * Created by BQT on 6/7/2017.
 */

public class DienTu {
    List<Brand> brands;
    List<Product> products;
    String urlImg;

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
