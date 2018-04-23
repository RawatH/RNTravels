package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 23/02/18.
 */

public class RNInfoFragment extends BackFragment {

    private TextView rnMobile;
    private TextView rnLandlineA;
    private TextView rnEmail;
    private TextView rnOfficeAddress;
    private TextView rnWebsite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rn_info, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        rnMobile = view.findViewById(R.id.rn_mobile);
        rnLandlineA = view.findViewById(R.id.rn_landline);
        rnEmail = view.findViewById(R.id.rn_email);
        rnOfficeAddress = view.findViewById(R.id.rn_office_address);
        rnWebsite = view.findViewById(R.id.rn_website);
        loadContactDetail();
    }

    private void loadContactDetail() {
        JSONObject paramObj = new JSONObject();

        try {
            {"id":"1","mob_num":"9811585124, 9811575243","landline_num":"23840568, 48325647","website":"www.google.com, http:\/\/www.facebook.com","email":"javiabhavik@gmail.com","addr":"19\/4 Shakti Nagar 4th FloorNew Delhi - 110007"}

            paramObj.put("user_id", "4");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new NRequestor.RequestBuilder()
                .setReqType(Request.Method.POST)
                .setUrl(Util.getUrlFor(NetworkConst.ReqTag.RN_CONTACT_DETAIL))
                .setListener(this)
                .setReqParams(paramObj)
                .setReqTag(NetworkConst.ReqTag.RN_CONTACT_DETAIL)
                .build()
                .sendRequest();
        pd.show();
    }

    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
        pd.dismiss();
        if(responseVO.isResponseValid()){
            try {
//                {"id":"4","fb_id":"2","first_name":"Harish","last_name":"Rawat","name":"harish","email_addres":"hrawat@gmail.com","contact_number":"123","user_name":"hRawat",
// "pass_word":"FCEA920F7412B5DA7BE0CF42B8C93759","travel_id":"123456"}
                JSONObject profileJson = (JSONObject) responseVO.getResponseArr().get(0);
                rnMobile.setText(profileJson.optString("mob_num"));
                rnLandlineA.setText(profileJson.optString("landline_num"));
                rnEmail.setText(profileJson.optString("email"));
                rnOfficeAddress.setText(profileJson.optString("addr"));
                rnWebsite.setText(profileJson.optString("website"));


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            Util.t(ctx,responseVO.getMsg());
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        pd.cancel();
    }

    @Override
    public String getTitle() {
        return "Contact Info";
    }
}
