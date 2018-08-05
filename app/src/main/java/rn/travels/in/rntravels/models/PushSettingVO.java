package rn.travels.in.rntravels.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by demo on 04/08/18.
 */

@Entity(tableName = "PUSH")
public class PushSettingVO {
    @PrimaryKey
    private int id = 1;
    private String pushRegToken;

    public PushSettingVO(String pushRegToken) {
        this.pushRegToken = pushRegToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPushRegToken() {
        return pushRegToken;
    }

    public void setPushRegToken(String pushRegToken) {
        this.pushRegToken = pushRegToken;
    }
}
