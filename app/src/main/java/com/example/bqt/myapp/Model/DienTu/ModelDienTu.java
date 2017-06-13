package com.example.bqt.myapp.Model.DienTu;

import com.example.bqt.myapp.Model.Constaint;
import com.example.bqt.myapp.Model.Home.Menu.HandleJsonMenu;
import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.Model.ObjectClass.Discount;
import com.example.bqt.myapp.Model.ObjectClass.Product;
import com.example.bqt.myapp.Model.ObjectClass.ProductCategory;
import com.example.bqt.myapp.Model.ObjectClass.ProductPrice;
import com.example.bqt.myapp.Parse.JSONParser;
import com.example.bqt.myapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by BQT on 6/7/2017.
 */

public class ModelDienTu {

    JSONParser parser;

    public List<Brand> getBrands(){
        List<Brand> brands=new ArrayList<>();
        String urlApi= Constaint.API_MOBILE+"brand/getallbrands";
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                Brand brand = new Brand();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                brand.setID(Integer.parseInt(jsonObject.getString("ID")));
                brand.setAlias(jsonObject.getString("Alias"));
                brand.setName(jsonObject.getString("Name"));
                brand.setImage(jsonObject.getString("Image"));
                brand.setStatus(jsonObject.getString("status"));

                brands.add(brand);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return brands;
    }

    public List<Brand> getBrands(int categoryID){
        List<Brand> brands=new ArrayList<>();
        String urlApi= Constaint.API_MOBILE+"brand/getbrandcategory/"+categoryID;
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                Brand brand = new Brand();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                brand.setID(Integer.parseInt(jsonObject.getString("ID")));
                brand.setAlias(jsonObject.getString("Alias"));
                brand.setName(jsonObject.getString("Name"));
                brand.setImage(jsonObject.getString("Image"));
                brand.setStatus(jsonObject.getString("status"));

                brands.add(brand);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return brands;
    }

    public  List<Brand> getBrandPhuKien(int parentID){
        List<Brand> brands=new ArrayList<>();
        String url=Constaint.API_MOBILE+"productcategory/getchild/"+parentID+"/0";
        String dataJson="";

        parser=new JSONParser(url);
        parser.execute();

        try {
            dataJson=parser.get();
            JSONArray jsonArray = new JSONArray(dataJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                Brand brand = new Brand();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                brand.setID(Integer.parseInt(jsonObject.getString("ID")));
                brand.setAlias(jsonObject.getString("Alias"));
                brand.setName(jsonObject.getString("Name"));
                brand.setImage(jsonObject.getString("Image"));
                brand.setStatus(jsonObject.getString("Status"));

                brands.add(brand);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return  brands;
    }

    public  List<Brand> getPhoneTabletBrand(){
        List<Brand> brands=new ArrayList<>();
        String url=Constaint.API_MOBILE+"brand/getphonetabletbrand";
        String dataJson="";

        parser=new JSONParser(url);
        parser.execute();

        try {
            dataJson=parser.get();
            JSONArray jsonArray = new JSONArray(dataJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                Brand brand = new Brand();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                brand.setID(Integer.parseInt(jsonObject.getString("ID")));
                brand.setAlias(jsonObject.getString("Alias"));
                brand.setName(jsonObject.getString("Name"));
                brand.setImage(jsonObject.getString("Image"));
                brand.setStatus(jsonObject.getString("status"));

                brands.add(brand);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return  brands;
    }

    public List<ProductPrice> getProductPrice(){
        List<ProductPrice> productPrices=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"price/getpricemaxdate";
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                ProductPrice productPrice = new ProductPrice();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                productPrice.setProductID(Integer.parseInt(jsonObject.getString("ProductID")));
                productPrice.setPrice(BigDecimal.valueOf(jsonObject.getDouble("PriceAmount")));

                productPrices.add(productPrice);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return productPrices;
    }

    public List<ProductPrice> getPrices(int id){
        List<ProductPrice> productPrices=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"price/getprice/"+id;
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                ProductPrice productPrice = new ProductPrice();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                productPrice.setProductID(Integer.parseInt(jsonObject.getString("ProductID")));
                productPrice.setPrice(BigDecimal.valueOf(jsonObject.getDouble("PriceAmount")));

                productPrices.add(productPrice);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return productPrices;
    }

    public List<Product> getProductTop(){
        List<Product> products=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"product/getphonetablet";
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                Product product=new Product();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                product.setID(Integer.parseInt(jsonObject.getString("ID")));
                product.setName(jsonObject.getString("Name"));
                product.setAlias(jsonObject.getString("Alias"));
                product.setProductDetails(jsonObject.getString("ProductDetails"));
                product.setSpecifications(jsonObject.getString("Specifications"));
                product.setProductSets(jsonObject.getString("ProductSets"));
                product.setWarranty(jsonObject.getString("Warranty"));
                product.setImage(jsonObject.getString("Image"));
                product.setMoreImages(jsonObject.getString("MoreImages"));
                product.setStatus(jsonObject.getString("Status"));
                product.setViewCount(Integer.parseInt(jsonObject.getString("ViewCount")));
                product.setQuanlity(Integer.parseInt(jsonObject.getString("Quanlity")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("CategoryID")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("BrandID")));

                products.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getProductTopID(int categoryID){
        List<Product> products=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"product/gettopproduct/"+categoryID;
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                Product product=new Product();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                product.setID(Integer.parseInt(jsonObject.getString("ID")));
                product.setName(jsonObject.getString("Name"));
                product.setAlias(jsonObject.getString("Alias"));
                product.setProductDetails(jsonObject.getString("ProductDetails"));
                product.setSpecifications(jsonObject.getString("Specifications"));
                product.setProductSets(jsonObject.getString("ProductSets"));
                product.setWarranty(jsonObject.getString("Warranty"));
                product.setImage(jsonObject.getString("Image"));
                product.setMoreImages(jsonObject.getString("MoreImages"));
                product.setStatus(jsonObject.getString("Status"));
                product.setViewCount(Integer.parseInt(jsonObject.getString("ViewCount")));
                product.setQuanlity(Integer.parseInt(jsonObject.getString("Quanlity")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("CategoryID")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("BrandID")));

                products.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return products;
    }

    public  List<ProductCategory> getCategory(int parentID){
        List<ProductCategory> categories=new ArrayList<>();
        String url=Constaint.API_MOBILE+"productcategory/getchild/"+parentID+"/0";
        String dataJson="";

        parser=new JSONParser(url);
        parser.execute();

        try {
            dataJson=parser.get();
            JSONArray jsonArray = new JSONArray(dataJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                ProductCategory category = new ProductCategory();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                category.setID(Integer.parseInt(jsonObject.getString("ID")));
                category.setName(jsonObject.getString("Name"));
                category.setAlias(jsonObject.getString("Alias"));
                category.setImage(jsonObject.getString("Image"));
                category.setParentID(Integer.parseInt(jsonObject.getString("ParentID")));
                category.setDisplayOrder(jsonObject.getString("DisplayOrder"));
                category.setStatus(jsonObject.getString("Status"));
                category.setCreateBy(jsonObject.getString("CreateBy"));
                category.setCreateDate(jsonObject.getString("CreateDate"));
                category.setUpdateBy(jsonObject.getString("UpdateBy"));
                category.setUpdateDate(jsonObject.getString("UpdateDate"));
                categories.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return  categories;
    }

    public Product getProductID(int productID){
        Product product=new Product();
        String urlApi=Constaint.API_MOBILE+"product/getbyid/"+productID;
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                product.setID(Integer.parseInt(jsonObject.getString("ID")));
                product.setName(jsonObject.getString("Name"));
                product.setAlias(jsonObject.getString("Alias"));
                product.setProductDetails(jsonObject.getString("ProductDetails"));
                product.setSpecifications(jsonObject.getString("Specifications"));
                product.setProductSets(jsonObject.getString("ProductSets"));
                product.setWarranty(jsonObject.getString("Warranty"));
                product.setImage(jsonObject.getString("Image"));
                product.setMoreImages(jsonObject.getString("MoreImages"));
                product.setStatus(jsonObject.getString("Status"));
                product.setViewCount(Integer.parseInt(jsonObject.getString("ViewCount")));
                product.setQuanlity(Integer.parseInt(jsonObject.getString("Quanlity")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("CategoryID")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("BrandID")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getTopPhuKien(){
        List<Product> products=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"product/gettopphukien";
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Product product=new Product();

                product.setID(Integer.parseInt(jsonObject.getString("ID")));
                product.setName(jsonObject.getString("Name"));
                product.setAlias(jsonObject.getString("Alias"));
                product.setProductDetails(jsonObject.getString("ProductDetails"));
                product.setSpecifications(jsonObject.getString("Specifications"));
                product.setProductSets(jsonObject.getString("ProductSets"));
                product.setWarranty(jsonObject.getString("Warranty"));
                product.setImage(jsonObject.getString("Image"));
                product.setMoreImages(jsonObject.getString("MoreImages"));
                product.setStatus(jsonObject.getString("Status"));
                product.setViewCount(Integer.parseInt(jsonObject.getString("ViewCount")));
                product.setQuanlity(Integer.parseInt(jsonObject.getString("Quanlity")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("CategoryID")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("BrandID")));
                products.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getTopProductCategory(int categoryID){
        List<Product> products=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"product/gettopproduct/"+categoryID;
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Product product=new Product();

                product.setID(Integer.parseInt(jsonObject.getString("ID")));
                product.setName(jsonObject.getString("Name"));
                product.setAlias(jsonObject.getString("Alias"));
                product.setProductDetails(jsonObject.getString("ProductDetails"));
                product.setSpecifications(jsonObject.getString("Specifications"));
                product.setProductSets(jsonObject.getString("ProductSets"));
                product.setWarranty(jsonObject.getString("Warranty"));
                product.setImage(jsonObject.getString("Image"));
                product.setMoreImages(jsonObject.getString("MoreImages"));
                product.setStatus(jsonObject.getString("Status"));
                product.setViewCount(Integer.parseInt(jsonObject.getString("ViewCount")));
                product.setQuanlity(Integer.parseInt(jsonObject.getString("Quanlity")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("CategoryID")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("BrandID")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getTopPhoneTablet(){
        List<Product> products=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"product/getTopphonetablet";
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Product product=new Product();

                product.setID(Integer.parseInt(jsonObject.getString("ID")));
                product.setName(jsonObject.getString("Name"));
                product.setAlias(jsonObject.getString("Alias"));
                product.setProductDetails(jsonObject.getString("ProductDetails"));
                product.setSpecifications(jsonObject.getString("Specifications"));
                product.setProductSets(jsonObject.getString("ProductSets"));
                product.setWarranty(jsonObject.getString("Warranty"));
                product.setImage(jsonObject.getString("Image"));
                product.setMoreImages(jsonObject.getString("MoreImages"));
                product.setStatus(jsonObject.getString("Status"));
                product.setViewCount(Integer.parseInt(jsonObject.getString("ViewCount")));
                product.setQuanlity(Integer.parseInt(jsonObject.getString("Quanlity")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("CategoryID")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("BrandID")));
                products.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getProductCategory(int categoryID){
        List<Product> products=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"product/getproduct/"+categoryID;
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Product product=new Product();

                product.setID(Integer.parseInt(jsonObject.getString("ID")));
                product.setName(jsonObject.getString("Name"));
                product.setAlias(jsonObject.getString("Alias"));
                product.setProductDetails(jsonObject.getString("ProductDetails"));
                product.setSpecifications(jsonObject.getString("Specifications"));
                product.setProductSets(jsonObject.getString("ProductSets"));
                product.setWarranty(jsonObject.getString("Warranty"));
                product.setImage(jsonObject.getString("Image"));
                product.setMoreImages(jsonObject.getString("MoreImages"));
                product.setStatus(jsonObject.getString("Status"));
                product.setViewCount(Integer.parseInt(jsonObject.getString("ViewCount")));
                product.setQuanlity(Integer.parseInt(jsonObject.getString("Quanlity")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("CategoryID")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("BrandID")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return products;
    }

    public List<Discount> getDiscountList(int categoryID){
        List<Discount> discounts=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"discount/getdiscountcategory/"+categoryID;
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Discount discount=new Discount();

                discount.setID(Integer.parseInt(jsonObject.getString("ID")));
                discount.setName(jsonObject.getString("Name"));
                discount.setDescription(jsonObject.getString("Description"));
                discount.setPercent(Integer.parseInt(jsonObject.getString("Percent")));
                discount.setStatus(jsonObject.getString("Status"));
                discount.setCode(jsonObject.getString("Code"));

                discounts.add(discount);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return discounts;
    }

    public List<Discount> getDiscountAll(){
        List<Discount> discounts=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"discount/getdiscountall";
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Discount discount=new Discount();

                discount.setID(Integer.parseInt(jsonObject.getString("ID")));
                discount.setName(jsonObject.getString("Name"));
                discount.setDescription(jsonObject.getString("Description"));
                discount.setPercent(Integer.parseInt(jsonObject.getString("Percent")));
                discount.setStatus(jsonObject.getString("Status"));
                discount.setCode(jsonObject.getString("Code"));

                discounts.add(discount);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return discounts;
    }

    public List<Product> getProductNews(){
        List<Product> products=new ArrayList<>();
        String urlApi=Constaint.API_MOBILE+"product/getproductnews";
        String jsonData="";

        parser=new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData=parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                Product product=new Product();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                product.setID(Integer.parseInt(jsonObject.getString("ID")));
                product.setName(jsonObject.getString("Name"));
                product.setAlias(jsonObject.getString("Alias"));
                product.setProductDetails(jsonObject.getString("ProductDetails"));
                product.setSpecifications(jsonObject.getString("Specifications"));
                product.setProductSets(jsonObject.getString("ProductSets"));
                product.setWarranty(jsonObject.getString("Warranty"));
                product.setImage(jsonObject.getString("Image"));
                product.setMoreImages(jsonObject.getString("MoreImages"));
                product.setStatus(jsonObject.getString("Status"));
                product.setViewCount(Integer.parseInt(jsonObject.getString("ViewCount")));
                product.setQuanlity(Integer.parseInt(jsonObject.getString("Quanlity")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("CategoryID")));
                product.setCategoryID(Integer.parseInt(jsonObject.getString("BrandID")));

                products.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return products;
    }

}
