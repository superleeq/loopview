package com.superleeq.loopview;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.AdapterView;

import java.lang.ref.WeakReference;

/**
 * Created by superleeq on 2016/12/24.
 */

public class LoopView extends ViewPager {

    private boolean mIsAutoPlay;
    private UIHandler mUiHandler;
    private long mLoopDelayMillis = 2000;
    private WeakReference<LoopView> mWeakReference;
    private int mSlop;
    private ILoopViewListener mILoopViewListener;

    private static class UIHandler extends Handler {
        private WeakReference<LoopView> weakReference;

        protected UIHandler(WeakReference<LoopView> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            LoopView loopView = weakReference.get();
            if (loopView == null || loopView.getContext() == null) {
                return;
            }
            if (loopView.getContext() instanceof Activity) {
                Activity activity = (Activity) loopView.getContext();
                if (activity.isFinishing()) {
                    return;
                }
            }
            loopView.nextPage();
        }
    }

    public void setILoopViewListener(ILoopViewListener mILoopViewListener) {
        this.mILoopViewListener = mILoopViewListener;
    }

    public void setLoopDelayMillis(long loopDelayMillis) {
        if (loopDelayMillis > 2000) {
            this.mLoopDelayMillis = loopDelayMillis;
        }
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

    public LoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLoopViewPager();
    }

    public LoopView(Context context) {
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
                if (Math.abs(upX - downX) <= mSlop) {
                    onItemClick();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                startLoop();
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void onItemClick() {
        if (mILoopViewListener != null) {
            mILoopViewListener.onItemClick(getCurrentItem());
        }
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

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
    }

    private void initLoopViewPager() {
        mWeakReference = new WeakReference<>(this);
        mUiHandler = new UIHandler(mWeakReference);
        super.setOnPageChangeListener(mOnPageChangeListener);
        mSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(getContext()));
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
                    int index = LoopView.super.getCurrentItem();
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
            if (mILoopViewListener != null) {
                mILoopViewListener.onPageSelected(getCurrentItem());
            }
        }
    };

    public interface ILoopViewListener {
        //图片点击事件，便于跳转图片详情页
        void onItemClick(int position);

        //图片切换事件，便于绑定指示器
        void onPageSelected(int position);
    }


}
