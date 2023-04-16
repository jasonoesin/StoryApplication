package com.example.storyapplication.utilities

import android.content.Context
import android.content.Intent

class NavigationUtil {

    companion object{
        fun replaceActivityNoBack(ctx: Context, activity:  Class<*>?){
            val intent = Intent(ctx, activity)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            ctx.startActivity(intent)
        }
    }
}