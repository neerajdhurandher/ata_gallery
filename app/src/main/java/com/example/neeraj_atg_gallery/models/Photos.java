
package com.example.neeraj_atg_gallery.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos {

    @SerializedName("photos")
    @Expose
    private Photos__1 photos;
    @SerializedName("stat")
    @Expose
    private String stat;

    public Photos__1 getPhotos() {
        return photos;
    }

    public void setPhotos(Photos__1 photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
