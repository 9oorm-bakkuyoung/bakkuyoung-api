package com.goorm.bakkuyoungapi.domain.swap.domain;

import com.goorm.bakkuyoungapi.domain.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Entity
@Table(name = "swap")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Swap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long swapNo;

    private SwapStatus status;


    //



}
