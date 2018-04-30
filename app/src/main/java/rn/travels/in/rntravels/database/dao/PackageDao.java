package rn.travels.in.rntravels.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import rn.travels.in.rntravels.models.PackageVO;

/**
 * Created by demo on 29/04/18.
 */

@Dao
public interface PackageDao {
    @Query("SELECT * FROM PACKAGE")
    PackageVO getAll();

    @Insert
    void insert(PackageVO packageVO);

    @Delete
    void delete(PackageVO packageVO);
}