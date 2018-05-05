package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import rn.travels.in.rntravels.PackageManager;
import rn.travels.in.rntravels.R;

/**
 * Created by demo on 18/03/18.
 */

public class TourHelpContactFragment extends BackFragment {

    private TextView tourContactView;
    private TextView tourCabContactView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        tourContactView = view.findViewById(R.id.tourContact);
        tourCabContactView = view.findViewById(R.id.tourCabContact);
        ArrayList<Pair<String,String>> contactList = PackageManager.getInstance().getSelectedPackage().getEmergencyContactList();

        for(int i = 0 ;i < contactList.size() ; i++){
            Pair<String,String> p = contactList.get(i);
            switch (i){
                case 0 :
                    tourContactView.setText(p.first + "    " + p.second);
                    break;
                case 1:
                    tourCabContactView.setText(p.first + "    " + p.second);
                    break;
            }
        }
    }

    @Override
    public String getTitle() {
        return "Emergency Contact";
    }
}
