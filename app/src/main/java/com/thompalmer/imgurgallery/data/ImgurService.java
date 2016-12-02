package com.thompalmer.imgurgallery.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by thompalmer on 2016-11-30.
 */

public interface ImgurService {

    @GET("/3/gallery/{section}/{sort}/{page}")
    Observable<ImgurGalleryResponse> getGallery(@Path("section") String section, @Path("sort") String sort, @Path("page") int page);

    @GET("/3/gallery/random/random/{page}")
    Observable<ImgurGalleryResponse> getRandomGallery(@Path("page") int page);

    @GET("/3/g/memes/{sort}/{page}")
    Observable<ImgurGalleryResponse> getMemesGallery(@Path("sort") String sort, @Path("page") int page);

    @GET("/3/album/{albumId}/images")
    Observable<ImgurGalleryResponse> getAlbumImages(@Path("albumId") String albumId);

    @GET("/3/image/{imageId}")
    Observable<ImageResponse> getImage(@Path("imageId") String imageId);
}
