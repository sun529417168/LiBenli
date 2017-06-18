package com.libenli.bean;

import java.util.List;

/**
 * Created by sun_t on 2017/6/17.
 */

public class CacheBean {
    private static List<StudentInfoBean> studentInfoBean;

    public static List<StudentInfoBean> getStudentInfoBean() {
        return studentInfoBean;
    }

    public static void setStudentInfoBean(List<StudentInfoBean> studentInfoBean) {
        CacheBean.studentInfoBean = studentInfoBean;
    }
}
