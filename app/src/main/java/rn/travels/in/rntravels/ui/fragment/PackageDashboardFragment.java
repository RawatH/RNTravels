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

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import rn.travels.in.rntravels.PackageManager;
import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.adapters.PackagePagerAdapter;
import rn.travels.in.rntravels.database.RNDatabase;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.models.UserVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.NWReqUtility;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 16/02/18.
 */

public class PackageDashboardFragment extends DrawerFragment implements ViewPager.OnPageChangeListener, PackagePagerAdapter.PackageSelectionListener {

    private ViewPager pager;
    private PackagePagerAdapter packagePagerAdapter;
    private BottomNavigationView bottomNavigationView;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
            init(view);
            rootView = view;
        }
        return rootView;
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
        loadPackage();
    }

    private void loadPackage() {
        UserVO userVO = db.getUserDao().getLoggedUser();
        if (Util.hasConnectivity(ctx)) {
            NRequestor nRequestor = NWReqUtility.getPackageReq(ctx, this, userVO.getUserId());

            if (nRequestor != null) {
                nRequestor.sendRequest();
                showProgress("Loading package...");
            }
        } else {
            renderPackage(userVO.getUserId());
        }

    }

    private void renderPackage(String userId) {
        ArrayList<PackageVO> packageList = (ArrayList<PackageVO>) db.getPackageDao().getUserPackages(userId);
        packagePagerAdapter = new PackagePagerAdapter(getContext(), packageList, this);
        pager.setAdapter(packagePagerAdapter);
        pager.addOnPageChangeListener(this);
    }

    @Override
    public String getTitle() {
        return "My Travel Desk";
    }


    @Override
    public void onPackageSelected(PackageVO packageVO) {
        PackageManager.getInstance().setSelectedPackage(packageVO);
        activity.loadFragment(Appconst.FragmentId.PKG_OPTION_FRAG, null, null);
    }

    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
        dismissProgress();
        switch (responseVO.getRequestTag()) {
            case NetworkConst.ReqTag.PKG_DETAIL:

                UserVO userVO = RNDatabase.getInstance(ctx).getUserDao().getLoggedUser();
                PackageVO packageVO;
                try {
                    if (responseVO.isResponseValid()) {
                        //Remove all packages from DB
//                    db.getPackageDao().deleteAllPackages();
                        File file = new File(ctx.getFilesDir() + File.separator + userVO.getUserId());

//                    //Clear old packages
//                    Util.deleteUserData(file);
                        //Create new structure
                        Util.createUserRootFolder(file);

                        //Push new packages in DB
                        JSONArray arr = responseVO.getResponseArr();
                        for (int idx = 0; idx < arr.length(); idx++) {
                            packageVO = new PackageVO(userVO.getUserId(), (JSONObject) responseVO.getResponseArr().get(idx));
                            PackageVO dbPkg = db.getPackageDao().getPkgById(packageVO.getPkgId());
                            if (dbPkg == null) {
                                db.getPackageDao().insert(packageVO);
                                Util.createFileStructure(file.getPath() + File.separator + packageVO.getPkgId());
                            } else {
                                //TODO : Update only if update is received
                            }

                        }
                    }

                    renderPackage(userVO.getUserId());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
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
