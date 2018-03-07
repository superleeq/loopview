## LoopView自动轮播控件</br>

1，支持自动轮播，可以设置轮播间隔时间，默认2秒</br>
2，支持无限滑动，采用首尾填充的方案，杜绝跳动感，达到真实流畅的无限滑动</br>
3，支持任意第三方图片加载库</br>
4，支持手动设置图片适配模式</br>

## Screenshots</br>
![demo.png](https://github.com/superleeq/loopview/blob/master/app/src/main/res/raw/demo.png)

## 添加依赖：</br>
Step 1.</br>
```javascript
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2.</br>
```javascript
dependencies {
	 compile 'com.github.superleeq:loopview:v2.5'
}
```

## 使用步骤：</br>
1，绑定图片
```javascript
    LoopAdapter loopAdapter = new LoopAdapter(this);
    loopAdapter.setUrls(urls);
    loopAdapter.setImageLoader(new AbstractLoopViewImageLoader() {
              @Override
              public void displyImage(String url, ImageView iv) {
                  //iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                  Glide.with(MainActivity.this).load(url).into(iv);
              }
          });
```

2，设置adapter
```javascript
    loopView.setAdapter(loopAdapter);
    loopView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
            }
    });
```

## 版本记录</br>
v1.0 初始版</br>
v2.0 移除无用类</br>
v2.5 简化loopview</br>

## 反馈</br>
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流</br>
* 邮件(superleeq@foxmail.com)
* QQ: 446486198

