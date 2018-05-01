package rn.travels.in.rntravels.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import rn.travels.in.rntravels.database.dao.PackageDao;
import rn.travels.in.rntravels.database.dao.UserDao;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.models.UserVO;

/**
 * Created by demo on 22/04/18.
 */

@Database(entities = {UserVO.class , PackageVO.class}, version = 1 ,exportSchema = false)
public abstract class RNDatabase extends RoomDatabase {
    private static final String DB_NAME = "rnDb.db";
    private static volatile RNDatabase instance;

    public static synchronized RNDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static RNDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                RNDatabase.class,
                DB_NAME).allowMainThreadQueries().build();
    }


    public abstract UserDao getUserDao();
    public abstract PackageDao getPackageDao();
}
