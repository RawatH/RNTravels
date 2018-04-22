package rn.travels.in.rntravels.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by demo on 18/04/18.
 */

public class ResponseVO {
    private boolean responseStatus;
    private String msg;
    private JSONObject response;

    public ResponseVO(JSONObject jsonObject) {
        try {
            this.responseStatus = jsonObject.getBoolean("status");
            this.msg = jsonObject.getString("msg");
            this.response = jsonObject.getJSONObject("response");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isResponseValid() {
        return responseStatus;
    }

    public void setResponseStatus(boolean responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getResponse() {
        return response;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

}
