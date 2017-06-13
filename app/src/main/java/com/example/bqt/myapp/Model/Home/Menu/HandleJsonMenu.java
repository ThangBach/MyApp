package com.example.bqt.myapp.Model.Home.Menu;

import com.example.bqt.myapp.Model.Constaint;
import com.example.bqt.myapp.Model.ObjectClass.ProductCategory;
import com.example.bqt.myapp.Parse.JSONParser;
import com.example.bqt.myapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HandleJsonMenu {
    public List<ProductCategory> ParseJsonMenu(String jsonData) {
        List<ProductCategory> categories = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
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
        }
        return categories;
    }

    public  List<ProductCategory> ParseMenuChild(int parentID){
        List<ProductCategory> categories=new ArrayList<>();
        HandleJsonMenu handleJsonMenu=new HandleJsonMenu();
        String url= Constaint.API_MOBILE+"productcategory/getchild/"+parentID+"/0";
        String dataJson="";

        JSONParser parser=new JSONParser(url);
        parser.execute();

        try{
            dataJson=parser.get();
            categories=handleJsonMenu.ParseJsonMenu(dataJson);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return categories;
    }
}
