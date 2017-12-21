package com.example.manoharpeswani.kotlindatabaseexample

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Manohar Peswani on 12/21/17.
 * Copyright (c) 2017 Zebpay
 */

@Module
class AppModule(private val context: Context) {

    @Provides
    fun providesAppContext() = context

    @Provides fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "my-values-db").allowMainThreadQueries().build()

    @Provides fun providesDatabaseValueDao(database: AppDatabase) = database.databaseValuesDao()
}