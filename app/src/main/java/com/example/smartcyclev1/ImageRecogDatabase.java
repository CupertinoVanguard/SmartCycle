package com.example.smartcyclev1;

import android.content.Context;
import android.media.Image;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ImageRecogObject.class}, version = 1, exportSchema = false)
public abstract class ImageRecogDatabase extends RoomDatabase {
    public abstract ImageRecogDAO imgDAO();
    private static ImageRecogDatabase databaseInstance;
    public static void init(Context context){
        databaseInstance = Room.inMemoryDatabaseBuilder(context, ImageRecogDatabase.class).allowMainThreadQueries().build();
    }
    public static ImageRecogDatabase getInstance(){
        return databaseInstance;
    }
}
