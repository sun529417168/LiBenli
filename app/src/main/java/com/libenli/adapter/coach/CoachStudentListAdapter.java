package com.libenli.adapter.coach;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.libenli.R;
import com.libenli.base.MyBaseAdapter;
import com.libenli.bean.StudentInfoBean;
import com.libenli.bean.TrainCourseCoachBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：ResultsInfoAdapter
 * 描    述：训练科目列表适配器
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class CoachStudentListAdapter extends MyBaseAdapter {


    private ArrayList<StudentInfoBean> listBean = new ArrayList<>();

    public CoachStudentListAdapter(Context context, List list) {
        super(context, list);
        listBean = (ArrayList<StudentInfoBean>) list;
    }

    @Override
    public int getContentView() {
        return R.layout.item_train_course;
    }

    @Override
    public void onInitView(View view, final int position) {
        TextView name = get(view, R.id.item_train_course);
        name.setText(listBean.get(position).getStudentName());
    }
}
