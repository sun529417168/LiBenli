package com.libenli.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.libenli.R;
import com.libenli.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * 文件名：TaskFragment
 * 描    述：任务界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class TaskFragment extends BaseFragment {

    private TextView tv_title;
    private RelativeLayout rl_back;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, null);
        return view;
    }

    @Override
    protected void setDate() {

    }


    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.task_task));
        rl_back = (RelativeLayout) rootView.findViewById(R.id.rl_back);
        rl_back.setVisibility(View.GONE);
    }




}
