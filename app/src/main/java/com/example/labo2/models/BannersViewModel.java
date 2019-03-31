package com.example.labo2.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.labo2.room.Banner;
import com.example.labo2.room.BannerDao;
import com.example.labo2.room.CustomDatabaseRoom;

import java.util.List;

public class BannersViewModel  extends AndroidViewModel {
    private BannerDao bannerDao;
    private LiveData<List<Banner>> allBanners;

    public BannersViewModel(@NonNull Application application) {
        super(application);
        bannerDao = CustomDatabaseRoom.getInstance(application).bannerDao();
        allBanners = bannerDao.getBanners();
    }

    public LiveData<List<Banner>> getBannerList(){
        return allBanners;
    }

    public void insert(Banner... banners){
        bannerDao.insert(banners);
    }

    public void deleteAll(){
        bannerDao.deleteAll();
    }
}
