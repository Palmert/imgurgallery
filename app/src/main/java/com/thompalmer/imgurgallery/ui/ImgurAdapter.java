package com.thompalmer.imgurgallery.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thompalmer.imgurgallery.R;
import com.thompalmer.imgurgallery.data.ImgurItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by thompalmer on 2016-11-30.
 */

class ImgurAdapter extends RecyclerView.Adapter<ImgurItemViewHolder> {

    private List<ImgurItem> imgurItems = Collections.emptyList();

    @Override
    public ImgurItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imgur_image, parent, false);
        return new ImgurItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImgurItemViewHolder holder, int position) {
        holder.init(imgurItems.get(position));
    }

    @Override
    public int getItemCount() {
        return imgurItems.size();
    }

    void setImgurItems(List<ImgurItem> imgurItems) {
        this.imgurItems = imgurItems;
        notifyDataSetChanged();
    }

    void addImgurItems(List<ImgurItem> imgurItems) {
        this.imgurItems.addAll(imgurItems);
        notifyItemRangeInserted(this.imgurItems.size() - imgurItems.size(), this.imgurItems.size());
    }
}
