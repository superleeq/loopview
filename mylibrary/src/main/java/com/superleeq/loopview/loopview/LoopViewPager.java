package com.superleeq.loopview.loopview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;

import com.superleeq.loopview.indicate.IndicateLayout;

import java.lang.ref.WeakReference;

/**
 * Created by superleeq on 2016/12/24.
 */

public class LoopViewPager extends ViewPager {

    private boolean mIsAutoPlay;
    private UIHandler mUiHandler;
    private long mLoopDelayMillis = 2000;
    private IndicateLayout mIndicateLayout;
    private WeakReference<LoopViewPager> mWeakReference;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    private static class UIHandler extends Handler {
        private WeakReference<LoopViewPager> weakReference;

        protected UIHandler(WeakReference<LoopViewPager> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            LoopViewPager loopViewPager = weakReference.get();
            if (loopViewPager == null || loopViewPager.getContext() == null) {
                return;
            }
            loopViewPager.nextPage();
        }
    }

    public void setLoopDelayMillis(long loopDelayMillis) {
        if (loopDelayMillis > 2000) {
            this.mLoopDelayMillis = loopDelayMillis;
        }
    }

    public void setIndicateLayout(IndicateLayout indicateLayout) {
        this.mIndicateLayout = indicateLayout;
    }

    private void startLoop() {
        mIsAutoPlay = true;
        mUiHandler.removeMessages(-1);
        mUiHandler.sendEmptyMessageDelayed(-1, mLoopDelayMillis);
    }

    private void nextPage() {
        if (mIsAutoPlay) {
            setCurrentItem((super.getCurrentItem() + 1) % getAdapter().getCount());
        }
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLoopViewPager();
    }

    public LoopViewPager(Context context) {
        super(context);
        initLoopViewPager();
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        if (getAdapter().getCount() > 1) {
            setCurrentItem(1);
            startLoop();
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private float downX;
    private float upX;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                mIsAutoPlay = false;
                break;
            case MotionEvent.ACTION_UP:
                startLoop();
                upX = ev.getX();
                if (Math.abs(upX - downX) <= 5) {
                    onItemClick();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                startLoop();
                break;
        }
        return super.onTouchEvent(ev);
    }

    public int getCurrentItem() {
        int position = super.getCurrentItem() - 1;
        if (super.getCurrentItem() == 0) {
            position = getAdapter().getCount() - 2;
        } else if (super.getCurrentItem() == getAdapter().getCount() - 1) {
            position = 0;
        }
        return position;
    }

    private void onItemClick() {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, LoopViewPager.this, getCurrentItem(), getId());
        }
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {

    }

    private void initLoopViewPager() {
        mWeakReference = new WeakReference<>(this);
        mUiHandler = new UIHandler(mWeakReference);
        super.setOnPageChangeListener(mOnPageChangeListener);
    }

    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case SCROLL_STATE_DRAGGING:
                    mIsAutoPlay = false;
                    break;
                case SCROLL_STATE_IDLE:
                    startLoop();
                    int index = LoopViewPager.super.getCurrentItem();
                    if (index == getAdapter().getCount() - 1) {
                        setCurrentItem(1, false);
                    } else if (index == 0) {
                        setCurrentItem(getAdapter().getCount() - 2, false);
                    }
                    break;
                case SCROLL_STATE_SETTLING:
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            if (mIndicateLayout != null) {
                mIndicateLayout.setChecked(getCurrentItem());
            }
        }
    };


}
