package com.example.bqt.myapp.Presenter.DienTu;

import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.Model.ObjectClass.Product;

import java.util.List;

/**
 * Created by BQT on 6/7/2017.
 */

public interface IPresenterDientu {
    List<Brand> getBrands();
    List<Product> getProducts();
}
