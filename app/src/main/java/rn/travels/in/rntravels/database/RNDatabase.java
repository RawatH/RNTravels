package rn.travels.in.rntravels.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import rn.travels.in.rntravels.database.dao.UserDao;
import rn.travels.in.rntravels.models.UserVO;

/**
 * Created by demo on 22/04/18.
 */

@Database(entities = {UserVO.class}, version = 1)
public abstract class RNDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
