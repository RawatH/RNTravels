package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.adapters.TranslateAdapter;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 14/07/18.
 */

public class TranslateFragment extends BackFragment  {

    private AutoCompleteTextView transFrom;
    private AutoCompleteTextView transTo;
    private EditText textToTranslate;
    private EditText translationResult;
    private Handler handler = new Handler();
    private Button transBtn;
    private static final String KEY = "AIzaSyCTV_CqpzBt8Qp5jR5_Klwr0OpOyb769zw";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_translate, container, false);
        init(rootView);
        // Do something else
        return rootView;
    }

    @Override
    public String getTitle() {
        return "Google Translate";
    }

    private void init(View rootView) {
        transFrom = rootView.findViewById(R.id.transFrom);
        transTo = rootView.findViewById(R.id.transTo);
        textToTranslate = rootView.findViewById(R.id.translationText);
        translationResult = rootView.findViewById(R.id.translationResult);
        transBtn = rootView.findViewById(R.id.translate);
        transBtn.setOnClickListener(this);
        handler.removeCallbacksAndMessages(null);
        handler.post(LangRunnable);
    }

    private void setLangAapters(ArrayList<Pair<String,String>> list) {
        final TranslateAdapter translateTo = new TranslateAdapter(ctx,R.layout.translate_row_layout,list);
        TranslateAdapter translateFrom = new TranslateAdapter(ctx,R.layout.translate_row_layout,list);

        transFrom.setAdapter(translateFrom);
        transFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    transFrom.showDropDown();
                }
            }
        });

        transTo.setAdapter(translateTo);
        transTo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    transTo.showDropDown();
                }
            }
        });

        transFrom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pair<String,String> valPair = ((TranslateAdapter)transFrom.getAdapter()).getItem(position);
                transFrom.setText(valPair.second);
            }
        });

        transTo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pair<String,String> valPair = ((TranslateAdapter)transTo.getAdapter()).getItem(position);
                transTo.setText(valPair.second);
            }
        });

    }

    private Runnable LangRunnable = new Runnable() {
        @Override
        public void run() {
            new NRequestor.RequestBuilder(ctx)
                    .setReqType(Request.Method.GET)
                    .setUrl(NetworkConst.TRANS_LANG_URL + "?key=" + KEY)
                    .setListener(TranslateFragment.this)
                    .setReqVolleyType(NetworkConst.VolleyReq.STRING)
                    .setReqTag(NetworkConst.ReqTag.TRANS_LANG)
                    .build()
                    .sendRequest();
            showProgress("Initializing... ");

        }
    };


    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
        super.onSuccessResponse(responseVO);
        dismissProgress();
        JSONObject resultJSON = responseVO.getResponse();
        switch (responseVO.getRequestTag()) {
            case NetworkConst.ReqTag.TRANS:
                try {
                    JSONObject dataJSON = resultJSON.getJSONObject("data");
                    JSONArray translationsArr = dataJSON.getJSONArray("translations");
                    ArrayList<String> langList = new ArrayList<>();
                    for (int i = 0; i < 1; i++) {
                        JSONObject transObj = translationsArr.getJSONObject(i);
                        langList.add(transObj.getString("translatedText"));
                    }
                    setTranslatedText(langList.get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case NetworkConst.ReqTag.TRANS_LANG:
                try {
                    ArrayList<Pair<String,String>> transList= new ArrayList<>();
                    JSONObject dataJSON = resultJSON.getJSONObject("data");
                    JSONArray langArr = dataJSON.getJSONArray("languages");

                    for (int i = 0; i < langArr.length(); i++) {
                        JSONObject langObj = langArr.getJSONObject(i);
                        String langCode = langObj.getString("language");

                        Locale loc = new Locale(langCode);
                        String name = loc.getDisplayLanguage();
//                        Log.d("code" , +"---"+langObj.getString("language"));

                        transList.add(new Pair<String, String>(loc.getDisplayLanguage(), langCode));


                    }
                    setLangAapters(transList);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    private void setTranslatedText(String s) {
        translationResult.setText(s);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        dismissProgress();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.translate:
                String txtToTrans = textToTranslate.getText().toString();
                String srcTrans = transFrom.getText().toString();
                String targetTrans = transTo.getText().toString();
                if (TextUtils.isEmpty(txtToTrans)) {
                    Util.t(ctx, "Add some text to translate");
                    return;
                } else if (TextUtils.isEmpty(targetTrans) || TextUtils.isEmpty(srcTrans)) {
                    Util.t(ctx, "Please fill translate conversion types");
                    return;
                }
                if (targetTrans.equalsIgnoreCase(srcTrans)) {
                    Util.t(ctx, "Select different transtation types.");
                    return;
                }

                new NRequestor.RequestBuilder(ctx)
                        .setReqType(Request.Method.POST)
                        .setUrl(NetworkConst.TRANS_URL + "?key=" + KEY + "&q=" + txtToTrans + "&target=" + targetTrans + "&source=" + srcTrans)
                        .setListener(TranslateFragment.this)
                        .setReqVolleyType(NetworkConst.VolleyReq.STRING)
                        .setReqTag(NetworkConst.ReqTag.TRANS)
                        .build()
                        .sendRequest();
                showProgress("Translating... ");
                break;
        }
    }

}
