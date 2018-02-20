package rn.travels.in.rntravels.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by demo on 16/02/18.
 */

public class PackageVO implements Serializable {

    private String heading;
    private String subHeading;
    private ArrayList<DayVO> dayList;
    private String remarks;
    private String creationDate;
    private String emergencyNumber;
    private String bannerImage;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
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

    @Override
    public String toString() {
        return "PackageVO{" +
                "heading='" + heading + '\'' +
                ", subHeading='" + subHeading + '\'' +
                ", dayList=" + dayList +
                ", remarks='" + remarks + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", emergencyNumber='" + emergencyNumber + '\'' +
                ", bannerImage='" + bannerImage + '\'' +
                '}';
    }
}
