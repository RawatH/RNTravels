package rn.travels.in.rntravels.network;

import com.android.volley.VolleyError;

import rn.travels.in.rntravels.models.ResponseVO;

/**
 * Created by demo on 16/02/18.
 */

public interface NetworkListener {
    void onSuccessResponse(ResponseVO responseVO);

    void onErrorResponse(VolleyError error);
}
