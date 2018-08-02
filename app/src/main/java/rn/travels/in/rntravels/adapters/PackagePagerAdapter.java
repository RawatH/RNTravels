package rn.travels.in.rntravels.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.database.RNDatabase;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.models.UserVO;
import rn.travels.in.rntravels.ui.activity.RootActivity;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by hrawat on 19-02-2018.
 */

public class PackagePagerAdapter extends PagerAdapter implements PackageAdapter.PagerCommunicator {
    private Context context;
    private ArrayList<PackageVO> packageList;
    private ArrayList<String> headerList;
    private PackageSelectionListener listener;

    public PackagePagerAdapter(Context context, ArrayList<PackageVO> dataList, PackageSelectionListener listener) {
        this.context = context;
        this.packageList = dataList;
        prepareDataSet();
        this.listener = listener;
    }

    private void prepareDataSet() {
        headerList = new ArrayList<>();
        headerList.add("Active");
        headerList.add("Past");
        headerList.add("Following");
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pkg_display_layout, container, false);
        init(view, position);
        container.addView(view);
        view.setTag("View" + position);
        return view;
    }

    private void init(View view, final int position) {

        final RecyclerView list = view.findViewById(R.id.catalogList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        LinearLayout followingContainer = view.findViewById(R.id.followingContainer);
        TextView followingText = view.findViewById(R.id.followingText);
        Button followingBtn = view.findViewById(R.id.followingBtn);
        list.setLayoutManager(layoutManager);
        switch (position) {
            case Appconst.PackageType.RECENT:
                this.packageList = Util.getPackageFor(Appconst.PackageType.RECENT, RNDatabase.getInstance(context));
                break;
            case Appconst.PackageType.PAST:
                this.packageList = Util.getPackageFor(Appconst.PackageType.PAST, RNDatabase.getInstance(context));
                break;
            case Appconst.PackageType.FOLLOWING:
                this.packageList = Util.getPackageFor(Appconst.PackageType.FOLLOWING, RNDatabase.getInstance(context));
                break;
        }

        //FOLLOWING UI
        if (position == 2) {
            followingContainer.setVisibility(View.VISIBLE);
            if (RNDatabase.getInstance(context).getPackageDao().getFollowingPkg().size() == 0) {
                followingText.setText("Following nobody");
                followingBtn.setText("Follow");
                followingBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showFollowingDialog();
                    }
                });
            } else {
                followingText.setText("Following " + RNDatabase.getInstance(context).getUserDao().getFollowedUser().getUserEmail());
                followingBtn.setText("Stop Following");
                followingBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RNDatabase.getInstance(context).getPackageDao().removeFollowingPkg();
                        RNDatabase.getInstance(context).getUserDao().deleteFollowedUser();
                        notifyDataSetChanged();
                    }
                });
            }
        } else {
            followingContainer.setVisibility(View.GONE);
        }

        if (this.packageList == null || this.packageList.size() == 0) {
            list.setVisibility(View.GONE);
        } else {
            list.setVisibility(View.VISIBLE);
            PackageAdapter adapter = new PackageAdapter(this.packageList, context, this);
            list.setAdapter(adapter);
        }

    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    private void showFollowingDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((RootActivity) context).getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.follow_login, null);
        dialogBuilder.setView(dialogView);

        final EditText email = dialogView.findViewById(R.id.following_username);
        final EditText password = dialogView.findViewById(R.id.following_password);

        dialogBuilder.setTitle("Follow user");
        dialogBuilder.setMessage("Following user credentials.");
        dialogBuilder.setPositiveButton("Follow", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                UserVO loggedUser = RNDatabase.getInstance(context).getUserDao().getLoggedUser();

                if (TextUtils.isEmpty(email.getText().toString())) {
                    Util.t(context, "Please enter email id.");
                    return;
                }

                if (loggedUser.getUserEmail().trim().toLowerCase().equalsIgnoreCase(email.getText().toString().trim().toLowerCase())) {
                    Util.t(context, "You can't follow yourself.");
                    return;
                }

                if (TextUtils.isEmpty(password.getText().toString())) {
                    Util.t(context, "Please enter password.");
                    return;
                }

                listener.getFollowingPackages(email.getText().toString(), password.getText().toString());
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        dialogBuilder.setIcon(R.mipmap.ic_launcher);
        AlertDialog b = dialogBuilder.create();
        b.show();

    }

    @Override
    public int getCount() {
        return headerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ViewGroup) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        title = headerList.get(position);
        return title;
    }

    public void deletePackage(String packageId) {
        listener.deletePackage(packageId);
    }

    @Override
    public void onPackageSelected(PackageVO packageVO) {
        listener.onPackageSelected(packageVO);
    }


    public interface PackageSelectionListener {
        void onPackageSelected(PackageVO packageVO);

        void getFollowingPackages(String userName, String password);

        void deletePackage(String packageId);
    }
}
