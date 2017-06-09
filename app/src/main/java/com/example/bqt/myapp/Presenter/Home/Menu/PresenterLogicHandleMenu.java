package com.example.bqt.myapp.Presenter.Home.Menu;

import com.example.bqt.myapp.Model.Home.Menu.HandleJsonMenu;
import com.example.bqt.myapp.Model.ObjectClass.ProductCategory;
import com.example.bqt.myapp.Parse.JSONParser;
import com.example.bqt.myapp.View.Home.View.IViewHandleMenu;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by BQT on 6/1/2017.
 */

public class PresenterLogicHandleMenu implements IPresenterHandleMenu {

    IViewHandleMenu myiViewHandleMenu;

    public PresenterLogicHandleMenu(IViewHandleMenu iViewHandleMenu) {
        this.myiViewHandleMenu=iViewHandleMenu;
    }

    @Override
    public void getMenuList() {
        String url="http://192.168.56.1/itech/api/productcategory/getparent";
        String dataJson="";
        List<ProductCategory> categories;

        HandleJsonMenu handleJsonMenu=new HandleJsonMenu();
        JSONParser parser=new JSONParser(url);
        parser.execute();

        try{
            dataJson=parser.get();
            categories=handleJsonMenu.ParseJsonMenu(dataJson);
            myiViewHandleMenu.ShowMenuList(categories);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}
