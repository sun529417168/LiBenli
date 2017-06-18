package com.libenli.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.BaseActivity;
import com.libenli.utils.MyRequest;
import com.libenli.utils.ToastUtil;

/**
 * Created by sun_t on 2017/6/18.
 */

public class AddTrainCourseCoachActivity extends BaseActivity implements View.OnClickListener {
    private EditText inputInfo;
    private Button btn;
    private TextView tv_title;
    private RelativeLayout back;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_add_course);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("添加课程");
        back = (RelativeLayout) findViewById(R.id.rl_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        inputInfo = (EditText) findViewById(R.id.add_course_input);
        btn = (Button) findViewById(R.id.add_course_sure);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(inputInfo.getText().toString().trim())) {
                    ToastUtil.show(AddTrainCourseCoachActivity.this, "请输入课程名称");
                } else {
                    MyRequest.addProject(AddTrainCourseCoachActivity.this, inputInfo.getText().toString().trim());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                BenLiApp.finishTop();
                break;
        }
    }
}
