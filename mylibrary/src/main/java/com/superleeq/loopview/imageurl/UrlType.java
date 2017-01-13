package com.superleeq.loopview.imageurl;

/**
 * Created by superleeq on 2016/12/25.
 */

public enum UrlType {
    DRAWABLE("drawable://"),
    CONTENT("content://"),
    SDCARD("file://"),
    ASSETS("assets://"),
    HTTP("");

    String type;

    UrlType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
