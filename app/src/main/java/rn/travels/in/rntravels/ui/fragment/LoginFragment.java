package rn.travels.in.rntravels.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 17/02/18.
 */

public class LoginFragment extends NoToolbarFragment {

    private FloatingActionButton loginBtn;
    private LoginButton fbLoginButton;
    private TextView signupBtn;

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
                                        Log.d("login_fb", response.toString());
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender, birthday");
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
                    }
                });


        loginBtn.setOnClickListener(this);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
                JSONObject paramObj = new JSONObject();

                try {

                    paramObj.put("email", "s1a@b.com");
                    paramObj.put("fb_id", "s111");
                    paramObj.put("first_name", "s1fname");
                    paramObj.put("last_name", "s1lname");
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

            case R.id.signup:
                activity.loadFragment(Appconst.FragmentId.REGISTER, null, null);
                break;

            case R.id.fblogin:
                break;
        }

    }

    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
        super.onSuccessResponse(responseVO);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
    }
}
