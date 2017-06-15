package com.example.bqt.myapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bqt.myapp.Model.Constaint;
import com.example.bqt.myapp.Model.ObjectClass.Brand;
import com.example.bqt.myapp.R;
import com.example.bqt.myapp.View.Products.ProductListActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BQT on 6/7/2017.
 */

public class AdapterThuongHieu extends RecyclerView.Adapter<AdapterThuongHieu.ViewHolder> {

    Context myContext;
    List<Brand> brands;
    boolean checkBrand;

    public AdapterThuongHieu(Context myContext, List<Brand> brands, boolean checkBrand) {
        this.myContext = myContext;
        this.brands = brands;
        this.checkBrand = checkBrand;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycle_thuonghieu, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Brand brand = brands.get(position);
        holder.txt_Tieude_Thuonghieu.setText(brand.getName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)myContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ProductListActivity productListActivity = new ProductListActivity();

                Bundle bundle = new Bundle();
                bundle.putInt("ID",brand.getID());
                bundle.putBoolean("CHECK",checkBrand);
                bundle.putString("NAME",brand.getName());

                productListActivity.setArguments(bundle);
                fragmentTransaction.addToBackStack("TrangChuActivity");
                fragmentTransaction.replace(R.id.themFragment,productListActivity);
                fragmentTransaction.commit();

//                Intent showProduct = new Intent(myContext, ProductListActivity.class);
//                showProduct.putExtra("ID", brands.get(position).getID());
//                showProduct.putExtra("Name", brands.get(position).getName());
//                showProduct.putExtra("Check", checkBrand);
//                myContext.startActivity(showProduct);
            }
        });

        String url = Constaint.URL_IMG_MOBILE + "UploadFiles/userfiles/images/brands/" + brand.getImage();
        Picasso.with(myContext).load(url).into(holder.img_Thuonghieu, new Callback() {
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
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_Tieude_Thuonghieu = (TextView) itemView.findViewById(R.id.txt_tieude);
            img_Thuonghieu = (ImageView) itemView.findViewById(R.id.img_thuonghieu);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_download);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_thuonghieu);
        }
    }
}
