package com.libenli.bean;

import java.io.Serializable;

/**
 * Created by sun_t on 2017/6/18.
 */

public class CoachInfoBean implements Serializable {

    /**
     * address : 123
     * contactName : 1234
     * contactPhone : 15999999999
     * dojoName : 1231
     * id : 1101
     * mtime : 1497507849000
     * status : 1
     */

    private String address;
    private String contactName;
    private String contactPhone;
    private String dojoName;
    private String id;
    private long mtime;
    private int status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDojoName() {
        return dojoName;
    }

    public void setDojoName(String dojoName) {
        this.dojoName = dojoName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CoachInfoBean{" +
                "address='" + address + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", dojoName='" + dojoName + '\'' +
                ", id='" + id + '\'' +
                ", mtime=" + mtime +
                ", status=" + status +
                '}';
    }
}
