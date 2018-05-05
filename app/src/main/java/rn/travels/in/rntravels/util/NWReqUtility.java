package rn.travels.in.rntravels.util;

import android.content.Context;
import android.net.NetworkRequest;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.network.NetworkListener;

/**
 * Created by demo on 05/05/18.
 */

public class NWReqUtility {

    public static NRequestor getPackageReq(Context ctx, NetworkListener listener, String userId) {

        NRequestor nRequestor = null;
        JSONObject paramObj = new JSONObject();
        try {
            paramObj.put("user_id", userId);
            nRequestor = new NRequestor.RequestBuilder(ctx)
                    .setReqType(Request.Method.POST)
                    .setUrl(Util.getUrlFor(NetworkConst.ReqTag.PKG_DETAIL))
                    .setListener(listener)
                    .setReqVolleyType(NetworkConst.VolleyReq.STRING)
                    .setReqParams(paramObj)
                    .setReqTag(NetworkConst.ReqTag.PKG_DETAIL)
                    .build();


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nRequestor;
    }
}
