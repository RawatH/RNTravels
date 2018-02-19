package rn.travels.in.rntravels.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.PackageVO;

/**
 * Created by hrawat on 19-02-2018.
 */

public class PackageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<PackageVO> dataList;
    private Context context;

    public PackageListAdapter(ArrayList<PackageVO> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder vh = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_row_item, parent, false);
        vh = new PkgViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        PackageVO packageVO = (PackageVO) dataList.get(position);
        PkgViewHolder pkgViewHolder = (PkgViewHolder) holder;
        pkgViewHolder.getPkgNameView().setText(packageVO.getName());
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            pkgViewHolder.getPkgBanner().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.sydney));
        } else {
            pkgViewHolder.getPkgBanner().setBackground(context.getResources().getDrawable(R.drawable.sydney));
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class PkgViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout pkgBanner;
        private TextView pkgNameView;

        public PkgViewHolder(View view) {
            super(view);
            pkgBanner = (LinearLayout) view.findViewById(R.id.packageBanner);
            pkgNameView = (TextView) view.findViewById(R.id.packageName);
        }

        public LinearLayout getPkgBanner() {
            return pkgBanner;
        }

        public TextView getPkgNameView() {
            return pkgNameView;
        }
    }
}
