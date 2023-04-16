package com.example.storyapplication.utilities

import com.example.storyapplication.responses.LoginResult

class UserUtil {
    companion object{
        var user_id = ""
        var name = ""
        var token = ""

        fun set(loginResult: LoginResult) {
            user_id = loginResult.userId
            name = loginResult.name
            token = loginResult.token
        }
    }
}