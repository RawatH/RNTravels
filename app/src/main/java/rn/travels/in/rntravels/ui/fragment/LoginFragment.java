package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import rn.travels.in.rntravels.R;
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
                Util.t(getContext(),"Login success");
                // App code
            }

            @Override
            public void onCancel() {
                // App code
                Util.t(getContext(),"Login cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Util.t(getContext(),"Login error");
                // App code
            }
        });


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Util.t(getContext(),"LoginManager success");
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        Util.t(getContext(),"LoginManager cancel");
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Util.t(getContext(),"LoginManager exception");
                        // App code
                    }
                });

        loginBtn.setOnClickListener(this);

    }

    @Override
    public String getTitle() {
        return "Login";
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login:
                break;

            case R.id.signup:
                activity.loadFragment(Appconst.FragmentId.REGISTER , null , null);
                break;

            case R.id.fblogin:
                break;
        }

    }
}
