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
import com.libenli.adapter.coach.DianMingCAdapter;
import com.libenli.adapter.coach.StudentInfoAdapter;
import com.libenli.adapter.parent.DianMingPAdapter;
import com.libenli.base.BaseFragment;
import com.libenli.bean.CacheBean;
import com.libenli.bean.DianMingBean;
import com.libenli.bean.StudentInfoBean;
import com.libenli.bean.StudentRollCallBean;
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.utils.MyRequest;
import com.libenli.utils.SharedUtil;
import com.libenli.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * 文件名：DianMingPFragment
 * 描    述：点名教练
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class DianMingCFragment extends BaseFragment implements InterfaceHandler.StudentRollCallInterface, InterfaceHandler.StudentInfoInterface {
    private Context context;

    private TextView tv_title, tv_date, nothing;
    private DianMingCAdapter dianMingAdapter;
    private ArrayList<StudentRollCallBean> listBean = new ArrayList<>();
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private int pageindex = 1;//页码数

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_dianming, null);
        return view;
    }

    @Override
    protected void setDate() {
//        requestData(pageindex);
        requestStudentInfo(pageindex);
    }

    private void requestData(int pageindex) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        MyRequest.studentRollCall(context, this, SharedUtil.getString(context, "DiId"), df.format(new Date()), 10, pageindex);
    }

    private void requestStudentInfo(int pageindex) {
        MyRequest.studentInfo(context, this, SharedUtil.getString(context, "DiId"), 10, pageindex);
    }

    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.main_dianming));
        mPullRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.dianming_refresh_list);
        tv_date = (TextView) rootView.findViewById(R.id.dianming_date_today);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        tv_date.setText(df.format(new Date()));
        nothing = (TextView) rootView.findViewById(R.id.dianming_nothing);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务
                pageindex = 1;
                requestData(pageindex);
                dianMingAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务
                pageindex++;
                requestData(pageindex);
                dianMingAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }
        });
    }


    @Override
    public void getstudentRollCall(ArrayList<StudentRollCallBean> studentRollCallBean) {
        if (studentRollCallBean == null) {
            return;
        }
        if (studentRollCallBean.size() == 0) {
            mPullRefreshListView.setVisibility(View.GONE);
            nothing.setVisibility(View.VISIBLE);
        } else {
            mPullRefreshListView.setVisibility(View.VISIBLE);
            nothing.setVisibility(View.GONE);
            if (pageindex == 1) {
                listBean = studentRollCallBean;
                dianMingAdapter = new DianMingCAdapter(context, listBean);
                mPullRefreshListView.setAdapter(dianMingAdapter);
            } else if (pageindex > 1 && studentRollCallBean.size() != 0) {
                listBean.addAll(studentRollCallBean);
                dianMingAdapter = new DianMingCAdapter(context, listBean);
                mPullRefreshListView.setAdapter(dianMingAdapter);
            } else if (pageindex > 1 && studentRollCallBean.size() == 0) {
                ToastUtil.show(context, "没有更多数据了");
            }
        }
    }

    @Override
    public void getstudentInfo(ArrayList<StudentInfoBean> studentInfoBean) {
        if (studentInfoBean == null) {
            return;
        }
        CacheBean.setStudentInfoBean(studentInfoBean);
        StudentInfoAdapter adapter = new StudentInfoAdapter(context, studentInfoBean);
        mPullRefreshListView.setAdapter(adapter);
    }

    @Override
    public void deleteStudent() {

    }
}
