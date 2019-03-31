package com.example.labo2.room;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "banner_table",
         indices = {@Index(value = "banner_name", unique = true)})
public class Banner {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "banner_id")
    public int id;

    @ColumnInfo(name = "banner_name")
    @NonNull
    public String name;

    public Banner( @NonNull  String name){
        this.name = name;
    }

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

    @Override
    public String toString(){
        return getName();
    }
}
