package rn.travels.in.rntravels.util;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by demo on 17/02/18.
 */

public class Appconst {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FragmentId.SPLASH, FragmentId.LOGIN, FragmentId.REGISTER,
            FragmentId.DASHBOARD, FragmentId.PKG_DETAIL,
            FragmentId.TICKET_FRAG, FragmentId.RN_INFO_FRAG,
            FragmentId.MISC_FRAG, FragmentId.HELPLINE_FRAG,
            FragmentId.PKG_OPTION_FRAG})
    public @interface FragmentId {
        int SPLASH = 0;
        int LOGIN = 1;
        int REGISTER = 2;
        int DASHBOARD = 3;
        int PKG_DETAIL = 4;
        int TICKET_FRAG = 5;
        int RN_INFO_FRAG = 6;
        int MISC_FRAG = 7;
        int HELPLINE_FRAG = 8;
        int PKG_OPTION_FRAG = 9;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({JKey.NAME,
            JKey.FLIGHT_DETAIL,
            JKey.TICKET_NUM,
            JKey.AIRLINE,
            JKey.ENDORSEMENT,
            JKey.STATUS
    })
    public @interface JKey {
        //TicketVO
        String NAME = "name";
        String FLIGHT_DETAIL = "flightDetail";
        String TICKET_NUM = "ticketNumber";
        String AIRLINE = "airline";
        String ENDORSEMENT = "endorsments";
        String STATUS = "status";

        //AgentVO
        String ADDRESS = "address";
        String EMAIL = "email";
        String PHONE = "phone";
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({BSTag.ROOT, BSTag.PKG_OPTION, BSTag.TICKET, BSTag.PKG_DETAIL , BSTag.RN_INFO
    })
    public @interface BSTag {
        String ROOT = "root";
        String PKG_OPTION = "pkg_option";
        String PKG_DETAIL = "pkg_detail";
        String TICKET = "ticket";
        String RN_INFO = "rn_info";
    }

}
