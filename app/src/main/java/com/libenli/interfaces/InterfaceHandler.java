package com.libenli.interfaces;

import com.libenli.bean.CoachInfoBean;
import com.libenli.bean.StudentInfoBean;
import com.libenli.bean.StudentRollCallBean;
import com.libenli.bean.StudentScoreBean;
import com.libenli.bean.StudentDiIlBean;
import com.libenli.bean.TrainCourseCoachBean;

import java.util.ArrayList;

/**
 * Created by sun_t on 2017/6/12.
 */

public class InterfaceHandler {
    /**
     * 点到列表的接口
     */
    public static interface StudentRollCallInterface {
        void getstudentRollCall(ArrayList<StudentRollCallBean> studentRollCallBean);
    }

    /**
     * 战力列表/学员信息列表的接口
     */
    public static interface StudentInfoInterface {
        void getstudentInfo(ArrayList<StudentInfoBean> studentInfoBean);

        void deleteStudent();
    }


    /**
     * 学生成绩列表的接口
     */
    public static interface StudentScoreInterface {
        void getstudentInfo(ArrayList<StudentScoreBean> studentScoreBean);
    }

    /**
     * 训练科目列表的接口
     */
    public static interface ProjectInterface {
        void getProjectInfo(ArrayList<TrainCourseCoachBean> trainCourseCoachBean);

        void deleteProject();
    }

    /**
     * 道馆信息的接口
     */
    public static interface DojoInfoInterface {
        void getDojoInfo(ArrayList<CoachInfoBean> coachInfoBean);
    }

    /**
     * 道馆信息更新的接口
     */
    public static interface DojoInfoUpdateInterface {
        void getDojoInfo(ArrayList<CoachInfoBean> coachInfoBean);
    }

    /**
     * 家长端先获取DiId的接口
     */
    public static interface GetDiIdInterface {
        void getDiIdInfo(StudentDiIlBean studentDiIlBean);
    }
}
