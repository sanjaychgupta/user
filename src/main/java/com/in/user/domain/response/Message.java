package com.in.user.domain.response;

import org.springframework.stereotype.Component;

@Component
public class Message {
    String type;
    String msg;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
