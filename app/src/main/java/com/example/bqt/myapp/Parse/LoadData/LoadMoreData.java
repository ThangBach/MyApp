package com.example.bqt.myapp.Parse.LoadData;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by BQT on 6/15/2017.
 */

public class LoadMoreData extends RecyclerView.OnScrollListener {
    int firstItem;
    int sumItem;
    int itemFirstLoad = 10;

    RecyclerView.LayoutManager layoutManager;

    ILoadMoreData iLoadMoreData;

    public LoadMoreData(RecyclerView.LayoutManager layoutManager, ILoadMoreData iLoadMoreData) {
        this.layoutManager = layoutManager;
        this.iLoadMoreData = iLoadMoreData;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        sumItem = layoutManager.getItemCount();

        if (layoutManager instanceof LinearLayoutManager) {
            firstItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else {
            firstItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if (sumItem<=(firstItem+itemFirstLoad)){
            iLoadMoreData.LoadMore(sumItem);
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
