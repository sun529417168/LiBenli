package com.libenli.bean;

import java.io.Serializable;

/**
 * 文件名：LoginBean
 * 描    述：登陆成功返回的实体类
 * 作    者：stt
 * 时    间：2017.6.10
 * 版    本：V1.0.0
 */

public class LoginBean implements Serializable{
    /**
     * diId : 1101
     * id : 22222222222222222222222
     * loginName : test1
     * loginPwd : 96e79218965eb72c92a549dd5a330112
     * status : 1
     * type : 2
     */

    private String diId;
    private String id;
    private String loginName;
    private String loginPwd;
    private int status;
    private int type;

    public String getDiId() {
        return diId;
    }

    public void setDiId(String diId) {
        this.diId = diId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * diId : 1101
     * id : 22222222222222222222222
     * loginName : test1
     * loginPwd : 96e79218965eb72c92a549dd5a330112
     * status : 1
     * type : 2
     */

//    private String diId;
//    private String id;
//    private String loginName;
//    private String loginPwd;
//    private String status;
//    private String type;
//
//    public LoginBean() {
//    }
//
//    public LoginBean(String diId, String id, String loginName, String loginPwd, String status, String type) {
//        this.diId = diId;
//        this.id = id;
//        this.loginName = loginName;
//        this.loginPwd = loginPwd;
//        this.status = status;
//        this.type = type;
//    }
//
//    public String getDiId() {
//        return diId;
//    }
//
//    public void setDiId(String diId) {
//        this.diId = diId;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getLoginName() {
//        return loginName;
//    }
//
//    public void setLoginName(String loginName) {
//        this.loginName = loginName;
//    }
//
//    public String getLoginPwd() {
//        return loginPwd;
//    }
//
//    public void setLoginPwd(String loginPwd) {
//        this.loginPwd = loginPwd;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    @Override
//    public String toString() {
//        return "LoginBean{" +
//                "diId='" + diId + '\'' +
//                ", id='" + id + '\'' +
//                ", loginName='" + loginName + '\'' +
//                ", loginPwd='" + loginPwd + '\'' +
//                ", status=" + status +
//                ", type=" + type +
//                '}';
//    }

}
