package com.skyworth.service.websocket.vo;

/**
 * @author li
 */
public class ContentVo {
    private String to;
    private String msg;
    private Integer type;

    public ContentVo() {}

    public ContentVo(String to, String msg, Integer type) {
        this.to = to;
        this.msg = msg;
        this.type = type;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
