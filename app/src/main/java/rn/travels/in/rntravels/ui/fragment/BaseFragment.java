package rn.travels.in.rntravels.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;
import rn.travels.in.rntravels.app.RNApp;
import rn.travels.in.rntravels.network.ApiClient;
import rn.travels.in.rntravels.network.ApiService;
import rn.travels.in.rntravels.ui.activity.RootActivity;

/**
 * Created by demo on 16/02/18.
 */

public class BaseFragment extends Fragment implements View.OnClickListener, retrofit2.Callback {

    public RootActivity activity;
    public FragListener listener;
    public Context ctx;
    private int fragId;
    private String backStackTag;
    public ApiService apiService;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ctx = context;
        activity = (RootActivity)context;
        listener = (FragListener)context;
        apiService = ((RNApp)getActivity().getApplication()).getApiService();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        listener.setupDrawerMenu();
        if (this instanceof DrawerFragment) {
            listener.toggleDrawerLock(false);
        } else {
            listener.toggleDrawerLock(true);
        }
    }

    public String getTitle(){
        return "";
    }

    public String getSubTitle(){
        return "";
    }

    public int getFragId() {
        return fragId;
    }

    public void setFragId(int fragId) {
        this.fragId = fragId;
    }

    @Override
    public void onClick(View v) {

    }


    public String getBackStackTag() {
        return backStackTag;
    }

    public void setBackStackTag(String backStackTag) {
        this.backStackTag = backStackTag;
    }

    @Override
    public void onResponse(Call call, Response response) {

    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }

    public interface FragListener {
        void toggleDrawerLock(boolean lockState);
        void setupDrawerMenu();
    }
}
