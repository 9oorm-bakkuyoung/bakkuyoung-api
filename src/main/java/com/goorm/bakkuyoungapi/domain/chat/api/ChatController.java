package com.goorm.bakkuyoungapi.domain.chat.api;

import com.goorm.bakkuyoungapi.domain.chat.dto.request.CreateMessage;
import com.goorm.bakkuyoungapi.domain.chat.dto.response.MessageDetail;
import com.goorm.bakkuyoungapi.domain.chat.service.ChatService;
import com.goorm.bakkuyoungapi.domain.common.entity.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CHAT", description = "채팅")
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @Operation(summary = "메세지 등록", description = "채팅방에 메시지 보냄")
    @PostMapping(value = "/send")
    public ResponseDto<String> save(@RequestBody CreateMessage createMessage) {
        chatService.createMessage(createMessage);
        return ResponseDto.ok();
    }

    @Operation(summary = "채팅방의 채팅 조회", description = "채팅방의 채팅 조회")
    @GetMapping("/room/{chatRoomNo}/message/all")
    public ResponseDto<List<MessageDetail>> getMessages(Long chatRoomNo) {
        List<MessageDetail> messageDetails = chatService.getMessages(chatRoomNo);
        return ResponseDto.of(messageDetails);
    }

}
