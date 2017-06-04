package com.libenli.bean;

/**
 * 文件名：DianMingBean
 * 描    述：点名实体类
 * 作    者：stt
 * 时    间：2017.4.30
 * 版    本：V1.0.0
 */

public class DianMingBean {
    private String name;
    private String type;

    public DianMingBean(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
