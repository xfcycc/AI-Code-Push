package io.github.xfcycc.domain;


import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 通用响应类
 *
 * @author xfcycc
 * @date 2025/7/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(HttpStatus.HTTP_OK, "请求成功", null);
    }


    public static <T> Result<T> success(T data) {
        return new Result<>(HttpStatus.HTTP_OK, "请求成功", data);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(HttpStatus.HTTP_OK, message, data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(HttpStatus.HTTP_INTERNAL_ERROR, message, null);
    }

}
