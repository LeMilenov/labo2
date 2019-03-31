package com.example.labo2.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.labo2.room.CustomDatabaseRoom;
import com.example.labo2.room.Store;
import com.example.labo2.room.StoreDao;

import java.util.List;

public class StoresViewModel extends AndroidViewModel {
    private StoreDao storeDao;
    private LiveData<List<Store>> allStores;
    public StoresViewModel(@NonNull Application application) {
        super(application);
        storeDao = CustomDatabaseRoom.getInstance(application).storeDao();
    }

    public LiveData<List<Store>> getAllStoresForBanner (int banner_id){
        return storeDao.getStoresForBanner(banner_id);
    }

    public void insert(Store... stores){
        storeDao.insert(stores);
    }

    public void deleteAll(){
        storeDao.deleteAll();
    }
}
