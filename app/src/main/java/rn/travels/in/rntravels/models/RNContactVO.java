package rn.travels.in.rntravels.models;

/**
 * Created by demo on 18/04/18.
 */

public class RNContactVO {
    private String id;
    private String mob_num;
    private String landline_num;
    private String website;
    private String email;
    private String addr;

    public RNContactVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMob_num() {
        return mob_num;
    }

    public void setMob_num(String mob_num) {
        this.mob_num = mob_num;
    }

    public String getLandline_num() {
        return landline_num;
    }

    public void setLandline_num(String landline_num) {
        this.landline_num = landline_num;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
