package rn.travels.in.rntravels.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

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
public class PackageVO implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String pkgJson;
    @Ignore
    private String heading;
    @Ignore
    private String subHeading;
    @Ignore
    private String travelDate;
    @Ignore
    private String emergencyNumber;
    @Ignore
    private String bannerImage;
    @Ignore
    private String ticketsPdf;
    @Ignore
    private String boardingPassPdf;
    @Ignore
    private String hotelvoucherPdf;
    @Ignore
    private ArrayList<DayVO> dayList;

//    @Ignore
//    private

    public PackageVO() {
    }

    public PackageVO initPackage(JSONObject jsonObject) {
        PackageVO packageVO = new PackageVO();
        this.heading = jsonObject.optString("pack_name");
        this.subHeading = jsonObject.optString("pack_detail");
        this.travelDate = jsonObject.optString("travel_dt");
        this.bannerImage = jsonObject.optString("bannery_img");

        try {
            JSONArray uploadArr = jsonObject.getJSONArray("uploads");
            for (int i = 0; i < 3; i++) {
                JSONObject uploadJson = uploadArr.getJSONObject(i);
                String data = jsonObject.optString("file_path");
                switch (uploadJson.optString("file_type")) {
                    case Appconst.Uploads.TICKET:
                        this.ticketsPdf = data;
                        break;
                    case Appconst.Uploads.BOARDING_PASS:
                        this.boardingPassPdf = data;
                        break;
                    case Appconst.Uploads.VOUCHER:
                        this.hotelvoucherPdf = data;
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return packageVO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPkgJson() {
        return pkgJson;
    }

    public void setPkgJson(String pkgJson) {
        this.pkgJson = pkgJson;
    }

    public String getTicketsPdf() {
        return ticketsPdf;
    }

    public void setTicketsPdf(String ticketsPdf) {
        this.ticketsPdf = ticketsPdf;
    }

    public String getBoardingPassPdf() {
        return boardingPassPdf;
    }

    public void setBoardingPassPdf(String boardingPassPdf) {
        this.boardingPassPdf = boardingPassPdf;
    }

    public String getHotelvoucherPdf() {
        return hotelvoucherPdf;
    }

    public void setHotelvoucherPdf(String hotelvoucherPdf) {
        this.hotelvoucherPdf = hotelvoucherPdf;
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

}
