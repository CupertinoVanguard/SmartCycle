package com.example.smartcyclev1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RecyclingOptions {
    @PrimaryKey
    public String type;
}