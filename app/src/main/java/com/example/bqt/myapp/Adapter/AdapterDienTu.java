package com.example.bqt.myapp.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bqt.myapp.Model.ObjectClass.DienTu;
import com.example.bqt.myapp.R;

import java.util.List;

/**
 * Created by BQT on 6/9/2017.
 */

public class AdapterDienTu extends RecyclerView.Adapter<AdapterDienTu.ViewHolderDienTu> {

    Context myContext;
    List<DienTu> dienTus;

    public AdapterDienTu(Context myContext, List<DienTu> dienTus) {
        this.myContext = myContext;
        this.dienTus = dienTus;
    }

    public class ViewHolderDienTu extends RecyclerView.ViewHolder {

        ImageView imageKhuyenMai;
        RecyclerView recyclerViewBrands, recyclerViewProducts;

        public ViewHolderDienTu(View itemView) {
            super(itemView);

            recyclerViewBrands = (RecyclerView) itemView.findViewById(R.id.recyclerThuongHieuLon);
            recyclerViewProducts = (RecyclerView) itemView.findViewById(R.id.recyclerTopDienThoaiMayTinhBang);
            imageKhuyenMai = (ImageView) itemView.findViewById(R.id.imKhuyenMaiDienTu);
        }
    }

    @Override
    public ViewHolderDienTu onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycle_dientu, parent, false);

        ViewHolderDienTu viewHolderDienTu = new ViewHolderDienTu(view);

        return viewHolderDienTu;
    }

    @Override
    public void onBindViewHolder(AdapterDienTu.ViewHolderDienTu holder, int position) {
        DienTu dienTu=dienTus.get(position);

        AdapterThuongHieu adapterThuongHieu=new AdapterThuongHieu(myContext,dienTu.getBrands());

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(myContext,3,GridLayoutManager.HORIZONTAL,true);

        holder.recyclerViewBrands.setLayoutManager(layoutManager);
        holder.recyclerViewBrands.setAdapter(adapterThuongHieu);
        adapterThuongHieu.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dienTus.size();
    }
}
