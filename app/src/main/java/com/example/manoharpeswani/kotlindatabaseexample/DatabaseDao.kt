package com.example.manoharpeswani.kotlindatabaseexample

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * Created by Manohar Peswani on 12/21/17.
 * Copyright (c) 2017 Zebpay
 */

@Dao
interface DatabaseDao {

    @Query("select * from table_values")
    fun getAllValues(): List<DatabaseValue>

    @Query("select * from table_values where id = :id")
    fun findValuesById(id: Long): DatabaseValue

    @Insert(onConflict = REPLACE)
    fun insertValues(task: DatabaseValue)

    @Update(onConflict = REPLACE)
    fun updateValues(task: DatabaseValue)

    @Delete
    fun deleteValues(task: DatabaseValue)
}