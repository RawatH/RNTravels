package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private TextView result;
    private Button convertBtn;
    private String key;
    private AutoCompleteTextView convertF;
    private AutoCompleteTextView convertT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_conversion, container, false);
        init(rootView);
        // Do something else
        return rootView;
    }

    private void init(View rootView) {

        convertF = rootView.findViewById(R.id.fromCurr);
        convertT = rootView.findViewById(R.id.toCurr);

        conversionAmt = rootView.findViewById(R.id.conversionAmt);
        result = rootView.findViewById(R.id.convResult);
        convertBtn = rootView.findViewById(R.id.convert);
        convertBtn.setOnClickListener(this);

        ArrayList<String> countryCodesList  = Util.getAllCurrCodes();

        ArrayAdapter<String> adapterFrom = new ArrayAdapter<>(ctx, android.R.layout.simple_dropdown_item_1line, countryCodesList.toArray(new String[countryCodesList.size()]) );

        convertF.setAdapter(adapterFrom);
        convertF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    convertF.showDropDown();
                }
            }
        });

        ArrayAdapter<String> adapterTo = new ArrayAdapter<>(ctx, android.R.layout.simple_dropdown_item_1line, countryCodesList.toArray(new String[countryCodesList.size()]) );

        convertT.setAdapter(adapterTo);
        convertT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    convertT.showDropDown();
                }
            }
        });
    }

    @Override
    public String getTitle() {
        return "Forex Rates";
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        String fromVal = convertF.getText().toString();
        String toVal = convertT.getText().toString();
        if (fromVal.equalsIgnoreCase(toVal)) {
            Util.t(ctx, "Select diff values for conversion");
            return;
        }
        else if(TextUtils.isEmpty(fromVal) || TextUtils.isEmpty(toVal) ){
            Util.t(ctx, "Please fill all conversion fields");
            return;
        }

        this.key = fromVal + "_" + toVal;
        String queryString = "?q=" + this.key + "&compact=y";
        new NRequestor.RequestBuilder(ctx)
                .setReqType(Request.Method.GET)
                .setUrl(Util.getUrlFor(NetworkConst.ReqTag.CONV) + queryString)
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
            String toVal = convertT.getText().toString();
            float value = Float.parseFloat(conversionAmt.getText().toString()) * Float.valueOf(convJson.getString("val"));
            String val = toVal + " : " + value;
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
