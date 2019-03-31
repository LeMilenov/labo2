//package com.example.labo2;
//
//import android.app.Application;
//import android.arch.lifecycle.LiveData;
//import android.os.AsyncTask;
//
//import com.example.labo2.room.Banner;
//import com.example.labo2.room.BannerDao;
//import com.example.labo2.room.CustomDatabaseRoom;
//
//import java.util.List;
//
//public class RoomRepository {
//
//    private BannerDao mBannerDao;
//    private LiveData<List<Banner>> mAllBanners;
//
//    RoomRepository(Application application) {
//        CustomDatabaseRoom db = CustomDatabaseRoom.getInstance(application);
//        mBannerDao = db.bannerDao();
//        mAllBanners = mBannerDao.getAll();
//    }
//
//    LiveData<List<Banner>> getAllBanners() {
//        return mAllBanners;
//    }
//
//
//
//    public void insertAll (Banner ...banners) {
//        new insertAsyncTask(mBannerDao).execute(banners);
//    }
//
//    private static class insertAsyncTask extends AsyncTask<Banner[], Void, Void> {
//
//        private BannerDao mAsyncTaskDao;
//
//        insertAsyncTask(BannerDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final Banner[]... params) {
//            mAsyncTaskDao.insertAll(params[0]);
//            return null;
//        }
//    }
//}