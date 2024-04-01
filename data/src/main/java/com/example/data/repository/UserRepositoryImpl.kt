package com.example.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.data.mapper.toModel
import com.example.data.room.entity.UserInfo
import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(): UserRepository {
    override suspend fun setUserInfo(
        context: Context,
        uri: String,
        name: String,
        birthday: String
    ) {
        val gson = Gson()
        val json = gson.toJson(UserInfo(uri, name, birthday).toModel())
        val prefs = context.getSharedPreferences("USER",Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("user",json)
        editor.apply()
    }

    override suspend fun getUserInfo(context: Context): UserModel {
        val prefs = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
        val json = prefs.getString("user", "[]") // 최초 null 일경우 NPE 발생으로 초기값 수정 "[]"
        val gson = Gson()
        return if(json == "[]"){
            gson.fromJson(
                gson.toJson(UserInfo("", "", "").toModel()),
                object : TypeToken<UserModel>() {}.type
            )
        } else {
            gson.fromJson(
                json,
                object : TypeToken<UserModel>() {}.type
            )
        }

    }

}