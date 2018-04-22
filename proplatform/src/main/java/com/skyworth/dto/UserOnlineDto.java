package com.skyworth.dto;

import com.skyworth.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by Shallow on 2018/4/15.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserOnlineDto extends User{

    private String sessionId;

    private String host;

    private Date startTime;

    private Date lastAccess;

    private long timeout;

    private boolean sessionState = Boolean.TRUE;

    public boolean isSessionStatus() {
        return sessionState;
    }

    public UserOnlineDto(User user){ super(user); }

    public UserOnlineDto(String sessionId, String host, Date startTime, Date lastAccess, long timeout, boolean sessionState){
        super();
        this.sessionId = sessionId;
        this.host = host;
        this.startTime = startTime;
        this.lastAccess = lastAccess;
        this.timeout = timeout;
        this.sessionState = sessionState;
    }

}
