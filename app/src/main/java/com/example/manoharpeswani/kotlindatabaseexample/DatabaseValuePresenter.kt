package com.example.manoharpeswani.kotlindatabaseexample

import javax.inject.Inject

/**
 * Created by Manohar Peswani on 12/21/17.
 * Copyright (c) 2017 Zebpay
 */

class DatabaseValuePresenter @Inject constructor(val databaseDao: DatabaseDao) {

    var databaseValues = ArrayList<DatabaseValue>()

    var presentation: DatabaseValuePresentation? = null

    interface DatabaseValuePresentation {
        fun showValues(values: List<DatabaseValue>)

        fun valuesAddedAt(position: Int)

        fun scrollTo(position: Int)
    }

    fun onCreate(toDoPresentation: DatabaseValuePresentation) {
        presentation = toDoPresentation
    }

    fun onDestroy() {
        presentation = null
    }

    fun loadTasks() {
        databaseValues.clear()
        databaseValues.addAll(databaseDao.getAllValues())
        presentation?.showValues(databaseValues)
    }

    fun insertValuesInDatabase(value1: String, value2: String) {
        val newTask = DatabaseValue(value1 = value1, value2 = value2)
        databaseValues.add(newTask)
        databaseDao.insertValues(newTask)
        (databaseValues.size - 1).let {
            presentation?.valuesAddedAt(it)
            presentation?.scrollTo(it)
        }
    }
}