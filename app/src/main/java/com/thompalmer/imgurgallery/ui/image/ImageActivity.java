package com.thompalmer.imgurgallery.ui.image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.thompalmer.imgurgallery.R;
import com.thompalmer.imgurgallery.data.ImageResponse;
import com.thompalmer.imgurgallery.domain.GetImageItem;
import com.thompalmer.imgurgallery.ui.ImgurImageView;
import com.thompalmer.imgurgallery.ui.ImgurItemViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by thompalmer on 2016-12-01.
 */

public class ImageActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.single_image) ImgurImageView imgurImageView;
    @BindView(R.id.headline_text) TextView headlineTextView;
    @BindView(R.id.views_count_text) TextView viewsCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        loadImage();
    }

    private void loadImage() {
        new GetImageItem().execute(getIntent().getStringExtra(ImgurItemViewHolder.EXTRA_ID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageResponse>() {
                    @Override
                    public void accept(ImageResponse imageResponse) throws Exception {
                        headlineTextView.setText(imageResponse.getImgurItem().getHeadline());
                        viewsCountTextView.setText(imageResponse.getImgurItem().getViewCount());
                        imgurImageView.setImgurItem(imageResponse.getImgurItem());
                    }
                });
    }
}
