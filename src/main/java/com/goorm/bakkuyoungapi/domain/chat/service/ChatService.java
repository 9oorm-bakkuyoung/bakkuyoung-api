package com.goorm.bakkuyoungapi.domain.chat.service;

import com.goorm.bakkuyoungapi.domain.chat.dao.ChatRoomMemberRepository;
import com.goorm.bakkuyoungapi.domain.chat.dao.ChatRoomRepository;
import com.goorm.bakkuyoungapi.domain.chat.dao.MessageRepository;
import com.goorm.bakkuyoungapi.domain.chat.domain.ChatRoom;
import com.goorm.bakkuyoungapi.domain.chat.domain.ChatRoomMember;
import com.goorm.bakkuyoungapi.domain.chat.dto.request.CreateMessage;
import com.goorm.bakkuyoungapi.domain.chat.dto.response.MessageDetail;
import com.goorm.bakkuyoungapi.domain.product.dao.ProductQuerydslRepository;
import com.goorm.bakkuyoungapi.domain.product.domain.TradeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final MessageRepository messageRepository;
    private final ProductQuerydslRepository productQuerydslRepository;

    //채팅방 생성
    public Long createChat(TradeRequest tradeRequest) {
        //채팅방생성
        ChatRoom chatRoom = chatRoomRepository.save(new ChatRoom(tradeRequest.getTradeRequestNo()));
        Long chatRoomNo = chatRoom.getChatRoomNo();
        //채팅방 회원 2명 생성
        Long member1No = productQuerydslRepository.getProductCreatorNo(tradeRequest.getOfferedProductNo()); // 상품등록자의 member_no
        Long member2No = productQuerydslRepository.getProductCreatorNo(tradeRequest.getRequestedProductNo()); // 상품등록자의 member_no

        chatRoomMemberRepository.save(new ChatRoomMember(chatRoomNo, member1No));
        chatRoomMemberRepository.save(new ChatRoomMember(chatRoomNo, member2No));
        //채팅방번호 리턴
        return chatRoomNo;
    }

    //메세지 생성
    public void createMessage(CreateMessage createMessage) {
        messageRepository.save(createMessage.toMessage());
    }

    //채팅방의 채팅 조회
    public List<MessageDetail> getMessages(Long chatRoomNo) {
        return messageRepository.findByChatRoomNoOrderByCreateDatetimeAsc(chatRoomNo)
                .stream()
                .map(message -> new MessageDetail(message.getMessage(), message.getCreatorNo(), message.getCreateDatetime()))
                .collect(Collectors.toList());
    }

}
