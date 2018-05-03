package rn.travels.in.rntravels.network;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by demo on 16/02/18.
 */

public class NetworkConst {

    public static final String BASE_URL = "http://www.hashcode.co.in/restro/api";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({Endpoints.RN_CONTACT_DETAIL,
            Endpoints.GET_PKGS,
            Endpoints.REGSITER,
            Endpoints.PSWD_RESET,
            Endpoints.LOGIN,
            Endpoints.PROFILE})
    public @interface Endpoints {
        String RN_CONTACT_DETAIL = "/getRnContactDetails";
        String GET_PKGS = "/getAllPackages";
        String REGSITER = "/registerClient";
        String PSWD_RESET = "/passwordReset";
        String LOGIN = "/loginClient";
        String PROFILE = "/getClientDetails";
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
            ReqTag.PSWD_RESET,
            ReqTag.PROFILE,
            ReqTag.DOWNLOAD
    })
    public @interface ReqTag {

        int LOGIN = 0;
        int REGISTER = 1;
        int RN_CONTACT_DETAIL = 2;
        int PKG_DETAIL = 3;
        int PSWD_RESET = 4;
        int PROFILE = 5;
        int DOWNLOAD = 6;
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
