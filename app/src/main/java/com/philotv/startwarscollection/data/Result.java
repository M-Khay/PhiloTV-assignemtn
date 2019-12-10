package com.philotv.startwarscollection.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Result {
    @SerializedName("image")
    @Expose
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
