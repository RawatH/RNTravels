package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.adapters.PackagePagerAdapter;
import rn.travels.in.rntravels.models.PackageVO;

/**
 * Created by demo on 16/02/18.
 */

public class DashboardFragment extends BaseFragment {

    private ViewPager pager;
    private PackagePagerAdapter packagePagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        pager = (ViewPager) view.findViewById(R.id.pkgPager);

        packagePagerAdapter = new PackagePagerAdapter(getContext(), getDummyPkgList());
        pager.setAdapter(packagePagerAdapter);
    }

    private ArrayList<PackageVO> getDummyPkgList() {
        ArrayList<PackageVO> list = new ArrayList<>();
        PackageVO packageVO = new PackageVO();
        packageVO.setName("Sydney.");
        PackageVO packageVO2 = new PackageVO();
        packageVO2.setName("Thailand.");
        list.add(packageVO);
        list.add(packageVO2);
        return list;
    }

}
