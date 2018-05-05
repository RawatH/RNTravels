package rn.travels.in.rntravels.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 16/02/18.
 */

@Entity(tableName = "PACKAGE")
public class PackageVO  implements Serializable {

    @PrimaryKey
    @NonNull
    private String pkgId;
    private String userId;
    private String pkgJson;
    private String heading;
    private String subHeading;
    private String travelDate;
    private String emergencyNumber;
    private String bannerImage;
    private String uploadJson;
    @Ignore
    private ArrayList<PdfVO> ticketsList;
    @Ignore
    private ArrayList<PdfVO> voucherList;
    @Ignore
    private ArrayList<PdfVO> boardingPassList;
    @Ignore
    private ArrayList<DayVO> dayList;

    public PackageVO() {
    }

    public PackageVO(String userId, JSONObject jsonObject) {
        this.userId = userId;
        this.pkgJson = jsonObject.toString();
        this.pkgId = jsonObject.optString("id");
        this.heading = jsonObject.optString("pack_name");
        this.subHeading = jsonObject.optString("pack_detail");
        this.travelDate = jsonObject.optString("travel_dt");
        this.bannerImage = jsonObject.optString("bannery_img");
        this.uploadJson = jsonObject.optString("uploads");
        populatePdfList();

    }

    public void populatePdfList() {

        JSONArray uploadArr = null;
        try {
            uploadArr = new JSONArray(uploadJson);
            for (int i = 0; i < uploadArr.length(); i++) {
                JSONObject pdfJson = uploadArr.getJSONObject(i);
                PdfVO pdfVO = new PdfVO(pdfJson);
                switch (pdfVO.getFileType()) {
                    case Appconst.Uploads.TICKET:
                        if (ticketsList == null) {
                            ticketsList = new ArrayList<>();
                        }
                        ticketsList.add(pdfVO);
                        break;
                    case Appconst.Uploads.BOARDING:
                        if (boardingPassList == null) {
                            boardingPassList = new ArrayList<>();
                        }
                        boardingPassList.add(pdfVO);
                        break;
                    case Appconst.Uploads.VOUCHER:
                        if (voucherList == null) {
                            voucherList = new ArrayList<>();
                        }
                        voucherList.add(pdfVO);
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getUploadJson() {
        return uploadJson;
    }

    public void setUploadJson(String uploadJson) {
        this.uploadJson = uploadJson;
    }

    @NonNull
    public String getPkgId() {
        return pkgId;
    }

    public void setPkgId(@NonNull String pkgId) {
        this.pkgId = pkgId;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getPkgJson() {
        return pkgJson;
    }

    public void setPkgJson(String pkgJson) {
        this.pkgJson = pkgJson;
    }

    public ArrayList<PdfVO> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(ArrayList<PdfVO> ticketsList) {
        this.ticketsList = ticketsList;
    }

    public ArrayList<PdfVO> getVoucherList() {
        return voucherList;
    }

    public void setVoucherList(ArrayList<PdfVO> voucherList) {
        this.voucherList = voucherList;
    }

    public ArrayList<PdfVO> getBoardingPassList() {
        return boardingPassList;
    }

    public void setBoardingPassList(ArrayList<PdfVO> boardingPassList) {
        this.boardingPassList = boardingPassList;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public ArrayList<DayVO> getDayList() {
        return dayList;
    }

    public void setDayList(ArrayList<DayVO> dayList) {
        this.dayList = dayList;
    }

    public ArrayList<PdfVO> getListByType(String type) {
        populatePdfList();
        switch (type) {
            case Appconst.Uploads.TICKET:
                return ticketsList;
            case Appconst.Uploads.VOUCHER:
                return voucherList;
            case Appconst.Uploads.BOARDING:
                return boardingPassList;
            default:
                return null;
        }
    }

}
