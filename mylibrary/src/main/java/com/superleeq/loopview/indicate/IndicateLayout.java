package com.superleeq.loopview.indicate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * Created by superleeq on 2016/12/25.
 */

public class IndicateLayout extends LinearLayout {
    private int dotDPSize = 10;
    public IndicateLayout(Context context) {
        super(context);
    }

    public IndicateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDotNumbers(int dotNumbers) {
        setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        for (int i = 0; i < dotNumbers; i++) {
            Dot dot = new Dot(getContext());
            int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dotDPSize, getResources().getDisplayMetrics());
            LayoutParams params = new LayoutParams(size, size);
            params.leftMargin = size;
            dot.setLayoutParams(params);
            addView(dot);
        }
        setChecked(0);
    }

    public void setChecked(int checkedItem) {
        if (checkedItem > -1 && checkedItem < getChildCount()) {
            for (int i = 0; i < getChildCount(); i++) {
                ((Dot) getChildAt(i)).setChecked(i == checkedItem);
            }
        }
    }

}
