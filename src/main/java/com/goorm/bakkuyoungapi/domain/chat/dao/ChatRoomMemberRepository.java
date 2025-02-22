package com.goorm.bakkuyoungapi.domain.chat.dao;

import com.goorm.bakkuyoungapi.domain.chat.domain.ChatRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {
}
