package rn.travels.in.rntravels.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by demo on 18/04/18.
 */

public class ResponseVO {
    private boolean responseStatus;
    private String msg;
    private JSONObject response;
    private JSONArray responseArr;
    private int requestTag;

    public ResponseVO(JSONObject jsonObject){
        this.responseStatus = true;
        this.response = jsonObject;
    }

    public ResponseVO(JSONObject jsonObject , int requestTag) {
        try {
            this.responseStatus = jsonObject.getBoolean("status");
            this.msg = jsonObject.getString("msg");
            this.response = jsonObject.getJSONObject("response");
        } catch (JSONException e) {
            try {
                this.responseArr = jsonObject.getJSONArray("response");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        this.requestTag = requestTag;
    }

    public int getRequestTag() {
        return requestTag;
    }

    public void setRequestTag(int reqTag){
        this.requestTag = reqTag;
    }

    public JSONArray getResponseArr() {
        return responseArr;
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
