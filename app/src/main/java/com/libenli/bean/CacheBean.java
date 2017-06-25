package com.libenli.bean;

import java.util.List;

/**
 * Created by sun_t on 2017/6/17.
 */

public class CacheBean {
    private static List<StudentInfoBean> studentInfoBean;
    private static StudentDiIlBean studentDiIlBean;

    public static List<StudentInfoBean> getStudentInfoBean() {
        return studentInfoBean;
    }

    public static void setStudentInfoBean(List<StudentInfoBean> studentInfoBean) {
        CacheBean.studentInfoBean = studentInfoBean;
    }

    public static StudentDiIlBean getStudentDiIlBean() {
        return studentDiIlBean;
    }

    public static void setStudentDiIlBean(StudentDiIlBean studentDiIlBean) {
        CacheBean.studentDiIlBean = studentDiIlBean;
    }
}
