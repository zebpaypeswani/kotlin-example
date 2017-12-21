package com.example.manoharpeswani.kotlindatabaseexample

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Manohar Peswani on 12/21/17.
 * Copyright (c) 2017 Zebpay
 */

@Entity(tableName = "table_values")
data class DatabaseValue(@ColumnInfo(name = "value1") var value1: String,
                         @ColumnInfo(name = "value2") var value2: String) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}