package com.skyworth.utils.shiro.session;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Shallow on 2018/3/30.
 */
public class SessionStatus {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Boolean onlineState = Boolean.TRUE;

    public Boolean isOnline(Boolean onlineState){
        return this.onlineState;
    }

}
