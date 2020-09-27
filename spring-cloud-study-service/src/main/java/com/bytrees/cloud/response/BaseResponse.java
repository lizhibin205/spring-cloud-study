package com.bytrees.cloud.response;

public class BaseResponse<T> {
    private static final int SUCCESS_CODE = 200;
    private static final int FAIL_CODE = 500;
    private static final String SUCCESS_MESSAGE = "success.";
    private final Integer code;
    private final String message;
    private final T data;

    public BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回成功的状态
     * @param data
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    /**
     * 返回成功的状态，包含消息文案
     * @param message
     * @param data
     * @return
     */
    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(SUCCESS_CODE, message, data);
    }

    /**
     * 返回失败的状态
     * @param message
     * @return
     */
    public static <T> BaseResponse<T> fail(String message) {
        return new BaseResponse<>(FAIL_CODE, message, null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
