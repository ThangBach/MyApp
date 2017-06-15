package com.example.bqt.myapp.Presenter.DienTu;

import com.example.bqt.myapp.Model.Constaint;
import com.example.bqt.myapp.Model.DienTu.ModelDienTu;
import com.example.bqt.myapp.Model.Home.Menu.HandleJsonMenu;
import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.Model.ObjectClass.DienTu;
import com.example.bqt.myapp.Model.ObjectClass.Discount;
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

    public void ShowData() {
        modelDienTu = new ModelDienTu();
        PresenterDientu presenterDientu = new PresenterDientu(iViewDienTu);
        List<DienTu> dienTus = new ArrayList<>();

        DienTu dienTu1 = new DienTu();
        List<Product> productS1 = modelDienTu.getTopPhoneTablet();
        for (int i = 0; i < productS1.size(); i++) {
            List<ProductPrice> prices = modelDienTu.getPrices(productS1.get(i).getID());
            productS1.get(i).setPrice(prices.get(0).getPrice());
        }
        dienTu1.setProducts(productS1);
        List<Discount> discounts1=modelDienTu.getDiscountList(13);
        dienTu1.setBrands(modelDienTu.getPhoneTabletBrand());
        dienTu1.setTenDanhmuc("Thương hiệu điện thoại & Máy tính bảng");
        dienTu1.setTenSanpham("Top điện thoại & Máy tính bảng");
        dienTu1.setUrlImg(Constaint.URL_IMG_MOBILE + "UploadFiles/userfiles/images/promotion/dienthoai/"+discounts1.get(0).getDescription());
        dienTu1.setCheckBrand(true);
        dienTus.add(dienTu1);

        DienTu dienTu2 = new DienTu();
        List<Discount> discount2=modelDienTu.getDiscountList(16);
        List<Product> productS2 = modelDienTu.getProductTopID(16);
        for (int i = 0; i < productS2.size(); i++) {
            List<ProductPrice> prices = modelDienTu.getPrices(productS2.get(i).getID());
            productS2.get(i).setPrice(prices.get(0).getPrice());
        }
        dienTu2.setProducts(productS2);
        dienTu2.setBrands(modelDienTu.getBrands(16));
        dienTu2.setTenDanhmuc("Thương Máy tính & Laptop");
        dienTu2.setTenSanpham("Top Máy tính & Laptop");
        dienTu2.setUrlImg(Constaint.URL_IMG_MOBILE + "UploadFiles/userfiles/images/promotion/laptop/"+discount2.get(0).getDescription());
        dienTu1.setCheckBrand(true);
        dienTus.add(dienTu2);

        DienTu dienTu3 = new DienTu();
        List<Discount> discount3=modelDienTu.getDiscountList(31);
        List<Product> productS3 = modelDienTu.getProductTopID(31);
        for (int i = 0; i < productS3.size(); i++) {
            List<ProductPrice> prices = modelDienTu.getPrices(productS3.get(i).getID());
            productS3.get(i).setPrice(prices.get(0).getPrice());
        }
        dienTu3.setProducts(productS3);
        dienTu3.setBrands(modelDienTu.getBrands(31));
        dienTu3.setTenDanhmuc("Thương hiệu Tivi");
        dienTu3.setTenSanpham("Top sẩn phẩm Tivi");
        dienTu1.setCheckBrand(true);
        dienTu3.setUrlImg("http://192.168.1.102/Itech/UploadFiles/userfiles/images/promotion/tivi/"+discount3.get(0).getDescription());
        dienTus.add(dienTu3);


        iViewDienTu.showBrands(dienTus);
    }

    @Override
    public List<Brand> getBrands() {
        List<Brand> brands = new ArrayList<>();

        brands = modelDienTu.getBrands();
        iViewDienTu.showBigBrands(brands);
        return brands;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        modelDienTu = new ModelDienTu();

        List<Product> productsTemp= modelDienTu.getProductNews();

        for (int i = 0; i < productsTemp.size(); i++) {
            List<ProductPrice> prices = modelDienTu.getPrices(productsTemp.get(i).getID());
            Product product = new Product();
            product = productsTemp.get(i);
            product.setPrice(prices.get(0).getPrice());
            products.add(product);
        }
        iViewDienTu.showNewProductS(products);

        return products;
    }
}
