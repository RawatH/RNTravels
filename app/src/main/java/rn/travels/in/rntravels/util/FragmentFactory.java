package rn.travels.in.rntravels.util;

import android.os.Bundle;

import rn.travels.in.rntravels.PackageManager;
import rn.travels.in.rntravels.ui.fragment.BaseFragment;
import rn.travels.in.rntravels.ui.fragment.EmergencyFragment;
import rn.travels.in.rntravels.ui.fragment.HelplineFragment;
import rn.travels.in.rntravels.ui.fragment.MiscFragment;
import rn.travels.in.rntravels.ui.fragment.PackageDashboardFragment;
import rn.travels.in.rntravels.ui.fragment.LoginFragment;
import rn.travels.in.rntravels.ui.fragment.PackageOptionFragment;
import rn.travels.in.rntravels.ui.fragment.PkgDetailFragment;
import rn.travels.in.rntravels.ui.fragment.RegisterFragment;
import rn.travels.in.rntravels.ui.fragment.SplashFragment;
import rn.travels.in.rntravels.ui.fragment.TicketFragment;

/**
 * Created by demo on 17/02/18.
 */

public class FragmentFactory {

    private static FragmentFactory INSTANCE;

    private FragmentFactory() {
    }

    public static FragmentFactory getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new FragmentFactory();
        }

        return INSTANCE;

    }

    public BaseFragment getFrgById(int fragmentId, Bundle bundle) {

        BaseFragment fragment;
        switch (fragmentId) {
            case Appconst.FragmentId.LOGIN:
                fragment = new LoginFragment();
                break;
            case Appconst.FragmentId.SPLASH:
                fragment = new SplashFragment();
                break;
            case Appconst.FragmentId.REGISTER:
                fragment = new RegisterFragment();
                break;
            case Appconst.FragmentId.DASHBOARD:
                fragment = new PackageDashboardFragment();
                break;
            case Appconst.FragmentId.PKG_DETAIL:
                fragment = new PkgDetailFragment();
                bundle = new Bundle();
                bundle.putSerializable("pkgObj" , PackageManager.getInstance().getSelectedPackage());
                break;
            case Appconst.FragmentId.TICKET_FRAG:
                fragment = new TicketFragment();
                break;
            case Appconst.FragmentId.EMERGENCY_FRAG:
                fragment = new EmergencyFragment();
                break;
            case Appconst.FragmentId.HELPLINE_FRAG:
                fragment = new HelplineFragment();
                break;
            case Appconst.FragmentId.MISC_FRAG:
                fragment = new MiscFragment();
                break;
            case Appconst.FragmentId.PKG_OPTION_FRAG:
                fragment = new PackageOptionFragment();
                break;
            default:
                fragment = null;
                break;

        }

        if(fragment != null){
            fragment.setFragId(fragmentId);
        }

        if (fragment != null && bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }


}
