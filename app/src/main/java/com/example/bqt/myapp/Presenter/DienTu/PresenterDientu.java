package com.example.bqt.myapp.Presenter.DienTu;

import com.example.bqt.myapp.Model.DienTu.ModelDienTu;
import com.example.bqt.myapp.Model.Home.Menu.HandleJsonMenu;
import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.Model.ObjectClass.Product;
import com.example.bqt.myapp.Model.ObjectClass.ProductCategory;
import com.example.bqt.myapp.Model.ObjectClass.ProductPrice;
import com.example.bqt.myapp.Parse.JSONParser;
import com.example.bqt.myapp.R;
import com.example.bqt.myapp.View.Home.View.IViewDienTu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by BQT on 6/7/2017.
 */

public class PresenterDientu implements IPresenterDientu {

    IViewDienTu iViewDienTu;
    ModelDienTu modelDienTu;

    public PresenterDientu(IViewDienTu iViewDienTu) {
        this.iViewDienTu = iViewDienTu;
    }

    @Override
    public List<Brand> getBrands() {
        List<Brand> brands=new ArrayList<>();
        modelDienTu=new ModelDienTu();

        for (int i=0; i<modelDienTu.getBrands().size();i++)
        {
            Brand brand=new Brand();
            brand=modelDienTu.getBrands().get(i);
            brands.add(brand);
        }
        iViewDienTu.showBrands(brands);
        return brands;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products=new ArrayList<>();
        modelDienTu=new ModelDienTu();

        List<ProductPrice> arrPrice=modelDienTu.getProductPrice();
        for (int i=0;i<arrPrice.size();i++){
            Product product=new Product();
            product=modelDienTu.getProductID(arrPrice.get(i).getProductID());
            product.setPrice(arrPrice.get(i).getPrice());

            products.add(product);
        }

        return products;
    }
}
