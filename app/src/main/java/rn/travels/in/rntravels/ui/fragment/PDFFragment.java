package rn.travels.in.rntravels.ui.fragment;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.IOException;

import rn.travels.in.rntravels.PackageManager;
import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.database.RNDatabase;
import rn.travels.in.rntravels.models.PdfVO;
import rn.travels.in.rntravels.models.ResponseVO;
import rn.travels.in.rntravels.models.UserVO;
import rn.travels.in.rntravels.network.NRequestor;
import rn.travels.in.rntravels.network.NetworkConst;
import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 21/03/18.
 */

public class PDFFragment extends BackFragment {
    private PDFView pdfView;
    private String title;
    private String pdfFile;
    private String filePath;
    private String fileName;
    private PdfVO pdf;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pdf, container, false);
        setBackStackTag(Appconst.BSTag.PDF);
        init(view);

        return view;
    }

    private void init(View view) {
        pdfView = view.findViewById(R.id.pdfView);
        Bundle bundle = getArguments();
        this.pdf = (PdfVO) bundle.getSerializable("obj");
        this.pdfFile = pdf.getFileUrl();
        UserVO userVO = RNDatabase.getInstance(ctx).getUserDao().getLoggedUser();
        this.filePath = ctx.getFilesDir() + File.separator + userVO.getUserId() + File.separator + PackageManager.getInstance().getSelectedPackage().getPkgId() + File.separator + pdf.getFileType();
        this.fileName = pdfFile.substring(pdfFile.lastIndexOf("/") + 1, pdfFile.length());
        if (Util.doesFileExists(filePath + File.separator + fileName)) {
            openPdf();
        } else {
            downloadPDF();
        }
    }

    @Override
    public String getTitle() {
        Bundle bundle = getArguments();
        this.title = bundle.getString("title");
        return this.title;
    }

    private void downloadPDF() {
        Bundle bundle = new Bundle();
        bundle.putString("dest", filePath);
        bundle.putString("fileName", fileName);
        new NRequestor.RequestBuilder(ctx)
                .setReqType(Request.Method.GET)
                .setUrl(pdfFile)
                .setListener(this)
                .setReqBundle(bundle)
                .setReqVolleyType(NetworkConst.VolleyReq.BYTE)
                .setReqTag(NetworkConst.ReqTag.DOWNLOAD)
                .build()
                .sendRequest();
        showProgress("Downloading...");

    }

    @Override
    public void onSuccessResponse(ResponseVO responseVO) {
        super.onSuccessResponse(responseVO);
        Toast.makeText(ctx, "Download complete.", Toast.LENGTH_LONG).show();
        openPdf();
        dismissProgress();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        dismissProgress();
    }

    private void openPdf() {
        pdfView.fromFile(new File(filePath + File.separator + fileName))
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .load();
    }
}
