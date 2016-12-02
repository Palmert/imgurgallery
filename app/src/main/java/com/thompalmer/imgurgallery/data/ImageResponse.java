package com.thompalmer.imgurgallery.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by thompalmer on 2016-11-30.
 */

public class ImageResponse {
    @SerializedName("data")
    @Expose
    private ImgurItem data;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("status")
    @Expose
    private Integer status;

    public ImgurItem getImgurItem() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getStatus() {
        return status;
    }
}
