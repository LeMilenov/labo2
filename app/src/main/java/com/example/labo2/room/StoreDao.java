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
public interface StoreDao {

    @Query("SELECT * FROM store_table WHERE store_name = :name LIMIT 1")
    Store getStore(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Store ...stores);

    @Query("DELETE FROM store_table")
    void deleteAll();
////
//    @Query("SELECT * FROM store_table ORDER BY store_name ASC")
//    LiveData<List<Store>> getStores();

    @Query("SELECT * FROM store_table WHERE store_id_banner = :bannerId ORDER BY store_name ASC ")
    LiveData<List<Store>> getStoresForBanner(int bannerId);
}
