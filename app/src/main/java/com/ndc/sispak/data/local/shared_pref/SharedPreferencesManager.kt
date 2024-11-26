package com.ndc.sispak.data.local.shared_pref

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    companion object {
        const val SHARED_ROOT_PREF_KEY = "SHARED_PREF_ROOT_KEY"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            SHARED_ROOT_PREF_KEY,
            Context.MODE_PRIVATE
        )

    fun saveString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }
    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
    fun saveInt(key: String, value: Int) {
        with(sharedPreferences.edit()) {
            putInt(key, value)
            apply()
        }
    }
    fun saveLong(key: String, value: Long) = with(sharedPreferences.edit()) {
        putLong(key, value)
        apply()
    }

    fun getLong(key: String, defaultValue: Long = 0L): Long =
        sharedPreferences.getLong(key, defaultValue)

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }
    fun saveBoolean(key: String, value: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(key, value)
            apply()
        }
    }
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
    fun delete(key: String) {
        with(sharedPreferences.edit()) {
            remove(key)
            apply()
        }
    }
}