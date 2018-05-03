package rn.travels.in.rntravels.network;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
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
    private int reqVolleyType;
    private JSONObject reqParams;
    private RNApp rnApp;
    private Context ctx;
    private Bundle reqBundle;
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
        INSTANCE.reqVolleyType = builder.reqVolleyType;
        INSTANCE.ctx = builder.ctx;
        INSTANCE.reqBundle = builder.reqBundle;

        return INSTANCE;
    }

    public void sendRequest() {
        Request request = null;
        switch (INSTANCE.reqVolleyType) {
            case NetworkConst.VolleyReq.STRING:
                request = new StringRequest(INSTANCE.reqType, INSTANCE.reqUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            INSTANCE.listener.onSuccessResponse(new ResponseVO(new JSONObject(response), INSTANCE.reqTag));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        INSTANCE.listener.onErrorResponse(error);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
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
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/x-www-form-urlencoded");
                        return headers;
                    }
                };
                break;
            case NetworkConst.VolleyReq.BYTE:
                request = new InputStreamVolleyRequest(INSTANCE.reqType, INSTANCE.reqUrl,
                        new Response.Listener<byte[]>() {
                            @Override
                            public void onResponse(byte[] response) {
                                String fileName = INSTANCE.reqBundle.getString("fileName");
                                String filePath = INSTANCE.reqBundle.getString("dest") + File.separator + fileName;
                                // TODO handle the response
                                try {
                                    if (response != null) {
                                        File file = new File(filePath);
                                        file.createNewFile();

                                        FileOutputStream fos = new FileOutputStream(file);
                                        fos.write(response);
                                        fos.close();

                                        INSTANCE.listener.onSuccessResponse(null);
                                    }
                                } catch (Exception e) {

                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO handle the error
                        error.printStackTrace();
                    }
                }, null);
                break;

        }


        if (request != null) {

            INSTANCE.rnApp.addToRequestQueue(request, String.valueOf(INSTANCE.reqTag));
        }

    }

    public void cancelRequest(int requestTag) {
        INSTANCE.rnApp.cancelRequest(requestTag);
    }


    public static class RequestBuilder {

        private final Context ctx;
        private String url;
        private NetworkListener listener;
        private int reqType;
        private int reqTag;
        private JSONObject reqParams;
        private int reqVolleyType;
        private Bundle reqBundle;

        public RequestBuilder(Context ctx) {
            this.ctx = ctx;
        }

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

        public RequestBuilder setReqVolleyType(int reqVolleyType) {
            this.reqVolleyType = reqVolleyType;
            return this;
        }

        public RequestBuilder setReqBundle(Bundle reqBundle) {
            this.reqBundle = reqBundle;
            return this;
        }

        public NRequestor build() {
            return NRequestor.getInstance(this);
        }
    }


}
