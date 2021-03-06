package rn.travels.in.rntravels.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.io.File;

import rn.travels.in.rntravels.PackageManager;
import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.database.RNDatabase;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 14/03/18.
 */

public class PackageOptionFragment extends BackFragment implements View.OnClickListener {

    private TextView travelId;
    private Button feedback;
    private Button itenary;
    private Button tickets;
    private Button helpline;
    private Button emergency;
    private Button boardingPass;
    private Button travelVoucher;
    private PackageVO selectedPkgVO;

    CallbackManager callbackManager;
    ShareDialog shareDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.package_option_fragment, container, false);
        selectedPkgVO = PackageManager.getInstance().getSelectedPackage();
        init(view);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
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
                if (PackageManager.getInstance().getSelectedPackage().getItinerary() != null) {
                    activity.loadFragment(Appconst.FragmentId.ITINEARY_DETAIL_FRAG, null, null);
                } else {
                    Util.t(ctx, "Itineary not uploaded.");
                }
                break;
            case R.id.tickets:
                bundle.putString("title", "Tickets");
                bundle.putString("fileType", Appconst.Uploads.TICKET);
                activity.loadFragment(Appconst.FragmentId.PDF_LIST, bundle, null);
                break;
            case R.id.travel_voucher:
                bundle.putString("title", "Vouchers");
                bundle.putString("fileType", Appconst.Uploads.VOUCHER);
                activity.loadFragment(Appconst.FragmentId.PDF_LIST, bundle, null);
                break;
            case R.id.boardingPass:
                bundle.putString("title", "Boarding Pass");
                bundle.putString("fileType", Appconst.Uploads.BOARDING);
                activity.loadFragment(Appconst.FragmentId.PDF_LIST, bundle, null);
                break;
            case R.id.emergency:
                activity.loadFragment(Appconst.FragmentId.TOUR_HELP_FRG, null, null);
                break;
            case R.id.feedback:

                if (RNDatabase.getInstance(ctx).getUserDao().getLoggedUser().isFBUser()) {
                    if (ShareDialog.canShow(ShareLinkContent.class)) {
                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setQuote("@Rntoursandtravels1")
                                .setContentUrl(Uri.parse("https://www.facebook.com/pg/RN-Travels-222764434405389/posts"))
                                .build();

                        shareDialog.show(linkContent);
                    }
                } else {
                    FeedbackFragment feedbackFragment = new FeedbackFragment();
                    feedbackFragment.show(getActivity().getSupportFragmentManager(), "feedback");
                }
                break;

        }
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
