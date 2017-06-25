package com.libenli.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.libenli.R;
import com.libenli.adapter.coach.CoachStudentListAdapter;
import com.libenli.base.BaseActivity;
import com.libenli.bean.StudentInfoBean;
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.utils.MyRequest;
import com.libenli.utils.SharedUtil;
import com.libenli.utils.ToastUtil;

import java.util.ArrayList;

/**
 * Created by sun_t on 2017/6/18.
 */

public class CoachStudentListActivity extends BaseActivity implements View.OnClickListener, InterfaceHandler.StudentInfoInterface, AdapterView.OnItemClickListener {
    private TextView tv_title;
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private int pageindex = 1;//页码数
    private TextView tv_add;
    private ArrayList<StudentInfoBean> listBean = new ArrayList<>();
    private CoachStudentListAdapter adapter;
    private RelativeLayout back;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_mine_studentlist);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        requestData(pageindex);
    }

    private void requestData(int pageindex) {
        MyRequest.studentInfo(this, this, SharedUtil.getString(this, "DiId"), 300, pageindex);
    }


    @Override
    protected void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.mine_list));
        tv_add = (TextView) findViewById(R.id.btn_add);
        tv_add.setVisibility(View.VISIBLE);
        tv_add.setOnClickListener(this);
        back = (RelativeLayout) findViewById(R.id.rl_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.mine_studentList_refresh_list);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                BenLiApp.finishTop();
                break;
            case R.id.btn_add:
                Intent intent = new Intent(this, AddorUpdateStudentCoachActivity.class);
                intent.putExtra("studentType", "0");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getstudentInfo(ArrayList<StudentInfoBean> studentInfoBean) {
        if (studentInfoBean == null) {
            return;
        }
        listBean = studentInfoBean;
        adapter = new CoachStudentListAdapter(this, listBean);
        mPullRefreshListView.setAdapter(adapter);
//        if (studentInfoBean.size() == 0) {
//            mPullRefreshListView.setVisibility(View.GONE);
//        } else {
//            mPullRefreshListView.setVisibility(View.VISIBLE);
//            if (pageindex == 1) {
//                listBean = studentInfoBean;
//                adapter = new CoachStudentListAdapter(this, listBean);
//                mPullRefreshListView.setAdapter(adapter);
//            } else if (pageindex > 1 && studentInfoBean.size() != 0) {
//                listBean.addAll(studentInfoBean);
//                adapter = new CoachStudentListAdapter(this, listBean);
//                mPullRefreshListView.setAdapter(adapter);
//            } else if (pageindex > 1 && studentInfoBean.size() == 0) {
//                ToastUtil.show(this, "没有更多数据了");
//            }
//        }
    }

    @Override
    public void deleteStudent() {
        requestData(1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("请选择");
        builder.setTitle("提示");
        builder.setPositiveButton("打开", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(CoachStudentListActivity.this, AddorUpdateStudentCoachActivity.class);
                intent.putExtra("studentType", "1");
                Bundle bundle = new Bundle();
                bundle.putSerializable("studentBean", listBean.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        builder.setNeutralButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyRequest.deleteStudentInfo(CoachStudentListActivity.this, listBean.get(position).getId());
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
    protected void onResume() {
        super.onResume();
        requestData(1);
    }
}
