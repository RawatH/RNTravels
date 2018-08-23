package rn.travels.in.rntravels.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rn.travels.in.rntravels.R;

/**
 * Created by demo on 19/08/18.
 */

public class TranslateAdapter extends ArrayAdapter {
    private ArrayList<Pair<String, String>> dataList;
    private int itemLayout;

    private ListFilter listFilter = new ListFilter();
    private List<Pair<String,String>> dataListAllItems;

    public TranslateAdapter(Context context, int resource, ArrayList<Pair<String, String>> dataList) {
        super(context, resource, dataList);
        this.dataList = dataList;
        itemLayout = resource;
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Pair<String, String> getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(itemLayout, parent, false);
        }

        TextView countryName = view.findViewById(R.id.countryName);
        countryName.setText(getItem(position).first);
        TextView countryCode = view.findViewById(R.id.countryCode);
        countryCode.setText(getItem(position).second);

        return view;
    }


    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    public class ListFilter extends Filter {
        private Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (dataListAllItems == null) {
                synchronized (lock) {
                    dataListAllItems = new ArrayList<Pair<String, String>>(dataList);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = dataListAllItems;
                    results.count = dataListAllItems.size();
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();

                ArrayList<Pair<String,String>> matchValues = new ArrayList<Pair<String,String>>();

                for (Pair<String,String> dataItem : dataListAllItems) {
                    if (dataItem.first.toLowerCase().startsWith(searchStrLowerCase)) {
                        matchValues.add(dataItem);
                    }
                }

                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                dataList = (ArrayList<Pair<String,String>>) results.values;
            } else {
                dataList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }


}