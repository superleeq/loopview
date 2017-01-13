package com.superleeq.loopview.loopview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.superleeq.loopview.imageurl.ImageUrl;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by superleeq on 2016/12/24.
 */

public class LoopAdapter extends PagerAdapter {

    private Context mContext;
    private List<ImageUrl> mUrls;
    private List<ImageView> mViews;

    public LoopAdapter(Context context, List<ImageUrl> urls) {
        this.mContext = context;
        this.mUrls = urls;
        initData();
    }

    private void initData() {
        if (mUrls == null || mUrls.size() == 0) {
            throw new NullPointerException();
        }
        mViews = new ArrayList<>();
        for (int i = 0; i < mUrls.size(); i++) {
            final ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            loadUrl(mUrls.get(i).getUrl(), imageView);
            mViews.add(imageView);
        }
        for (int i = 0; i < 2; i++) {
            final ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (i == 0) {
                loadUrl(mUrls.get(mUrls.size() - 1).getUrl(), imageView);
                mViews.add(0,imageView);
            } else {
                loadUrl(mUrls.get(0).getUrl(), imageView);
                mViews.add(imageView);
            }
        }
    }

    private void loadUrl(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view);
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
