package com.libenli.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.BaseFragment;



/**
 * 文件名：NoticeFragment
 * 描    述：通知界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class NoticeFragment extends BaseFragment {

    private TextView tv_title;
    private RelativeLayout rl_back;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, null);
        return view;
    }

    @Override
    protected void setDate() {

    }

    private void request(int searchTime) {
    }

    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.scores));
        rl_back = (RelativeLayout) rootView.findViewById(R.id.rl_back);
        rl_back.setVisibility(View.GONE);
    }



}
