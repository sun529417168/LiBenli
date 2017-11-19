package com.libenli.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.BaseActivity;
import com.libenli.utils.MyUtils;

/**
 * 文件名：ContactActivity
 * 描    述：联系我们的界面
 * 作    者：stt
 * 时    间：2017.11.19
 * 版    本：V1.0.0
 */
public class ContactActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout back;
    private TextView versionText, telePhoneText, tv_title;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_mine_contact);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(getResources().getString(R.string.mine_contact));
        back = (RelativeLayout) findViewById(R.id.rl_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        versionText = (TextView) findViewById(R.id.mine_contact_version);
        versionText.setText("V " + MyUtils.getLocalVersionName(this));
        telePhoneText = (TextView) findViewById(R.id.mine_contact_telephone);
        telePhoneText.setOnClickListener(this);
        telePhoneText.setText("TEL:13675424343(赵先生)");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                BenLiApp.finishTop();
                break;
            case R.id.mine_contact_telephone:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13675424343"));
                startActivity(intent);
                break;
        }
    }
}
