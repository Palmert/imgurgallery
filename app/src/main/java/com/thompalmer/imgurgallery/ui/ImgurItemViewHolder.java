package com.thompalmer.imgurgallery.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thompalmer.imgurgallery.R;
import com.thompalmer.imgurgallery.data.ImageResponse;
import com.thompalmer.imgurgallery.data.ImgurItem;
import com.thompalmer.imgurgallery.domain.GetImageItem;
import com.thompalmer.imgurgallery.ui.album.AlbumActivity;
import com.thompalmer.imgurgallery.ui.image.ImageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by thompalmer on 2016-12-02.
 */

public class ImgurItemViewHolder extends RecyclerView.ViewHolder {
    public static final String EXTRA_ID = "extra_id";
    @BindView(R.id.imgur_image) ImgurImageView imgurImageView;
    @BindView(R.id.headline_text) TextView headlineTextView;
    @BindView(R.id.type_text) TextView typeTextView;
    @BindView(R.id.views_count_text) TextView viewsCountTextView;

    ImgurItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void init(final ImgurItem imgurItem) {
        imgurImageView.setImageDrawable(null);
        headlineTextView.setText(imgurItem.getHeadline());
        typeTextView.setText(imgurItem.getType());
        viewsCountTextView.setText(imgurItem.getViewCount());

        if (imgurItem.isAlbum()) {
            GetImageItem getImageItem = new GetImageItem();
            getImageItem.execute(imgurItem.getCover())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ImageResponse>() {
                        @Override
                        public void accept(ImageResponse response) throws Exception {
                            imgurImageView.setImgurItem(response.getImgurItem());
                        }
                    });
        } else {
            imgurImageView.setImgurItem(imgurItem);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), imgurItem.isAlbum() ? AlbumActivity.class : ImageActivity.class);
                intent.putExtra(EXTRA_ID, imgurItem.getId());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}