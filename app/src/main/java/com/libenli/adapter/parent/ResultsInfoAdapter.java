package com.libenli.adapter.parent;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.activity.RankCoachActivity;
import com.libenli.base.MyBaseAdapter;
import com.libenli.bean.StudentInfoBean;
import com.libenli.bean.StudentScoreBean;
import com.libenli.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：ResultsInfoAdapter
 * 描    述：成绩信息适配器
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class ResultsInfoAdapter extends MyBaseAdapter {
    private ArrayList<StudentScoreBean> listBean = new ArrayList<>();

    public ResultsInfoAdapter(Context context, List list) {
        super(context, list);
        listBean = (ArrayList<StudentScoreBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_fragment_score_info;
    }

    @Override
    public void onInitView(View view, final int position) {
        TextView name = get(view, R.id.info_NO);
        TextView liLiang = get(view, R.id.info_name);
        TextView suDu = get(view, R.id.info_score);
        TextView time = get(view, R.id.info_time);
        name.setText(listBean.get(position).getP().getProjectName());
        liLiang.setText(listBean.get(position).getPowerScore() + "");
        suDu.setText(listBean.get(position).getSpeedScore() + "");
        time.setText(MyUtils.stampToDate(String.valueOf(listBean.get(position).getCtime())));
    }
}
