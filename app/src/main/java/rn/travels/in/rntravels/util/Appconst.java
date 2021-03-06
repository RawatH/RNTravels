package rn.travels.in.rntravels.util;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import com.facebook.AccessToken;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Currency;

/**
 * Created by demo on 17/02/18.
 */

public class Appconst {

    public static AccessToken FB_TOKEN;

    //FRAGMENTS TAG

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FragmentId.SPLASH,
            FragmentId.LOGIN,
            FragmentId.REGISTER,
            FragmentId.DASHBOARD,
            FragmentId.ITINEARY_DETAIL_FRAG,
            FragmentId.TICKET_FRAG,
            FragmentId.RN_INFO_FRAG,
            FragmentId.MISC_FRAG,
            FragmentId.HELPLINE_FRAG,
            FragmentId.PKG_OPTION_FRAG,
            FragmentId.NOTIFICATION_FRAG,
            FragmentId.PASSWORD_FRAG,
            FragmentId.PROFILE_FRG,
            FragmentId.TOUR_HELP_FRG,
            FragmentId.FEEDBACK_FRG,
            FragmentId.PDF_FRG,
            FragmentId.LOGOUT,
            FragmentId.PDF_LIST,
            FragmentId.CONV_FRG,
            FragmentId.TRANS
    })
    public @interface FragmentId {
        int SPLASH = 0;
        int LOGIN = 1;
        int REGISTER = 2;
        int DASHBOARD = 3;
        int ITINEARY_DETAIL_FRAG = 4;
        int TICKET_FRAG = 5;
        int RN_INFO_FRAG = 6;
        int MISC_FRAG = 7;
        int HELPLINE_FRAG = 8;
        int PKG_OPTION_FRAG = 9;
        int NOTIFICATION_FRAG = 10;
        int PASSWORD_FRAG = 11;
        int PROFILE_FRG = 12;
        int TOUR_HELP_FRG = 14;
        int FEEDBACK_FRG = 15;
        int PDF_FRG = 16;
        int LOGOUT = 17;
        int PDF_LIST = 18;
        int CONV_FRG = 19;
        int TRANS = 20;
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


    //BACKSTACK TAG
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({BSTag.ROOT,
            BSTag.PKG_OPTION,
            BSTag.TICKET,
            BSTag.ITINEARY_DETAIL,
            BSTag.RN_INFO,
            BSTag.NOTIFICATION,
            BSTag.PROFILE,
            BSTag.TOUR_HELP,
            BSTag.FEEDBACK,
            BSTag.PDF,
            BSTag.PDF_LIST,
            BSTag.CONV,
            BSTag.TRANS
    })
    public @interface BSTag {
        String ROOT = "root";
        String PKG_OPTION = "pkg_option";
        String ITINEARY_DETAIL = "itinerary_detail";
        String TICKET = "ticket";
        String RN_INFO = "rn_info";
        String NOTIFICATION = "notification";
        String PASSWORD = "password";
        String PROFILE = "profile";
        String TOUR_HELP = "tour_help";
        String FEEDBACK = "feedback";
        String PDF = "pdf";
        String PDF_LIST = "pdf_list";
        String CONV = "conv";
        String TRANS = "trans";
    }


    @Retention(RetentionPolicy.SOURCE)
    @StringDef({Uploads.TICKET,
            Uploads.BOARDING,
            Uploads.VOUCHER

    })
    public @interface Uploads {
        //TicketVO
        String TICKET = "TICK";
        String BOARDING = "BOAR";
        String VOUCHER = "VOUC";
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PackageType.RECENT,
            PackageType.PAST,
            PackageType.FOLLOWING
    })
    public @interface PackageType {

        int RECENT = 0;
        int PAST = 1;
        int FOLLOWING = 2;

    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({UserType.LOGGED_USER,
            UserType.FOLLOWED_USER
    })
    public @interface UserType {

        int LOGGED_USER = 0;
        int FOLLOWED_USER = 1;

    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({MessageEvent.NOTIFICATION_RECEIVED
    })
    public @interface MessageEvent {
        String NOTIFICATION_RECEIVED = "notification";
    }

}
