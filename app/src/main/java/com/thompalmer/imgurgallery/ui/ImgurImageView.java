package com.thompalmer.imgurgallery.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thompalmer.imgurgallery.data.ImgurItem;

/**
 * Created by thompalmer on 2016-12-02.
 */

public class ImgurImageView extends ImageView {
    public ImgurImageView(Context context) {
        super(context);
    }

    public ImgurImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImgurImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImgurItem(ImgurItem imgurItem) {
        DrawableRequestBuilder<String> thumbnailRequest = Glide
                .with(getContext())
                .load(imgurItem.getMediumThumbnailLink());

        Glide.with(getContext())
                .load(imgurItem.getLink())
                .thumbnail(thumbnailRequest)
                .fitCenter()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(this);
    }
}
