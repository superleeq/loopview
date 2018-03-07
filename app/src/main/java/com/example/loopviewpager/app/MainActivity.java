package com.example.loopviewpager.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        LoopView loopView = (LoopView) findViewById(R.id.loopPlay);
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
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(MainActivity.this).load(url).into(iv);
            }
        });
        loopView.setAdapter(loopAdapter);
        loopView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
