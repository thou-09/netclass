package com.itany.netclass.util;

import com.itany.netclass.constant.StatusCodeEnum;

import java.io.Serializable;

/**
 * 自定义响应对象
 *
 * @author Thou
 * @date 2022/8/30
 */
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = 1997384422946860831L;
    /**
     * 响应状态码<br/>
     * 使用 com.itany.netclass.constant.StatusCodeEnum#statusCode
     */
    private String statusCode;
    /**
     * 响应消息<br/>
     * 使用 com.itany.netclass.constant.StatusCodeEnum#message
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;

    private ResponseResult() {}

    private ResponseResult(String status, String message, Object data) {
        this.statusCode = status;
        this.message = message;
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 成功响应
     *
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/8/30
     */
    public static ResponseResult success() {
        return new ResponseResult(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功响应，包含响应数据
     *
     * @param data 响应数据
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/8/30
     */
    public static ResponseResult success(Object data) {
        return new ResponseResult(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 失败响应，包含错误状态码和响应信息
     *
     * @param status 错误状态码
     * @param message 响应信息
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/8/30
     */
    public static ResponseResult fail(String status, String message) {
        return new ResponseResult(status, message, null);
    }

    /**
     * 失败响应，包含错误状态码、响应信息和响应数据
     *
     * @param status 错误状态码
     * @param message 响应信息
     * @param data 响应数据
     * @return com.itany.netclass.util.ResponseResult
     * @author Thou
     * @date 2022/8/30
     */
    public static ResponseResult fail(String status, String message, Object data) {
        return new ResponseResult(status, message, data);
    }
}
