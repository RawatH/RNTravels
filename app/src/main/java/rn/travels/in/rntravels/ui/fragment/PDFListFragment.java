package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import rn.travels.in.rntravels.PackageManager;
import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.adapters.PDFAdapter;
import rn.travels.in.rntravels.models.PdfVO;
import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 04/05/18.
 */

public class PDFListFragment extends BackFragment implements PDFAdapter.PDFSelectionListener {

    private String title;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pdf_list, container, false);
        init(view);
        return view;
    }

    @Override
    public String getTitle() {
        Bundle bundle = getArguments();
        this.title = bundle.getString("title");
        return this.title;
    }

    private void init(View view) {
        TextView noUploadView = view.findViewById(R.id.no_record);
        RecyclerView pdfList = view.findViewById(R.id.pdfList);
        pdfList.setLayoutManager(new LinearLayoutManager(ctx));

        String fileType = getArguments().getString("fileType");
        ArrayList<PdfVO> dataList = PackageManager.getInstance().getSelectedPackage().getListByType(fileType);
        if (dataList != null) {
            pdfList.setVisibility(View.VISIBLE);
            noUploadView.setVisibility(View.GONE);
            PDFAdapter adapter = new PDFAdapter(dataList, this);
            pdfList.setAdapter(adapter);
        }else{
            noUploadView.setVisibility(View.VISIBLE);
            pdfList.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPdfSelected(PdfVO pdf) {
        Bundle bundle = new Bundle();
        bundle.putString("title", pdf.getFileTitle());
        bundle.putSerializable("obj" , pdf);
        activity.loadFragment(Appconst.FragmentId.PDF_FRG, bundle, null);
    }
}
