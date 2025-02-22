package com.goorm.bakkuyoungapi.domain.chat.dao;

import com.goorm.bakkuyoungapi.domain.chat.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByChatRoomNoOrderByCreateDatetimeAsc(Long chatRoomNo);

}
