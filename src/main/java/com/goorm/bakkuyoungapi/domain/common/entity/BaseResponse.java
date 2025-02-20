package com.goorm.bakkuyoungapi.domain.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponse implements Serializable {
    private HttpStatus status;
    private String message;
}
