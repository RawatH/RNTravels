package rn.travels.in.rntravels.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.database.RNDatabase;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by hrawat on 19-02-2018.
 */

public class PackageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<PackageVO> dataList;
    private Context context;
    private PagerCommunicator communicator;

    public PackageAdapter(ArrayList<PackageVO> dataList, Context context, PagerCommunicator communicator) {
        this.dataList = dataList;
        this.context = context;
        this.communicator = communicator;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final PackageVO packageVO = dataList.get(position);
        final PkgViewHolder pkgViewHolder = (PkgViewHolder) holder;
        pkgViewHolder.getPkgNameView().setText(packageVO.getHeading());
        int resID = context.getResources().getIdentifier(packageVO.getBannerImage(), "drawable", context.getPackageName());
        pkgViewHolder.getPkgBanner().setImageResource(resID);

        String bannerImg = packageVO.getBannerImage();
        if (bannerImg.startsWith("http")) {
            Glide.with(context)
                    .load(packageVO.getBannerImage())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bmp, GlideAnimation<? super Bitmap> glideAnimation) {
                            pkgViewHolder.getPkgBanner().setImageBitmap(bmp);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            String encodedBitmap = Base64.encodeToString(byteArray, Base64.DEFAULT);
                            packageVO.setBannerImage(encodedBitmap);
                            RNDatabase.getInstance(context).getPackageDao().update(packageVO);
                        }
                    });
        } else {
            byte[] bitmap = Base64.decode(bannerImg, Base64.DEFAULT);
            Glide.with(context)
                    .load(bitmap)
                    .asBitmap()
                    .into(pkgViewHolder.getPkgBanner());
        }


        pkgViewHolder.getPkgSubHeading().setText(packageVO.getTravelDate());
        pkgViewHolder.getPkgBanner().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.onPackageSelected(packageVO);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class PkgViewHolder extends RecyclerView.ViewHolder {

        private ImageView pkgBanner;
        private TextView pkgNameView;
        private TextView pkgSubHeading;

        public PkgViewHolder(View view) {
            super(view);
            pkgBanner = view.findViewById(R.id.packageBanner);
            pkgNameView = view.findViewById(R.id.packageName);
            pkgSubHeading = view.findViewById(R.id.packageDate);
        }

        public ImageView getPkgBanner() {
            return pkgBanner;
        }

        public TextView getPkgNameView() {
            return pkgNameView;
        }

        public TextView getPkgSubHeading() {
            return pkgSubHeading;
        }
    }

    public interface PagerCommunicator {
        void onPackageSelected(PackageVO packageVO);
    }
}
