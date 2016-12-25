package com.example.loopviewpager.app.loopview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.example.loopviewpager.app.imageurl.ImageUrl;
import com.example.loopviewpager.app.indicate.IndicateLayout;

import java.util.List;

/**
 * Created by superleeq on 2016/12/25.
 */

public class LoopView extends FrameLayout {
    private LoopViewPager mLoopViewPager;

    public LoopView(Context context) {
        super(context);
    }

    public LoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(List<ImageUrl> data) {
        initLoopView(data);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mLoopViewPager.setOnItemClickListener(onItemClickListener);
    }

    private void initLoopView(List<ImageUrl> data) {
        removeAllViews();
        mLoopViewPager = new LoopViewPager(getContext());
        LoopAdapter loopAdapter = new LoopAdapter(getContext(), data);
        mLoopViewPager.setAdapter(loopAdapter);
        addView(mLoopViewPager);
        IndicateLayout indicateLayout = new IndicateLayout(getContext());
        indicateLayout.setDotNumbers(data.size());
        mLoopViewPager.setIndicateLayout(indicateLayout);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        addView(indicateLayout, params);
    }

}
