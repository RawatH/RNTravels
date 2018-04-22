package rn.travels.in.rntravels.models;

/**
 * Created by demo on 19/04/18.
 */

public class ResetPasswordVO {
    private String fb_id;
    private String user_name;
    private String password;

    public ResetPasswordVO() {
    }

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
