package com.example.labo2.room;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "store_table",
        foreignKeys = @ForeignKey(
            entity = Banner.class,
            parentColumns = "banner_id",
            childColumns = "store_id_banner",
            onDelete = CASCADE),
            indices = {@Index("store_id"), @Index("store_name"), @Index(("store_id_banner"))})
public class Store {

    public Store(@NonNull String name,@NonNull String address, @NonNull String website, int idBanner) {
        this.name = name;
        this.address = address;
        this.website = website;
        this.idBanner = idBanner;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "store_id")
    public int id;
    @ColumnInfo(name = "store_id_banner")
    public int idBanner;

    @ColumnInfo(name = "store_name")
    public String name;

    @ColumnInfo(name = "store_address")
    public String address;

    @ColumnInfo(name = "store_website")
    public String website;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getIdBanner() {
        return idBanner;
    }

    public void setIdBanner(int idBanner) {
        this.idBanner = idBanner;
    }
}
