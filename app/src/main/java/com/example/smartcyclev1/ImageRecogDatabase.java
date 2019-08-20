package com.example.smartcyclev1;

import android.media.Image;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ImageRecogObject.class}, version = 1, exportSchema = false)
public abstract class ImageRecogDatabase extends RoomDatabase {
    public abstract ImageRecogDAO imgDAO();
}
