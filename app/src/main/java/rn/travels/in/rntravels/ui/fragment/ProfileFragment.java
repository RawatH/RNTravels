package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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

import java.io.File;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.PdfVO;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.models.UserVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Appconst;
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
    private UserVO userVO;
    private TextView passportView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        this.userVO = db.getUserDao().getLoggedUser();
        firstName = view.findViewById(R.id.profileFirstName);
        lastName = view.findViewById(R.id.profileLastName);
        userName = view.findViewById(R.id.profileUserName);
        email = view.findViewById(R.id.profileEmail);
        travelId = view.findViewById(R.id.profileTravleId);
        passportView = view.findViewById(R.id.viewPassport);
        if(userVO.getPassportUrl() == null || userVO.getPassportUrl().equals("null")){
            passportView.setVisibility(View.GONE);
        }else {

            passportView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userVO.getPassportUrl() != null || !TextUtils.isEmpty(userVO.getPassportUrl())) {
                        //TODO LOAD PASSPORT PDF
                        PdfVO pdf = new PdfVO(userVO.getPassportUrl());
                        Bundle bundle = new Bundle();
                        bundle.putString("title", "Passport");
                        bundle.putSerializable("obj", pdf);
                        bundle.putBoolean("isPassport", true);
                        activity.loadFragment(Appconst.FragmentId.PDF_FRG, bundle, null);
                    }
                }
            });
        }
        loadProfile();

    }

    private void loadProfile() {

        File userFolder = new File(ctx.getFilesDir()+File.separator+this.userVO.getUserId());
        if(!userFolder.exists()){
            userFolder.mkdir();
        }
        firstName.setText(userVO.getFirstName());
        lastName.setText(userVO.getLastName());
        userName.setText(userVO.getUserName());
        email.setText(userVO.getUserEmail());
        travelId.setText(userVO.getTravelId());
    }


    @Override
    public String getTitle() {
        return "Profile";
    }
}
