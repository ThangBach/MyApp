package com.example.bqt.myapp.Model.Products;

import com.example.bqt.myapp.Model.Constaint;
import com.example.bqt.myapp.Model.DienTu.ModelDienTu;
import com.example.bqt.myapp.Model.ObjectClass.Product;
import com.example.bqt.myapp.Model.ObjectClass.ProductPrice;
import com.example.bqt.myapp.Parse.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by BQT on 6/13/2017.
 */

public class ModelProducts {

    JSONParser parser;

    public List<Product> ProductsByCategoryID(int categoryID) {
        List<Product> products = new ArrayList<>();
        String urlApi = Constaint.API_MOBILE + "product/getproduct/" + categoryID;
        String jsonData = "";

        parser = new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData = parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Product product = new Product();

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> ProductsByBrandID(int brandID) {
        List<Product> products = new ArrayList<>();
        String urlApi = Constaint.API_MOBILE + "product/getproductbrand/" + brandID;
        String jsonData = "";

        parser = new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData = parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Product product = new Product();

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> ProductMoreBrandID(int brandID, int startIndex) {
        List<Product> products = new ArrayList<>();
        String urlApi = Constaint.API_MOBILE + "product/getmoreproductbrand/" + brandID+"/"+startIndex+"/10";
        String jsonData = "";

        parser = new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData = parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Product product = new Product();

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> ProductMoreCategoryID(int categoryID, int startIndex) {
        List<Product> products = new ArrayList<>();
        String urlApi = Constaint.API_MOBILE + "product/getmoreproductcategory/" + categoryID+"/"+startIndex+"/10";
        String jsonData = "";

        parser = new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData = parser.get();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Product product = new Product();

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<ProductPrice> getPrices(int id) {
        List<ProductPrice> productPrices = new ArrayList<>();
        String urlApi = Constaint.API_MOBILE + "price/getprice/" + id;
        String jsonData = "";

        parser = new JSONParser(urlApi);
        parser.execute();

        try {
            jsonData = parser.get();
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return productPrices;
    }

}
