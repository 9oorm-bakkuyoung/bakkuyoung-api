package com.goorm.bakkuyoungapi.domain.chat.dao;

import com.goorm.bakkuyoungapi.domain.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
