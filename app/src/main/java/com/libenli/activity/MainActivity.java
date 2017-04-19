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
import com.libenli.fragment.MineFragment;
import com.libenli.fragment.NoticeFragment;
import com.libenli.fragment.ProblemFragment;
import com.libenli.fragment.StatisticalFragment;
import com.libenli.fragment.TaskFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * 文件名：MainActivity
 * 描    述：主界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */
public class MainActivity extends BaseFragmentActivity {
    private Context context;
    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private static List<Fragment> fragmentList = new ArrayList<Fragment>();

    private ImageView taskImage, noticeImage, problemImage, statisticalImage, mineImage;
    private TextView taskText, noticeText, problemText, statisticalText, mineText;
    private LinearLayout layout_task, layout_notice, layout_problem, layout_statistical, layout_mine;


    private int fragmentIndex = 0;

    @Override
    public void setView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setData(Bundle savedInstanceState) {
        fragmentIndex = getIntent().getIntExtra("index", 0);
    }

    @Override
    public void init() {
        context = this;
        viewPager = (ViewPager) findViewById(R.id.activity_main_viewpager);
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
//        taskImage.setImageResource(R.mipmap.main_task_s);
//        taskText.setTextColor(ContextCompat.getColor(context, R.color.blue));
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
        taskImage.setImageResource(R.mipmap.main_task);
        noticeImage.setImageResource(R.mipmap.main_notice);
        problemImage.setImageResource(R.mipmap.main_problem);
        statisticalImage.setImageResource(R.mipmap.main_statistical);
        mineImage.setImageResource(R.mipmap.main_mine);
        /**
         * 文字
         */
        taskText.setTextColor(ContextCompat.getColor(context, R.color.gray));
        noticeText.setTextColor(ContextCompat.getColor(context, R.color.gray));
        problemText.setTextColor(ContextCompat.getColor(context, R.color.gray));
        statisticalText.setTextColor(ContextCompat.getColor(context, R.color.gray));
        mineText.setTextColor(ContextCompat.getColor(context, R.color.gray));

        switch (position) {
            case 0:
                taskImage.setImageResource(R.mipmap.main_task_s);
                taskText.setTextColor(ContextCompat.getColor(context, R.color.blue));
                break;
            case 1:
                noticeImage.setImageResource(R.mipmap.main_notice_s);
                noticeText.setTextColor(ContextCompat.getColor(context, R.color.blue));
                break;
            case 2:
                problemImage.setImageResource(R.mipmap.main_problem_s);
                problemText.setTextColor(ContextCompat.getColor(context, R.color.blue));
                break;
            case 3:
                statisticalImage.setImageResource(R.mipmap.main_statistical_s);
                statisticalText.setTextColor(ContextCompat.getColor(context, R.color.blue));
                break;
            case 4:
                mineImage.setImageResource(R.mipmap.main_mine_s);
                mineText.setTextColor(ContextCompat.getColor(context, R.color.blue));
                break;
        }
    }

    private void initView() {
        /**
         * 小图标
         */
        taskImage = (ImageView) findViewById(R.id.main_image_task);
        noticeImage = (ImageView) findViewById(R.id.main_image_notice);
        problemImage = (ImageView) findViewById(R.id.main_image_problem);
        statisticalImage = (ImageView) findViewById(R.id.main_image_statistical);
        mineImage = (ImageView) findViewById(R.id.main_image_mine);
        /**
         * 文字
         */
        taskText = (TextView) findViewById(R.id.main_text_task);
        noticeText = (TextView) findViewById(R.id.main_text_notice);
        problemText = (TextView) findViewById(R.id.main_text_problem);
        statisticalText = (TextView) findViewById(R.id.main_text_statistical);
        mineText = (TextView) findViewById(R.id.main_text_mine);

        /**
         * 点击布局控件
         */
        layout_task = (LinearLayout) findViewById(R.id.main_layout_task);
        layout_notice = (LinearLayout) findViewById(R.id.main_layout_notice);
        layout_problem = (LinearLayout) findViewById(R.id.main_layout_problem);
        layout_statistical = (LinearLayout) findViewById(R.id.main_layout_statistical);
        layout_mine = (LinearLayout) findViewById(R.id.main_layout_mine);


        fragmentList.add(new TaskFragment());
        fragmentList.add(new NoticeFragment());
        fragmentList.add(new ProblemFragment());
        fragmentList.add(new StatisticalFragment());
        fragmentList.add(new MineFragment());

        layout_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                viewPager.setCurrentItem(0);
            }
        });

        layout_notice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                viewPager.setCurrentItem(1);
            }
        });

        layout_problem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                viewPager.setCurrentItem(2);
            }
        });

        layout_statistical.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                viewPager.setCurrentItem(3);
            }
        });
        layout_mine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                viewPager.setCurrentItem(4);
            }
        });
    }


}
