package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 17/02/18.
 */

public class LoginFragment extends NoToolbarFragment {

    private FloatingActionButton loginBtn;
    private Button fbLoginBtn;
    private TextView signupBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        loginBtn = view.findViewById(R.id.login);
        signupBtn = view.findViewById(R.id.signup);
        fbLoginBtn = view.findViewById(R.id.fbLogin);

        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        fbLoginBtn.setOnClickListener(this);

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

            case R.id.fbLogin:
                break;
        }

    }
}
