##LoopView自动轮播控件
1, 支持自动轮播，可以设置轮播间隔时间，默认2秒
2, 支持无限滑动，采用首尾填充的方案，没有跳动感，达到真实流畅的无限滑动
3, 支持任意第三方图片加载库，loopAdapter.setImageLoader即可
4, 支持手动设置图片适配模式

##Screenshots
![demo.png](https://github.com/superleeq/loopview/blob/master/app/src/main/res/raw/demo.png)

##使用步骤：
1,绑定图片
    LoopAdapter loopAdapter = new LoopAdapter(this);
    loopAdapter.setUrls(urls);
    loopAdapter.setImageLoader(new AbstractLoopViewImageLoader() {
            @Override
            public void displyImage(String url, ImageView iv) {
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(MainActivity.this).load(url).into(iv);
            }
        });

2,设置adapter
    loopView.setAdapter(loopAdapter);
    loopView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
            }
    });

##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流

* 邮件(superleeq@foxmail.com)
* QQ: 446486198

##为何开发LoopView
项目需要一个无限轮播控件，一般都是两种方案，一是301230方式首尾填充，二是getCount()返回Integer.MAX_VALUE
因第二种方案并不是真正的无限滑动，这里使用第一种方案实现
