package sn.ept.git.dic2.ventevelos.api;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class MyResponse {
    private String msg;

    public MyResponse() {
    }

    public MyResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyResponse{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
