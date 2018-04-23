package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 18/03/18.
 */

public class PasswordFragment extends BackFragment {

    private EditText oldPassword;
    private EditText newPassword;
    private EditText confPassword;
    private Button changePswd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        oldPassword = view.findViewById(R.id.oldPassword);
        newPassword = view.findViewById(R.id.newPassword);
        confPassword = view.findViewById(R.id.confirmPassword);
        changePswd = view.findViewById(R.id.changePassword);
        changePswd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changePassword:
                if (validate()) {
                    JSONObject paramObj = new JSONObject();
                    try {
                        paramObj.put("user_name", "a");
                        paramObj.put("password",newPassword.getText().toString());
                        new NRequestor.RequestBuilder()
                                .setReqType(Request.Method.POST)
                                .setUrl(Util.getUrlFor(NetworkConst.ReqTag.PSWD_RESET))
                                .setListener(this)
                                .setReqParams(paramObj)
                                .setReqTag(NetworkConst.ReqTag.PSWD_RESET)
                                .build()
                                .sendRequest();
                        pd.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private boolean validate() {
        boolean validationFlag = true;
        if (TextUtils.isEmpty(oldPassword.getText().toString())) {
            oldPassword.setError("Enter old password");
            validationFlag = false;
        }
        if (TextUtils.isEmpty(newPassword.getText().toString())) {
            newPassword.setError("Enter new password");
            validationFlag = false;
        }
        if (TextUtils.isEmpty(confPassword.getText().toString())) {
            confPassword.setError("Confirm password");
            validationFlag = false;
        }
        if (!newPassword.getText().toString().equals(confPassword.getText().toString())) {
            confPassword.setError("Confirm  password not matching.");
            validationFlag = false;
        }
        return validationFlag;
    }

    @Override
    public String getTitle() {
        return "Change Password";
    }

    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
        pd.dismiss();
        if (responseVO.isResponseValid()) {
            Util.t(ctx, "Password successfuly updated");
            activity.loadFragment(Appconst.FragmentId.LOGIN, null, null);
        } else {
            Util.t(ctx, responseVO.getMsg());
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        pd.cancel();
    }
}
