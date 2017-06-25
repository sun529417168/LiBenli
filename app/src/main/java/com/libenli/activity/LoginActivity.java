package com.libenli.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
        usernameEdit.addTextChangedListener(new EditChangedListener());
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

    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 2) {
                loginLayout.setBackgroundResource(R.mipmap.login_btn_s);
            }else{
                loginLayout.setBackgroundResource(R.mipmap.login_btn_n);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    ;
}
