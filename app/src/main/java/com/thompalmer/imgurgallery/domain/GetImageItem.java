package com.thompalmer.imgurgallery.domain;

import com.thompalmer.imgurgallery.data.ImageResponse;
import com.thompalmer.imgurgallery.data.ImgurServiceProvider;

import io.reactivex.Observable;

/**
 * Created by thompalmer on 2016-11-30.
 */

public class GetImageItem {
    public Observable<ImageResponse> execute(String imageId) {
        return ImgurServiceProvider.getService().getImage(imageId);
    }
}
