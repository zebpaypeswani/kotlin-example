package com.example.manoharpeswani.kotlindatabaseexample

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Manohar Peswani on 12/21/17.
 * Copyright (c) 2017 Zebpay
 */


@Database(entities = [(DatabaseValue::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun databaseValuesDao(): DatabaseDao
}