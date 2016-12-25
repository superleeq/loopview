package com.example.loopviewpager.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.loopviewpager.app.imageurl.ImageUrl;
import com.example.loopviewpager.app.imageurl.UrlType;
import com.example.loopviewpager.app.loopview.LoopView;

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
        startLoop();
    }

    private void startLoop() {
        LoopView loopView = (LoopView) findViewById(R.id.loopPlay);
        List<ImageUrl> urls = new ArrayList<>();
        ImageUrl imageUrl1 = new ImageUrl(UrlType.DRAWABLE, R.mipmap.car);
        ImageUrl imageUrl2 = new ImageUrl(UrlType.DRAWABLE, R.mipmap.bose);
        ImageUrl imageUrl3 = new ImageUrl(UrlType.DRAWABLE, R.mipmap.mac);
        ImageUrl imageUrl4 = new ImageUrl(UrlType.DRAWABLE, R.mipmap.yohji);
        urls.add(imageUrl1);
        urls.add(imageUrl2);
        urls.add(imageUrl3);
        urls.add(imageUrl4);
        loopView.setData(urls);
        loopView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
