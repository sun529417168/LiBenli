package com.libenli.bean;

import java.io.Serializable;

/**
 * 文件名：StudentDiIlBean
 * 描    述：家长端先获取DiId
 * 作    者：stt
 * 时    间：2017.6.10
 * 版    本：V1.0.0
 */

public class StudentDiIlBean implements Serializable {

    /**
     * address : 哈哈
     * diId : 1101
     * fightingCapacity : 500
     * id : 112
     * mtime : 1497579479000
     * parentsContactPhone : 15712631358
     * status : 1
     * studentAge : 25
     * studentName : 李四
     * studentSex : 男
     */

    private String address;
    private String diId;
    private int fightingCapacity;
    private String id;
    private long mtime;
    private String parentsContactPhone;
    private int status;
    private int studentAge;
    private String studentName;
    private String studentSex;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDiId() {
        return diId;
    }

    public void setDiId(String diId) {
        this.diId = diId;
    }

    public int getFightingCapacity() {
        return fightingCapacity;
    }

    public void setFightingCapacity(int fightingCapacity) {
        this.fightingCapacity = fightingCapacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMtime() {
        return mtime;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }

    public String getParentsContactPhone() {
        return parentsContactPhone;
    }

    public void setParentsContactPhone(String parentsContactPhone) {
        this.parentsContactPhone = parentsContactPhone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    @Override
    public String toString() {
        return "StudentDiIlBean{" +
                "address='" + address + '\'' +
                ", diId='" + diId + '\'' +
                ", fightingCapacity=" + fightingCapacity +
                ", id='" + id + '\'' +
                ", mtime=" + mtime +
                ", parentsContactPhone='" + parentsContactPhone + '\'' +
                ", status=" + status +
                ", studentAge=" + studentAge +
                ", studentName='" + studentName + '\'' +
                ", studentSex='" + studentSex + '\'' +
                '}';
    }
}
