package com.libenli.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.activity.CoachStudentListActivity;
import com.libenli.activity.LoginActivity;
import com.libenli.activity.CoachUpdateInfoActivity;
import com.libenli.base.BaseFragment;
import com.libenli.utils.SharedUtil;


/**
 * 文件名：MineFragment
 * 描    述：个人中心界面
 * 作    者：stt
 * 时    间：2016.12.30
 * 版    本：V1.0.0
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private Context context;

    private TextView tv_title;
    private RelativeLayout rl_back;
    private TextView tv_exit;
    private RelativeLayout update, studentList;

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
        tv_exit.setOnClickListener(this);
        update = (RelativeLayout) rootView.findViewById(R.id.rl_me_update);
        update.setOnClickListener(this);
        studentList = (RelativeLayout) rootView.findViewById(R.id.rl_me_list);
        studentList.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_exit:
                exit();
                break;
            case R.id.rl_me_update:
                intent = new Intent(context, CoachUpdateInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_me_list:
                intent = new Intent(context, CoachStudentListActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确认要退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, LoginActivity.class);
                SharedUtil.setString(context, "DiId", "");
                startActivity(intent);
                getActivity().finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
