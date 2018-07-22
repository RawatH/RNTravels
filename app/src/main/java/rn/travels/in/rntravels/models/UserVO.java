package rn.travels.in.rntravels.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONObject;

/**
 * Created by demo on 16/02/18.
 */

@Entity(tableName = "USER")
public class UserVO {

    @PrimaryKey
    @NonNull
    private String userEmail;

    private String userId;
    private String fbId;
    private String firstName;
    private String lastName;
    private String userCred;
    private String travelId;
    private String userName;
    private String phoneNumber;
    private boolean isFBUser;
    private int userType;

    public UserVO() {
    }

    public UserVO(JSONObject json) {
        this.fbId = json.optString("fb_id");
        this.userId = json.optString("id");
        this.firstName = json.optString("first_name");
        this.lastName = json.optString("last_name");
        this.userEmail = json.optString("email_addres");
        this.phoneNumber = json.optString("contact_number");
        this.userCred = json.optString("pass_word");
        this.travelId = json.optString("travel_id");
        this.userName = json.optString("user_name");
        setFBUser(isFBUser());
    }


    public boolean isFBUser() {
        return !this.fbId.equals("null");
    }

    public void setFBUser(boolean FBUser) {
        isFBUser = FBUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserCred() {
        return userCred;
    }

    public void setUserCred(String userCred) {
        this.userCred = userCred;
    }

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userEmail='" + userEmail + '\'' +
                ", userId='" + userId + '\'' +
                ", fbId='" + fbId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userCred='" + userCred + '\'' +
                ", travelId='" + travelId + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isFBUser=" + isFBUser +
                '}';
    }
}
