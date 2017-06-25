package com.libenli.fragment.coach;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.libenli.R;
import com.libenli.activity.AddTrainCourseCoachActivity;
import com.libenli.adapter.coach.TrainCourseCoachAdapter;
import com.libenli.base.BaseFragment;
import com.libenli.bean.TrainCourseCoachBean;
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.utils.MyRequest;
import com.libenli.utils.SharedUtil;
import com.libenli.utils.ToastUtil;

import java.util.ArrayList;


/**
 * 文件名：RankCoachFragment
 * 描    述：家长端成绩排名
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class TrainCourseCoachFragment extends BaseFragment implements InterfaceHandler.ProjectInterface, View.OnClickListener, AdapterView.OnItemClickListener {
    private Context context;
    private TextView tv_title;
    private ArrayList<TrainCourseCoachBean> listBean = new ArrayList<>();
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private TrainCourseCoachAdapter adapter;
    private int pageindex = 1;//页码数
    private TextView tv_add;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_train_course_coach, null);
        return view;
    }

    @Override
    protected void setDate() {
        requestData(pageindex);
    }

    private void requestData(int pageindex) {
        MyRequest.project(context, this, SharedUtil.getString(context, "DiId"), 300, pageindex);
    }


    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.main_info));
        tv_add = (TextView) rootView.findViewById(R.id.btn_add);
        tv_add.setVisibility(View.VISIBLE);
        tv_add.setOnClickListener(this);
        mPullRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.train_course_coach_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setOnItemClickListener(this);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务
                pageindex = 1;
                requestData(pageindex);
                adapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullUpToRefresh");
                // 这里写上拉加载更多的任务
//                pageindex++;
//                requestData(pageindex);
//                adapter.notifyDataSetChanged();
                mPullRefreshListView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData(1);
    }

    @Override
    public void getProjectInfo(ArrayList<TrainCourseCoachBean> trainCourseCoachBean) {
        if (trainCourseCoachBean == null) {
            return;
        }
        listBean = trainCourseCoachBean;
        adapter = new TrainCourseCoachAdapter(context, listBean);
        mPullRefreshListView.setAdapter(adapter);
//        if (trainCourseCoachBean.size() == 0) {
//            mPullRefreshListView.setVisibility(View.GONE);
//        } else {
//            mPullRefreshListView.setVisibility(View.VISIBLE);
//            if (pageindex == 1) {
//                listBean = trainCourseCoachBean;
//                adapter = new TrainCourseCoachAdapter(context, listBean);
//                mPullRefreshListView.setAdapter(adapter);
//            } else if (pageindex > 1 && trainCourseCoachBean.size() != 0) {
//                listBean.addAll(trainCourseCoachBean);
//                adapter = new TrainCourseCoachAdapter(context, listBean);
//                mPullRefreshListView.setAdapter(adapter);
//            } else if (pageindex > 1 && trainCourseCoachBean.size() == 0) {
//                ToastUtil.show(context, "没有更多数据了");
//            }
//        }
    }

    @Override
    public void deleteProject() {
        requestData(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(context, AddTrainCourseCoachActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确认删除吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete(position);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private void delete(int position) {
        MyRequest.deleteProject(context, this, listBean.get(position).getId());
    }
}
