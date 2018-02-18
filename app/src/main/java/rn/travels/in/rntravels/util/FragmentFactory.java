package rn.travels.in.rntravels.util;

import android.os.Bundle;

import rn.travels.in.rntravels.ui.fragment.BaseFragment;
import rn.travels.in.rntravels.ui.fragment.DashboardFragment;
import rn.travels.in.rntravels.ui.fragment.LoginFragment;
import rn.travels.in.rntravels.ui.fragment.RegisterFragment;
import rn.travels.in.rntravels.ui.fragment.SplashFragment;

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
                fragment = new DashboardFragment();
                break;
            default:
                fragment = null;
                break;

        }

        if (fragment != null && bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }


}
