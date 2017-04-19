package com.libenli.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, null);
        return view;
    }

    @Override
    protected void setDate() {

    }

    private void request(int searchTime) {
    }

    @Override
    protected void init(View rootView) {
    }



}
