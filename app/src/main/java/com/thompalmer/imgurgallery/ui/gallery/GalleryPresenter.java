package com.thompalmer.imgurgallery.ui.gallery;

import com.thompalmer.imgurgallery.data.ImgurItem;
import com.thompalmer.imgurgallery.domain.GetGalleryImgurItems;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by thompalmer on 2016-12-02.
 */

class GalleryPresenter {
    private int currentPage = 0;
    private String galleryType = GetGalleryImgurItems.HOT;
    private final GalleryView view;
    private final GetGalleryImgurItems getGalleryImgurItems;

    GalleryPresenter(GalleryView view) {
        this.view = view;
        getGalleryImgurItems = new GetGalleryImgurItems();
        onGalleryTypeChanged(galleryType);
    }

    void onGalleryTypeChanged(String galleryType) {
        currentPage = 0;
        this.galleryType = galleryType;
        getImgurItems(galleryType).subscribe(new Consumer<List<ImgurItem>>() {
            @Override
            public void accept(List<ImgurItem> imgurItems) throws Exception {
                view.setImgurItems(imgurItems);
            }
        });
    }

    void requestMoreItems() {
        currentPage++;
        getImgurItems(galleryType).subscribe(new Consumer<List<ImgurItem>>() {
            @Override
            public void accept(List<ImgurItem> imgurItems) throws Exception {
                view.addImgurItems(imgurItems);
            }
        });
    }

    private Observable<List<ImgurItem>> getImgurItems(String galleryType) {
        return getGalleryImgurItems.execute(galleryType, currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
