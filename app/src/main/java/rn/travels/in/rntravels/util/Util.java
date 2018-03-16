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

    public static ArrayList<PackageVO> getDummyList() {
        ArrayList<PackageVO> list = new ArrayList<>();

        PackageVO packageVO = new PackageVO();
        packageVO.setHeading("Sydney");
        packageVO.setSubHeading("10 March - 15 March");

        ArrayList<DayVO> dayList = new ArrayList<>();
        DayVO dayVO = new DayVO();
        dayVO.setTitle("Day 1");
        ArrayList<String> daySnippetList = new ArrayList<>();
        daySnippetList.add("10 AM to 12PM will go Opera house");
        daySnippetList.add("12 PM to 1PM will go XYZ");
        daySnippetList.add("1 PM to 2PM will have lunch at ABC restaurent");
        dayVO.setDaySnippetList(daySnippetList);
        dayList.add(dayVO);

        DayVO dayVO1 = new DayVO();
        dayVO1.setTitle("Day 2");
        dayVO1.setDaySnippetList(daySnippetList);
        dayList.add(dayVO1);
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
        icon[5] = R.drawable.ic_logout;

        for (int i = 0; i < titleList.size(); i++) {
            list.add(new DrawerItemVO(titleList.get(i), icon[i]));
        }

        return list;

    }

    public static int getFragIdForDrawerItem(int itemId) {

        switch (itemId) {
            case  R.drawable.trip:
                return Appconst.FragmentId.DASHBOARD;
            case R.drawable.ic_contact_info:
                return Appconst.FragmentId.HELPLINE_FRAG;
            case R.drawable.ic_alert_blue:
                return Appconst.FragmentId.HELPLINE_FRAG;
            case R.drawable.ic_profile:
            case R.drawable.ic_logout:
            case R.drawable.ic_password:
            default:
                return -1;

        }
    }
}
