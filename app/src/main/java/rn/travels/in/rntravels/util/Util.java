package rn.travels.in.rntravels.util;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import rn.travels.in.rntravels.models.DayVO;
import rn.travels.in.rntravels.models.PackageVO;

/**
 * Created by demo on 16/02/18.
 */

public class Util {

    public static void t(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static ArrayList<PackageVO> getDummyList(){
        ArrayList<PackageVO> list = new ArrayList<>();

        PackageVO packageVO = new PackageVO();
        packageVO.setHeading("Heading");
        packageVO.setSubHeading("Sub - Heading");

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



        PackageVO packageVO2 = new PackageVO();
        packageVO2.setHeading("Heading");
        packageVO.setSubHeading("Sub - Heading");
        packageVO2.setDayList(dayList);

        PackageVO packageVO3 = new PackageVO();
        packageVO3.setHeading("Heading");
        packageVO.setSubHeading("Sub- Heading");
        packageVO3.setDayList(dayList);

        list.add(packageVO);
        list.add(packageVO2);
        list.add(packageVO3);
        return list;
    }
}
