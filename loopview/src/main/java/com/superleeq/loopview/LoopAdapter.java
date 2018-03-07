package com.superleeq.loopview;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by superleeq on 2016/12/24.
 */

public class LoopAdapter extends PagerAdapter {

    private Context mContext;
    private List<ImageView> mViews = new ArrayList<>();
    private List<Object> mUrls;
    private AbstractLoopViewImageLoader mImageLoader;

    public LoopAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setUrls(List<Object> mUrls) {
        this.mUrls = mUrls;
        initData();
    }

    public void setImageLoader(AbstractLoopViewImageLoader mImageLoader) {
        this.mImageLoader = mImageLoader;
    }

    private void initData() {
        if (mUrls == null || mUrls.size() == 0) {
            throw new NullPointerException();
        }
        mViews = new ArrayList<>();
        for (int i = 0; i < mUrls.size(); i++) {
            final ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mViews.add(imageView);
        }
        for (int i = 0; i < 2; i++) {
            final ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (i == 0) {
                mViews.add(0, imageView);
            } else {
                mViews.add(imageView);
            }
        }
    }

    private void loadUrl(Object url, ImageView imageView) {
        if (mImageLoader != null) {
            if (url instanceof String) {
                mImageLoader.displyImage((String) url, imageView);
            } else if (url instanceof Uri) {
                mImageLoader.displyImage((Uri) url, imageView);
            } else if (url instanceof File) {
                mImageLoader.displyImage((File) url, imageView);
            } else if (url instanceof Integer) {
                mImageLoader.displyImage((Integer) url, imageView);
            }
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view);

        int endPosition = mViews.size() - 1;
        if (position == 0) {
            loadUrl(mUrls.get(mUrls.size() - 1), (ImageView) view);
        } else if (position == endPosition) {
            loadUrl(mUrls.get(0), (ImageView) view);
        } else {
            loadUrl(mUrls.get(position - 1), (ImageView) view);
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
