package rn.travels.in.rntravels.ui.fragment;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 21/03/18.
 */

public class PDFFragment extends BackFragment {
    private PDFView pdfView;
    private String title;
    private String pdfFileName;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pdf, container, false);
        setBackStackTag(Appconst.BSTag.PDF);
        pdfView = view.findViewById(R.id.pdfView);

        AssetManager assetManager = getActivity().getAssets();

        try {
            pdfView.fromStream(assetManager.open(pdfFileName))
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)

                    .load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public String getTitle() {
        Bundle bundle = getArguments();
        this.title = bundle.getString("title");
        this.pdfFileName = bundle.getString("pdfName");
        return this.title;
    }
}
