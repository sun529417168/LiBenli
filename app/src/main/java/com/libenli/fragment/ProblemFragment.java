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
 * 文件名：ProblemFragment
 * 描    述：问题上报界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class ProblemFragment extends BaseFragment {

    private TextView tv_title;
    private RelativeLayout rl_back;
    private TextView btn_add;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_rank, null);
        return view;
    }

    @Override
    protected void setDate() {

    }

    private void requestData(int pageindex, int searchState, String searchProblemType, int searchDate) {
    }

    @Override
    protected void init(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.course));
        rl_back = (RelativeLayout) rootView.findViewById(R.id.rl_back);
        rl_back.setVisibility(View.GONE);
        btn_add = (TextView) rootView.findViewById(R.id.btn_add);
        btn_add.setVisibility(View.VISIBLE);

    }


}
