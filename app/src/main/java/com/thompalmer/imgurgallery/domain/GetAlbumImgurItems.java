package com.thompalmer.imgurgallery.domain;

import com.thompalmer.imgurgallery.data.ImgurGalleryResponse;
import com.thompalmer.imgurgallery.data.ImgurItem;
import com.thompalmer.imgurgallery.data.ImgurServiceProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by thompalmer on 2016-12-02.
 */

public class GetAlbumImgurItems {

    public Observable<List<ImgurItem>> execute(String albumId) {
       return ImgurServiceProvider.getService()
               .getAlbumImages(albumId)
               .map(new Function<ImgurGalleryResponse, List<ImgurItem>>() {
                   @Override
                   public List<ImgurItem> apply(ImgurGalleryResponse imgurGalleryResponse) throws Exception {
                       return imgurGalleryResponse.getData();
                   }
               });
    }
}
