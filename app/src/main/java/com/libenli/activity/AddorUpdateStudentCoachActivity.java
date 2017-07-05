package com.libenli.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.BaseActivity;
import com.libenli.bean.StudentInfoBean;
import com.libenli.utils.MyRequest;
import com.libenli.utils.MyUtils;
import com.libenli.utils.ToastUtil;

/**
 * Created by sun_t on 2017/6/18.
 */

public class AddorUpdateStudentCoachActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_title;
    private TextView tv_add;
    private RelativeLayout back;
    private EditText ed_name, ed_sex, ed_age, ed_phone, ed_address, ed_fignting;
    private String studentType;
    private RelativeLayout bottom;
    private StudentInfoBean studentInfoBean = new StudentInfoBean();

    @Override
    protected void setView() {
        setContentView(R.layout.activity_studentlist_add);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        studentType = getIntent().getStringExtra("studentType");
        studentInfoBean = (StudentInfoBean) getIntent().getSerializableExtra("studentBean");
    }

    @Override
    protected void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        back = (RelativeLayout) findViewById(R.id.rl_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        tv_add = (TextView) findViewById(R.id.btn_add);
        tv_add.setVisibility(View.VISIBLE);
        tv_add.setText(getResources().getString(R.string.btn_ok));
        tv_add.setOnClickListener(this);
        ed_name = (EditText) findViewById(R.id.studentinfo_name_et);
        ed_sex = (EditText) findViewById(R.id.studentinfo_sex_et);
        ed_age = (EditText) findViewById(R.id.studentinfo_age_et);
        ed_phone = (EditText) findViewById(R.id.studentinfo_phone_et);
        ed_address = (EditText) findViewById(R.id.studentinfo_address_et);
        ed_fignting = (EditText) findViewById(R.id.studentinfo_combat_et);
        bottom = (RelativeLayout) findViewById(R.id.fighting_Layout);
        if ("0".equals(studentType)) {
            tv_title.setText("添加学员");
            bottom.setVisibility(View.GONE);
        }
        if ("1".equals(studentType)) {
            tv_title.setText("修改学员");
            bottom.setVisibility(View.GONE);
            ed_name.setText(studentInfoBean.getStudentName());
            ed_sex.setText(studentInfoBean.getStudentSex() + "");
            ed_age.setText(studentInfoBean.getStudentAge() + "");
            ed_phone.setText(studentInfoBean.getParentsContactPhone() + "");
            ed_address.setText(studentInfoBean.getAddress());
            ed_fignting.setText(studentInfoBean.getFightingCapacity() + "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                BenLiApp.finishTop();
                break;
            case R.id.btn_add:
                String name = ed_name.getText().toString().trim();
                String sex = ed_sex.getText().toString().trim();
                String age = ed_age.getText().toString().trim();
                String phone = ed_phone.getText().toString().trim();
                String address = ed_address.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.show(this, "请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(sex)) {
                    ToastUtil.show(this, "请输入性别");
                    return;
                }
                if (TextUtils.isEmpty(age)) {
                    ToastUtil.show(this, "请输入年龄");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ToastUtil.show(this, "请输入手机号");
                    return;
                }
                if (!MyUtils.isMobile(phone)) {
                    ToastUtil.show(this, "请输入正确手机号");
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    ToastUtil.show(this, "请输入地址");
                    return;
                }
                if ("0".equals(studentType)) {
                    MyRequest.saveStudentInfo(this, name, age, sex, phone, address);
                }
                if ("1".equals(studentType)) {
                    MyRequest.updateStudentInfo(this, studentInfoBean.getId(), name, age, sex, phone, address);
                }
                break;
        }
    }
}
