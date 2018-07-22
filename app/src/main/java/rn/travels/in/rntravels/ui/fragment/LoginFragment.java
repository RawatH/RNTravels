package rn.travels.in.rntravels.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.models.UserVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.NWReqUtility;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 17/02/18.
 */

public class LoginFragment extends NoToolbarFragment {

    private FloatingActionButton loginBtn;
    private LoginButton fbLoginButton;
    private TextView signupBtn;
    private EditText userName;
    private EditText password;
    private UserVO loggingUserVO;
    private static final String EMAIL = "email";
    private CallbackManager callbackManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        userName = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);

        loginBtn = view.findViewById(R.id.login);
        loginBtn.setOnClickListener(this);
        signupBtn = view.findViewById(R.id.signup);
        signupBtn.setOnClickListener(this);


        callbackManager = CallbackManager.Factory.create();
        fbLoginButton = view.findViewById(R.id.fblogin);
        fbLoginButton.setFragment(this);
        fbLoginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call fbLoginButton.setFragment(this);

        // Callback registration
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("login_fb", "Login success");
                // App code
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("login_fb", "Login cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("login_fb", "Login error");
                // App code
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.d("login_fb", "LoginManager login result");
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {

                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        String graphResponse = response.getRawResponse();
                                        if (graphResponse != null) {
                                            registerFbUser(graphResponse);
                                        }
                                    }

                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,first_name, last_name");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.d("login_fb", "LoginManager cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.d("login_fb", "LoginManager exception");
                        if(exception.getMessage().startsWith("CONNECTION_FAILURE")){
                            Util.t(ctx , "Internet connection off.");
                        }
                    }
                });


        loginBtn.setOnClickListener(this);

    }

    private void registerFbUser(String response) {

        try {

            JSONObject responseObj = new JSONObject(response);
            JSONObject paramObj = new JSONObject();
            paramObj.put("fb_id", responseObj.optString("id"));
            paramObj.put("first_name", responseObj.optString("first_name"));
            paramObj.put("last_name", responseObj.optString("last_name"));
            paramObj.put("user_name", responseObj.optString("name"));
            paramObj.put("email", responseObj.optString("email"));

            loggingUserVO = new UserVO();
            loggingUserVO.setFbId(paramObj.optString("fb_id"));
            loggingUserVO.setUserEmail(paramObj.optString("email"));
            loggingUserVO.setFirstName(paramObj.optString("first_name"));
            loggingUserVO.setLastName(paramObj.optString("last_name"));
            loggingUserVO.setUserEmail(paramObj.optString("email"));
            loggingUserVO.setFBUser(true);


            new NRequestor.RequestBuilder(ctx)
                    .setReqType(Request.Method.POST)
                    .setUrl(Util.getUrlFor(NetworkConst.ReqTag.REGISTER))
                    .setListener(this)
                    .setReqParams(paramObj)
                    .setReqTag(NetworkConst.ReqTag.REGISTER)
                    .build()
                    .sendRequest();
            showProgress("Initializing...");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Util.printKeyHash(getActivity());
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public String getTitle() {
        return "Login";
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login:
                if (validateData()) {
                    JSONObject paramObj = new JSONObject();
                    try {
                        paramObj.put("email", userName.getText().toString().trim());
                        paramObj.put("password", password.getText().toString().trim());
                        new NRequestor.RequestBuilder(ctx)
                                .setReqType(Request.Method.POST)
                                .setUrl(Util.getUrlFor(NetworkConst.ReqTag.LOGIN))
                                .setListener(this)
                                .setReqVolleyType(NetworkConst.VolleyReq.STRING)
                                .setReqParams(paramObj)
                                .setReqTag(NetworkConst.ReqTag.LOGIN)
                                .build()
                                .sendRequest();
                        showProgress("Authenticating... ");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                break;

            case R.id.signup:
                activity.loadFragment(Appconst.FragmentId.REGISTER, null, null);
                break;
        }

    }

    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
        dismissProgress();

        switch (responseVO.getRequestTag()) {
            case NetworkConst.ReqTag.LOGIN:
                if (responseVO.isResponseValid()) {
                    loggingUserVO = new UserVO(responseVO.getResponse());
                    loggingUserVO.setUserType(Appconst.UserType.LOGGED_USER);
                    if (db.getUserDao().findByName(loggingUserVO.getUserEmail()) == null) {
                        db.getUserDao().insert(loggingUserVO);
                    }
                    activity.loadFragment(Appconst.FragmentId.DASHBOARD, null, null);

                } else {
                    Util.t(ctx, responseVO.getMsg());
                }
                break;


            case NetworkConst.ReqTag.REGISTER:

                try {
                    String userId = String.valueOf(responseVO.getResponse().get("user_id"));
                    loggingUserVO.setUserId(userId);
                    if (db.getUserDao().findByName(loggingUserVO.getUserEmail()) == null) {
                        db.getUserDao().insert(loggingUserVO);
                    }
                    activity.loadFragment(Appconst.FragmentId.DASHBOARD, null, null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        dismissProgress();
    }

    public boolean validateData() {
        boolean flag = true;
        if (TextUtils.isEmpty(userName.getText().toString().trim())) {
            Util.t(ctx, "Please enter username.");
            return false;
        }

        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            Util.t(ctx, "Please enter password.");
            return false;
        }

        return flag;
    }
}
