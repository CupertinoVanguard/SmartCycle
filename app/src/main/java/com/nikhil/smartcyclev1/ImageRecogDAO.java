package com.nikhil.smartcyclev1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ImageRecogDAO {
    @Query("SELECT * FROM imagerecogobject")
    List<ImageRecogObject> getAll();

    @Query("SELECT * FROM imagerecogobject WHERE confidenceLevel IN (:confidenceLevels)")
    List<ImageRecogObject> loadAllByIds(float[] confidenceLevels);

    @Query("SELECT * FROM imagerecogobject WHERE image_type LIKE :imageT LIMIT 1")
    ImageRecogObject findByName(String imageT);

    @Insert
    void insertAll(ImageRecogObject... users);

    @Delete
    void delete(ImageRecogObject user);


}
