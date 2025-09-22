package org.ecommerce.authservice.dtos.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse <T>{
    private String message;
    private ResponseStatus responseStatus;
    private  T data;

    public static <T> BaseResponse<T> success(T data, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setMessage(message);
        response.setResponseStatus(ResponseStatus.SUCCESS);
        response.setData(data);
        return response;
    }

    public static <T> BaseResponse<T> failure(T data, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setMessage(message);
        response.setResponseStatus(ResponseStatus.FAILURE);
        response.setData(data);
        return response;
    }
}
