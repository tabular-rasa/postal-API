package com.vnici.postal.web.response;


public class Response<T> {
    private String msg;

    private int code;

    private T data;

    public Response() {
    }

    public Response(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public Response(String msg, int code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        Response<T> res = new Response<>("请求成功", 200);
        res.setData(data);
        return res;
    }

    public static <T> Response<T> error(T data) {
        Response<T> res = new Response<>("服务端错误", 500);
        res.setData(data);
        return res;
    }

    public static <T> Response<T> noPermission(T data) {
        Response<T> res = new Response<>("没有权限", 201);
        res.setData(data);
        return res;
    }


}


