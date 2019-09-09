package com.example.smartcyclev1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface RecyclingDAO {
    @Query("SELECT * FROM recyclingoptions")
    List<RecyclingOptions> getAll();

    @Query("SELECT * FROM recyclingoptions WHERE type LIKE :imageT LIMIT 1")
    RecyclingOptions findByName(String imageT);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(RecyclingOptions... users);

    @Delete
    void delete(RecyclingOptions user);
}

