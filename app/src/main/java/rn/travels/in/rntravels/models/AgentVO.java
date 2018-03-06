package rn.travels.in.rntravels.models;

import org.json.JSONObject;

import java.io.Serializable;

import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 02/03/18.
 */

public class AgentVO implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String email;

    public AgentVO(JSONObject json) {
        this.name = json.optString(Appconst.JKey.NAME);
        this.address = json.optString(Appconst.JKey.ADDRESS);
        this.phone = json.optString(Appconst.JKey.PHONE);
        this.email = json.optString(Appconst.JKey.EMAIL);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AgentVO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
