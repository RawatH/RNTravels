package rn.travels.in.rntravels.network.results;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import rn.travels.in.rntravels.models.RNContactVO;

/**
 * Created by demo on 18/04/18.
 */

public class RNContactResult {
    @SerializedName("response")
    private List<RNContactVO> response;

    public List<RNContactVO> getResponse() {
        return response;
    }
}
