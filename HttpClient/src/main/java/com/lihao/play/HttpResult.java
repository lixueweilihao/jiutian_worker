package com.lihao.play;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/9/13  17:20
 */
public class HttpResult {
    private int responseCode;
    private String result;

    public HttpResult(int responseCode, String result) {
        this.responseCode = responseCode;
        this.result = result;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String toString() {
        return "HttpResult [responseCode=" + this.responseCode + ", result=" + this.result + "]";
    }
}
