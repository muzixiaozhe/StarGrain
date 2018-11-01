package com.start.blog.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 8344203883756698965L;

    /** 处理是否成功. */
    private Boolean success;

    /** 提示信息. */
    private String message;

    /** 具体内容. */
    private T data;

    public ResultVO(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResultVO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}