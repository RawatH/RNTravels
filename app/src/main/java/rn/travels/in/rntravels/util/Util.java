package rn.travels.in.rntravels.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by demo on 16/02/18.
 */

public class Util {

    public static void t(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }
}
