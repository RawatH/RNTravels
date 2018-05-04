package rn.travels.in.rntravels.ui.fragment;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import rn.travels.in.rntravels.database.RNDatabase;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.network.NetworkListener;
import rn.travels.in.rntravels.ui.activity.RootActivity;

/**
 * Created by demo on 16/02/18.
 */

public class BaseFragment extends Fragment implements View.OnClickListener, NetworkListener {

    public RootActivity activity;
    public FragListener listener;
    public Context ctx;
    public int fragId;
    private String backStackTag;
    public RNDatabase db;
    private ProgressDialog pd;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ctx = context;
        activity = (RootActivity)context;
        listener = (FragListener)context;
        db = RNDatabase.getInstance(ctx);
        pd = new ProgressDialog(ctx);
        pd.setCanceledOnTouchOutside(false);
    }

    public void showProgress(String msg){
        pd.setMessage(msg == null ? "Loading..." : msg);
        pd.show();
    }

    public void dismissProgress(){
        pd.dismiss();
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
    public void onSuccessResponse(ResponseVO responseVO) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        VolleyLog.d("err", "Error: " + error.getMessage());
    }


    public interface FragListener {
        void toggleDrawerLock(boolean lockState);
        void setupDrawerMenu();
    }
}
