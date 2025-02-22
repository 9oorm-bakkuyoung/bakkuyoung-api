package com.goorm.bakkuyoungapi.domain.chat.domain;

import com.goorm.bakkuyoungapi.domain.common.entity.BaseOnlyTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_room")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatRoom extends BaseOnlyTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomNo;

    private Long tradeRequestNo;

    public ChatRoom(Long tradeRequestNo) {
        this.tradeRequestNo = tradeRequestNo;
    }

}
