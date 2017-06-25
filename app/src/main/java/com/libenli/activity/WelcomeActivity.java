package com.libenli.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.libenli.R;
import com.libenli.base.BaseActivity;
import com.libenli.okhttps.OkHttpUtils;
import com.libenli.okhttps.callback.BitmapCallback;
import com.libenli.utils.SharedUtil;

import okhttp3.Call;


/**
 * 文件名：NoticeWelcomeActivity
 * 描    述：通知公告的欢迎界面
 * 作    者：stt
 * 时    间：2017.5.3
 * 版    本：V1.0.0
 */

public class WelcomeActivity extends BaseActivity {

    private ImageView splash;//欢迎图片,这里固定设置,可动态设置
    private Context mContext;
    private int type;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_welcome);
        mContext = this;
    }

    /**
     * 接收上级，填充数据 第二步
     */
    @Override
    protected void setDate(Bundle savedInstanceState) {
        type = SharedUtil.getInteger(this, "type", 0);
    }

    /**
     * 实例化控件 第三步
     */
    @Override
    protected void init() {
        splash = (ImageView) findViewById(R.id.splash);
        //欢迎图片动态设置操作
        initView();
    }

    /**
     * 数据操作
     */
    private void initView() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(5000);
        splash.startAnimation(scaleAnimation);

        //缩放动画监听
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent in = new Intent();
                switch (type) {
                    case 0:
                        in.setClass(WelcomeActivity.this, LoginActivity.class);
                        startActivity(in);
                        break;
                    case 2:
                        in.setClass(WelcomeActivity.this, MainCoachActivity.class);
                        startActivity(in);
                        break;
                    case 3:
                        in.setClass(WelcomeActivity.this, MainParentActivity.class);
                        startActivity(in);
                        break;
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    public void getImage(View view) {
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtils.get().url(url).tag(this).build().connTimeOut(20000).readTimeOut(20000).writeTimeOut(20000).execute(new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(Bitmap bitmap, int id) {
                Log.e("TAG", "onResponse：complete");
                splash.setImageBitmap(bitmap);
            }
        });
    }
}
