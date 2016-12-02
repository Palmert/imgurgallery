package com.thompalmer.imgurgallery.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Locale;

/**
 * Created by thompalmer on 2016-11-30.
 */

public class ImgurItem {
    private static final String MEDIUM = "m";
    private static final String VIEW_COUNT_TEMPLATE = "%d view%s";
    private static final String ALBUM = "Album";
    private static final String ANIMATED = "Animated";
    private static final String IMAGE = "Image";

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("views")
    @Expose
    private Long views;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("is_album")
    @Expose
    private Boolean isAlbum;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("animated")
    @Expose
    private Boolean animated;

    public String getId() {
        return id;
    }

    public String getCover() {
        return cover;
    }

    public String getLink() {
        return link;
    }

    public boolean isAlbum() {
        return isAlbum != null && isAlbum;
    }

    public String getMediumThumbnailLink() {
        return link.substring(0, link.lastIndexOf(".")) + MEDIUM + link.substring(link.lastIndexOf("."));
    }

    public String getHeadline() {
        return title != null ? title : description != null ? description : "";
    }

    @NonNull
    public String getType() {
        return isAlbum() ? ALBUM : animated ? ANIMATED : IMAGE;
    }

    public String getViewCount() {
        return String.format(Locale.getDefault(), VIEW_COUNT_TEMPLATE, views, views == 1 ? "" : "s");
    }
}

