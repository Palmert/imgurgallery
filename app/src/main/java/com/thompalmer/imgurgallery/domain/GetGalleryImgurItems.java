package com.thompalmer.imgurgallery.domain;

import com.thompalmer.imgurgallery.data.ImgurGalleryResponse;
import com.thompalmer.imgurgallery.data.ImgurItem;
import com.thompalmer.imgurgallery.data.ImgurService;
import com.thompalmer.imgurgallery.data.ImgurServiceProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by thompalmer on 2016-11-30.
 */

public class GetGalleryImgurItems {
    public static final String HOT = "Hot";
    public static final String TOP = "Top";
    public static final String MEME = "Meme";
    public static final String RANDOM = "Random";
    private static final String DEFAULT_SORT = "viral";


    public Observable<List<ImgurItem>> execute(String gallerySection, int currentPage) {
        ImgurService imgurService = ImgurServiceProvider.getService();
        Observable<ImgurGalleryResponse> galleryResponseObservable;
        switch(gallerySection) {
            case HOT:
            case TOP:
                galleryResponseObservable = imgurService.getGallery(gallerySection.toLowerCase(), DEFAULT_SORT, currentPage);
                break;
            case MEME:
                galleryResponseObservable = imgurService.getMemesGallery(DEFAULT_SORT, currentPage);
                break;
            case RANDOM:
                galleryResponseObservable = imgurService.getRandomGallery(currentPage);
                break;
            default:
                throw new IllegalArgumentException(String.format("Invalid gallery section %s", gallerySection));
        }

        return galleryResponseObservable.map(new Function<ImgurGalleryResponse, List<ImgurItem>>() {
            @Override
            public List<ImgurItem> apply(ImgurGalleryResponse imgurGalleryResponse) throws Exception {
                return imgurGalleryResponse.getData();
            }
        });
    }
}
