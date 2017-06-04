package com.libenli.adapter.coach;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.MyBaseAdapter;
import com.libenli.bean.DianMingBean;

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
    private ArrayList<DianMingBean> listBean = new ArrayList<>();

    public DianMingCAdapter(Context context, List list) {
        super(context, list);
        listBean = (ArrayList<DianMingBean>) list;
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
        name.setText(listBean.get(position).getName());
        type1.setText(listBean.get(position).getType());
        type2.setText(listBean.get(position).getType());
        if (position % 2 == 0) {
            type1.setTextColor(ContextCompat.getColor(context, R.color.red));
        }
    }
}
