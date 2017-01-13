package com.superleeq.loopview.indicate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by superleeq on 2017/1/13.
 */

public class Dot extends View {
    private Paint mPaint = new Paint();
    private int mColor = Color.LTGRAY;

    public void setChecked(boolean checked) {
        mColor = checked ? Color.DKGRAY : Color.LTGRAY;
        mPaint.setColor(mColor);
        invalidate();
    }

    public Dot(Context context) {
        super(context);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        setPadding(5,5,5,5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
    }


}
