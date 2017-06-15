package com.example.bqt.myapp.View.Products.View;

import com.example.bqt.myapp.Model.ObjectClass.Product;

import java.util.List;

/**
 * Created by BQT on 6/14/2017.
 */

public interface IViewProductList {
    void HienThiDanhSachSanPham(List<Product> products);
    void LoiHienThiDanhSachSanPham();
}
