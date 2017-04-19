package com.libenli.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_mine, null);
        return view;
    }

    @Override
    protected void setDate() {
    }

    @Override
    protected void init(View rootView) {
    }


}
