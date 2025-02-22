package com.goorm.bakkuyoungapi.domain.chat.dto.request;

import com.goorm.bakkuyoungapi.domain.chat.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessage {

    private Long chatRoomNo;
    private String message;

    public Message toMessage() {
        return new Message(this.chatRoomNo, this.message);
    }

}
