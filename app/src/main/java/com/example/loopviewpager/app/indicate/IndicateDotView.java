package com.example.loopviewpager.app.indicate;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.loopviewpager.app.R;

/**
 * Created by superleeq on 2016/12/25.
 */

public class IndicateDotView extends ImageView {

    public IndicateDotView(Context context) {
        super(context);
        init();
    }

    public IndicateDotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setImageResource(R.mipmap.dotnormal);
    }

    public void setChecked(boolean checked) {
        setImageResource(checked ? R.mipmap.dotselected : R.mipmap.dotnormal);
    }

}
