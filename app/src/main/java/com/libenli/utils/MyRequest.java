package com.libenli.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.libenli.activity.MainCoachActivity;
import com.libenli.activity.MainParentActivity;
import com.libenli.bean.CoachInfoBean;
import com.libenli.bean.LoginBean;
import com.libenli.bean.StudentInfoBean;
import com.libenli.bean.StudentRollCallBean;
import com.libenli.bean.StudentScoreBean;
import com.libenli.bean.TrainCourseCoachBean;
import com.libenli.config.UrlConfig;
import com.libenli.interfaces.InterfaceHandler;
import com.libenli.okhttps.OkHttpUtils;
import com.libenli.okhttps.callback.GenericsCallback;
import com.libenli.okhttps.callback.StringCallback;
import com.libenli.okhttps.utils.JsonGenericsSerializator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 文件名：MyRequest
 * 描    述：统一请求工具类
 * 作    者：stt
 * 时    间：2017.5.20
 * 版    本：V1.0.0
 */
public class MyRequest {
    public static final String SHA1 = "4a15195aa92744fdad3d6a2f5e4cf7b0";

    /**
     * 方法名：loginRequest
     * 功    能：登陆
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void loginRequest(final Activity activity, final String username, final String password) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("loginName", username);
            params.put("loginPwd", password);
            params.put("sign", "XBGD");
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(UrlConfig.URL_LOGIN).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {

                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：loginRequest
     * 功    能：登陆
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void login(final Activity activity, final String username, final String password) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        String[] str = {username, SHA1};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_LOGIN + "loginName=" + username + "&sign=" + MyUtils.getSha1(sb.toString());
        OkHttpUtils.get().url(url).id(100).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("loginRequestString", response);
                ArrayList<LoginBean> loginBean = (ArrayList<LoginBean>) JSON.parseArray(response, LoginBean.class);
                Intent intent;
                if (loginBean.size() > 0 && MD5Util.MD5(password).equals(loginBean.get(0).getLoginPwd())) {
                    switch (loginBean.get(0).getType()) {
                        case 2:
                            SharedUtil.setString(activity, "DiId", loginBean.get(0).getDiId());
                            intent = new Intent(activity, MainCoachActivity.class);
                            activity.startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(activity, MainParentActivity.class);
                            activity.startActivity(intent);
                            break;
                    }
                } else {
                    ToastUtil.show(activity, "帐号或者密码");
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
                SharedUtil.setString(activity, "DiId", "1103");
                Intent intent = new Intent(activity, MainCoachActivity.class);
                activity.startActivity(intent);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：studentRollCall
     * 功    能：点到列表
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void studentRollCall(final Context context, InterfaceHandler.StudentRollCallInterface studentRollCalls, final String diId, String date, int size, int pn) {
        final Dialog progDialog = DialogUtils.showWaitDialog(context);
        final InterfaceHandler.StudentRollCallInterface studentRollCall = studentRollCalls;
        String[] str = {diId, SHA1, date};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
//        String url = UrlConfig.URL_STUDENTROLLCALL + "diId=" + diId + "&sign=" + MyUtils.getSha1(sb.toString());
        String url = UrlConfig.URL_STUDENTROLLCALL + "diId=" + diId + "&rollCallDate=" + date + "&status=" + 1 + "&sign=" + MyUtils.getSha1(sb.toString()) + "&size=" + size + "&pn=" + pn;
        OkHttpUtils.get().url(url).id(100).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("requestString", response);
                ArrayList<StudentRollCallBean> studentRollCallBean = (ArrayList<StudentRollCallBean>) JSON.parseArray(response, StudentRollCallBean.class);
                studentRollCall.getstudentRollCall(studentRollCallBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(context, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：studentRollCall
     * 功    能：战力列表/学员信息列表接口
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void studentInfo(final Context context, InterfaceHandler.StudentInfoInterface studentRollCalls, final String diId, int size, int pn) {
        final Dialog progDialog = DialogUtils.showWaitDialog(context);
        final InterfaceHandler.StudentInfoInterface studentInfo = studentRollCalls;
        String[] str = {diId, SHA1, "1"};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_STUDENTINFO + "diId=" + diId + "&status=" + 1 + "&sign=" + MyUtils.getSha1(sb.toString()) + "&size=" + size + "&pn=" + pn;
        OkHttpUtils.get().url(url).id(100).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("studentInfo", response);
                ArrayList<StudentInfoBean> studentInfoBean = (ArrayList<StudentInfoBean>) JSON.parseArray(response, StudentInfoBean.class);
                studentInfo.getstudentInfo(studentInfoBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(context, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：studentRollCall
     * 功    能：战力列表/学员信息列表接口
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void studentScore(final Context context, final String diId, int size, int pn) {
        final Dialog progDialog = DialogUtils.showWaitDialog(context);
        final InterfaceHandler.StudentScoreInterface studentInfo = (InterfaceHandler.StudentScoreInterface) context;
        String[] str = {diId, SHA1, "1"};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_STUDENTSCORE + "siId=" + diId + "&status=" + 1 + "&sign=" + MyUtils.getSha1(sb.toString()) + "&size=" + size + "&pn=" + pn;
        OkHttpUtils.get().url(url).id(100).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("studentScore", response);
                ArrayList<StudentScoreBean> studentScoreBean = (ArrayList<StudentScoreBean>) JSON.parseArray(response, StudentScoreBean.class);
                studentInfo.getstudentInfo(studentScoreBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(context, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：project
     * 功    能：训练科目列表
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void project(final Context context, InterfaceHandler.ProjectInterface projectInterface, final String diId, int size, int pn) {
        final Dialog progDialog = DialogUtils.showWaitDialog(context);
        final InterfaceHandler.ProjectInterface projectInfo = projectInterface;
        String[] str = {diId, SHA1, "1"};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_PROJECT + "diId=" + diId + "&status=" + 1 + "&sign=" + MyUtils.getSha1(sb.toString()) + "&size=" + size + "&pn=" + pn;
        OkHttpUtils.get().url(url).id(100).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("project", response);
                ArrayList<TrainCourseCoachBean> projectBean = (ArrayList<TrainCourseCoachBean>) JSON.parseArray(response, TrainCourseCoachBean.class);
                projectInfo.getProjectInfo(projectBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(context, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：addProject
     * 功    能：添加新课程
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void addProject(final Activity activity, final String projectName) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("projectName", projectName);
            params.put("diId", SharedUtil.getString(activity, "DiId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] str = {SharedUtil.getString(activity, "DiId"), SHA1, projectName};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_ADDPROJECT + "sign=" + MyUtils.getSha1(sb.toString());
        OkHttpUtils.post().url(url).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                JSONObject jsonObject = JSON.parseObject(response);
                ToastUtil.show(activity, (String) jsonObject.get("msg"));
                if (jsonObject.getBoolean("data")) {
                    activity.finish();
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：addProject
     * 功    能：删除课程
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void deleteProject(final Context activity, InterfaceHandler.ProjectInterface projectInterface, final String id) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        final InterfaceHandler.ProjectInterface projectInfo = projectInterface;
        try {
            params.put("id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] str = {SHA1, id};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_DELETEPROJECT + "sign=" + MyUtils.getSha1(sb.toString());
        OkHttpUtils.post().url(url).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                JSONObject jsonObject = JSON.parseObject(response);
                ToastUtil.show(activity, (String) jsonObject.get("msg"));
                if (jsonObject.getBoolean("data")) {
                    projectInfo.deleteProject();
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：dojoInfo
     * 功    能：道馆信息
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void dojoInfo(final Activity context) {
        final Dialog progDialog = DialogUtils.showWaitDialog(context);
        final InterfaceHandler.DojoInfoInterface ojoInfo = (InterfaceHandler.DojoInfoInterface) context;
        String[] str = {SharedUtil.getString(context, "DiId"), SHA1};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_DOJOINFO + "id=" + SharedUtil.getString(context, "DiId") + "&sign=" + MyUtils.getSha1(sb.toString());
        OkHttpUtils.get().url(url).id(100).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                Log.i("project", response);
                ArrayList<CoachInfoBean> projectBean = (ArrayList<CoachInfoBean>) JSON.parseArray(response, CoachInfoBean.class);
                ojoInfo.getDojoInfo(projectBean);
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(context, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }


    /**
     * 方法名：dojoInfoUpdate
     * 功    能：道馆信息更新
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void dojoInfoUpdate(final Activity activity, String dojoName, String address, String contactName, String contactPhone) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("dojoName", dojoName);
            params.put("address", address);
            params.put("contactName", contactName);
            params.put("contactPhone", contactPhone);
            params.put("id", SharedUtil.getString(activity, "DiId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] str = {SHA1, SharedUtil.getString(activity, "DiId"), dojoName, address, contactName, contactPhone};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_DOJOINFOUPDATE + "sign=" + MyUtils.getSha1(sb.toString());
        OkHttpUtils.post().url(url).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                JSONObject jsonObject = JSON.parseObject(response);
                ToastUtil.show(activity, (String) jsonObject.get("msg"));
                if (jsonObject.getBoolean("data")) {
                    activity.finish();
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：deleteStudentInfo
     * 功    能：学员删除
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void deleteStudentInfo(final Activity activity, String id) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        final InterfaceHandler.StudentInfoInterface studentInfoInterface = (InterfaceHandler.StudentInfoInterface) activity;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] str = {SHA1, id};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_DELETESTUDENTINFO + "sign=" + MyUtils.getSha1(sb.toString());
        OkHttpUtils.post().url(url).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                JSONObject jsonObject = JSON.parseObject(response);
                ToastUtil.show(activity, (String) jsonObject.get("msg"));
                if (jsonObject.getBoolean("data")) {
                    studentInfoInterface.deleteStudent();
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：saveStudentInfo
     * 功    能：学员信息添加
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void saveStudentInfo(final Activity activity, String studentName, String studentAge, String studentSex, String phone, String address) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("studentName", studentName);
            params.put("diId", SharedUtil.getString(activity, "DiId"));
            params.put("studentAge", studentAge);
            params.put("studentSex", studentSex);
            params.put("parentsContactPhone", phone);
            params.put("address", address);
            params.put("fightingCapacity", "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] str = {SHA1, SharedUtil.getString(activity, "DiId"), studentName, studentAge, studentSex, phone, "0", address};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_SAVESTUDENTINFO + "sign=" + MyUtils.getSha1(sb.toString());
        OkHttpUtils.post().url(url).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                JSONObject jsonObject = JSON.parseObject(response);
                ToastUtil.show(activity, (String) jsonObject.get("msg"));
                if (jsonObject.getBoolean("data")) {
                    activity.finish();
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }

    /**
     * 方法名：saveStudentInfo
     * 功    能：学员信息添加
     * 参    数：Activity activity final String username, final String password
     * 返回值：无
     */
    public static void updateStudentInfo(final Activity activity, String id, String studentName, String studentAge, String studentSex, String phone, String address) {
        final Dialog progDialog = DialogUtils.showWaitDialog(activity);
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("studentName", studentName);
            params.put("id", id);
            params.put("studentAge", studentAge);
            params.put("studentSex", studentSex);
            params.put("parentsContactPhone", phone);
            params.put("address", address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] str = {SHA1, id, studentName, studentAge, studentSex, phone, address};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(str);
        for (String string : str) {
            sb.append(string);
        }
        String url = UrlConfig.URL_UPDATESTUDENTINFO + "sign=" + MyUtils.getSha1(sb.toString());
        OkHttpUtils.post().url(url).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onResponse(String response, int id) {
                JSONObject jsonObject = JSON.parseObject(response);
                ToastUtil.show(activity, (String) jsonObject.get("msg"));
                if (jsonObject.getBoolean("data")) {
                    activity.finish();
                }
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(activity, "服务器有错误，请稍候再试");
                if (progDialog.isShowing()) {
                    progDialog.dismiss();
                }
            }
        });
    }
}
