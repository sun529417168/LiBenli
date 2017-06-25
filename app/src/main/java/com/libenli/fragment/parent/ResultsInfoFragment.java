package com.libenli.fragment.parent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.libenli.activity.LoginActivity;
import com.libenli.adapter.coach.RankScoreCoachAdapter;
import com.libenli.adapter.parent.ResultsInfoAdapter;
import com.libenli.base.BaseFragment;
import com.libenli.bean.CacheBean;
import com.libenli.bean.StudentDiIlBean;
import com.libenli.bean.StudentScoreBean;
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.utils.MyRequest;
import com.libenli.utils.SharedUtil;
import com.libenli.utils.ToastUtil;

import java.util.ArrayList;


/**
 * 文件名：ResultsInfoFragment
 * 描    述：训练课程
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class ResultsInfoFragment extends BaseFragment implements InterfaceHandler.StudentScoreInterface, View.OnClickListener {

    private Context context;
    private TextView tv_title, tv_name, tv_fightNum;
    private TextView tv_exit;
    private ArrayList<StudentScoreBean> listBean = new ArrayList<>();
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private ResultsInfoAdapter infoAdapter;
    private int pageindex = 1;//页码数
    private StudentDiIlBean studentDiIlBean;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_score_rank, null);
        return view;
    }

    @Override
    protected void setDate() {
        studentDiIlBean = CacheBean.getStudentDiIlBean();
        requestStudentInfo(pageindex);
    }

    private void requestStudentInfo(int pageindex) {
        MyRequest.studentScore(context, this, SharedUtil.getString(context, "DiId"), 300, pageindex);
    }

    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.main_results));
        tv_exit = (TextView) rootView.findViewById(R.id.tv_exit);
        tv_exit.setVisibility(View.VISIBLE);
        tv_exit.setOnClickListener(this);
        tv_name = (TextView) rootView.findViewById(R.id.score_rank_name);
        tv_fightNum = (TextView) rootView.findViewById(R.id.score_rank_score);
        if (studentDiIlBean != null) {
            tv_name.setText(studentDiIlBean.getStudentName());
            tv_fightNum.setText(Integer.toString(studentDiIlBean.getFightingCapacity()));
        }
        mPullRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.score_rank_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务
                pageindex = 1;
                requestStudentInfo(pageindex);
                infoAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务
//                pageindex++;
//                requestStudentInfo(pageindex);
//                infoAdapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }
        });
    }


    @Override
    public void getstudentInfo(ArrayList<StudentScoreBean> studentScoreBean) {
        if (studentScoreBean == null) {
            return;
        }
        listBean = studentScoreBean;
        infoAdapter = new ResultsInfoAdapter(context, listBean);
        mPullRefreshListView.setAdapter(infoAdapter);
//        if (studentScoreBean.size() == 0) {
//            mPullRefreshListView.setVisibility(View.GONE);
//        } else {
//            mPullRefreshListView.setVisibility(View.VISIBLE);
//            if (pageindex == 1) {
//                listBean = studentScoreBean;
//                infoAdapter = new ResultsInfoAdapter(context, listBean);
//                mPullRefreshListView.setAdapter(infoAdapter);
//            } else if (pageindex > 1 && studentScoreBean.size() != 0) {
//                listBean.addAll(studentScoreBean);
//                infoAdapter = new ResultsInfoAdapter(context, listBean);
//                mPullRefreshListView.setAdapter(infoAdapter);
//            } else if (pageindex > 1 && studentScoreBean.size() == 0) {
//                ToastUtil.show(context, "没有更多数据了");
//            }
//        }
    }


    private void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确认要退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedUtil.setString(context, "DiId", "");
                SharedUtil.setInteger(context, "type", 0);
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exit:
                exit();
                break;
        }
    }


}
