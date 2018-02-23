package rn.travels.in.rntravels.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.DrawerItemVO;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 21/02/18.
 */

public class DrawerListAdapter extends RecyclerView.Adapter<DrawerListAdapter.DrawerHolder> {

    private Context ctx;
    private ArrayList<DrawerItemVO> dataList;
    private DrawerListener listner;

    public DrawerListAdapter(Context ctx , DrawerListener listner) {
        this.ctx = ctx;
        dataList = Util.getDrawerList(ctx);
        this.listner = listner;
    }

    @Override
    public DrawerListAdapter.DrawerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_row_layout, parent, false);
        return new DrawerHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerListAdapter.DrawerHolder holder, int position) {
        final DrawerItemVO drawerItemVO = dataList.get(position);
        holder.title.setText(drawerItemVO.getText());
        holder.icon.setImageResource(drawerItemVO.getDrawable());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onDrawerItemSelected();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DrawerHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView icon;
        public LinearLayout parent;

        public DrawerHolder(View view) {
            super(view);
            title = view.findViewById(R.id.drawer_row_title);
            icon = view.findViewById(R.id.drawer_row_icon);
            parent = view.findViewById(R.id.drawer_row_parent);
        }
    }

    public interface DrawerListener {
        void onDrawerItemSelected(int fragId);
    }
}
