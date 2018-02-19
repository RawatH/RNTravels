package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.ui.activity.RootActivity;
import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 17/02/18.
 */

public class LoginFragment extends BaseFragment {

    private Button loginBtn;
    private Button signupBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        loginBtn = (Button)view.findViewById(R.id.login);
        signupBtn = (Button)view.findViewById(R.id.register);

        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login:
                break;

            case R.id.register:
                activity.loadFragment(Appconst.FragmentId.REGISTER , null , null);
                break;
        }

    }
}
