package com.example.bqt.myapp.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bqt.myapp.Model.Home.Menu.HandleJsonMenu;
import com.example.bqt.myapp.Model.ObjectClass.ProductCategory;
import com.example.bqt.myapp.R;

import java.util.List;

/**
 * Created by BQT on 6/2/2017.
 */

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<ProductCategory> categories;
    ViewHolderMenu viewHolderMenu;

    public ExpandAdapter(Context context, List<ProductCategory> categories) {
        this.context = context;
        this.categories = categories;

        setChild();
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (categories.get(groupPosition).getListSub().size() != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categories.get(groupPosition).getListSub().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return categories.get(groupPosition).getParentID();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return categories.get(groupPosition).getListSub().get(childPosition).getParentID();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu{
        TextView txtCategoryName;
        ImageView menuImage;
        LinearLayout linearLayout;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View viewGroupCha = convertView;
        if(viewGroupCha == null){
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_parent,parent,false);

            viewHolderMenu.txtCategoryName = (TextView) viewGroupCha.findViewById(R.id.menu_txt_categoryName);
            viewHolderMenu.menuImage = (ImageView) viewGroupCha.findViewById(R.id.menuImage);
            viewHolderMenu.linearLayout= (LinearLayout) viewGroupCha.findViewById(R.id.menuLayout);

            viewGroupCha.setTag(viewHolderMenu);
        }else{
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }

        viewHolderMenu.txtCategoryName.setText(categories.get(groupPosition).getName());

        int demsanphamcon = categories.get(groupPosition).getListSub().size();

        if(demsanphamcon > 0){
            viewHolderMenu.menuImage.setVisibility(View.VISIBLE);
        }else{
            viewHolderMenu.menuImage.setVisibility(View.INVISIBLE);
        }

        if(isExpanded){
            viewHolderMenu.menuImage.setImageResource(R.drawable.ic_remove_black_24dp);
            viewGroupCha.setBackgroundResource(R.color.it_colorgray);

        }else{
            viewHolderMenu.menuImage.setImageResource(R.drawable.more_icon);
            viewGroupCha.setBackgroundResource(R.color.it_White);
        }

        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        return viewGroupCha;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.custom_layout_child, parent, false);
//        ExpandableListView expandableListView= (ExpandableListView) view.findViewById(R.id.expMenuChild);

        SecondExpandable secondExpandable = new SecondExpandable(context);
        ExpandAdapter SecondAdapter = new ExpandAdapter(context, categories.get(groupPosition).getListSub());
        secondExpandable.setAdapter(SecondAdapter);
        notifyDataSetChanged();

        return secondExpandable;
    }

    public class SecondExpandable extends ExpandableListView {

        public SecondExpandable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;

//            widthMeasureSpec=MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private void setChild() {
        HandleJsonMenu handleJsonMenu = new HandleJsonMenu();

        for (int i = 0; i < categories.size(); i++) {
            categories.get(i).setListSub(handleJsonMenu.ParseMenuChild(categories.get(i).getID()));
        }
    }
}
