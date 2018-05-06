package rn.travels.in.rntravels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.DayVO;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 20/02/18.
 */

public class PkgDetailAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> headerList;
    private HashMap<String, DayVO> dataList;

    public PkgDetailAdapter(Context context, ArrayList<String> headerList, HashMap<String, DayVO> dataList) {
        this.context = context;
        this.headerList = headerList;
        this.dataList = dataList;
    }

    @Override
    public int getGroupCount() {
        return headerList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
//        return this.dataList.get(this.headerList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.dataList.get(this.headerList.get(groupPosition));//.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        holder.getExpandableImg().setImageResource(isExpanded ? R.drawable.ic_expand_less : R.drawable.ic_expand_more);
        holder.getGroupTextLabel().setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildHolder holder;
        final DayVO dayVO = (DayVO) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.pkg_detail_row_item, null);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        StringBuilder sb = new StringBuilder();
        for (String daySnippet : dayVO.getDaySnippetList()) {
            sb.append(daySnippet +"\n\n");
        }
        holder.getDaySnippet().setText(sb.toString());
        Util.loadImage(context, dayVO.getDayPhoto(), holder.getItineraryImg());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupHolder {
        private TextView groupTextLabel;
        //        TextView groupTextSubtitle;
        ImageView expandableImg;

        public GroupHolder(View view) {
            groupTextLabel = view.findViewById(R.id.day_header_title);
//            groupTextLabel = view.findViewById(R.id.day_header_subtitle);
            expandableImg = view.findViewById(R.id.expandImg);
        }

        public TextView getGroupTextLabel() {
            return groupTextLabel;
        }

        public ImageView getExpandableImg() {
            return expandableImg;
        }
    }

    static class ChildHolder {
        private TextView daySnippet;
        private ImageView itineraryImg;

        public ChildHolder(View view) {
            daySnippet = view.findViewById(R.id.itinerary_txt);
            itineraryImg = view.findViewById(R.id.itinerary_img);
        }

        public TextView getDaySnippet() {
            return daySnippet;
        }

        public ImageView getItineraryImg() {
            return itineraryImg;
        }
    }
}
