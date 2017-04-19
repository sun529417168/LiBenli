package com.libenli.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_problem, null);
        return view;
    }

    @Override
    protected void setDate() {

    }

    private void requestData(int pageindex, int searchState, String searchProblemType, int searchDate) {
    }

    @Override
    protected void init(View rootView) {
    }


}
