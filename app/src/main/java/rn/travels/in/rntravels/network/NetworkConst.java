package rn.travels.in.rntravels.network;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by demo on 16/02/18.
 */

public class NetworkConst {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RequestType.GET, RequestType.POST})
    public @interface RequestType {
        int GET = 0;
        int POST = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RequestTag.REG, RequestTag.PKG_DETAILS})
    public @interface RequestTag {
        int REG = 0;
        int PKG_DETAILS = 1;
    }
}
