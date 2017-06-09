package com.example.bqt.myapp.Model.DienTu;

import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.Model.ObjectClass.Product;
import com.example.bqt.myapp.Model.ObjectClass.ProductCategory;
import com.example.bqt.myapp.Model.ObjectClass.ProductPrice;
import com.example.bqt.myapp.Parse.JSONParser;
import com.example.bqt.myapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        String urlApi="http://192.168.56.1/Itech/api/brand/getallbrands";
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

    public List<ProductPrice> getProductPrice(){
        List<ProductPrice> productPrices=new ArrayList<>();
        String urlApi="http://192.168.56.1/Itech/api/price/getpricemaxdate";
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
                productPrice.setPrice(jsonObject.getString("PriceAmount"));

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

    public Product getProductID(int productID){
        Product product=new Product();
        String urlApi=R.string.localhost_api+"/product/getbyid/"+productID;
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
}
