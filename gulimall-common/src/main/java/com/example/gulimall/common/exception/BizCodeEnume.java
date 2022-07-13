package com.example.gulimall.common.exception;



public enum BizCodeEnume {
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    PRODUCT_UP_EXCEPTION(11000,"商品上架失败"),
    VAILD_EXCEPTION(10001,"参数校验失败"),
    SMS_CODE_EXCEPTION(10002,"获取频率太高");

    private int code;
    private String msg;

    BizCodeEnume(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
