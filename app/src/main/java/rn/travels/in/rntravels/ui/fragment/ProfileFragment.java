package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 18/03/18.
 */

public class ProfileFragment extends BackFragment {
    private TextView firstName;
    private TextView lastName;
    private TextView userName;
    private TextView email;
    private TextView travelId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        firstName = view.findViewById(R.id.profileFirstName);
        lastName = view.findViewById(R.id.profileLastName);
        userName = view.findViewById(R.id.profileUserName);
        email = view.findViewById(R.id.profileEmail);
        travelId = view.findViewById(R.id.profileTravleId);
        loadProfile();

    }

    private void loadProfile() {
        JSONObject paramObj = new JSONObject();

        try {

            paramObj.put("user_id", "4");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new NRequestor.RequestBuilder(ctx)
                .setReqType(Request.Method.POST)
                .setUrl(Util.getUrlFor(NetworkConst.ReqTag.PROFILE))
                .setListener(this)
                .setReqParams(paramObj)
                .setReqTag(NetworkConst.ReqTag.PROFILE)
                .build()
                .sendRequest();
        showProgress("Loading profile...");
    }

    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
       dismissProgress();
        if(responseVO.isResponseValid()){
            try {
//                {"id":"4","fb_id":"2","first_name":"Harish","last_name":"Rawat","name":"harish","email_addres":"hrawat@gmail.com","contact_number":"123","user_name":"hRawat","pass_word":"FCEA920F7412B5DA7BE0CF42B8C93759","travel_id":"123456"}
                JSONObject profileJson = (JSONObject) responseVO.getResponseArr().get(0);
                firstName.setText(profileJson.optString("first_name"));
                lastName.setText(profileJson.optString("last_name"));
                userName.setText(profileJson.optString("user_name"));
                email.setText(profileJson.optString("email_addres"));
                travelId.setText(profileJson.optString("travel_id"));


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
        dismissProgress();
    }

    @Override
    public String getTitle() {
        return "Profile";
    }
}
