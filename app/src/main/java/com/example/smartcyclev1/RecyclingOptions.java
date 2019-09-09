package com.example.smartcyclev1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RecyclingOptions {

    @PrimaryKey
    public long id;

    @NonNull
    public String type;
}