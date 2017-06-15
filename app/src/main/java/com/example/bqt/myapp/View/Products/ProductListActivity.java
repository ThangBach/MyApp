package com.example.bqt.myapp.View.Products;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.bqt.myapp.Adapter.AdapterTopDienThoai;
import com.example.bqt.myapp.Model.ObjectClass.Product;
import com.example.bqt.myapp.Model.Products.ModelProducts;
import com.example.bqt.myapp.Parse.LoadData.ILoadMoreData;
import com.example.bqt.myapp.Parse.LoadData.LoadMoreData;
import com.example.bqt.myapp.Presenter.Products.PresenterProduct;
import com.example.bqt.myapp.R;
import com.example.bqt.myapp.View.Products.View.IViewProductList;

import java.util.List;
import java.util.zip.Inflater;

public class ProductListActivity extends Fragment implements IViewProductList, View.OnClickListener, ILoadMoreData {

    RecyclerView recyclerView;
    Button btn_changeDisplay;
    ProgressBar progressBar;
    Toolbar toolbar;
    boolean checkGrid = true;
    RecyclerView.LayoutManager layoutManager;
    AdapterTopDienThoai adapterTopDienThoai;

    PresenterProduct presenterProduct;

    List<Product> productsTemp;
    int id;
    String name;
    boolean check;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product_list, container, false);
        InitUI(view);
        InitEvent();
        presenterProduct = new PresenterProduct(this);

        GetIntent();
        presenterProduct.GetProducts(id, check);
        toolbar.setTitle(name);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("TrangChuActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return view;
    }

    private void InitUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMuc);
        btn_changeDisplay = (Button) view.findViewById(R.id.btn_changeDisplay);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    }

    private void InitEvent() {
        btn_changeDisplay.setOnClickListener(this);
    }


    private void GetIntent() {

        Bundle bundle = getArguments();
        id = bundle.getInt("ID",0);
        name = bundle.getString("NAME");
        check = bundle.getBoolean("CHECK",false);
    }

    @Override
    public void HienThiDanhSachSanPham(List<Product> products) {
        productsTemp=products;
        if (checkGrid) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
            adapterTopDienThoai =  new AdapterTopDienThoai(getContext(),products,R.layout.custom_layout_topdienthoaivamaytinhbang);

        } else {
            layoutManager = new LinearLayoutManager(getActivity());
            adapterTopDienThoai =  new AdapterTopDienThoai(getContext(),products,R.layout.custom_layout_list_topdienthoaivamaytinhbang);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoai);
        recyclerView.addOnScrollListener(new LoadMoreData(layoutManager,this));
        adapterTopDienThoai.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_changeDisplay: {
                checkGrid = !checkGrid;
                presenterProduct.GetProducts(id, check);
                break;
            }
        }
    }

    @Override
    public void LoadMore(int itemSum) {
        List<Product> listsanPhamsLoadMore = presenterProduct.GetMoreProduct(id,check,itemSum,progressBar);
        productsTemp.addAll(listsanPhamsLoadMore);

        adapterTopDienThoai.notifyDataSetChanged();
    }
}
