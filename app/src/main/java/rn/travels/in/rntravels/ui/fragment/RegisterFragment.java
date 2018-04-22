package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 17/02/18.
 */

public class RegisterFragment extends BaseFragment {

    private FloatingActionButton registerBtn;
    private EditText firstName;
    private EditText lastName;
    private EditText userName;
    private EditText email;
    private EditText travelId;


    @Override
    public String getTitle() {
        return "Register";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        registerBtn = view.findViewById(R.id.registerUser);
        userName = view.findViewById(R.id.regusername);
        email = view.findViewById(R.id.regemail);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        travelId = view.findViewById(R.id.regTravelId);
        registerBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.registerUser:
                if (validateData()) {
                    JSONObject paramObj = new JSONObject();

                    try {
                        paramObj.put("email", email.getText().toString().trim());
                        paramObj.put("first_name", firstName.getText().toString().trim());
                        paramObj.put("last_name", lastName.getText().toString().trim());

                        paramObj.put("fb_id", "s111");
                        paramObj.put("number", "s1111");
                        paramObj.put("password", "s12222");
                        paramObj.put("user_name", "s1user");
                        paramObj.put("travel_id", "s1333");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new NRequestor.RequestBuilder()
                            .setReqType(Request.Method.POST)
                            .setUrl(Util.getUrlFor(NetworkConst.ReqTag.REGISTER))
                            .setListener(this)
                            .setReqParams(paramObj)
                            .setReqTag(NetworkConst.ReqTag.REGISTER)
                            .build()
                            .sendRequest();

                    break;

                }

        }
    }

    public boolean validateData() {
        boolean flag = true;
        if (TextUtils.isEmpty(email.getText().toString().trim())) {
            email.setError("Please enter email.");
            flag = false;
        }
        if (TextUtils.isEmpty(firstName.getText().toString().trim())) {
            firstName.setError("Please enter first name.");
            flag = false;
        }
        if (TextUtils.isEmpty(lastName.getText().toString().trim())) {
            lastName.setError("Please enter last name.");
            flag = false;
        }

        return flag;
    }

    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
        if(responseVO.isResponseValid()){
            Util.t(ctx , "Successfully registered.");
        }else{
            Util.t(ctx,responseVO.getMsg());
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
    }
}
