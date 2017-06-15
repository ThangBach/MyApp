package com.example.bqt.myapp.Presenter.Products;

import android.view.View;
import android.widget.ProgressBar;

import com.example.bqt.myapp.Model.ObjectClass.Product;
import com.example.bqt.myapp.Model.Products.ModelProducts;
import com.example.bqt.myapp.View.Products.View.IViewProductList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BQT on 6/13/2017.
 */

public class PresenterProduct implements IPresenterProduct {

    IViewProductList iViewProductList;
    ModelProducts modelProducts = new ModelProducts();

    public PresenterProduct(IViewProductList iViewProductList) {
        this.iViewProductList = iViewProductList;
    }

    @Override
    public void GetProducts(int id, boolean checkbrand) {
        List<Product> productsTemp = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        if (checkbrand) {
            productsTemp = modelProducts.ProductMoreBrandID(id, 6);
        } else {
            productsTemp = modelProducts.ProductMoreCategoryID(id, 6);
        }

        if (productsTemp.size() != 0){
            for (int i = 0; i < productsTemp.size(); i++) {
                Product product = new Product();
                product = productsTemp.get(i);
                product.setPrice(modelProducts.getPrices(product.getID()).get(0).getPrice());

                products.add(product);
            }
        }

            if (products.size() != 0) {

                iViewProductList.HienThiDanhSachSanPham(products);
            } else {
                iViewProductList.LoiHienThiDanhSachSanPham();
            }
    }

    public List<Product> GetMoreProduct(int masp, boolean kiemtra, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<Product> productsTemp = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        if (kiemtra) {
            productsTemp = modelProducts.ProductMoreBrandID(masp, limit);
        } else {
            productsTemp = modelProducts.ProductMoreCategoryID(masp, limit);
        }
        if (productsTemp.size() != 0) {
            for (int i = 0; i < productsTemp.size(); i++) {
                Product product = new Product();
                product = productsTemp.get(i);
                product.setPrice(modelProducts.getPrices(product.getID()).get(0).getPrice());

                products.add(product);
            }
        }

        if (products.size() != 0) {
            progressBar.setVisibility(View.GONE);
        }

        return products;
    }
}
