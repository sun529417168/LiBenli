package com.libenli.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.libenli.R;
import com.libenli.adapter.coach.RankScoreCoachAdapter;
import com.libenli.base.BaseActivity;
import com.libenli.bean.StudentScoreBean;
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.utils.MyRequest;
import com.libenli.utils.ToastUtil;

import java.util.ArrayList;

/**
 * Created by sun_t on 2017/6/17.
 */

public class RankCoachActivity extends BaseActivity implements View.OnClickListener, InterfaceHandler.StudentScoreInterface {
    private TextView tv_title;
    private ArrayList<StudentScoreBean> listBean = new ArrayList<>();
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private String name, fignting;
    private TextView tv_name, tv_fighting;
    private RelativeLayout back;
    private String ssid;
    private int pageindex = 1;//页码数
    private RankScoreCoachAdapter rankScoreCoachAdapter;

    @Override
    protected void setView() {
        setContentView(R.layout.fragment_score_rank);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        name = getIntent().getStringExtra("studentName");
        fignting = getIntent().getStringExtra("fightingCapacity");
        ssid = getIntent().getStringExtra("ssid");
        requestData(pageindex);
    }

    private void requestData(int pageindex) {
        MyRequest.studentScore(this, ssid, 10, pageindex);
    }

    @Override
    protected void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        back = (RelativeLayout) findViewById(R.id.rl_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.main_paihang));
        tv_name = (TextView) findViewById(R.id.score_rank_name);
        tv_name.setText(name);
        tv_fighting = (TextView) findViewById(R.id.score_rank_score);
        tv_fighting.setText(fignting);
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.score_rank_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务
                pageindex = 1;
                requestData(pageindex);
                rankScoreCoachAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务
                pageindex++;
                requestData(pageindex);
                rankScoreCoachAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                BenLiApp.finishTop();
                break;
        }
    }

    @Override
    public void getstudentInfo(ArrayList<StudentScoreBean> studentScoreBean) {
        if (studentScoreBean == null) {
            return;
        }
        for (StudentScoreBean bean : studentScoreBean) {
            Log.i("战斗力数据", bean.toString());
        }
        if (studentScoreBean.size() == 0) {
            mPullRefreshListView.setVisibility(View.GONE);
        } else {
            mPullRefreshListView.setVisibility(View.VISIBLE);
            if (pageindex == 1) {
                listBean = studentScoreBean;
                rankScoreCoachAdapter = new RankScoreCoachAdapter(this, listBean);
                mPullRefreshListView.setAdapter(rankScoreCoachAdapter);
            } else if (pageindex > 1 && studentScoreBean.size() != 0) {
                listBean.addAll(studentScoreBean);
                rankScoreCoachAdapter = new RankScoreCoachAdapter(this, listBean);
                mPullRefreshListView.setAdapter(rankScoreCoachAdapter);
            } else if (pageindex > 1 && studentScoreBean.size() == 0) {
                ToastUtil.show(this, "没有更多数据了");
            }
        }
    }
}
