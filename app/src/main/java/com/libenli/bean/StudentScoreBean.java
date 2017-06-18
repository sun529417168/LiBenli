package com.libenli.bean;

import java.io.Serializable;

/**
 * Created by sun_t on 2017/6/17.
 */

public class StudentScoreBean implements Serializable {

    /**
     * ctime : 1497579479000
     * id : 59d81daed32b4350b0fde2758eac8b50
     * mtime : 1497579479000
     * p : {"diId":"1101","id":"1111","projectName":"项目1","status":1}
     * powerScore : 50
     * si : {"address":"哈哈","diId":"1101","fightingCapacity":500,"id":"112","mtime":1497579479000,"parentsContactPhone":"15712631358","status":1,"studentAge":25,"studentName":"李四","studentSex":"男"}
     * siId : 112
     * speedScore : 5
     * status : 1
     */

    private long ctime;
    private String id;
    private long mtime;
    private PBean p;
    private int powerScore;
    private SiBean si;
    private String siId;
    private int speedScore;
    private int status;

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
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

    public PBean getP() {
        return p;
    }

    public void setP(PBean p) {
        this.p = p;
    }

    public int getPowerScore() {
        return powerScore;
    }

    public void setPowerScore(int powerScore) {
        this.powerScore = powerScore;
    }

    public SiBean getSi() {
        return si;
    }

    public void setSi(SiBean si) {
        this.si = si;
    }

    public String getSiId() {
        return siId;
    }

    public void setSiId(String siId) {
        this.siId = siId;
    }

    public int getSpeedScore() {
        return speedScore;
    }

    public void setSpeedScore(int speedScore) {
        this.speedScore = speedScore;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class PBean {
        /**
         * diId : 1101
         * id : 1111
         * projectName : 项目1
         * status : 1
         */

        private String diId;
        private String id;
        private String projectName;
        private int status;

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
    }

    public static class SiBean implements Serializable {
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
            return "SiBean{" +
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

    @Override
    public String toString() {
        return "StudentScoreBean{" +
                "ctime=" + ctime +
                ", id='" + id + '\'' +
                ", mtime=" + mtime +
                ", p=" + p +
                ", powerScore=" + powerScore +
                ", si=" + si +
                ", siId='" + siId + '\'' +
                ", speedScore=" + speedScore +
                ", status=" + status +
                '}';
    }
}
