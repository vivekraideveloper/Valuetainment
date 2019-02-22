package com.vivekrai.vivekraivaluetainment.valuetainment;

import com.google.firebase.database.Exclude;
//
/**
 * Created by MR VIVEK RAI on 15-06-2018.
 */

public class Upload {

    private String name;
    private String imageUrl;
    private String mkey;
    private String videoId;
    private String likes;
    private String authorName;

    public Upload() {
    }

    public Upload(String name, String imageUrl, String videoId, String likes, String authorName) {
        if (name.trim() == ""){
            name = "No Name";
        }
        this.name = name;
        this.imageUrl = imageUrl;
        this.videoId = videoId;
        this.likes = likes;
        this.authorName = authorName;
    }

    public String getName() {
        return name;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    @Exclude
    public String getMkey() {
        return mkey;
    }

    @Exclude
    public void setMkey(String mkey) {
        this.mkey = mkey;
    }
}
