package rn.travels.in.rntravels.network;

import retrofit2.Call;
import retrofit2.http.POST;
import rn.travels.in.rntravels.models.ResponseVO;

/**
 * Created by demo on 10/04/18.
 */

public interface ApiService {
    @POST("getRnContactDetails")
    public Call<ResponseVO> getRNContactDetail();
}
