package com.example.loopviewpager.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.superleeq.loopview.AbstractLoopViewImageLoader;
import com.superleeq.loopview.LoopAdapter;
import com.superleeq.loopview.LoopView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by superleeq on 2016/12/24.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        final LoopView loopView = (LoopView) findViewById(R.id.loopview);
        List<Object> urls = new ArrayList<>();
        urls.add(R.mipmap.car);
        urls.add(R.mipmap.bose);
        urls.add("http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg");
        urls.add(R.mipmap.yohji);
        LoopAdapter loopAdapter = new LoopAdapter(this);
        loopAdapter.setUrls(urls);
        loopAdapter.setImageLoader(new AbstractLoopViewImageLoader() {
            @Override
            public void displyImage(int resId, ImageView iv) {
                Glide.with(MainActivity.this).load(resId).into(iv);
            }

            @Override
            public void displyImage(String url, ImageView iv) {
                //手动设置图片适配模式
                //iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //加载图片
                Glide.with(MainActivity.this).load(url).into(iv);
            }
        });
        //禁用自动轮播 在setAdapter前调用
        loopView.setEnableAutoPlay(true);
        //设置轮播时间(毫秒)
        loopView.setLoopDelayMillis(1000);
        loopView.setAdapter(loopAdapter);
        loopView.setILoopViewListener(new LoopView.ILoopViewListener() {
            @Override
            public void onItemClick(int position) {
                //跳转页面
                Toast.makeText(loopView.getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int position) {
                //绑定指示器
                Log.e("lq", "onPageSelected==" + position);
            }
        });

    }


}
