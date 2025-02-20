package com.goorm.bakkuyoungapi.domain.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> extends BaseResponse {
    private T data;

    public static <T> ResponseDto<T> ok() {
        ResponseDto<T> response = new ResponseDto<>();
        response.setMessage("success");
        response.setStatus(HttpStatus.OK);
        return response;
    }

    public static <T> ResponseDto<T> of(T data) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setData(data);
        response.setMessage("success");
        response.setStatus(HttpStatus.OK);
        return response;
    }

    public static <T> ResponseDto<T> fail(T data, String message, HttpStatus httpStatus) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setMessage(message);
        response.setStatus(httpStatus);
        response.setData(data);
        return response;
    }
}
