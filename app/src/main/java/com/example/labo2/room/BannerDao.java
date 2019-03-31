package com.example.labo2.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface BannerDao {
    @Query("SELECT * FROM banner_table WHERE banner_id = :id LIMIT 1")
    Banner getBanner(int id);

    @Query("SELECT banner_id FROM banner_table WHERE banner_name = :name")
    int getIdByBannerName(String name);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Banner ...banners);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Banner banner);

    @Query("DELETE FROM banner_table")
    void deleteAll();

    @Query("SELECT * FROM banner_table ORDER BY banner_name ASC ")
    LiveData<List<Banner>> getBanners();

}
