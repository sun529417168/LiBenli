package com.libenli.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.libenli.R;

/**
 * 文件名：DialogUtils
 * 描    述：工具类
 * 作    者：stt
 * 时    间：2017.5.20
 * 版    本：V1.0.0
 */

public class DialogUtils {
    /**
     * 方法名：showWaitDialog
     * 功    能：加载数据时的等待对话框
     * 参    数：Context context
     * 返回值：Dialog
     */
    public static Dialog showWaitDialog(final Context context) {
        Dialog dialog = new Dialog(context, R.style.DialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = 1.0f; // 透明显示效果
        window.setAttributes(params);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();
        dialog.setContentView(view);
        dialog.setCancelable(false);
        return dialog;
    }
}
