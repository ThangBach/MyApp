package com.example.bqt.myapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BQT on 6/7/2017.
 */

public class AdapterThuongHieu extends RecyclerView.Adapter<AdapterThuongHieu.ViewHolder> {

    Context myContext;
    List<Brand> brands;

    public AdapterThuongHieu(Context myContext, List<Brand> brands) {
        this.myContext = myContext;
        this.brands = brands;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycle_thuonghieu, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Brand brand = brands.get(position);
        holder.txt_Tieude_Thuonghieu.setText(brand.getName());
        Picasso.with(myContext).load("http://192.168.56.1/Itech/UploadFiles/userfiles/images/" + brand.getImage()).resize(120, 120).into(holder.img_Thuonghieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_Tieude_Thuonghieu;
        ImageView img_Thuonghieu;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_Tieude_Thuonghieu = (TextView) itemView.findViewById(R.id.txt_tieude);
            img_Thuonghieu = (ImageView) itemView.findViewById(R.id.img_thuonghieu);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progress_bar_download);
        }
    }
}
