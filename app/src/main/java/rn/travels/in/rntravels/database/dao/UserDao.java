package rn.travels.in.rntravels.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import rn.travels.in.rntravels.models.UserVO;

/**
 * Created by demo on 22/04/18.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<UserVO> getAll();

    @Query("SELECT * FROM user WHERE userEmail LIKE :email  LIMIT 1")
    UserVO findByName(String email);

    @Insert
    void insert(UserVO userVO);

    @Delete
    void delete(UserVO userVO);
}