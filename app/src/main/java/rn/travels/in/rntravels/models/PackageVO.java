package rn.travels.in.rntravels.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import rn.travels.in.rntravels.util.Appconst;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 16/02/18.
 */

@Entity(tableName = "PACKAGE")
public class PackageVO implements Serializable {

    @PrimaryKey
    @NonNull
    private String pkgId;
    private String userId;
    private String pkgJson;
    private String heading;
    private String subHeading;
    private String travelDate;
    private String bannerImage;
    private String uploadJson;
    private boolean isFollowingPkg;

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

    public boolean isFollowingPkg() {
        return isFollowingPkg;
    }

    public void setFollowingPkg(boolean followingPkg) {
        isFollowingPkg = followingPkg;
    }

    public ArrayList<PdfVO> getListByType(String type) {

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

    public ItineraryVO getItinerary() {
        try {
            JSONObject json = new JSONObject(pkgJson);
            return new ItineraryVO(json.optJSONObject("itinerary"));
        } catch (JSONException | NullPointerException e) {
            return null;
        }
    }

    public ArrayList<Pair<String, String>> getEmergencyContactList() {
        ArrayList<Pair<String, String>> emergencyContactList = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(pkgJson);

            JSONArray eContactJson = new JSONArray(json.optString("e_contacts"));
            for (int i = 0; i < eContactJson.length(); i++) {
                JSONObject jsonObject = eContactJson.getJSONObject(i);
                Pair<String, String> p = new Pair<>(jsonObject.optString("contact_name"), jsonObject.optString("contact_number"));
                emergencyContactList.add(p);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return emergencyContactList;

    }

    public void populatePdfList() {

        try {
            JSONArray uploadArr = new JSONArray(this.uploadJson);
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

}
