package rn.travels.in.rntravels.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.models.PdfVO;

/**
 * Created by demo on 04/05/18.
 */

public class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.PDFHolder> {
    private final PDFSelectionListener listener;
    private ArrayList<PdfVO> dataList;

    public PDFAdapter(ArrayList<PdfVO> dataList, PDFSelectionListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PDFAdapter.PDFHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PDFAdapter.PDFHolder vh = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_row_layout, parent, false);
        vh = new PDFAdapter.PDFHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PDFAdapter.PDFHolder holder, int position) {
        final PdfVO pdfVO = dataList.get(position);
        holder.getPdfName().setText(pdfVO.getFileTitle());
        holder.getPdfName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPdfSelected(pdfVO);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface PDFSelectionListener {
        void onPdfSelected(PdfVO pdf);
    }

    public static class PDFHolder extends RecyclerView.ViewHolder {

        private TextView pdfName;

        public PDFHolder(View view) {
            super(view);
            pdfName = view.findViewById(R.id.pdfText);

        }

        public TextView getPdfName() {
            return pdfName;
        }
    }
}
