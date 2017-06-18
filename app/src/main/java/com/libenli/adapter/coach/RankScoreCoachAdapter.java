package com.libenli.adapter.coach;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.MyBaseAdapter;
import com.libenli.bean.StudentScoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun_t on 2017/6/17.
 */

public class RankScoreCoachAdapter extends MyBaseAdapter {
    private ArrayList<StudentScoreBean> listBean = new ArrayList<>();

    public RankScoreCoachAdapter(Context context, List list) {
        super(context, list);
        listBean = (ArrayList<StudentScoreBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_fragment_info;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView name = get(view, R.id.info_NO);
        TextView liLiang = get(view, R.id.info_name);
        TextView suDu = get(view, R.id.info_score);
        name.setText(listBean.get(position).getP().getProjectName());
        liLiang.setText(listBean.get(position).getPowerScore() + "");
        suDu.setText(listBean.get(position).getSpeedScore() + "");
    }
}
