package rn.travels.in.rntravels.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.PackageVO;

/**
 * Created by hrawat on 19-02-2018.
 */

public class PackagePagerAdapter extends PagerAdapter implements PackageAdapter.PagerCommunicator {
    private Context context;
    private ArrayList<PackageVO> packageList;
    private LayoutInflater layoutInflater;
    private ArrayList<String> headerList;
    private PackageSelectionListener listener;

    public PackagePagerAdapter(Context context, ArrayList<PackageVO> dataList , PackageSelectionListener listener ) {
        this.context = context;
        this.packageList = dataList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        prepareDataSet();
        this.listener = listener;
    }

    private void prepareDataSet() {
        headerList = new ArrayList<>();
        headerList.add("On Going");
        headerList.add("Visited");
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

    private void init(View view, int position) {
        RecyclerView packageList = view.findViewById(R.id.catalogList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        packageList.setLayoutManager(layoutManager);
        PackageAdapter adapter = new PackageAdapter(this.packageList, context ,this);
        packageList.setAdapter(adapter);
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
        container.removeView((CardView) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        title = headerList.get(position);
        return title;
    }

    @Override
    public void onPackageSelected(PackageVO packageVO) {
       listener.onPackageSelected(packageVO);
    }

    public interface PackageSelectionListener {
         void onPackageSelected(PackageVO packageVO);
    }
}
