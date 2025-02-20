package com.goorm.bakkuyoungapi.domain.chat.domain;

import com.goorm.bakkuyoungapi.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Chat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Chat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatNo;

    private Long productNo;

    //

}
