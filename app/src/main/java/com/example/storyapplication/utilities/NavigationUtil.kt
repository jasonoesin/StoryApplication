package com.example.storyapplication.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat

class NavigationUtil {

    companion object{
        fun replaceActivityNoBack(ctx: Context, activity:  Class<*>?){
            val intent = Intent(ctx, activity)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            ctx.startActivity(intent)
        }

        fun replaceAct(ctx: Context, activity:  Class<*>?){
            val intent = Intent(ctx, activity)
            ctx.startActivity(intent)
        }

        fun toDetail(ctx: Context, activity: Class<*>?, id: String, toBundle: Bundle?){
            val intent = Intent(ctx, activity)
            intent.putExtra("id", id)
            ctx.startActivity(intent, toBundle)
        }
    }
}