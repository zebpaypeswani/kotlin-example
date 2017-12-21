package com.example.manoharpeswani.kotlindatabaseexample

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), DatabaseValuePresenter.DatabaseValuePresentation {

    @Inject lateinit var presenter: DatabaseValuePresenter

    companion object {
        private const val TAG: String = "MainActivity"
        private val PREF_NAME: String? = "pref"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPref = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        presenter.onCreate(this)
        btnSendLog.setOnClickListener {
            Log.v(TAG, editLog.text.toString())
        }
        btnSaveDatabase.setOnClickListener {
            val value1 = db_value_1.text.toString();
            val value2 = db_value_2.text.toString();
            if (value1.isBlank()) {
                Toast.makeText(this, "enter value 1", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (value2.isBlank()) {
                Toast.makeText(this, "enter value 2", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            presenter.insertValuesInDatabase(value1, value2)
        }
        btnSavePreference.setOnClickListener {
            val key = sp_key.text.toString();
            val value = sp_value.text.toString();
            if (key.isBlank()) {
                Toast.makeText(this, "enter key", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (value.isBlank()) {
                Toast.makeText(this, "enter value", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            sharedPref.edit().putString(key, value).apply()
            Toast.makeText(this, "saved in shared preference", Toast.LENGTH_SHORT).show()

        }
        btnShowDatabase.setOnClickListener {
            presenter.loadTasks()
        }

        btnShowPreferences.setOnClickListener {
            showPreferences(sharedPref.all)
        }
    }

    private fun showPreferences(values: Map<String, *>) {
        val builderSingle = AlertDialog.Builder(this@MainActivity)

        val arrayAdapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1)
        for (value in values) {
            arrayAdapter.add("key: " + value.key + ",  value: " + value.value)
        }
        builderSingle.setAdapter(arrayAdapter, { _, which ->
            val strName = arrayAdapter.getItem(which)
            AlertDialog.Builder(this@MainActivity).apply {
                setMessage(strName)
                setTitle("Your Selected Item is")
                setPositiveButton("Ok", { dialog, _ -> dialog.dismiss() })
                show()
            }
        })
        builderSingle.show()
    }


    override fun showValues(values: List<DatabaseValue>) {
        val builderSingle = AlertDialog.Builder(this@MainActivity)

        val arrayAdapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1)
        for (value in values) {
            arrayAdapter.add("value 1: " + value.value1 + ",  value 2: " + value.value2 + ",  id: " + value.id)
        }

        builderSingle.setAdapter(arrayAdapter, { _, which ->
            val strName = arrayAdapter.getItem(which)
            AlertDialog.Builder(this@MainActivity).apply {
                setMessage(strName)
                setTitle("Your Selected Item is")
                setPositiveButton("Ok", { dialog, _ -> dialog.dismiss() })
                show()
            }
        })
        builderSingle.show()
    }

    override fun valuesAddedAt(position: Int) {
        Toast.makeText(this, "saved in database at position " + position, Toast.LENGTH_SHORT).show()
    }

    override fun scrollTo(position: Int) {
        Log.v(TAG, "position " + position)

    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}
