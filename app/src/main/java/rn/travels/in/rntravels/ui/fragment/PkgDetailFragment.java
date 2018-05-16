package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;

import rn.travels.in.rntravels.PackageManager;
import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.adapters.PkgDetailAdapter;
import rn.travels.in.rntravels.models.DayVO;
import rn.travels.in.rntravels.models.ItineraryVO;
import rn.travels.in.rntravels.models.PackageVO;

/**
 * Created by demo on 19/02/18.
 */

public class PkgDetailFragment extends BackFragment {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private ArrayList<DayVO> headerList;
    private HashMap<String, DayVO> dataList;
    private PackageVO packageVO;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        packageVO = PackageManager.getInstance().getSelectedPackage();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pkg_detail, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        expListView =  view.findViewById(R.id.detailLst);
        prepareListData();
        listAdapter = new PkgDetailAdapter(ctx, headerList, dataList);
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        ItineraryVO itineraryVO = PackageManager.getInstance().getSelectedPackage().getItinerary();

        headerList = new ArrayList<>();
        dataList = new HashMap<>();

        for (DayVO dayVO : itineraryVO.getDaysList()) {
            headerList.add(dayVO);
            dataList.put(dayVO.getTitle() ,dayVO);

        }
    }

    @Override
    public String getTitle() {
        return "Itinerary";
    }
}
