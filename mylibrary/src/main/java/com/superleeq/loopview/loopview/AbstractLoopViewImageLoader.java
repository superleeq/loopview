package com.superleeq.loopview.loopview;

import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by superleeq on 2018/3/7.
 * .load(String string)	string可以为一个文件路径、uri或者url
 * .load(Uri uri)	uri类型
 * .load(File file)	文件
 * .load(Integer resourceId)资源Id,R.drawable.xxx或者R.mipmap.xxx
 */

public abstract class AbstractLoopViewImageLoader {
    public void displyImage(String url, ImageView iv) {
    }

    public void displyImage(Uri uri, ImageView iv) {
    }

    public void displyImage(File file, ImageView iv) {
    }

    public void displyImage(int resId, ImageView iv) {
    }
}
