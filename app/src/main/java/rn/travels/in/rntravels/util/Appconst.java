package rn.travels.in.rntravels.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rn.travels.in.rntravels.network.NetworkConst;

/**
 * Created by demo on 17/02/18.
 */

public class Appconst {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FragmentId.SPLASH, FragmentId.LOGIN, FragmentId.REGISTER,
            FragmentId.DASHBOARD, FragmentId.PKG_DETAIL,
            FragmentId.TICKET_FRAG, FragmentId.EMERGENCY_FRAG,
            FragmentId.MISC_FRAG, FragmentId.HELPLINE_FRAG})
    public @interface FragmentId {
        int SPLASH = 0;
        int LOGIN = 1;
        int REGISTER = 2;
        int DASHBOARD = 3;
        int PKG_DETAIL = 4;
        int TICKET_FRAG = 5;
        int EMERGENCY_FRAG = 6;
        int MISC_FRAG = 7;
        int HELPLINE_FRAG = 8;
    }
}
