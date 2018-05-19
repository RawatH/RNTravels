package rn.travels.in.rntravels.util;

import android.os.Bundle;

import java.util.Currency;
import java.util.Locale;

import rn.travels.in.rntravels.PackageManager;
import rn.travels.in.rntravels.ui.fragment.BaseFragment;
import rn.travels.in.rntravels.ui.fragment.ConversionFragment;
import rn.travels.in.rntravels.ui.fragment.NotificationFragment;
import rn.travels.in.rntravels.ui.fragment.PDFFragment;
import rn.travels.in.rntravels.ui.fragment.PDFListFragment;
import rn.travels.in.rntravels.ui.fragment.PasswordFragment;
import rn.travels.in.rntravels.ui.fragment.ProfileFragment;
import rn.travels.in.rntravels.ui.fragment.RNInfoFragment;
import rn.travels.in.rntravels.ui.fragment.HelplineFragment;
import rn.travels.in.rntravels.ui.fragment.MiscFragment;
import rn.travels.in.rntravels.ui.fragment.PackageDashboardFragment;
import rn.travels.in.rntravels.ui.fragment.LoginFragment;
import rn.travels.in.rntravels.ui.fragment.PackageOptionFragment;
import rn.travels.in.rntravels.ui.fragment.PkgDetailFragment;
import rn.travels.in.rntravels.ui.fragment.RegisterFragment;
import rn.travels.in.rntravels.ui.fragment.SplashFragment;
import rn.travels.in.rntravels.ui.fragment.TicketFragment;
import rn.travels.in.rntravels.ui.fragment.TourHelpContactFragment;

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
                fragment.setBackStackTag(Appconst.BSTag.ROOT);
                break;
            case Appconst.FragmentId.ITINEARY_DETAIL_FRAG:
                fragment = new PkgDetailFragment();
                fragment.setBackStackTag(Appconst.BSTag.ITINEARY_DETAIL);
                break;
            case Appconst.FragmentId.TICKET_FRAG:
                fragment = new TicketFragment();
                fragment.setBackStackTag(Appconst.BSTag.TICKET);
                break;
            case Appconst.FragmentId.RN_INFO_FRAG:
                fragment = new RNInfoFragment();
                fragment.setBackStackTag(Appconst.BSTag.RN_INFO);
                break;
            case Appconst.FragmentId.HELPLINE_FRAG:
                fragment = new HelplineFragment();
                break;
            case Appconst.FragmentId.MISC_FRAG:
                fragment = new MiscFragment();
                break;
            case Appconst.FragmentId.PKG_OPTION_FRAG:
                fragment = new PackageOptionFragment();
                fragment.setBackStackTag(Appconst.BSTag.PKG_OPTION);
                break;
            case Appconst.FragmentId.NOTIFICATION_FRAG:
                fragment = new NotificationFragment();
                fragment.setBackStackTag(Appconst.BSTag.NOTIFICATION);
                break;
            case Appconst.FragmentId.PASSWORD_FRAG:
                fragment = new PasswordFragment();
                fragment.setBackStackTag(Appconst.BSTag.PASSWORD);
                break;
            case Appconst.FragmentId.PROFILE_FRG:
                fragment = new ProfileFragment();
                fragment.setBackStackTag(Appconst.BSTag.PROFILE);
                break;
            case Appconst.FragmentId.TOUR_HELP_FRG:
                fragment = new TourHelpContactFragment();
                fragment.setBackStackTag(Appconst.BSTag.TOUR_HELP);
                break;
            case Appconst.FragmentId.PDF_FRG:
                fragment = new PDFFragment();
                fragment.setBackStackTag(Appconst.BSTag.PDF);
                break;
            case Appconst.FragmentId.PDF_LIST:
                fragment = new PDFListFragment();
                fragment.setBackStackTag(Appconst.BSTag.PDF_LIST);
                break;
            case Appconst.FragmentId.CONV_FRG:
                fragment = new ConversionFragment();
                fragment.setBackStackTag(Appconst.BSTag.CONV);
                break;
            default:
                fragment = null;
                break;

        }

        if (fragment != null) {
            fragment.setFragId(fragmentId);
        }

        if (fragment != null && bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }


}
