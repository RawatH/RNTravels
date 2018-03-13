package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.ui.activity.RootActivity;
import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 17/02/18.
 */

public class RegisterFragment extends BaseFragment {

    private FloatingActionButton registerBtn;
    private EditText userName;
    private EditText email;
    private EditText phone;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        registerBtn =  view.findViewById(R.id.registerUser);

        userName =  view.findViewById(R.id.regusername);
        email =  view.findViewById(R.id.regemail);
        phone =  view.findViewById(R.id.regphone);

        registerBtn.setOnClickListener(this);


    }

    @Override
    public String getTitle() {
        return "Register";
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.registerUser:
                ((RootActivity) getActivity()).loadFragment(Appconst.FragmentId.DASHBOARD, null, null);
                break;

        }

    }
}
