package com.skyworth.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author li
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessageDto {
    private Integer messageId;
    private String messageContent;
    private Long messagePubTime;

    public MessageDto() {
    }

    public MessageDto(Integer messageId, String messageContent, Long messagePubTime) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.messagePubTime = messagePubTime;
    }


}
