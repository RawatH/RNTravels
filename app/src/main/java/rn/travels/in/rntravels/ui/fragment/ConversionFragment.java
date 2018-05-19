package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 19/05/18.
 */

public class ConversionFragment extends BackFragment {

    private EditText conversionAmt;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private TextView result;
    private Button convertBtn;
    private String key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_conversion, container, false);
        init(rootView);
        // Do something else
        return rootView;
    }

    private void init(View rootView) {
        conversionAmt = rootView.findViewById(R.id.conversionAmt);
        fromSpinner = rootView.findViewById(R.id.fromCurr);
        toSpinner = rootView.findViewById(R.id.toCurr);
        result = rootView.findViewById(R.id.convResult);
        convertBtn = rootView.findViewById(R.id.convert);
        convertBtn.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item,Util.getAllCurrCodes());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
    }

    @Override
    public String getTitle() {
        return "Forex Rates";
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        String fromVal = fromSpinner.getSelectedItem().toString();
        String toVal = toSpinner.getSelectedItem().toString();
        if(fromVal.equalsIgnoreCase(toVal)){
            Util.t(ctx ,"Select diff values for conversion");
            return;
        }

        this.key = fromVal+"_"+toVal;
        String queryString = "?q="+this.key+"&compact=y";
        new NRequestor.RequestBuilder(ctx)
                .setReqType(Request.Method.GET)
                .setUrl(Util.getUrlFor(NetworkConst.ReqTag.CONV)+queryString)
                .setListener(this)
                .setReqVolleyType(NetworkConst.VolleyReq.STRING)
                .setReqTag(NetworkConst.ReqTag.CONV)
                .build()
                .sendRequest();
        showProgress("Converting... ");
        Util.hideKeyboard(getActivity());
    }

    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
        super.onSuccessResponse(responseVO);
        JSONObject responseJson = responseVO.getResponse();
        try {
            JSONObject convJson = responseJson.getJSONObject(this.key);
            String toVal = toSpinner.getSelectedItem().toString();
            float value = Float.parseFloat(conversionAmt.getText().toString()) * Float.valueOf(convJson.getString("val"));
            String val = toVal +" : "+value;
            this.result.setText(val);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
    }
}
