package com.example.domain.repository

import android.content.Context
import android.net.Uri
import com.example.domain.model.UserModel

interface UserRepository {
    suspend fun setUserInfo(context: Context, uri: String, name: String, birthday: String)
    suspend fun getUserInfo(context: Context): UserModel
}