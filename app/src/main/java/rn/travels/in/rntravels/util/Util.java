package rn.travels.in.rntravels.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.database.RNDatabase;
import rn.travels.in.rntravels.models.DayVO;
import rn.travels.in.rntravels.models.DrawerItemVO;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.ui.activity.RootActivity;

/**
 * Created by demo on 16/02/18.
 */

public class Util {

    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    public static Date getFormattedDate(String date) {
        Date formattedDate = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
            formattedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static ArrayList<String> getAllCurrCodes() {
        Set<Currency> toret = new HashSet<Currency>();
        Locale[] locs = Locale.getAvailableLocales();

        for (Locale loc : locs) {
            try {
                Currency currency = Currency.getInstance(loc);

                if (currency != null) {
                    toret.add(currency);
                }
            } catch (Exception exc) {
                // Locale not found
            }
        }

        ArrayList<String> currList = new ArrayList<>();
        Iterator<Currency> itr = toret.iterator();
        while (itr.hasNext()) {
            Currency curr = itr.next();
            currList.add(curr.getCurrencyCode());
        }

        Collections.sort(currList);

        return currList;
    }

    public static void loadImageToView(ImageView view, Context ctx, String imgName) {
        Glide.with(ctx).load(getImage(imgName, ctx)).into(view);
    }

    private static int getImage(String imageName, Context ctx) {

        int drawableResourceId = ctx.getResources().getIdentifier(imageName, "drawable", ctx.getPackageName());

        return drawableResourceId;
    }

    public static void t(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }


    public static ArrayList<DrawerItemVO> getDrawerList(Context ctx) {
        ArrayList<DrawerItemVO> list = new ArrayList<>();
        List<String> titleList = Arrays.asList(ctx.getResources().getStringArray(R.array.drawer_list));
        int icon[] = new int[titleList.size()];
        icon[0] = R.drawable.trip;
        icon[1] = R.drawable.ic_contact_info;
        icon[2] = R.drawable.ic_alert_blue;
        icon[3] = R.drawable.ic_password;
        icon[4] = R.drawable.ic_profile;
        icon[5] = R.drawable.ic_forex_rate;
        icon[6] = R.drawable.ic_g_translate;
        icon[7] = R.drawable.ic_logout;

        for (int i = 0; i < titleList.size(); i++) {
            list.add(new DrawerItemVO(titleList.get(i), icon[i]));
        }

        return list;

    }

    public static int getFragIdForDrawerItem(int itemId) {

        switch (itemId) {
            case R.drawable.trip:
                return Appconst.FragmentId.DASHBOARD;
            case R.drawable.ic_contact_info:
                return Appconst.FragmentId.RN_INFO_FRAG;
            case R.drawable.ic_alert_blue:
                return Appconst.FragmentId.NOTIFICATION_FRAG;
            case R.drawable.ic_password:
                return Appconst.FragmentId.PASSWORD_FRAG;
            case R.drawable.ic_profile:
                return Appconst.FragmentId.PROFILE_FRG;
            case R.drawable.ic_logout:
                return Appconst.FragmentId.LOGOUT;
            case R.drawable.ic_forex_rate:
                return Appconst.FragmentId.CONV_FRG;
            case R.drawable.ic_g_translate:
                return Appconst.FragmentId.TRANS;
            default:
                return -1;

        }
    }

    public static int getIdByPosition(int position) {
        if (position == 0) {
            return R.id.active;
        } else if (position == 1) {
            return R.id.past;
        } else {
            return R.id.following;
        }
    }

    public static String getUrlFor(int urlFor) {
        String url = NetworkConst.BASE_URL;
        switch (urlFor) {
            case NetworkConst.ReqTag.LOGIN:
                url += NetworkConst.Endpoints.LOGIN;
                break;
            case NetworkConst.ReqTag.REGISTER:
                url += NetworkConst.Endpoints.REGSITER;
                break;

            case NetworkConst.ReqTag.RN_CONTACT_DETAIL:
                url += NetworkConst.Endpoints.RN_CONTACT_DETAIL;
                break;

            case NetworkConst.ReqTag.PSWD_RESET:
                url += NetworkConst.Endpoints.PSWD_RESET;
                break;

            case NetworkConst.ReqTag.PKG_DETAIL:
                url += NetworkConst.Endpoints.GET_PKGS;
                break;

            case NetworkConst.ReqTag.PROFILE:
                url += NetworkConst.Endpoints.PROFILE;
                break;

            case NetworkConst.ReqTag.CONV:
                return NetworkConst.CONV_URL;

            case NetworkConst.ReqTag.FEEDBACK:
                url += NetworkConst.Endpoints.FEEDBACK;
                break;
            case NetworkConst.ReqTag.DEL_PKG:
                url += NetworkConst.Endpoints.DEL_PKG;
                break;
            case NetworkConst.ReqTag.FORGET_PASSWORD:
                url += NetworkConst.Endpoints.FORGET_PASSWORD;
                break;

        }

        return url;
    }

    public static String getTokenisedString(String data) {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(data, ",");
        int tokenCount = 0;
        while (st.hasMoreTokens()) {
            if (tokenCount == st.countTokens()) {
                sb.append(st.nextToken().trim());
            } else {
                sb.append(st.nextToken().trim() + "\n");
            }
            tokenCount++;
        }
        return sb.toString();
    }

    public static void loadImage(Context ctx, String url, ImageView imageView) {
        Glide.with(ctx)
                .load(url)
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView);
    }

//    public static String saveImage(Bitmap image) {
//        String savedImagePath = null;
//
//        String imageFileName = "JPEG_" + "FILE_NAME" + ".jpg";
//        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//                + "/YOUR_FOLDER_NAME");
//        boolean success = true;
//        if (!storageDir.exists()) {
//            success = storageDir.mkdirs();
//        }
//        if (success) {
//            File imageFile = new File(storageDir, imageFileName);
//            savedImagePath = imageFile.getAbsolutePath();
//            try {
//                OutputStream fOut = new FileOutputStream(imageFile);
//                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//                fOut.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            // Add the image to the system gallery
//            galleryAddPic(savedImagePath);
//            Toast.makeText(mContext, "IMAGE SAVED", Toast.LENGTH_LONG).show();
//        }
//        return savedImagePath;
//    }

    public static void createFileStructure(String pkgPath) {
        File file = new File(pkgPath);
        if (!file.exists()) {
            if (file.mkdir()) {
                File boarding = new File(pkgPath + File.separator + Appconst.Uploads.BOARDING);
                boarding.mkdir();
                File ticket = new File(pkgPath + File.separator + Appconst.Uploads.TICKET);
                ticket.mkdir();
                File voucher = new File(pkgPath + File.separator + Appconst.Uploads.VOUCHER);
                voucher.mkdir();
            }
        }
    }

    public static void createUserRootFolder(File file) {
        if (!file.exists()) {
            file.mkdir();
        }
    }


    public static boolean doesFileExists(String filePath) {
        return new File(filePath).exists();
    }


    private static void eraseStructure(File rootFile) {
        if (rootFile.isDirectory()) {
            for (File child : rootFile.listFiles()) {
                eraseStructure(child);
            }
        }
        rootFile.delete();
    }


    public static void deleteUserData(File f) {
        if (f.exists()) {
            eraseStructure(f);
        }
    }

    public static ArrayList<String> getDaySnippet(String itineary_details) {
        ArrayList<String> snippetList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(itineary_details, "@@@");
        while (st.hasMoreTokens()) {
            snippetList.add(st.nextToken());
        }
        return snippetList;
    }

    public static boolean hasConnectivity(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    public static ArrayList<PackageVO> getPackageFor(int pkgType, RNDatabase db) {

        ArrayList<PackageVO> filteredList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date currentDate = new Date();
        ArrayList<PackageVO> pkgList = (ArrayList<PackageVO>) db.getPackageDao().getAll();

        for (PackageVO pkgVO : pkgList) {

            try {
                Date pkgDate = sdf.parse(pkgVO.getTravelDate());
                switch (pkgType) {
                    case Appconst.PackageType.RECENT:
                        if (!pkgVO.isFollowingPkg() && (pkgDate.compareTo(currentDate) == 0 || pkgDate.compareTo(currentDate) > 0)) {
                            filteredList.add(pkgVO);
                        }
                        break;
                    case Appconst.PackageType.PAST:
                        if (!pkgVO.isFollowingPkg() && pkgDate.compareTo(currentDate) < 0) {
                            filteredList.add(pkgVO);
                        }
                        break;

                    case Appconst.PackageType.FOLLOWING:

                        if (pkgVO.isFollowingPkg() && (pkgDate.compareTo(currentDate) > 0 || pkgDate.compareTo(currentDate) == 0)) {
                            filteredList.add(pkgVO);
                        }
                        break;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return filteredList;
    }

    public static void initNotificationSetting(Context context){
        SharedPreferences pref = context.getSharedPreferences("notif_pref", 0);
        boolean val = pref.getBoolean("isNotifEnabled",true);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isNotifEnabled", val);
        editor.commit();
    }

    public static boolean isNotificationEnabled(Context context){
        SharedPreferences pref = context.getSharedPreferences("notif_pref", 0);
        return pref.getBoolean("isNotifEnabled",true);
    }

    public static void updateNotifSetting(Context context , boolean val){
        SharedPreferences pref = context.getSharedPreferences("notif_pref", 0);
        pref.getBoolean("isNotifEnabled",true);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isNotifEnabled", val);
        editor.commit();
    }

    public static Intent getNotificationIntent(Context context){
        Intent intent = new Intent(context, RootActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("notification", true);
        return intent;
    }
}
