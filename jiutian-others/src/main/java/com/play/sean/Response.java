package com.play.sean;


//import javax.xml.bind.annotation.XmlRootElement;
//@XmlRootElement
public class Response {
    private int respCode;
    private String respDesc;

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }
}
