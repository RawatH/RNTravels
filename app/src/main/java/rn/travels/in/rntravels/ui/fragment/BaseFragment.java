package rn.travels.in.rntravels.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import rn.travels.in.rntravels.ui.activity.RootActivity;

/**
 * Created by demo on 16/02/18.
 */

public class BaseFragment extends Fragment implements View.OnClickListener {

    public RootActivity activity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (RootActivity)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String getTitle(){
        return "";
    }


    @Override
    public void onClick(View v) {

    }
}
