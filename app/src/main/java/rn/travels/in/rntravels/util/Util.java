package rn.travels.in.rntravels.util;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.DayVO;
import rn.travels.in.rntravels.models.DrawerItemVO;
import rn.travels.in.rntravels.models.PackageVO;

/**
 * Created by demo on 16/02/18.
 */

public class Util {

    public static void t(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static ArrayList<PackageVO> getActiveDummyList() {
        ArrayList<PackageVO> list = new ArrayList<>();

        PackageVO packageVO = new PackageVO();
        packageVO.setHeading("Sydney (Australia)");
        packageVO.setBannerImage("sydney");
        packageVO.setSubHeading("10 March - 15 March");

        ArrayList<DayVO> dayList = new ArrayList<>();
        DayVO dayVO = new DayVO();
        dayVO.setTitle("A day out at Opera House");
        ArrayList<String> daySnippetList = new ArrayList<>();
        daySnippetList.add("10 AM to 12 PM : Opera house");
        daySnippetList.add("12 PM to 01 PM : Bondi beach");
        daySnippetList.add("01 PM to 02 PM : Lunch at Bondi beach.");
        dayVO.setDaySnippetList(daySnippetList);
        dayList.add(dayVO);

        packageVO.setDayList(dayList);


        list.add(packageVO);

        return list;
    }

    public static ArrayList<PackageVO> getPastDummyList() {
        ArrayList<PackageVO> list = new ArrayList<>();

        PackageVO packageVO = new PackageVO();
        packageVO.setBannerImage("kashmir");
        packageVO.setHeading("Kashmir (India)");
        packageVO.setSubHeading("10 Jan - 15 Jan");

        ArrayList<DayVO> dayList = new ArrayList<>();
        DayVO dayVO = new DayVO();
        dayVO.setTitle("Enjoy Dal Lake");
        ArrayList<String> daySnippetList = new ArrayList<>();
        daySnippetList.add("10 AM to 12PM : Dal lake");
        daySnippetList.add("01 PM to 02PM : Lunch at KAWA restaurent");
        dayVO.setDaySnippetList(daySnippetList);
        dayList.add(dayVO);

        packageVO.setDayList(dayList);


        list.add(packageVO);

        return list;
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
            case R.drawable.ic_g_translate:
            case R.drawable.ic_forex_rate:
            case R.drawable.ic_logout:
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
}
