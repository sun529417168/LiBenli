package com.libenli.fragment.parent;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.libenli.R;
import com.libenli.adapter.parent.InfoAdapter;
import com.libenli.base.BaseFragment;
import com.libenli.bean.StudentInfoBean;

import java.util.ArrayList;
import java.util.Random;


/**
 * 文件名：RankCoachFragment
 * 描    述：家长端成绩排名
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class RankFragment extends BaseFragment {
    private Context context;
    private TextView tv_title;
    private ArrayList<StudentInfoBean> listBean = new ArrayList<>();
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private InfoAdapter infoAdapter;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_score_rank, null);
        return view;
    }

    @Override
    protected void setDate() {

    }


    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.main_paihang));
        mPullRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.score_rank_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
//        infoAdapter = new InfoAdapter(context, listBean);
//        mPullRefreshListView.setAdapter(infoAdapter);
//        infoAdapter.notifyDataSetChanged();
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务

                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务

                mPullRefreshListView.onRefreshComplete();
            }
        });
    }



}
