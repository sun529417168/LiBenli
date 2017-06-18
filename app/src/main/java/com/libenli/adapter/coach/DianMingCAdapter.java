package com.libenli.adapter.coach;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.MyBaseAdapter;
import com.libenli.bean.DianMingBean;
import com.libenli.bean.StudentRollCallBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：DianMingCAdapter
 * 描    述：教练点到信息适配器
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class DianMingCAdapter extends MyBaseAdapter {
    private ArrayList<StudentRollCallBean> listBean = new ArrayList<>();

    public DianMingCAdapter(Context context, List list) {
        super(context, list);
        listBean = (ArrayList<StudentRollCallBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_coach_dianming;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView name = get(view, R.id.coach_dianming_name);
        Button type1 = get(view, R.id.coach_dianming_qingJia);
        Button type2 = get(view, R.id.coach_dianming_zhengChang);
        StudentRollCallBean bean = listBean.get(position);
        name.setText(bean.getSiId());
        if (bean.getState() == 0) {
            type1.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            type2.setBackgroundColor(ContextCompat.getColor(context, R.color.gray_s));
        }
        if (bean.getState() == 1) {
            type1.setBackgroundColor(ContextCompat.getColor(context, R.color.gray_s));
            type2.setBackgroundColor(ContextCompat.getColor(context, R.color.blue));
        }
    }
}
