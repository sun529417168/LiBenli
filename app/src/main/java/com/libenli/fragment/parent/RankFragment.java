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
import com.libenli.adapter.coach.RankCoachAdapter;
import com.libenli.adapter.parent.RankParentAdapter;
import com.libenli.base.BaseFragment;
import com.libenli.bean.CacheBean;
import com.libenli.bean.StudentDiIlBean;
import com.libenli.bean.StudentInfoBean;
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.utils.MyRequest;
import com.libenli.utils.SharedUtil;
import com.libenli.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Random;


/**
 * 文件名：RankCoachFragment
 * 描    述：家长端成绩排名
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class RankFragment extends BaseFragment implements InterfaceHandler.StudentInfoInterface, InterfaceHandler.GetDiIdInterface {
    private Context context;
    private TextView tv_title;
    private ArrayList<StudentInfoBean> listBean = new ArrayList<>();
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private RankParentAdapter adapter;
    private int pageindex = 1;//页码数
    private TextView tv_NO, tv_name, tv_score;
    private StudentDiIlBean studentDiIlBean = new StudentDiIlBean();

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_info, null);
        return view;
    }

    @Override
    protected void setDate() {
        requestDiId();
    }

    private void requestStudentInfo(int pageindex) {
        MyRequest.studentInfo(context, this, studentDiIlBean.getDiId(), 300, pageindex);
    }

    private void requestDiId() {
        MyRequest.studentInfoDiId(context, this, SharedUtil.getString(context, "DiId"));
    }

    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.main_paihang));
        rootView.findViewById(R.id.main_parent_titleLayout).setVisibility(View.VISIBLE);
        tv_NO = (TextView) rootView.findViewById(R.id.main_parent_titleNO);
        tv_name = (TextView) rootView.findViewById(R.id.main_parent_titleName);
        tv_score = (TextView) rootView.findViewById(R.id.main_parent_titleScore);
        mPullRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.info_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务
                pageindex = 1;
                requestStudentInfo(pageindex);
                adapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务
//                pageindex++;
//                requestStudentInfo(pageindex);
//                adapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }
        });
    }


    @Override
    public void getstudentInfo(ArrayList<StudentInfoBean> studentInfoBean) {
        if (studentInfoBean == null) {
            return;
        }
        listBean = studentInfoBean;
        adapter = new RankParentAdapter(context, listBean);
        mPullRefreshListView.setAdapter(adapter);
        setTitleData(studentInfoBean);
//        if (studentInfoBean.size() == 0) {
//            mPullRefreshListView.setVisibility(View.GONE);
//        } else {
//            setTitleData(studentInfoBean);
//            mPullRefreshListView.setVisibility(View.VISIBLE);
//            if (pageindex == 1) {
//                listBean = studentInfoBean;
//                adapter = new RankParentAdapter(context, listBean);
//                mPullRefreshListView.setAdapter(adapter);
//            } else if (pageindex > 1 && studentInfoBean.size() != 0) {
//                listBean.addAll(studentInfoBean);
//                adapter = new RankParentAdapter(context, listBean);
//                mPullRefreshListView.setAdapter(adapter);
//            } else if (pageindex > 1 && studentInfoBean.size() == 0) {
//                ToastUtil.show(context, "没有更多数据了");
//            }
//        }
    }

    @Override
    public void deleteStudent() {

    }

    @Override
    public void getDiIdInfo(StudentDiIlBean studentDiIlBean) {
        this.studentDiIlBean = studentDiIlBean;
        SharedUtil.setString(context, "getDiId", studentDiIlBean.getDiId());
        requestStudentInfo(pageindex);
    }

    public void setTitleData(ArrayList<StudentInfoBean> titleData) {
        for (int i = 0; i < titleData.size(); i++) {
            if (studentDiIlBean.getId().equals(titleData.get(i).getId())) {
                tv_NO.setText(Integer.toString(i + 1));
                tv_name.setText(studentDiIlBean.getStudentName());
                tv_score.setText(Integer.toString(studentDiIlBean.getFightingCapacity()));
                CacheBean.setStudentDiIlBean(studentDiIlBean);
            }
        }
    }
}
