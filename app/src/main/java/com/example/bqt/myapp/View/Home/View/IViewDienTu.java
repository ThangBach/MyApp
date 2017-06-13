package com.example.bqt.myapp.View.Home.View;

import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.Model.ObjectClass.DienTu;
import com.example.bqt.myapp.Model.ObjectClass.Product;
import com.example.bqt.myapp.Model.ObjectClass.ProductCategory;

import java.util.List;

/**
 * Created by BQT on 6/7/2017.
 */

public interface IViewDienTu {
    void showBrands(List<DienTu> dienTus);
    void showBigBrands(List<Brand> brands);
    void showNewProductS(List<Product> products);
}
