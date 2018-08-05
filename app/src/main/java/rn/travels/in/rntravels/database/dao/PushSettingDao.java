package rn.travels.in.rntravels.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import rn.travels.in.rntravels.models.PushSettingVO;
import rn.travels.in.rntravels.models.UserVO;

/**
 * Created by demo on 04/08/18.
 */

@Dao
public interface PushSettingDao {
    @Query("SELECT * FROM PUSH WHERE id = 1")
    PushSettingVO getPushSetting();

    @Insert
    void insert(PushSettingVO pushSettingVO);

    @Update
    public void update(PushSettingVO pushSettingVO);

}
