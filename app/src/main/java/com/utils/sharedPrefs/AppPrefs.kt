package com.utils.sharedPrefs

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject


class AppPrefs @Inject constructor(
    application: Application,
) {

    private var sharedPreferences: SharedPreferences =
        application.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveValue(key: String, value: String) {
        Log.d("TAG", "saveValue: $key : $value")
        editor.putString(key, value)
        commit()
    }

    fun saveIntValue(key: String, value: Int) {
        Log.d("TAG", "saveValue: $key : $value")
        editor.putInt(key, value)
        commit()
    }

    fun getValue(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    fun getIntValue(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    private fun commit() {
        editor.commit()
    }


}