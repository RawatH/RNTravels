package rn.travels.in.rntravels.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.facebook.login.LoginManager;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.File;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.adapters.DrawerListAdapter;
import rn.travels.in.rntravels.database.RNDatabase;
import rn.travels.in.rntravels.models.UserVO;
import rn.travels.in.rntravels.ui.fragment.BackFragment;
import rn.travels.in.rntravels.ui.fragment.BaseFragment;
import rn.travels.in.rntravels.ui.fragment.DrawerFragment;
import rn.travels.in.rntravels.ui.fragment.NoToolbarFragment;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.FragmentFactory;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 16/02/18.
 */

public class RootActivity extends AppCompatActivity implements BaseFragment.FragListener, DrawerListAdapter.DrawerListener {

    private BaseFragment loadedFragment;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private DrawerListAdapter drawerListAdapter;
    private RecyclerView drawerList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
        FirebaseInstanceId.getInstance().getInstanceId();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);

        drawerList = findViewById(R.id.drawerList);

        drawerListAdapter = new DrawerListAdapter(this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        drawerList.setLayoutManager(mLayoutManager);
        drawerList.setItemAnimator(new DefaultItemAnimator());
        drawerList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        drawerList.setAdapter(drawerListAdapter);

        loadFragment(Appconst.FragmentId.SPLASH, null, null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserVO loggedUserVO = RNDatabase.getInstance(RootActivity.this).getUserDao().getLoggedUser();
                if (loggedUserVO == null) {
                    loadFragment(Appconst.FragmentId.LOGIN, null, null);
                } else {
                    loadFragment(Appconst.FragmentId.DASHBOARD, null, null);
                }
            }
        }, 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void loadFragment(int fragmentId, Bundle bundle, String tag) {

        BaseFragment fragment = FragmentFactory.getInstance().getFrgById(fragmentId, bundle);

        if (loadedFragment != null && loadedFragment.getFragId() == fragment.getFragId()) {
            closeDrawer();
            return;
        }

        if (fragment != null) {
            loadedFragment = fragment;
            setupToolbar();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (!(loadedFragment.getFragId() == Appconst.FragmentId.SPLASH)) {
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            }
            if (loadedFragment.getBackStackTag() == null) {
                fragmentTransaction.replace(R.id.container, fragment);
            } else {
                fragmentTransaction.replace(R.id.container, fragment, loadedFragment.getBackStackTag());
                fragmentTransaction.addToBackStack(loadedFragment.getBackStackTag());
            }

            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
        }

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            super.onBackPressed();
            FragmentManager.BackStackEntry backStackEntry = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1);
            loadedFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(backStackEntry.getName());
            setupToolbar();
        } else {
            finish();
        }

    }


    private void setupToolbar() {
        if (loadedFragment instanceof NoToolbarFragment) {
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
            if (loadedFragment instanceof BackFragment) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        }
        toolbar.setTitle(loadedFragment.getTitle());
        if (loadedFragment.getSubTitle() != null) {
            toolbar.setSubtitle(loadedFragment.getSubTitle());
        }
    }


    private void closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(Gravity.END)) {
            mDrawerLayout.closeDrawer(Gravity.END);
        }
    }

    @Override
    public void toggleDrawerLock(boolean lockState) {
        int lockMode = lockState == true ? DrawerLayout.LOCK_MODE_LOCKED_CLOSED : DrawerLayout.LOCK_MODE_UNLOCKED;
        mDrawerLayout.setDrawerLockMode(lockMode);
        closeDrawer();
    }

    @Override
    public void setupDrawerMenu() {
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (loadedFragment instanceof DrawerFragment) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.root_menu, menu);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                mDrawerLayout.openDrawer(Gravity.END);
                return true;

            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onDrawerItemSelected(int fragId) {
        if (fragId == -1) {
            Util.t(this, "Not yet implemented");
        } else {
            switch (fragId) {
                case Appconst.FragmentId.DASHBOARD:
                    getSupportFragmentManager().popBackStack(Appconst.BSTag.ROOT, 0);
                    loadedFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(Appconst.BSTag.ROOT);
                    setupToolbar();
                    break;
                case Appconst.FragmentId.LOGOUT:
                    UserVO userVO = RNDatabase.getInstance(this).getUserDao().getLoggedUser();
                    Util.deleteUserData(new File(getFilesDir()+File.separator+userVO.getUserId()));
                    RNDatabase.getInstance(this).getUserDao().deleteUser();
                    RNDatabase.getInstance(this).getPackageDao().deleteAllPackages();
                    loadFragment(Appconst.FragmentId.LOGIN, null, null);
                    if(userVO.isFBUser()){
                        LoginManager.getInstance().logOut();
                    }
                    break;

                default:
                    loadFragment(fragId, null, null);
                    break;
            }

        }
    }
}
