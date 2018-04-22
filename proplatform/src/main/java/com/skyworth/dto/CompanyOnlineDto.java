package com.skyworth.dto;

import com.skyworth.model.Company;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by Shallow on 2018/4/15.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CompanyOnlineDto extends Company {
    private String sessionId;

    private String host;

    private Date startTime;

    private Date lastAccess;

    private long timeout;

    private boolean sessionState = Boolean.TRUE;

    public boolean isSessionStatus() {
        return sessionState;
    }

    public CompanyOnlineDto(Company company){
        super(company);
    }

    public CompanyOnlineDto(String sessionId, String host, Date startTime, Date lastAccess, long timeout, boolean sessionState){
        super();
        this.sessionId = sessionId;
        this.host = host;
        this.startTime = startTime;
        this.lastAccess = lastAccess;
        this.timeout = timeout;
        this.sessionState = sessionState;
    }

}
