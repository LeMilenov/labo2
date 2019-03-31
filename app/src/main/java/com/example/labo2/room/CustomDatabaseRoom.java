package com.example.labo2.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;


@Database(entities = {Store.class, Banner.class}, version = 1)
public abstract class CustomDatabaseRoom extends RoomDatabase {

    private static final String DB_NAME = "db";
    private static volatile CustomDatabaseRoom instance;

    public abstract StoreDao storeDao();
    public abstract BannerDao bannerDao();

   public static CustomDatabaseRoom getInstance( final Context context){
        if(instance == null){
            synchronized (CustomDatabaseRoom.class){
                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            CustomDatabaseRoom.class,
                            DB_NAME)
                            .allowMainThreadQueries() //pour eviter que je surcomplique le projet
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }

            }
        }
        return instance;
    }
//    pour remplir notre database des qu on le peux
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(instance).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final BannerDao mBannerDao;
        private final StoreDao mStoreDao;

        PopulateDbAsync(CustomDatabaseRoom db) {
            mBannerDao = db.bannerDao();
            mStoreDao = db.storeDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mBannerDao.deleteAll();
            mStoreDao.deleteAll();

            Banner b1 = new Banner("IGA");
            Banner b2 = new Banner("Metro");
            Banner b3 = new Banner("Maxi");
            Banner b4 = new Banner("WallMart");
            final int banner1 = (int) mBannerDao.insert(b1);
            final int banner2 = (int) mBannerDao.insert(b2);
            final int banner3 = (int) mBannerDao.insert(b3);
            final int banner4 = (int) mBannerDao.insert(b4);

            Store s1 = new Store("IGA AUTEUIL","1234 rue aa", "www.iga.asdasd",mBannerDao.getIdByBannerName("IGA"));
//            Store s2 = new Store("IGA CHOMEDEY","1234 rue bb", "www.iga.asdasd",b.getId());
            Store s3 = new Store("Metro","1234 rue ccc", "www.metro.asdasd",mBannerDao.getIdByBannerName("Metro"));
            Store s4 = new Store("Maxi","1234 rue ddd", "www.maxi.asdasd",mBannerDao.getIdByBannerName("Maxi"));
            Store s5 = new Store("Wall mart","1234 rue fff", "www.wallmart.asdasd",mBannerDao.getIdByBannerName("WallMart"));

            mStoreDao.insert(s1,s3,s4,s5);

            return null;
        }
    }
}
