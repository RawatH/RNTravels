package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.database.RNDatabase;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.models.UserVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.network.NetworkListener;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 17/03/18.
 */

public class FeedbackFragment extends DialogFragment implements View.OnClickListener, NetworkListener {
    private EditText feedbackMsg;
    private Button feebackBtn;
    private ProgressBar pb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        getDialog().setTitle("Feedback");

        pb = rootView.findViewById(R.id.progress);
        feedbackMsg = rootView.findViewById(R.id.feedbackMsg);
        feebackBtn = rootView.findViewById(R.id.feedbackBtn);
        feebackBtn.setOnClickListener(this);
        // Do something else
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedbackBtn:
                if (TextUtils.isEmpty(feedbackMsg.getText().toString())) {
                    Util.t(getActivity(), "Write some feedback.");
                    return;
                } else {
                    RNDatabase db = RNDatabase.getInstance(getContext());
                    UserVO loggedUser = db.getUserDao().getLoggedUser();

                    JSONObject paramObj = new JSONObject();
                    try {

                        paramObj.put("user_id", loggedUser.getUserId());
                        paramObj.put("email", loggedUser.getUserEmail());
                        paramObj.put("msg", feedbackMsg.getText().toString());
                        paramObj.put("extra", "");
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        paramObj.put("date", formatter.format(date));

                        new NRequestor.RequestBuilder(getContext())
                                .setReqType(Request.Method.POST)
                                .setUrl(Util.getUrlFor(NetworkConst.ReqTag.FEEDBACK))
                                .setListener(this)
                                .setReqParams(paramObj)
                                .setReqTag(NetworkConst.ReqTag.FEEDBACK)
                                .build()
                                .sendRequest();
                        pb.setVisibility(View.VISIBLE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }

    }

    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
        pb.setVisibility(View.GONE);
        if (responseVO.isResponseValid()) {
            Util.t(getContext(), "Feeback submitted successfully.");
        }
        dismissAllowingStateLoss();

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pb.setVisibility(View.GONE);
        dismissAllowingStateLoss();

    }
}
