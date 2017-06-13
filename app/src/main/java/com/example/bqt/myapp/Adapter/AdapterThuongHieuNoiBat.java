package com.example.bqt.myapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bqt.myapp.Model.Constaint;
import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BQT on 6/13/2017.
 */

public class AdapterThuongHieuNoiBat extends RecyclerView.Adapter<AdapterThuongHieuNoiBat.ViewHolderThuongHieuNoiBat> {

    Context myContext;
    List<Brand> brands;

    public AdapterThuongHieuNoiBat(Context myContext, List<Brand> brands) {
        this.myContext = myContext;
        this.brands = brands;
    }

    public class ViewHolderThuongHieuNoiBat extends RecyclerView.ViewHolder {
        ImageView imageThuongHieuNoiBat;

        public ViewHolderThuongHieuNoiBat(View itemView) {
            super(itemView);

            imageThuongHieuNoiBat = (ImageView) itemView.findViewById(R.id.imLogoTopThuongNoiBat);
        }
    }

    @Override
    public AdapterThuongHieuNoiBat.ViewHolderThuongHieuNoiBat onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycle_thuonghieunoibat, parent, false);

        ViewHolderThuongHieuNoiBat viewHolderThuongHieuNoiBat = new ViewHolderThuongHieuNoiBat(view);
        return viewHolderThuongHieuNoiBat;
    }

    @Override
    public void onBindViewHolder(AdapterThuongHieuNoiBat.ViewHolderThuongHieuNoiBat holder, int position) {
        Brand brand = brands.get(position);
        String url = Constaint.URL_IMG_MOBILE + "UploadFiles/userfiles/images/brands/" + brand.getImage();

        Picasso.with(myContext).load(url).resize(180,90).centerInside().into(holder.imageThuongHieuNoiBat);
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }


}
