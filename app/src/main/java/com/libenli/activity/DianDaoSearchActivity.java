package com.libenli.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.libenli.R;
import com.libenli.adapter.coach.DianMingCAdapter;
import com.libenli.adapter.parent.DianMingPAdapter;
import com.libenli.base.BaseActivity;
import com.libenli.bean.CacheBean;
import com.libenli.bean.StudentInfoBean;
import com.libenli.bean.StudentRollCallBean;
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.utils.MyRequest;
import com.libenli.utils.MyUtils;
import com.libenli.utils.SharedUtil;
import com.libenli.utils.ToastUtil;
import com.libenli.weight.DoubleDatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 文件名：DianDaoSearchActivity
 * 描    述：点到信息查询
 * 作    者：stt
 * 时    间：2017.06.25
 * 版    本：V1.0.0
 */

public class DianDaoSearchActivity extends BaseActivity implements View.OnClickListener, InterfaceHandler.StudentRollCallInterface {
    private RelativeLayout back;
    private EditText ed_name;
    private Button btn_name, btn_time;
    private TextView tv_startTime, tv_endTime, tv_title, tv_titleName, tv_titleTime;
    private LinearLayout nameLayout, timeLayout;
    private RelativeLayout nameSearchLayout, timeSearchLayout;
    //刷新控件
    private PullToRefreshListView mPullRefreshListView;
    private int pageindex = 1;//页码数
    private DianMingPAdapter dianMingAdapter;
    private ArrayList<StudentRollCallBean> listBean = new ArrayList<>();


    @Override
    protected void setView() {
        setContentView(R.layout.activity_mine_pointselected);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String time = df.format(new Date());
        requestData(time);
    }

    private void requestData(String time) {
        MyRequest.studentRollCall(this, this, SharedUtil.getString(this, "DiId"), time, 300, 1);
    }


    @Override
    protected void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.main_dianming));
        back = (RelativeLayout) findViewById(R.id.rl_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        ed_name = (EditText) findViewById(R.id.edit_search);
        btn_name = (Button) findViewById(R.id.mine_pointselected_nameSearch);
        btn_time = (Button) findViewById(R.id.mine_pointselected_timeSearch);
        btn_name.setOnClickListener(this);
        btn_time.setOnClickListener(this);
        tv_startTime = (TextView) findViewById(R.id.mine_pointselected_startTime);
        tv_endTime = (TextView) findViewById(R.id.mine_pointselected_endTime);
        tv_startTime.setOnClickListener(this);
        tv_endTime.setOnClickListener(this);
        tv_titleName = (TextView) findViewById(R.id.mine_search_titleName);
        tv_titleTime = (TextView) findViewById(R.id.mine_search_titleTime);
        nameSearchLayout = (RelativeLayout) findViewById(R.id.search_edittext_layout);
        timeSearchLayout = (RelativeLayout) findViewById(R.id.search_textview_layout);
        nameLayout = (LinearLayout) findViewById(R.id.search_name_layout);
        timeLayout = (LinearLayout) findViewById(R.id.search_time_layout);
        nameLayout.setOnClickListener(this);
        timeLayout.setOnClickListener(this);

        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.mine_pointselected_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.e("TAG", "onPullDownToRefresh");
                // 这里写下拉刷新的任务
//                pageindex = 1;
//                requestData(pageindex);
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
    public void onClick(View v) {
        Calendar c = Calendar.getInstance();
        switch (v.getId()) {
            case R.id.rl_back:
                BenLiApp.finishTop();
                break;
            case R.id.search_name_layout://点击姓名
                nameSearchLayout.setVisibility(View.VISIBLE);
                timeSearchLayout.setVisibility(View.GONE);
                tv_titleName.setTextColor(ContextCompat.getColor(this, R.color.blue));
                tv_titleTime.setTextColor(ContextCompat.getColor(this, R.color.black));
                break;
            case R.id.search_time_layout://点击时间
                nameSearchLayout.setVisibility(View.GONE);
                timeSearchLayout.setVisibility(View.VISIBLE);
                tv_titleName.setTextColor(ContextCompat.getColor(this, R.color.black));
                tv_titleTime.setTextColor(ContextCompat.getColor(this, R.color.blue));
                break;
            case R.id.mine_pointselected_nameSearch://按姓名搜索
                ArrayList<StudentInfoBean> studentInfoList = (ArrayList<StudentInfoBean>) CacheBean.getStudentInfoBean();
                String name = ed_name.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    String time = df.format(new Date());
                    requestData(time);
                } else {
                    String id = "";
                    for (StudentInfoBean bean : studentInfoList) {
                        if (bean.getStudentName().equals(name)) {
                            id = bean.getId();
                        }
                    }
                    if (!TextUtils.isEmpty(id)) {
                        MyRequest.studentRollCall(this, id);
                    } else {
                        listBean = new ArrayList<>();
                        dianMingAdapter = new DianMingPAdapter(this, listBean);
                        mPullRefreshListView.setAdapter(dianMingAdapter);
                        dianMingAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.mine_pointselected_startTime://开始时间
                // 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
                new DoubleDatePickerDialog(this, 5, new DoubleDatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear, int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear, int endDayOfMonth) {
                        String start = String.format("%d-%d-%d", startYear, startMonthOfYear + 1, startDayOfMonth);
                        String end = String.format("%d-%d-%d", endYear, endMonthOfYear + 1, endDayOfMonth);
                        if (MyUtils.dateToStamps(start) > MyUtils.dateToStamps(end)) {
                            ToastUtil.show(DianDaoSearchActivity.this, "开始时间不能大于结束时间");
                        } else {
                            tv_startTime.setText(start);
                            tv_endTime.setText(end);
                        }
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DATE), true).show();
                break;
            case R.id.mine_pointselected_endTime://结束时间
                // 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
                new DoubleDatePickerDialog(this, 5, new DoubleDatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear, int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear, int endDayOfMonth) {
                        String start = String.format("%d-%d-%d", startYear, startMonthOfYear + 1, startDayOfMonth);
                        String end = String.format("%d-%d-%d", endYear, endMonthOfYear + 1, endDayOfMonth);
                        if (MyUtils.dateToStamps(start) > MyUtils.dateToStamps(end)) {
                            ToastUtil.show(DianDaoSearchActivity.this, "开始时间不能大于结束时间");
                        } else {
                            tv_startTime.setText(start);
                            tv_endTime.setText(end);
                        }
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DATE), true).show();
                break;
            case R.id.mine_pointselected_timeSearch://按时间搜索
                ToastUtil.show(this, "还没写好");
                break;
        }
    }

    @Override
    public void getstudentRollCall(ArrayList<StudentRollCallBean> studentRollCallBean) {
        if (studentRollCallBean == null || studentRollCallBean.size() == 0) {
            return;
        }
        listBean = studentRollCallBean;
        dianMingAdapter = new DianMingPAdapter(this, listBean);
        mPullRefreshListView.setAdapter(dianMingAdapter);
        dianMingAdapter.notifyDataSetChanged();
    }
}
