package com.thompalmer.imgurgallery.ui.gallery;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.thompalmer.imgurgallery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) GalleryView galleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, galleryView, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        galleryView.addDrawerListener(toggle);
        toggle.syncState();

        GalleryPresenter presenter = new GalleryPresenter(galleryView);
        galleryView.setPresenter(presenter);
    }

    @Override
    public void onBackPressed() {
        if (galleryView.isDrawerOpen(GravityCompat.START)) {
            galleryView.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
