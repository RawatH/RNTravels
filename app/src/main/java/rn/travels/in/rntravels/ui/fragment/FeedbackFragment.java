package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rn.travels.in.rntravels.R;

/**
 * Created by demo on 17/03/18.
 */

public class FeedbackFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feedback, container,
                false);
        getDialog().setTitle("Feedback");
        // Do something else
        return rootView;
    }


}
