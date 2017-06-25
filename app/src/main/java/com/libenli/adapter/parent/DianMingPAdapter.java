package com.libenli.adapter.parent;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.MyBaseAdapter;
import com.libenli.bean.DianMingBean;
import com.libenli.bean.StudentRollCallBean;
import com.libenli.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：DianMingPAdapter
 * 描    述：家长点到信息适配器
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class DianMingPAdapter extends MyBaseAdapter {
    private ArrayList<StudentRollCallBean> listBean = new ArrayList<>();

    public DianMingPAdapter(Context context, List list) {
        super(context, list);
        listBean = (ArrayList<StudentRollCallBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_parent_dianming;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView date = get(view, R.id.dianming_date);
        TextView name = get(view, R.id.dianming_name);
        Button type = get(view, R.id.dianming_type);
        date.setText(MyUtils.stampToDate(String.valueOf(listBean.get(position).getRollCallDate())));
        name.setText(listBean.get(position).getSi().getStudentName());
        if (listBean.get(position).getState() == 1) {
            type.setBackgroundColor(ContextCompat.getColor(context, R.color.blue));
            type.setText("正常");
        }
        if (listBean.get(position).getState() == 0) {
            type.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
            type.setText("请假");
        }
    }
}
