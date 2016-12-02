package com.thompalmer.imgurgallery.ui.gallery;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;

import com.thompalmer.imgurgallery.R;
import com.thompalmer.imgurgallery.data.ImgurItem;
import com.thompalmer.imgurgallery.domain.GetGalleryImgurItems;
import com.thompalmer.imgurgallery.ui.ImgurRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thompalmer on 2016-12-02.
 */

public class GalleryView extends DrawerLayout implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.imgur_recyclerview) ImgurRecyclerView imgurRecyclerView;
    private GalleryPresenter presenter;

    public GalleryView(Context context) {
        super(context);
    }

    public GalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GalleryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setPresenter(GalleryPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        closeDrawer(GravityCompat.START);
        String galleryType = null;
        switch(item.getItemId()) {
            case R.id.nav_hot:
                galleryType = GetGalleryImgurItems.HOT;
                break;
            case R.id.nav_top:
                galleryType = GetGalleryImgurItems.TOP;
                break;
            case R.id.nav_meme:
                galleryType = GetGalleryImgurItems.MEME;
                break;
            case R.id.nav_random:
                galleryType = GetGalleryImgurItems.RANDOM;
                break;
        }
        presenter.onGalleryTypeChanged(galleryType);
        toolbar.setTitle(galleryType);
        return true;
    }

    public void setImgurItems(List<ImgurItem> imgurItems) {
        imgurRecyclerView.setImgurItems(imgurItems, new ImgurRecyclerView.ScrollListener() {
            @Override
            public void requestMoreItems() {
                presenter.requestMoreItems();
            }
        });
    }

    public void addImgurItems(List<ImgurItem> imgurItems) {
        imgurRecyclerView.addImgurItems(imgurItems);
    }
}
