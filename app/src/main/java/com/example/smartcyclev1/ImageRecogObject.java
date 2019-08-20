package com.example.smartcyclev1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ImageRecogObject {
    @PrimaryKey
    public float confidenceLevel;

    @ColumnInfo(name = "image_type")
    public String imageType;
}