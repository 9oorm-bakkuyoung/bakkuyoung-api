package com.goorm.bakkuyoungapi.domain.swap.domain;

import com.goorm.bakkuyoungapi.domain.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "swap")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SwapItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long swapItemNo;

    private Long swapNo;


}
