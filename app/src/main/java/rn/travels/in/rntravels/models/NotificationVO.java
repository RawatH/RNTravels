package rn.travels.in.rntravels.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by demo on 30/07/18.
 */

@Entity(tableName = "NOTIFICATION")
public class NotificationVO {
    @PrimaryKey
    @NonNull
    private int id ;
    private int count;

    public NotificationVO(@NonNull int id, int count) {
        this.id = id;
        this.count = count;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
