package com.thompalmer.imgurgallery.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.thompalmer.imgurgallery.data.ImgurItem;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by thompalmer on 2016-11-30.
 */

public class ImgurRecyclerView extends RecyclerView {
    private static final int VISIBLE_THRESHOLD = 5;
    private boolean loading;
    private ScrollListener scrollListener;
    private ImgurAdapter imgurAdapter;
    private LinearLayoutManager layoutManager;

    public interface ScrollListener {
        void requestMoreItems();
    }

    public ImgurRecyclerView(Context context) {
        super(context);
    }

    public ImgurRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImgurRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        imgurAdapter = new ImgurAdapter();
        layoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(layoutManager);
        setAdapter(imgurAdapter);
    }

    public void setImgurItems(List<ImgurItem> imgurItems, ScrollListener scrollListener) {
        this.scrollListener = scrollListener;
        loading = false;
        imgurAdapter.setImgurItems(imgurItems);
    }

    public void setImgurItems(List<ImgurItem> imgurItems) {
        setImgurItems(imgurItems, null);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        int totalItemCount = imgurAdapter.getItemCount();
        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (!loading && (totalItemCount - getChildCount()) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            if(scrollListener != null) {
                scrollListener.requestMoreItems();
            }
            loading = true;
        }
    }
}
