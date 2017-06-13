package com.example.bqt.myapp.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bqt.myapp.Model.Constaint;
import com.example.bqt.myapp.Model.ObjectClass.Product;
import com.example.bqt.myapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by BQT on 6/10/2017.
 */

public class AdapterTopDienThoai extends RecyclerView.Adapter<AdapterTopDienThoai.ViewHolderTopDienThoai> {

    Context myContext;
    List<Product> products;
    int layout;

    public AdapterTopDienThoai(Context myContext, List<Product> products) {
        this.myContext = myContext;
        this.products = products;
    }

    public class ViewHolderTopDienThoai extends RecyclerView.ViewHolder {
        ImageView imHinhSanPham;
        TextView txtTenSP,txtGiaTien,txtGiamGia;
        CardView cardView;
        public ViewHolderTopDienThoai(View itemView) {
            super(itemView);
            imHinhSanPham = (ImageView) itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtTenSP = (TextView) itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = (TextView) itemView.findViewById(R.id.txtGiamGiaDienTu);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

    @Override
    public ViewHolderTopDienThoai onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_topdienthoaivamaytinhbang,parent,false);

        ViewHolderTopDienThoai viewHolderTopDienThoai = new ViewHolderTopDienThoai(view);

        return viewHolderTopDienThoai;
    }

    @Override
    public void onBindViewHolder(AdapterTopDienThoai.ViewHolderTopDienThoai holder, int position) {
        Picasso.with(myContext).load(Constaint.URL_IMG_MOBILE+"UploadFiles/userfiles/images/products/"+products.get(position).getImage()).resize(140,140).centerInside().into(holder.imHinhSanPham);
        holder.txtTenSP.setText(products.get(position).getName());

        NumberFormat numberFormat=new DecimalFormat("###,###");
        String gia=numberFormat.format(products.get(position).getPrice());
        holder.txtGiaTien.setText(gia+" VND");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

}
