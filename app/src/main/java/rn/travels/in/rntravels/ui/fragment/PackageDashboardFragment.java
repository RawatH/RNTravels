package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
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
import rn.travels.in.rntravels.models.PushSettingVO;
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
    private String pkgIdToDelete;

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
        updateToken();

    }

    private void updateToken() {
        if (Util.hasConnectivity(ctx)) {
            showProgress("Initializing...");
            UserVO userVO = db.getUserDao().getLoggedUser();
            PushSettingVO pushSettingVO = db.getPushDao().getPushSetting();

            JSONObject paramObj = new JSONObject();
            try {
                paramObj.put("email", userVO.getUserId());
                paramObj.put("fcm_key", pushSettingVO.getPushRegToken());
                new NRequestor.RequestBuilder(ctx)
                        .setReqType(Request.Method.POST)
                        .setUrl(Util.getUrlFor(NetworkConst.ReqTag.UPDATE_FCM_KEY))
                        .setListener(this)
                        .setReqVolleyType(NetworkConst.VolleyReq.STRING)
                        .setReqParams(paramObj)
                        .setReqTag(NetworkConst.ReqTag.UPDATE_FCM_KEY)
                        .build()
                        .sendRequest();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            loadPackage();
        }
    }

    private void loadPackage() {
        UserVO userVO = db.getUserDao().getLoggedUser();

        if (Util.hasConnectivity(ctx)) {
            NRequestor nRequestor = NWReqUtility.getPackageReq(ctx, this, userVO.getUserId());

            if (nRequestor != null) {
                nRequestor.sendRequest();
                showProgress("Loading packages...");
            }
        } else {
            renderPackage(userVO.getUserId());
        }

    }

    private void renderPackage(String userId) {
        ArrayList<PackageVO> packageList = (ArrayList<PackageVO>) db.getPackageDao().getUserPackages(userId);
        packagePagerAdapter = new PackagePagerAdapter(getActivity(), packageList, this);
        pager.setAdapter(packagePagerAdapter);
        pager.addOnPageChangeListener(this);
    }

    @Override
    public String getTitle() {
        return "My Travel Desk";
    }

    @Override
    public void getFollowingPackages(String userName, String password) {
        JSONObject paramObj = new JSONObject();
        try {
            paramObj.put("email", userName);
            paramObj.put("password", password);
            new NRequestor.RequestBuilder(ctx)
                    .setReqType(Request.Method.POST)
                    .setUrl(Util.getUrlFor(NetworkConst.ReqTag.LOGIN))
                    .setListener(this)
                    .setReqVolleyType(NetworkConst.VolleyReq.STRING)
                    .setReqParams(paramObj)
                    .setReqTag(NetworkConst.ReqTag.LOGIN)
                    .build()
                    .sendRequest();
            showProgress("Authenticating... ");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void deletePackage(String packageId) {

        JSONObject paramObj = new JSONObject();
        try {
            pkgIdToDelete = packageId;
            paramObj.put("pkg_id", packageId);
            paramObj.put("usr_id", RNDatabase.getInstance(ctx).getUserDao().getLoggedUser().getUserId());
            showProgress("Deleting package. ");
            new NRequestor.RequestBuilder(ctx)
                    .setReqType(Request.Method.POST)
                    .setUrl(Util.getUrlFor(NetworkConst.ReqTag.DEL_PKG))
                    .setListener(this)
                    .setReqVolleyType(NetworkConst.VolleyReq.STRING)
                    .setReqParams(paramObj)
                    .setReqTag(NetworkConst.ReqTag.DEL_PKG)
                    .build()
                    .sendRequest();

        } catch (JSONException e) {
            e.printStackTrace();
        }
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

            case NetworkConst.ReqTag.UPDATE_FCM_KEY:
                loadPackage();
                break;
            case NetworkConst.ReqTag.DEL_PKG:
                RNDatabase.getInstance(ctx).getPackageDao().deletePkgByPkgId(pkgIdToDelete);
                File fileToDelete = new File(ctx.getFilesDir() + File.separator +
                        RNDatabase.getInstance(ctx).getUserDao().getLoggedUser().getUserId() +
                        File.separator + pkgIdToDelete);
                fileToDelete.delete();
                packagePagerAdapter.notifyDataSetChanged();
                break;
            case NetworkConst.ReqTag.LOGIN:
                if (!responseVO.isResponseValid()) {
                    Util.t(ctx, "User doesn't exists.");
                    return;
                }
                showProgress("Fetching packages...");
                UserVO followingUserVO = new UserVO(responseVO.getResponse());
                followingUserVO.setUserType(Appconst.UserType.FOLLOWED_USER);
                db.getUserDao().insert(followingUserVO);

                JSONObject paramObj = new JSONObject();
                try {
                    paramObj.put("user_id", followingUserVO.getUserId());
                    new NRequestor.RequestBuilder(ctx)
                            .setReqType(Request.Method.POST)
                            .setUrl(Util.getUrlFor(NetworkConst.ReqTag.PKG_DETAIL))
                            .setListener(this)
                            .setReqVolleyType(NetworkConst.VolleyReq.STRING)
                            .setReqParams(paramObj)
                            .setReqTag(NetworkConst.ReqTag.FOLLOWING_PKG_DETAIL)
                            .build()
                            .sendRequest();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case NetworkConst.ReqTag.PKG_DETAIL:
            case NetworkConst.ReqTag.FOLLOWING_PKG_DETAIL:
                dismissProgress();
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
                        if (!file.exists()) {
                            Util.createUserRootFolder(file);
                        }

                        //Push new packages in DB
                        JSONArray arr = responseVO.getResponseArr();
                        for (int idx = 0; idx < arr.length(); idx++) {
                            packageVO = new PackageVO(userVO.getUserId(), (JSONObject) responseVO.getResponseArr().get(idx));
                            if (responseVO.getRequestTag() == NetworkConst.ReqTag.FOLLOWING_PKG_DETAIL) {
                                packageVO.setFollowingPkg(true);
                            } else {
                                packageVO.setFollowingPkg(false);
                            }
                            PackageVO dbPkg = db.getPackageDao().getPkgById(packageVO.getPkgId());
                            if (dbPkg == null) {
                                db.getPackageDao().insert(packageVO);
                                Util.createFileStructure(file.getPath() + File.separator + packageVO.getPkgId());
                            } else {
                                //TODO : Update only if update is received
                            }

                        }
                    }

                    if (responseVO.getRequestTag() == NetworkConst.ReqTag.FOLLOWING_PKG_DETAIL) {
                        packagePagerAdapter.notifyDataSetChanged();
                    } else {
                        renderPackage(userVO.getUserId());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        dismissProgress();

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
