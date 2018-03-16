package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 22/02/18.
 */

public class TicketFragment extends BackFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        setBackStackTag(Appconst.BSTag.TICKET);
        return view;
    }

    @Override
    public String getTitle() {
        return "Ticket Details";
    }
}

