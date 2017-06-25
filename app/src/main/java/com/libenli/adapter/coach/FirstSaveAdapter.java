package com.libenli.adapter.coach;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.libenli.R;
import com.libenli.base.MyBaseAdapter;
import com.libenli.bean.StudentInfoBean;
import com.libenli.bean.StudentRollCallBean;
import com.libenli.config.UrlConfig;
import com.libenli.okhttps.OkHttpUtils;
import com.libenli.okhttps.callback.GenericsCallback;
import com.libenli.okhttps.utils.JsonGenericsSerializator;
import com.libenli.utils.DialogUtils;
import com.libenli.utils.MyRequest;
import com.libenli.utils.MyUtils;
import com.libenli.utils.SharedUtil;
import com.libenli.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

import static com.libenli.utils.MyRequest.SHA1;

/**
 * 文件名：DianMingCAdapter
 * 描    述：教练点到信息适配器
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class FirstSaveAdapter extends MyBaseAdapter {
    private ArrayList<StudentInfoBean> listBean = new ArrayList<>();

    public FirstSaveAdapter(Context context, List list) {
        super(context, list);
        listBean = (ArrayList<StudentInfoBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_coach_dianming;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView name = get(view, R.id.coach_dianming_name);
        Button qingJia = get(view, R.id.coach_dianming_qingJia);
        Button zhengChang = get(view, R.id.coach_dianming_zhengChang);
        final StudentInfoBean bean = listBean.get(position);
        name.setText(bean.getStudentName());
        switch (bean.getStates()) {
            case 0:
                qingJia.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
                zhengChang.setBackgroundColor(ContextCompat.getColor(context, R.color.gray_s));
                break;
            case 1:
                qingJia.setBackgroundColor(ContextCompat.getColor(context, R.color.gray_s));
                zhengChang.setBackgroundColor(ContextCompat.getColor(context, R.color.blue));
                break;
            case 4:
                qingJia.setBackgroundColor(ContextCompat.getColor(context, R.color.gray_s));
                zhengChang.setBackgroundColor(ContextCompat.getColor(context, R.color.gray_s));
                break;
        }
        qingJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRequest.saveFirst(context, bean.getId(), "0");
            }
        });
        zhengChang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRequest.saveFirst(context, bean.getId(), "1");
            }
        });
    }



}
