package com.goorm.bakkuyoungapi.domain.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Wish")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishNo;

    private String wishName;

    private Long productNo;


}
