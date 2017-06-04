package com.libenli.adapter.parent;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.MyBaseAdapter;
import com.libenli.bean.InfoBean;

import java.util.ArrayList;
import java.util.List;

import static android.R.string.no;

/**
 * 文件名：InfoAdapter
 * 描    述：成绩信息适配器
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class InfoAdapter extends MyBaseAdapter {
    private ArrayList<InfoBean> listBean = new ArrayList<>();

    public InfoAdapter(Context context, List list) {
        super(context, list);
        listBean = (ArrayList<InfoBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_fragment_info;
    }

    @Override
    public void onInitView(View view, int position) {
        InfoBean bean = listBean.get(position);
        TextView NO = get(view, R.id.info_NO);
        TextView name = get(view, R.id.info_name);
        TextView score = get(view, R.id.info_score);
        NO.setText(bean.getRank()+"");
        name.setText(bean.getName());
        score.setText(bean.getScore());
    }
}
