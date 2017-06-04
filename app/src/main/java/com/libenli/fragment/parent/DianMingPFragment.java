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
import com.libenli.adapter.parent.DianMingPAdapter;
import com.libenli.base.BaseFragment;
import com.libenli.bean.DianMingBean;

import java.util.ArrayList;


/**
 * 文件名：DianMingPFragment
 * 描    述：点名
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class DianMingPFragment extends BaseFragment {
    private Context context;

    private TextView tv_title;
    private DianMingPAdapter dianMingAdapter;
    private ArrayList<DianMingBean> listBean = new ArrayList<>();
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_dianming, null);
        return view;
    }

    @Override
    protected void setDate() {
        for (int i = 0; i < 20; i++) {
            DianMingBean bean = new DianMingBean("测试" + i, i % 2 == 0 ? "请假" : "正常");
            listBean.add(bean);
        }
    }

    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.main_dianming));
        mPullRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.dianming_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        dianMingAdapter = new DianMingPAdapter(context, listBean);
        mPullRefreshListView.setAdapter(dianMingAdapter);
        dianMingAdapter.notifyDataSetChanged();
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
