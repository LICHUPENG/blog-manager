package com.lcp.blog.common.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private int code = AppException.NORMAL;

    private Object message = "success";

    private boolean success = true;

    private T data;

    public boolean getSuccess() {
        return code == AppException.NORMAL;
    }

    public Result() {
        super();
    }

    public Result(String msg) {
        super();
        this.message = msg;
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(T data, String msg, int code) {
        super();
        this.data = data;
        this.message = msg;
        this.code = code;
    }

    public Result(String msg, int code) {
        super();
        this.code = code;
        this.message = msg;
    }
}