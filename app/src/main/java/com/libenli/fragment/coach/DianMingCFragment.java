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
import com.libenli.adapter.coach.FirstSaveAdapter;
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
import java.util.Iterator;


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
    private ArrayList<StudentInfoBean> studentInfoBeen = new ArrayList<>();

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_dianming, null);
        return view;
    }

    @Override
    protected void setDate() {
        requestStudentInfo(pageindex);
    }

    private void requestData(int pageindex) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        MyRequest.studentRollCall(context, this, SharedUtil.getString(context, "DiId"), df.format(new Date()), 300, pageindex);
    }

    private void requestStudentInfo(int pageindex) {
        MyRequest.studentInfo(context, this, SharedUtil.getString(context, "DiId"), 300, pageindex);
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
                studentInfoBeen = new ArrayList<>();
                requestStudentInfo(pageindex);
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务
//                pageindex++;
//                requestData(pageindex);
                mPullRefreshListView.onRefreshComplete();
            }
        });
    }


    @Override
    public void getstudentRollCall(ArrayList<StudentRollCallBean> studentRollCallBean) {
        if (studentRollCallBean == null || studentRollCallBean.size() == 0) {
            FirstSaveAdapter adapter = new FirstSaveAdapter(context, studentInfoBeen, this);
            mPullRefreshListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            /**
             * 这是在网络的数据小于本地的数据的情况下
             * 用网络的数据匹配本地的数据，如果网络的等于本地的先remove掉在add那条到本地
             */
            if (studentRollCallBean.size() < studentInfoBeen.size()) {
                for (int i = 0; i < studentRollCallBean.size(); i++) {
                    StudentRollCallBean bean = studentRollCallBean.get(i);
                    for (int j = 0; j < studentInfoBeen.size(); j++) {
                        StudentInfoBean infoBean = studentInfoBeen.get(j);
                        if (bean.getSi().getId().equals(infoBean.getId())) {//如果相同的话,从新赋值
                            studentInfoBeen.get(j).setId(bean.getId());
                            studentInfoBeen.get(j).setStudentName(bean.getSi().getStudentName());
                            studentInfoBeen.get(j).setStates(bean.getState());
                        }
                    }
                }
                FirstSaveAdapter adapter = new FirstSaveAdapter(context, studentInfoBeen, this);
                mPullRefreshListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                listBean = studentRollCallBean;
                dianMingAdapter = new DianMingCAdapter(context, listBean, this);
                mPullRefreshListView.setAdapter(dianMingAdapter);
                dianMingAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void getstudentInfo(ArrayList<StudentInfoBean> studentInfoBean) {
        CacheBean.setStudentInfoBean(studentInfoBean);
        for (int i = 0; i < studentInfoBean.size(); i++) {
            StudentInfoBean bean = new StudentInfoBean();
            bean = studentInfoBean.get(i);
            bean.setStates(4);
            studentInfoBeen.add(bean);
        }
        requestData(1);
    }

    @Override
    public void deleteStudent() {
        pageindex = 1;
        studentInfoBeen = new ArrayList<>();
        requestStudentInfo(pageindex);
    }

    @Override
    public void onStop() {
        super.onStop();
        studentInfoBeen = new ArrayList<>();
    }
}
