package rn.travels.in.rntravels.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import rn.travels.in.rntravels.app.RNApp;
import rn.travels.in.rntravels.models.ResponseVO;

/**
 * Created by demo on 16/02/18.
 */

public class NRequestor {
    private static NRequestor INSTANCE;

    private String reqUrl;
    private int reqType;
    private NetworkListener listener;
    private int reqTag;
    private JSONObject reqParams;
    private RNApp rnApp;
    private static final String TAG = "nreq";

    private NRequestor() {

    }

    public static NRequestor getInstance(RequestBuilder builder) {

        if (INSTANCE == null) {
            INSTANCE = new NRequestor();
            INSTANCE.rnApp = RNApp.getContext();
        }
        INSTANCE.reqType = builder.reqType;
        INSTANCE.reqUrl = builder.url;
        INSTANCE.listener = builder.listener;
        INSTANCE.reqTag = builder.reqTag;
        INSTANCE.reqParams = builder.reqParams;

        return INSTANCE;
    }

    public void sendRequest() {

        StringRequest request = new StringRequest(INSTANCE.reqType, INSTANCE.reqUrl , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    INSTANCE.listener.onSuccessResponse(new ResponseVO(new JSONObject(response),INSTANCE.reqTag));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                INSTANCE.listener.onErrorResponse(error);  
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<>();
                if (INSTANCE.reqParams != null) {
                    Iterator<String> itr = INSTANCE.reqParams.keys();
                    while (itr.hasNext()) {
                        String k = itr.next();
                        params.put(k, INSTANCE.reqParams.optString(k));
                    }
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("Content-Type","application/x-www-form-urlencoded");
                return headers;
            }
        };

//        // Adding request to request queue
//
        INSTANCE.rnApp.addToRequestQueue(request, String.valueOf(INSTANCE.reqTag));

    }

    public void cancelRequest(int requestTag) {
        INSTANCE.rnApp.cancelRequest(requestTag);
    }


    public static class RequestBuilder {

        private String url;
        private NetworkListener listener;
        private int reqType;
        private int reqTag;
        private JSONObject reqParams;

        public RequestBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public RequestBuilder setListener(NetworkListener listener) {
            this.listener = listener;
            return this;
        }

        public RequestBuilder setReqType(int reqType) {
            this.reqType = reqType;
            return this;
        }

        public RequestBuilder setReqTag(int reqTag) {
            this.reqTag = reqTag;
            return this;
        }

        public RequestBuilder setReqParams(JSONObject reqParams) {
            this.reqParams = reqParams;
            return this;
        }

        public NRequestor build() {
            return NRequestor.getInstance(this);
        }
    }


}
