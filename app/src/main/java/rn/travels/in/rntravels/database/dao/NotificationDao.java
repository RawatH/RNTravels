package rn.travels.in.rntravels.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import rn.travels.in.rntravels.models.NotificationVO;
import rn.travels.in.rntravels.models.UserVO;

/**
 * Created by demo on 30/07/18.
 */

@Dao
public interface NotificationDao {
    @Query("SELECT * FROM NOTIFICATION where id = 1")
    NotificationVO getNotification();

    @Insert
    void insert(NotificationVO notificationVO);

    @Query("UPDATE NOTIFICATION SET count = :count  where id = 1")
    void updateCount(int count);

    @Query("UPDATE NOTIFICATION SET count = 0 where id = 1")
    void clearNotificationCount();

    @Query("SELECT * FROM NOTIFICATION ")
    List<NotificationVO> getAllNotification();

}