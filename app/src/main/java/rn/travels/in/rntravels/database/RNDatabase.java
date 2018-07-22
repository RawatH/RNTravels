package rn.travels.in.rntravels.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import rn.travels.in.rntravels.database.dao.PackageDao;
import rn.travels.in.rntravels.database.dao.UserDao;
import rn.travels.in.rntravels.models.PackageVO;
import rn.travels.in.rntravels.models.UserVO;

/**
 * Created by demo on 22/04/18.
 */

@Database(entities = {UserVO.class, PackageVO.class}, version = 3, exportSchema = false)
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
                DB_NAME).allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build();
    }


    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE PACKAGE ADD COLUMN isFollowingPkg INTEGER DEFAULT 0 NOT NULL");
            database.execSQL("ALTER TABLE USER ADD COLUMN userType INTEGER DEFAULT 0 NOT NULL");
        }
    };

    public abstract UserDao getUserDao();

    public abstract PackageDao getPackageDao();
}
