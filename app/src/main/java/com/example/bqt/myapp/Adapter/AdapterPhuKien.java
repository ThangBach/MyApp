package com.example.bqt.myapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bqt.myapp.Model.Constaint;
import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.Model.ObjectClass.ProductCategory;
import com.example.bqt.myapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BQT on 6/10/2017.
 */

public class AdapterPhuKien extends RecyclerView.Adapter<AdapterPhuKien.ViewHolder>{

    Context myContext;
    List<ProductCategory> productCategories;

    public AdapterPhuKien(Context myContext, List<ProductCategory> productCategories) {
        this.myContext = myContext;
        this.productCategories = productCategories;
    }

    @Override
    public AdapterPhuKien.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycle_phukien, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterPhuKien.ViewHolder holder, int position) {
        ProductCategory category = productCategories.get(position);
        holder.txt_phukien.setText(category.getName());
        Picasso.with(myContext).load(Constaint.URL_IMG_MOBILE+"UploadFiles/userfiles/images/productcategories/" + category.getImage()).resize(120, 120).into(holder.img_phukien, new Callback() {
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
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_phukien;
        ImageView img_phukien;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_phukien = (TextView) itemView.findViewById(R.id.txt_phukien);
            img_phukien = (ImageView) itemView.findViewById(R.id.img_thuonghieu);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progress_bar_download);
        }
    }
}
