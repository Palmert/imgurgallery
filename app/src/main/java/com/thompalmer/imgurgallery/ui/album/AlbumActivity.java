package com.thompalmer.imgurgallery.ui.album;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.thompalmer.imgurgallery.R;
import com.thompalmer.imgurgallery.data.ImgurItem;
import com.thompalmer.imgurgallery.domain.GetAlbumImgurItems;
import com.thompalmer.imgurgallery.ui.ImgurItemViewHolder;
import com.thompalmer.imgurgallery.ui.ImgurRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AlbumActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imgur_recyclerview) ImgurRecyclerView imgurRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_gallery);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        loadAlbumItems();
    }

    public void loadAlbumItems() {
        new GetAlbumImgurItems().execute(getIntent().getStringExtra(ImgurItemViewHolder.EXTRA_ID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ImgurItem>>() {
                    @Override
                    public void accept(List<ImgurItem> imgurItems) throws Exception {
                        imgurRecyclerView.setImgurItems(imgurItems);
                    }
                });
    }
}
