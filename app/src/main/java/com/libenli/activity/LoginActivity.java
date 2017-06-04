package com.libenli.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.libenli.R;
import com.libenli.base.BaseActivity;

/**
 * 文件名：LoginActivity
 * 描    述：登陆
 * 作    者：stt
 * 时    间：2017.04.24
 * 版    本：V1.0.0
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText usernameEdit, passwordEdit;
    private RelativeLayout loginLayout;
    private RadioGroup loginGroup;
    private RadioButton parents, coach;
    private int loginType = 0;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        usernameEdit = (EditText) findViewById(R.id.login_edmail);
        passwordEdit = (EditText) findViewById(R.id.login_edpass);
        loginLayout = (RelativeLayout) findViewById(R.id.login_rrbtn);
        loginLayout.setOnClickListener(this);
        loginGroup = (RadioGroup) findViewById(R.id.login_group);
        loginGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        parents = (RadioButton) findViewById(R.id.login_parents);
        coach = (RadioButton) findViewById(R.id.login_coach);
    }

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.login_parents:
                    loginType = 0;
                    break;
                case R.id.login_coach:
                    loginType = 1;
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_rrbtn:
                Intent intent = new Intent(this, loginType == 0 ? MainParentActivity.class : MainCoachActivity.class);
                startActivity(intent);
                break;
        }
    }
}
