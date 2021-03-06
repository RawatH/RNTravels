package rn.travels.in.rntravels.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import rn.travels.in.rntravels.models.PackageVO;

/**
 * Created by demo on 29/04/18.
 */

@Dao
public interface PackageDao {
    @Query("SELECT * FROM PACKAGE")
    List<PackageVO> getAll();

    @Query("SELECT * FROM PACKAGE where userId =:userId")
    List<PackageVO> getUserPackages(String userId);

    @Query("SELECT * FROM PACKAGE where pkgId =:pkgId")
    PackageVO getPkgById(String pkgId);

    @Query("SELECT * FROM PACKAGE where isFollowingPkg = 1")
    List<PackageVO> getFollowingPkg();

    @Query("DELETE FROM PACKAGE where isFollowingPkg = 1")
    void removeFollowingPkg();

    @Query("DELETE  FROM PACKAGE where pkgId =:pkgId")
    void deletePkgByPkgId(String pkgId);

    @Insert
    void insert(PackageVO packageVO);

    @Query("DELETE  FROM PACKAGE")
    void deleteAllPackages();

    @Update
    void update(PackageVO packageVO);
}