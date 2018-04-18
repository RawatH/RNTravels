package rn.travels.in.rntravels.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demo on 18/04/18.
 */

public class ResponseVO {
    private boolean status;
    private String msg;
    private List response;

    public ResponseVO() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getResponse() {
        return response;
    }

    public void setResponse(List response) {
        this.response = response;
    }
}
