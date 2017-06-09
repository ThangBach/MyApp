package com.example.bqt.myapp.View.Home.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bqt.myapp.Adapter.AdapterDienTu;
import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.Model.ObjectClass.DienTu;
import com.example.bqt.myapp.Presenter.DienTu.PresenterDientu;
import com.example.bqt.myapp.R;
import com.example.bqt.myapp.View.Home.View.IViewDienTu;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DienTuFragment extends Fragment implements IViewDienTu {

    RecyclerView recyclerView;
    List<DienTu> dienTus;
    PresenterDientu presenterDientu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dien_tu, container, false);
        init(view);
        presenterDientu=new PresenterDientu(this);
        dienTus=new ArrayList<>();

        presenterDientu.getBrands();

        return view;
    }

    void init(View view){
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerDienTu);
    }


    @Override
    public void showBrands(List<Brand> brands) {
        DienTu dienTu=new DienTu();

        dienTu.setBrands(brands);
        dienTus.add(dienTu);

        AdapterDienTu adapterDienTu=new AdapterDienTu(getContext(),dienTus);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();
    }
}
