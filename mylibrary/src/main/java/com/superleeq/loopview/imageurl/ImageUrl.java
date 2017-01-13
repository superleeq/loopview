package com.superleeq.loopview.imageurl;

/**
 * Created by superleeq on 2016/12/25.
 */

public class ImageUrl {
    private String url;

    public ImageUrl(UrlType type, Object url) {
        this.url = type.getType() + url;
    }

    public String getUrl() {
        return url;
    }
}
