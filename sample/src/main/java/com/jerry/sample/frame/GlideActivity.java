package com.jerry.sample.frame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jerry.sample.ListInfoAdapter;
import com.jerry.sample.ListInfoBean;
import com.jerry.sample.R;
import com.jerry.sample.frame.album.AlbumBucketActivity;
import com.jerry.sample.frame.eventbus.EventBusActivity;
import com.jerry.sample.frame.litepal.LitePalActivity;
import com.jerry.sample.frame.okhttp.OkHttpActivity;
import com.jerry.sample.frame.tab.TabActivity;
import com.jerry.sample.frame.wxalbum.WXAlbumActivity;
import com.jerry.sample.frame.xutils.XUtilsActivity;
import com.jerry.sample.utils.MyActivityManager;

import java.util.ArrayList;
import java.util.List;

public class GlideActivity extends Activity {

    private Context mContext;
    private ImageView mImageView;
    private Button mNormalBtn;
    private Button mGifBtn;
    private Button mCacheBtn;
    private Button mThumbnailBtn;

    private String imageUrl = "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D220/sign=55691eb096510fb367197095e932c893/a8014c086e061d95b9df673173f40ad162d9ca1a.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        mContext = GlideActivity.this;

        initView();
        initData();
    }

    private void initView(){
        mImageView = (ImageView)findViewById(R.id.image);
        mNormalBtn = (Button)findViewById(R.id.normal_btn);
        mGifBtn = (Button)findViewById(R.id.gif_btn);
        mCacheBtn = (Button)findViewById(R.id.cache_btn);
        mThumbnailBtn = (Button)findViewById(R.id.thumbnail_btn);
    }

    private void initData(){
        mNormalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                normalClick();
            }
        });
        mGifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gifClick();
            }
        });
        mCacheBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cacheClick();
            }
        });
        mThumbnailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thumbnailClick();
            }
        });

    }

    private void normalClick(){
        Glide.with(mContext)
                .load(imageUrl)      //图片加载路径
                                     // 1. 从资源中加载,比如drawable中
                                     // 2. 从文件中加载，比如sdcard中
                                     // 3. 从 Uri 中加载
                                     // 4. 加载一个gif
                                     // 5. 加载一个本地视频
                // 6.
                .placeholder(R.drawable.test_image)    //等待占位符
                .error(R.mipmap.ic_launcher)   //错误占位符
//                .crossFade()     //使用动画，此处使用默认动画
//                .dontAnimate()     //不使用动画
//                .override(200,200)    //在图片显示到 ImageView之前重新改变图片大小。
//                .centerCrop()         //缩放图像让它填充到 ImageView 界限内并且裁剪额外的部分。ImageView 可能会完全填充，但图像可能不会完整显示。
//                .fitCenter()       //缩放图像让图像都测量出来等于或小于 ImageView 的边界范围。该图像将会完全显示，但可能不会填满整个 ImageView。
//                .priority(Priority.HIGH)   //优先级　　按照递增关系 Priority.LOW < Priority.NORMAL < Priority.HIGH < Priority.IMMEDIATE
                .into(mImageView);     //图片显示到对应的 ImageView 中


    }

    private void gifClick(){
        Glide.with(mContext)
                .load(imageUrl)      //图片加载路径
//                .asGif()    //Gif检查。如果 imageUrl 是一个 gif，这没什么变化。反之，Glide 将会把这个 load 当成失败处理。
                .asBitmap()   //显示 Gif 的第一帧
                .placeholder(R.drawable.test_image)    //等待占位符
                .error(R.mipmap.ic_launcher)   //错误占位符
                .into(mImageView);     //图片显示到对应的 ImageView 中
    }

    /**
     * Picasso 仅仅缓存了全尺寸的图像。然而 Glide 缓存了原始图像，全分辨率图像和另外小版本的图像。
     * 比如，如果你请求的一个图像是 1000x1000 像素的，但你的 ImageView 是 500x500 像素的，Glide 将会把这两个尺寸都进行缓存。
     * DiskCacheStrategy.NONE 什么都不缓存，就像刚讨论的那样
     * DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像。在我们上面的例子中，将会只有一个 1000x1000 像素的图片
     * DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即，降低分辨率后的（或者是转换后的）
     * DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
     */
    private void cacheClick(){
        Glide.with(mContext)
                .load(imageUrl)      //图片加载路径
                .placeholder(R.drawable.test_image)    //等待占位符
                .error(R.mipmap.ic_launcher)   //错误占位符
                .skipMemoryCache(true)  //忽略内存缓存，默认为false,即不忽略内存缓存。
                .diskCacheStrategy(DiskCacheStrategy.NONE)    //忽略磁盘缓存
                .into(mImageView);     //图片显示到对应的 ImageView 中
    }

    /**
     * 缩略图是动态占位符。缩略图在原始图像到达之后，它不会取代原始图像。它只会被抹除。
     * Glide 为缩略图提供2个不同的方式。
     *      第一个是简单的选择，在原始图像被用过之后，这只需要一个较小的分辨率。
     *      第二个选择是传一个完全新的 Glide 请求作为参数。
     */
    private void thumbnailClick(){
        Glide.with(mContext)
                .load(imageUrl)      //图片加载路径
                .placeholder(R.drawable.test_image)    //等待占位符
                .error(R.mipmap.ic_launcher)   //错误占位符
                .thumbnail(0.1f)    //你传了一个 0.1f 作为参数，Glide 将会显示原始图像的10%的大小。如果原始图像有 1000x1000 像素，那么缩略图将会有 100x100 像素。
//                .thumbnail(thumbnailRequest)
                .into(mImageView);     //图片显示到对应的 ImageView 中
    }



}
