package com.libenli.bean;

import java.io.Serializable;

/**
 * Created by sun_t on 2017/6/18.
 */

public class TrainCourseCoachBean implements Serializable {

    /**
     * ctime : 1497519326000
     * diId : 1101
     * id : 015a7f85757e40529f0831541b4306dd
     * mtime : 1497519326000
     * projectName : hahah
     * status : 1
     */

    private long ctime;
    private String diId;
    private String id;
    private long mtime;
    private String projectName;
    private int status;

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

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

    public long getMtime() {
        return mtime;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TrainCourseCoachBean{" +
                "ctime=" + ctime +
                ", diId='" + diId + '\'' +
                ", id='" + id + '\'' +
                ", mtime=" + mtime +
                ", projectName='" + projectName + '\'' +
                ", status=" + status +
                '}';
    }
}
