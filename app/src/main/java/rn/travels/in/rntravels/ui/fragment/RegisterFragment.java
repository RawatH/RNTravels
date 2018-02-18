package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private Button registerBtn;
    private Button cancelBtn;
    private EditText userName;
    private EditText email;
    private EditText phone;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test, null);
        init(view);
        return view;
    }

    private void init(View view) {

//        registerBtn = (Button) view.findViewById(R.id.registerUser);
//        cancelBtn = (Button) view.findViewById(R.id.cancelRegister);
//
//        userName = (EditText) view.findViewById(R.id.regusername);
//        email = (EditText) view.findViewById(R.id.regemail);
//        phone = (EditText) view.findViewById(R.id.regphone);
//
//        registerBtn.setOnClickListener(this);
//        cancelBtn.setOnClickListener(this);

    }

//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()) {
//            case R.id.registerUser:
//                break;
//
//            case R.id.cancelRegister:
//                ((RootActivity) getActivity()).loadFragment(Appconst.FragmentId.LOGIN, null, null);
//                break;
//        }
//
//    }
}
