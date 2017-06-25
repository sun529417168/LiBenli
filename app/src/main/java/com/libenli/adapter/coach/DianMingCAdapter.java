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
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.utils.MyRequest;

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
    private InterfaceHandler.StudentInfoInterface studentInfoInterface;

    public DianMingCAdapter(Context context, List list, InterfaceHandler.StudentInfoInterface studentInfoInterface) {
        super(context, list);
        listBean = (ArrayList<StudentRollCallBean>) list;
        this.studentInfoInterface = studentInfoInterface;
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
        final StudentRollCallBean bean = listBean.get(position);
        name.setText(bean.getSi().getStudentName());
        switch (bean.getState()) {
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
                MyRequest.updateDianMing(context, studentInfoInterface, bean.getId(), "0");
            }
        });
        zhengChang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRequest.updateDianMing(context, studentInfoInterface, bean.getId(), "1");
            }
        });
    }

}
