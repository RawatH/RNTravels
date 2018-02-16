package rn.travels.in.rntravels.models;

/**
 * Created by demo on 16/02/18.
 */

public class PackageVO {

    private String name;
    private String remarks;
    private String creationDate;
    private String emergencyNumber;
    private String bannerImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "PackageVO{" +
                "name='" + name + '\'' +
                ", remarks='" + remarks + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", emergencyNumber='" + emergencyNumber + '\'' +
                ", bannerImage='" + bannerImage + '\'' +
                '}';
    }
}
