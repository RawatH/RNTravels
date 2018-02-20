package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.adapters.PackagePagerAdapter;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 16/02/18.
 */

public class PackageDashboardFragment extends BaseFragment implements PackagePagerAdapter.PackageSelectionListener {

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

        packagePagerAdapter = new PackagePagerAdapter(getContext(), Util.getDummyList() , this);
        pager.setAdapter(packagePagerAdapter);
    }

    @Override
    public String getTitle() {
        return "Packages";
    }


    @Override
    public void onPackageSelected(PackageVO packageVO) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("pkgObj" , packageVO);
        activity.loadFragment(Appconst.FragmentId.PKG_DETAIL , bundle , null);
    }
}
