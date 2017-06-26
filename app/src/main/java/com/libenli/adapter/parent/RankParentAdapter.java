package com.libenli.adapter.parent;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.libenli.R;
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

public class RankParentAdapter extends MyBaseAdapter {


    private ArrayList<StudentInfoBean> listBean = new ArrayList<>();

    public RankParentAdapter(Context context, List list) {
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
        ImageView info_img = get(view,R.id.info_img);
        if(position==0){
            info_img.setVisibility(View.VISIBLE);
            info_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.one));
        }else if(position==1){
            info_img.setVisibility(View.VISIBLE);
            info_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.two));
        }else if(position==2){
            info_img.setVisibility(View.VISIBLE);
            info_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.three));
        }else{
            info_img.setVisibility(View.INVISIBLE);
        }
        NO.setText(Integer.toString(position + 1));
        name.setText(listBean.get(position).getStudentName());
        score.setText(listBean.get(position).getFightingCapacity() + "");
    }
}
