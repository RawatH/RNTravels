package rn.travels.in.rntravels.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.ui.fragment.BaseFragment;
import rn.travels.in.rntravels.ui.fragment.PkgDetailFragment;
import rn.travels.in.rntravels.ui.fragment.SplashFragment;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.FragmentFactory;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 16/02/18.
 */

public class RootActivity extends AppCompatActivity implements BaseFragment.FragListener {

    private BaseFragment loadedFragment;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        loadFragment(Appconst.FragmentId.SPLASH, null, null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadFragment(Appconst.FragmentId.LOGIN, null, null);
            }
        }, 2000);
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
        if (fragment != null) {
            loadedFragment = fragment;
            setupToolbar();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
        }

    }

    private void setupToolbar() {
        if (loadedFragment instanceof SplashFragment) {
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
        }
        toolbar.setTitle(loadedFragment.getTitle());
    }


    @Override
    public void toggleDrawerLock(boolean lockState) {
        int lockMode = lockState == true ? DrawerLayout.LOCK_MODE_LOCKED_CLOSED : DrawerLayout.LOCK_MODE_UNLOCKED;
        mDrawerLayout.setDrawerLockMode(lockMode);
    }

    @Override
    public void setupDrawerMenu() {
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(loadedFragment instanceof PkgDetailFragment) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.root_menu, menu);
            return true;
        }else{
            return false;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                mDrawerLayout.openDrawer(Gravity.END);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
