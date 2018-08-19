package rn.travels.in.rntravels.network;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by demo on 16/02/18.
 */

public class NetworkConst {

    public static final String BASE_URL = "http://qzipsolutions.com/travel/api";
    public static final String CONV_URL = "http://free.currencyconverterapi.com/api/v5/convert";
    public static final String TRANS_LANG_URL = "https://translation.googleapis.com/language/translate/v2/languages";
    public static final String TRANS_URL = "https://translation.googleapis.com/language/translate/v2";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({Endpoints.RN_CONTACT_DETAIL,
            Endpoints.GET_PKGS,
            Endpoints.REGSITER,
            Endpoints.PSWD_RESET,
            Endpoints.LOGIN,
            Endpoints.PROFILE,
            Endpoints.CONVERT,
            Endpoints.FEEDBACK,
            Endpoints.DEL_PKG,
            Endpoints.FORGET_PASSWORD,
            Endpoints.UPDATE_NOTIFICATION_KEY
    })
    public @interface Endpoints {
        String RN_CONTACT_DETAIL = "/getRnContactDetails";
        String GET_PKGS = "/getAllPackages";
        String REGSITER = "/registerClient";
        String PSWD_RESET = "/passwordReset";
        String LOGIN = "/loginClient";
        String PROFILE = "/getClientDetails";
        String CONVERT = "/convert";
        String FEEDBACK = "/addFeedBack";
        String DEL_PKG = "/deletePackage";
        String FORGET_PASSWORD = "/sendEmail";
        String UPDATE_NOTIFICATION_KEY = "/updateNotificationKey";
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RequestType.GET, RequestType.POST})
    public @interface RequestType {
        int GET = 0;
        int POST = 1;
    }

    //REQUEST TAGS

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ReqTag.LOGIN,
            ReqTag.REGISTER,
            ReqTag.RN_CONTACT_DETAIL,
            ReqTag.PKG_DETAIL,
            ReqTag.FOLLOWING_PKG_DETAIL,
            ReqTag.PSWD_RESET,
            ReqTag.PROFILE,
            ReqTag.DOWNLOAD,
            ReqTag.CONV,
            ReqTag.FEEDBACK,
            ReqTag.TRANS_LANG,
            ReqTag.TRANS,
            ReqTag.DEL_PKG,
            ReqTag.FORGET_PASSWORD,
            ReqTag.UPDATE_FCM_KEY
    })
    public @interface ReqTag {

        int LOGIN = 0;
        int REGISTER = 1;
        int RN_CONTACT_DETAIL = 2;
        int PKG_DETAIL = 3;
        int PSWD_RESET = 4;
        int PROFILE = 5;
        int DOWNLOAD = 6;
        int CONV = 7;
        int FEEDBACK = 8;
        int TRANS_LANG = 9;
        int TRANS = 10;
        int FOLLOWING_PKG_DETAIL = 11;
        int DEL_PKG = 12;
        int FORGET_PASSWORD = 13;
        int UPDATE_FCM_KEY = 14;
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({VolleyReq.STRING,
            VolleyReq.BYTE
    })
    public @interface VolleyReq {

        int STRING = 0;
        int BYTE = 1;
    }

}
