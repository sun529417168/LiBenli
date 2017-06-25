package com.libenli.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.BaseFragmentActivity;
import com.libenli.fragment.parent.DianMingPFragment;
import com.libenli.fragment.parent.ResultsInfoFragment;
import com.libenli.fragment.parent.RankFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * 文件名：MainParentActivity
 * 描    述：主界面家长
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */
public class MainParentActivity extends BaseFragmentActivity {
    private Context context;
    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private static List<Fragment> fragmentList = new ArrayList<Fragment>();

    private ImageView dianDaoImage, infoImage, rankImage;
    private TextView dianDaoText, infoText, rankText;
    private LinearLayout layout_dianDao, layout_info, layout_rank;


    private int fragmentIndex = 0;

    @Override
    public void setView() {
        setContentView(R.layout.activity_main_parent);
    }

    @Override
    public void setData(Bundle savedInstanceState) {
        fragmentIndex = getIntent().getIntExtra("index", 0);
    }

    @Override
    public void init() {
        context = this;
        viewPager = (ViewPager) findViewById(R.id.activity_main_parent_viewpager);
        initView();
        fragmentPagerAdapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                // TODO Auto-generated method stub
                return fragmentList.get(position);
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);


        //初始化第一个
        dianDaoImage.setImageResource(R.mipmap.dianying_h);
        dianDaoText.setTextColor(ContextCompat.getColor(context, R.color.blue));
        changeBottom(fragmentIndex);
        viewPager.setCurrentItem(fragmentIndex);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                changeBottom(position);

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void changeBottom(int position) {
        /**
         * 图片
         */
        dianDaoImage.setImageResource(R.mipmap.dianming_nor);
        infoImage.setImageResource(R.mipmap.me_nor);
        rankImage.setImageResource(R.mipmap.rank_nor);
        /**
         * 文字
         */
        dianDaoText.setTextColor(ContextCompat.getColor(context, R.color.gray));
        infoText.setTextColor(ContextCompat.getColor(context, R.color.gray));
        rankText.setTextColor(ContextCompat.getColor(context, R.color.gray));

        switch (position) {
            case 0:
                dianDaoImage.setImageResource(R.mipmap.dianying_h);
                dianDaoText.setTextColor(ContextCompat.getColor(context, R.color.blue));
                break;
            case 1:
                rankImage.setImageResource(R.mipmap.rank_h);
                rankText.setTextColor(ContextCompat.getColor(context, R.color.blue));
                break;
            case 2:
                infoImage.setImageResource(R.mipmap.me_h);
                infoText.setTextColor(ContextCompat.getColor(context, R.color.blue));
                break;
        }
    }

    private void initView() {
        /**
         * 小图标
         */
        dianDaoImage = (ImageView) findViewById(R.id.main_parent_image_dianming);
        infoImage = (ImageView) findViewById(R.id.main_parent_image_info);
        rankImage = (ImageView) findViewById(R.id.main_parent_image_rank);
        /**
         * 文字
         */
        dianDaoText = (TextView) findViewById(R.id.main_parent_text_dianming);
        infoText = (TextView) findViewById(R.id.main_parent_text_info);
        rankText = (TextView) findViewById(R.id.main_parent_text_rank);

        /**
         * 点击布局控件
         */
        layout_dianDao = (LinearLayout) findViewById(R.id.main_parent_layout_dianming);
        layout_info = (LinearLayout) findViewById(R.id.main_parent_layout_info);
        layout_rank = (LinearLayout) findViewById(R.id.main_parent_layout_rank);


        fragmentList.add(new DianMingPFragment());
        fragmentList.add(new RankFragment());
        fragmentList.add(new ResultsInfoFragment());

        layout_dianDao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                viewPager.setCurrentItem(0);
            }
        });

        layout_rank.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                viewPager.setCurrentItem(1);
            }
        });

        layout_info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                viewPager.setCurrentItem(2);
            }
        });


    }


}
