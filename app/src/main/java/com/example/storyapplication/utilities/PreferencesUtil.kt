package com.example.storyapplication.utilities

import android.content.Context
import com.example.storyapplication.responses.LoginResult

class PreferencesUtil {
    companion object{
        private const val KEY: String = "APP_PREFERENCES"

        fun getData(ctx: Context): LoginResult {
            val preferences = ctx.getSharedPreferences(KEY, Context.MODE_PRIVATE)
            val name = preferences.getString("name", "")
            val token = preferences.getString("token", "")
            val user_id = preferences.getString("user_id", "")

            return LoginResult(name!!, user_id!!, token!!)
        }

        fun writeData(ctx: Context, data: LoginResult){
            val preferences = ctx.getSharedPreferences(KEY, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("token", data.token)
            editor.putString("name", data.name)
            editor.putString("user_id", data.userId)
            editor.apply()

            return
        }

        fun deleteData(ctx: Context){
            val preferences = ctx.getSharedPreferences(KEY, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("token", "")
            editor.putString("name", "")
            editor.putString("user_id", "")
            editor.apply()

            return
        }
    }
}