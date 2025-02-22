package com.goorm.bakkuyoungapi.domain.chat.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_room_member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatRoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomMemberNo;

    Long chatRoomNo;

    Long memberNo;

    public ChatRoomMember(Long chatRoomNo, Long memberNo) {
        this.chatRoomNo = chatRoomNo;
        this.memberNo = memberNo;
    }
}
