package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 14/03/18.
 */

public class PackageOptionFragment extends BackFragment implements View.OnClickListener {

    private TextView pkgOptionTitle;
    private TextView travelId;
    private TextView feedback;
    private TextView itenary;
    private TextView tickets;
    private TextView helpline;
    private TextView emergency;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.package_option_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        pkgOptionTitle = view.findViewById(R.id.login);
        travelId = view.findViewById(R.id.signup);
        feedback = view.findViewById(R.id.feedback);
        itenary = view.findViewById(R.id.itenary);
        tickets = view.findViewById(R.id.tickets);
        helpline = view.findViewById(R.id.helpline);
        emergency = view.findViewById(R.id.emergency);

        itenary.setOnClickListener(this);
        tickets.setOnClickListener(this);
        helpline.setOnClickListener(this);
        emergency.setOnClickListener(this);

    }

    @Override
    public String getTitle() {
        return "Package Options";
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpline:
                activity.loadFragment(Appconst.FragmentId.HELPLINE_FRAG, null, null);
                break;
            case R.id.itenary:
                activity.loadFragment(Appconst.FragmentId.PKG_DETAIL, null, null);
                break;
            case R.id.tickets:
                activity.loadFragment(Appconst.FragmentId.TICKET_FRAG, null, null);
                break;
            case R.id.emergency:
                activity.loadFragment(Appconst.FragmentId.RN_INFO_FRAG, null, null);
                break;
            case R.id.feedback:
                Util.t(ctx, "Feedback");
                break;
        }
    }


}
