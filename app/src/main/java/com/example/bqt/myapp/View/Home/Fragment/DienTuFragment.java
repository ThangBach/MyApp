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
import android.widget.TextView;

import com.example.bqt.myapp.Adapter.AdapterDienTu;
import com.example.bqt.myapp.Adapter.AdapterThuongHieuNoiBat;
import com.example.bqt.myapp.Adapter.AdapterTopDienThoai;
import com.example.bqt.myapp.Model.Constaint;
import com.example.bqt.myapp.Model.DienTu.ModelDienTu;
import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.Model.ObjectClass.DienTu;
import com.example.bqt.myapp.Model.ObjectClass.Discount;
import com.example.bqt.myapp.Model.ObjectClass.Product;
import com.example.bqt.myapp.Model.ObjectClass.ProductCategory;
import com.example.bqt.myapp.Presenter.DienTu.PresenterDientu;
import com.example.bqt.myapp.R;
import com.example.bqt.myapp.View.Home.View.IViewDienTu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DienTuFragment extends Fragment implements IViewDienTu {

    RecyclerView recyclerView, recylerTopCacThuongHieuNoiBat, recylerHangMoiVe;
    ImageView imageCha, imageCon1, imageCon2, imageCon3, imageCon4;
    TextView txt_con1, txt_con2, txt_con3, txt_con4;
    List<DienTu> dienTuList;
    PresenterDientu presenterDientu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dien_tu, container, false);
        init(view);
        presenterDientu = new PresenterDientu(this);
        dienTuList = new ArrayList<>();

        presenterDientu.ShowData();
        presenterDientu.getBrands();
        presenterDientu.getProducts();
        LoadImageKhuyemai();

        return view;
    }

    void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerDienTu);
        recylerTopCacThuongHieuNoiBat = (RecyclerView) view.findViewById(R.id.recylerTopCacThuongHieuLon);
        recylerHangMoiVe= (RecyclerView) view.findViewById(R.id.recylerHangMoiVe);

        imageCha = (ImageView) view.findViewById(R.id.imKhuyenMaiDienTuCha);
        imageCon1 = (ImageView) view.findViewById(R.id.imKhuyenMaiDienTuCon1);
        imageCon2 = (ImageView) view.findViewById(R.id.imKhuyenMaiDienTuCon2);
        imageCon3 = (ImageView) view.findViewById(R.id.imKhuyenMaiDienTuCon3);
        imageCon4 = (ImageView) view.findViewById(R.id.imKhuyenMaiDienTuCon4);

        txt_con1 = (TextView) view.findViewById(R.id.txtKhuyenMaiDienTuCon1);
        txt_con2 = (TextView) view.findViewById(R.id.txtKhuyenMaiDienTuCon2);
        txt_con3 = (TextView) view.findViewById(R.id.txtKhuyenMaiDienTuCon3);
        txt_con4 = (TextView) view.findViewById(R.id.txtKhuyenMaiDienTuCon4);


    }

    void LoadImageKhuyemai() {
        ModelDienTu modelDienTu = new ModelDienTu();
        List<Discount> discounts = modelDienTu.getDiscountAll();
        String url = Constaint.URL_IMG_MOBILE + "UploadFiles/userfiles/images/promotion/common/";
        Picasso.with(getContext()).load(url + discounts.get(0).getDescription()).into(imageCha);
        Picasso.with(getContext()).load(url + discounts.get(1).getDescription()).into(imageCon1);
        Picasso.with(getContext()).load(url + discounts.get(2).getDescription()).into(imageCon2);
        Picasso.with(getContext()).load(url + discounts.get(3).getDescription()).into(imageCon3);
        Picasso.with(getContext()).load(url + discounts.get(4).getDescription()).into(imageCon4);

        txt_con1.setText(discounts.get(1).getName());
        txt_con2.setText(discounts.get(2).getName());
        txt_con3.setText(discounts.get(3).getName());
        txt_con4.setText(discounts.get(4).getName());


    }


    @Override
    public void showBrands(List<DienTu> dienTus) {
        dienTuList = dienTus;

        AdapterDienTu adapterDienTu = new AdapterDienTu(getContext(), dienTuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();
    }

    @Override
    public void showBigBrands(List<Brand> brands) {
        AdapterThuongHieuNoiBat adapterThuongHieuNoiBat = new AdapterThuongHieuNoiBat(getContext(), brands);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.HORIZONTAL, false);

        recylerTopCacThuongHieuNoiBat.setLayoutManager(layoutManager);
        recylerTopCacThuongHieuNoiBat.setAdapter(adapterThuongHieuNoiBat);
        adapterThuongHieuNoiBat.notifyDataSetChanged();
    }

    @Override
    public void showNewProductS(List<Product> products) {
        AdapterTopDienThoai adapterTopDienThoai = new AdapterTopDienThoai(getContext(), products);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);

        recylerHangMoiVe.setLayoutManager(layoutManager);
        recylerHangMoiVe.setAdapter(adapterTopDienThoai);
        adapterTopDienThoai.notifyDataSetChanged();
    }
}
