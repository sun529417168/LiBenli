package com.libenli.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.BaseFragment;


/**
 * 文件名：MineFragment
 * 描    述：个人中心界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class MineFragment extends BaseFragment {
    private Context context;

    private TextView tv_title;
    private RelativeLayout rl_back;
    private TextView tv_exit;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_coach_mine, null);
        return view;
    }

    @Override
    protected void setDate() {
    }

    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.mine));
        rl_back = (RelativeLayout) rootView.findViewById(R.id.rl_back);
        rl_back.setVisibility(View.GONE);
        tv_exit = (TextView) rootView.findViewById(R.id.tv_exit);
        tv_exit.setVisibility(View.VISIBLE);
    }



}
