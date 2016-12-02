package com.thompalmer.imgurgallery.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thompalmer on 2016-11-30.
 */

public class ImgurGalleryResponse {
    @SerializedName("data")
    @Expose
    private List<ImgurItem> data = new ArrayList<>();
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<ImgurItem> getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getStatus() {
        return status;
    }
}

