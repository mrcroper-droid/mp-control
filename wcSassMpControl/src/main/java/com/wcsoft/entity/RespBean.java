package com.wcsoft.entity;

import java.io.Serializable;

/**
 * @author: theTian
 * @date: 2020/5/27 23:41
 */
public class RespBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8538932281346496873L;
	private String statusCode;
    private String msg;
    private Object obj;

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String msg) {
        return new RespBean("00000", msg, null);
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean("00000", msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean("99999", msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean("99999", msg, obj);
    }
    public static RespBean error(String statusCode, String msg) {
        return new RespBean(statusCode, msg, null);
    }

    private RespBean() {
    }

    private RespBean(String statusCode, String msg, Object obj) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.obj = obj;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public RespBean setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public RespBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}
