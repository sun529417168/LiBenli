package com.libenli.fragment.coach;

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
import com.libenli.adapter.coach.RankCoachAdapter;
import com.libenli.base.BaseFragment;
import com.libenli.bean.CacheBean;
import com.libenli.bean.StudentInfoBean;
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.utils.MyRequest;
import com.libenli.utils.SharedUtil;

import java.util.ArrayList;


/**
 * 文件名：ResultsInfoFragment
 * 描    述：战力排行
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class RankCoachFragment extends BaseFragment implements InterfaceHandler.StudentInfoInterface {

    private Context context;
    private TextView tv_title;
    private ArrayList<StudentInfoBean> listBean = new ArrayList<>();
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private RankCoachAdapter rankCoachAdapter;
    private int pageindex = 1;//页码数

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_info, null);
        return view;
    }

    @Override
    protected void setDate() {
        requestStudentInfo(pageindex);
    }

    private void requestStudentInfo(int pageindex) {
        MyRequest.studentInfo(context, this, SharedUtil.getString(context, "DiId"), 300, pageindex);
    }

    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.main_paihang));
        mPullRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.info_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务
                pageindex = 1;
                requestStudentInfo(pageindex);
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务
//                pageindex++;
//                requestStudentInfo(pageindex);
//                rankCoachAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }
        });

    }


    @Override
    public void getstudentInfo(ArrayList<StudentInfoBean> studentInfoBean) {
        if (studentInfoBean == null || studentInfoBean.size() == 0) {
            return;
        }

        rankCoachAdapter = new RankCoachAdapter(context, studentInfoBean);
        mPullRefreshListView.setAdapter(rankCoachAdapter);
        rankCoachAdapter.notifyDataSetChanged();
//        if (studentInfoBean.size() == 0) {
//            mPullRefreshListView.setVisibility(View.GONE);
//        } else {
//            CacheBean.setStudentInfoBean(studentInfoBean);
//            mPullRefreshListView.setVisibility(View.VISIBLE);
//            if (pageindex == 1) {
//                listBean = studentInfoBean;
//                rankCoachAdapter = new RankCoachAdapter(context, listBean);
//                mPullRefreshListView.setAdapter(rankCoachAdapter);
//            } else if (pageindex > 1 && studentInfoBean.size() != 0) {
//                listBean.addAll(studentInfoBean);
//                rankCoachAdapter = new RankCoachAdapter(context, listBean);
//                mPullRefreshListView.setAdapter(rankCoachAdapter);
//            } else if (pageindex > 1 && studentInfoBean.size() == 0) {
//                ToastUtil.show(context, "没有更多数据了");
//            }
//        }
    }

    @Override
    public void deleteStudent() {

    }
}
