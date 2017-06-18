package com.libenli.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.libenli.R;
import com.libenli.base.BaseActivity;
import com.libenli.utils.MyRequest;
import com.libenli.utils.SharedUtil;
import com.libenli.utils.ToastUtil;

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
        if (!TextUtils.isEmpty(SharedUtil.getString(this, "DiId"))) {
            Intent intent = new Intent(this, MainCoachActivity.class);
            startActivity(intent);
            BenLiApp.finishTop();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_rrbtn:
                String username = usernameEdit.getText().toString().trim();
                String pwd = passwordEdit.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    ToastUtil.show(this, "请输入帐号");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtil.show(this, "请输入密码");
                    return;
                }
                MyRequest.login(this, username, pwd);
                break;
        }
    }
}
