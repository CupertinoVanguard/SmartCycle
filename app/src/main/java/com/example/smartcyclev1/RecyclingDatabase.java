package com.example.smartcyclev1;

import android.content.Context;
import android.media.Image;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RecyclingOptions.class}, version = 1, exportSchema = false)
public abstract class RecyclingDatabase extends RoomDatabase {
    public abstract RecyclingDAO metalDAO();
    public abstract RecyclingDAO glassDAO();
    public abstract RecyclingDAO plasticDAO();
    public abstract RecyclingDAO electronicsDAO();
    public abstract RecyclingDAO batteriesDAO();
    public abstract RecyclingDAO woodDAO();
    public abstract RecyclingDAO paperCardboardDAO();
    private static RecyclingDatabase databaseInstance;
    public static void init(Context context){
        databaseInstance = Room.inMemoryDatabaseBuilder(context, RecyclingDatabase.class).allowMainThreadQueries().build();
    }
    public static RecyclingDatabase getInstance(){
        return databaseInstance;
    }
}