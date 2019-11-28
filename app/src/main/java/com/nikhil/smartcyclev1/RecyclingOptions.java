package com.nikhil.smartcyclev1;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RecyclingOptions {

    @PrimaryKey
    public long id;

    @NonNull
    public String name;

    @NonNull
    public String type;
}