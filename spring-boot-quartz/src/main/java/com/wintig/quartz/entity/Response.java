package com.wintig.quartz.entity;


import com.wintig.quartz.enums.ResultStatusEnum;
import lombok.Data;

/**
 * @Description 基础返回参数
 * @Author wintig
 * @Create 2018-10-02 上午1:33
 */
@Data
public final class Response<T> {

    private Integer status;

    private String message;

    private T data;

    public Response(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(Integer status) {
        this.status = status;
    }

    public Response(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static Response success() {
        return new Response(ResultStatusEnum.SUCCESS.getStatus());
    }

    public Response(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return success(ResultStatusEnum.SUCCESS.getMsg(), data);
    }


    public static <T> Response<T> success(String message, T data) {
        return new Response(ResultStatusEnum.SUCCESS.getStatus(), message, data);
    }

    public static <T> Response<T> failed(ResultStatusEnum code, String message) {
        return failed(code, message, null);
    }

    public static <T> Response<T> failed(ResultStatusEnum code, String message, T data) {
        return new Response(code.getStatus(), message, data);
    }
}
