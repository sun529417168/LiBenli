package com.libenli.utils;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.libenli.config.UrlConfig;
import com.libenli.okhttps.OkHttpUtils;
import com.libenli.okhttps.callback.GenericsCallback;
import com.libenli.okhttps.utils.JsonGenericsSerializator;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 文件名：MyRequest
 * 描    述：统一请求工具类
 * 作    者：stt
 * 时    间：2017.5.20
 * 版    本：V1.0.0
 */
public class MyRequest {
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
}
