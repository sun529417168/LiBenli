package com.libenli.adapter.coach;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.activity.RankCoachActivity;
import com.libenli.base.MyBaseAdapter;
import com.libenli.bean.StudentInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：ResultsInfoAdapter
 * 描    述：成绩信息适配器
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class RankCoachAdapter extends MyBaseAdapter {


    private ArrayList<StudentInfoBean> listBean = new ArrayList<>();

    public RankCoachAdapter(Context context, List list) {
        super(context, list);
        listBean = (ArrayList<StudentInfoBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_fragment_info;
    }

    @Override
    public void onInitView(View view, final int position) {
        TextView NO = get(view, R.id.info_NO);
        TextView name = get(view, R.id.info_name);
        TextView score = get(view, R.id.info_score);
        NO.setText(Integer.toString(position + 1));
        name.setText(listBean.get(position).getStudentName());
        score.setText(listBean.get(position).getFightingCapacity() + "");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RankCoachActivity.class);
                intent.putExtra("studentName", listBean.get(position).getStudentName());
                intent.putExtra("fightingCapacity", listBean.get(position).getFightingCapacity() + "");
                intent.putExtra("ssid", listBean.get(position).getId());
                context.startActivity(intent);
            }
        });
    }
}
