package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import rn.travels.in.rntravels.PackageManager;
import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.adapters.PackagePagerAdapter;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 16/02/18.
 */

public class PackageDashboardFragment extends DrawerFragment implements ViewPager.OnPageChangeListener , PackagePagerAdapter.PackageSelectionListener {

    private ViewPager pager;
    private PackagePagerAdapter packagePagerAdapter;
    private BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.active:
                                pager.setCurrentItem(0);
                                break;

                            case R.id.past:
                                pager.setCurrentItem(1);
                                break;

                            case R.id.following:
                                pager.setCurrentItem(2);
                                break;
                        }
                        return true;
                    }
                });
        pager = view.findViewById(R.id.pkgPager);
        packagePagerAdapter = new PackagePagerAdapter(getContext(), Util.getDummyList(), this);
        pager.setAdapter(packagePagerAdapter);
        pager.addOnPageChangeListener(this);
    }

    @Override
    public String getTitle() {
        return "RN Desk";
    }


    @Override
    public void onPackageSelected(PackageVO packageVO) {
        PackageManager.getInstance().setSelectedPackage(packageVO);
        activity.loadFragment(Appconst.FragmentId.PKG_OPTION_FRAG, null, null);
    }

    //Page selection listener

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
     bottomNavigationView.setSelectedItemId(Util.getIdByPosition(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
