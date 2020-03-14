package com.lcp.blog.common.utils;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 7159593928141190355L;

    /** 正常响应 */
    public static final int NORMAL = 200;

    /** 无效的认证 */
    public static final int AUTH_INVALID = 401;

    /** 权限不足 */
    public static final int AUTH_DENIED  = 402;

    /** 表单验证失败 */
    public static final int FORM_INVALID = 403;

    /** 系统表单验证失败 */
    public static final int SYSTEM_FORM_INVALID = 406;

    /** 系统异常 */
    public static final int SYSTEM_FAIL = 500;

    private int code = NORMAL;

    public int getCode() {
        return this.code;
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, int code) {
        super(message);
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("code:%s msg:%s", getCode(), getMessage());
    }
}
