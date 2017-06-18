package com.libenli.config;

/**
 * 文件名：UrlConfig
 * 描    述：统一请求地址
 * 作    者：stt
 * 时    间：2017.5.20
 * 版    本：V1.0.0
 */

public class UrlConfig {
    public static final String BaseUrl = "https://106.3.45.222:9443/DojoMS/interfac";// 正式域名


    /**
     * 登陆接口
     */
    public static final String URL_LOGIN = BaseUrl + "/loginUser/find?";
    /**
     * 点到列表
     */
    public static final String URL_STUDENTROLLCALL = BaseUrl + "/studentRollCall/find?";

    /**
     * 战力列表/学员信息列表
     */
    public static final String URL_STUDENTINFO = BaseUrl + "/studentInfo/find?";

    /**
     * 学生成绩列表
     */
    public static final String URL_STUDENTSCORE = BaseUrl + "/studentScore/find?";

    /**
     * 训练科目列表
     */
    public static final String URL_PROJECT = BaseUrl + "/project/find?";

    /**
     * 添加新课程
     */
    public static final String URL_ADDPROJECT = BaseUrl + "/project/save?";

    /**
     * 删除课程
     */
    public static final String URL_DELETEPROJECT = BaseUrl + "/project/delete?";

    /**
     * 道馆信息
     */
    public static final String URL_DOJOINFO = BaseUrl + "/dojoInfo/find?";

    /**
     * 道馆信息更新
     */
    public static final String URL_DOJOINFOUPDATE = BaseUrl + "/dojoInfo/update?";

    /**
     * 学员删除
     */
    public static final String URL_DELETESTUDENTINFO = BaseUrl + "/studentInfo/delete?";

    /**
     * 学员信息添加
     */
    public static final String URL_SAVESTUDENTINFO = BaseUrl + "/studentInfo/save?";

    /**
     * 学员信息更新
     */
    public static final String URL_UPDATESTUDENTINFO = BaseUrl + "/studentInfo/update?";
}
