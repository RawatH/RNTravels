package rn.travels.in.rntravels.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.github.barteksc.pdfviewer.PDFView;

import org.json.JSONObject;

import java.io.Serializable;

import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 04/05/18.
 */


public class PdfVO implements Serializable {

    private String fileTitle;
    private String fileUrl;
    private String fileName;
    private String fileType;
    private String pkgId;


    public PdfVO(String fileUrl){
        this.fileUrl = fileUrl;
    }
    public PdfVO(JSONObject json) {
        this.pkgId = json.optString("pkg_id");
        this.fileTitle = json.optString("title");
        this.fileUrl = json.optString("file_path");
        this.fileType = json.optString("file_type");
        this.fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.length());
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getPkgId() {
        return pkgId;
    }

}
