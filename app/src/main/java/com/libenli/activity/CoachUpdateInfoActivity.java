package com.libenli.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.BaseActivity;
import com.libenli.bean.CoachInfoBean;
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.utils.MyRequest;
import com.libenli.utils.MyUtils;
import com.libenli.utils.ToastUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by sun_t on 2017/6/18.
 */

public class CoachUpdateInfoActivity extends BaseActivity implements View.OnClickListener, InterfaceHandler.DojoInfoInterface {
    private EditText ed_name, ed_address, ed_contact, ed_phone;
    private RelativeLayout back;
    private TextView tv_title, tv_sure;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_mine_updateinfo);
    }

    @Override
    protected void setDate(Bundle savedInstanceState) {
        MyRequest.dojoInfo(this);
    }

    @Override
    protected void init() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        back = (RelativeLayout) findViewById(R.id.rl_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        tv_sure = (TextView) findViewById(R.id.btn_add);
        tv_sure.setVisibility(View.VISIBLE);
        tv_sure.setText(getResources().getString(R.string.btn_ok));
        tv_sure.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.mine_update));
        ed_name = (EditText) findViewById(R.id.updateinfo_name_et);
        ed_address = (EditText) findViewById(R.id.updateinfo_address_et);
        ed_contact = (EditText) findViewById(R.id.updateinfo_contact_et);
        ed_phone = (EditText) findViewById(R.id.updateinfo_phone_et);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                BenLiApp.finishTop();
                break;
            case R.id.btn_add:
                String name = ed_name.getText().toString().trim();
                String address = ed_address.getText().toString().trim();
                String contact = ed_contact.getText().toString().trim();
                String phone = ed_phone.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.show(this, "请输入道馆名称");
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    ToastUtil.show(this, "请输入地址");
                    return;
                }
                if (TextUtils.isEmpty(contact)) {
                    ToastUtil.show(this, "请输入联系人");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ToastUtil.show(this, "请输入联系电话");
                    return;
                }
                if (!MyUtils.isMobile(phone)) {
                    ToastUtil.show(this, "请输入正确电话号码");
                    return;
                }
                MyRequest.dojoInfoUpdate(this, name, address, contact, phone);
                break;
        }
    }

    @Override
    public void getDojoInfo(ArrayList<CoachInfoBean> coachInfoBean) {
        if (coachInfoBean == null || coachInfoBean.size() == 0) {
            return;
        }
        CoachInfoBean bean = coachInfoBean.get(0);
        ed_name.setText(bean.getDojoName());
        ed_address.setText(bean.getAddress());
        ed_contact.setText(bean.getContactName());
        ed_phone.setText(bean.getContactPhone());
    }
}
