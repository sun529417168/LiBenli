package com.libenli.bean;

/**
 * 文件名：InfoBean
 * 描    述：成绩排行实体类
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class InfoBean {
    private int rank;
    private String name;
    private String score;

    public InfoBean(int rank, String name, String score) {
        this.rank = rank;
        this.name = name;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
