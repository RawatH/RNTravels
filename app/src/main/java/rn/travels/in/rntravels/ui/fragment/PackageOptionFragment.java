package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import rn.travels.in.rntravels.PackageManager;
import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 14/03/18.
 */

public class PackageOptionFragment extends BackFragment implements View.OnClickListener {

    private TextView pkgOptionTitle;
    private TextView pkgoptionSubTitle;
    private TextView travelId;
    private Button feedback;
    private Button itenary;
    private Button tickets;
    private Button helpline;
    private Button emergency;
    private Button boardingPass;
    private Button travelVoucher;
    private PackageVO selectedPkgVO;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.package_option_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        travelId = view.findViewById(R.id.signup);
        feedback = view.findViewById(R.id.feedback);
        itenary = view.findViewById(R.id.itenary);
        tickets = view.findViewById(R.id.tickets);
        helpline = view.findViewById(R.id.helpline);
        emergency = view.findViewById(R.id.emergency);
        boardingPass = view.findViewById(R.id.boardingPass);
        travelVoucher = view.findViewById(R.id.travel_voucher);

        itenary.setOnClickListener(this);
        tickets.setOnClickListener(this);
        helpline.setOnClickListener(this);
        emergency.setOnClickListener(this);
        feedback.setOnClickListener(this);
        boardingPass.setOnClickListener(this);
        travelVoucher.setOnClickListener(this);

    }

    @Override
    public String getTitle() {
        selectedPkgVO = PackageManager.getInstance().getSelectedPackage();
        return selectedPkgVO.getHeading();
    }

    @Override
    public String getSubTitle() {
        return selectedPkgVO.getSubHeading();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.helpline:
                activity.loadFragment(Appconst.FragmentId.RN_INFO_FRAG, null, null);
                break;
            case R.id.itenary:
                activity.loadFragment(Appconst.FragmentId.ITINEARY_DETAIL_FRAG, null, null);
                break;
            case R.id.tickets:
            case R.id.travel_voucher:
                bundle.putString("title","Ticket Details");
                bundle.putString("pdfName","ticket_detail.pdf");
                activity.loadFragment(Appconst.FragmentId.PDF_FRG, bundle, null);
                break;
            case R.id.emergency:
                activity.loadFragment(Appconst.FragmentId.TOUR_HELP_FRG, null, null);
                break;
            case R.id.feedback:
                FeedbackFragment feedbackFragment = new FeedbackFragment();
                feedbackFragment.show(getActivity().getSupportFragmentManager(), "feedback");
                break;
            case R.id.boardingPass:
                bundle.putString("title","Boarding Pass");
                bundle.putString("pdfName","boarding_pass.pdf");
                activity.loadFragment(Appconst.FragmentId.PDF_FRG, bundle, null);
                break;
        }
    }


}
