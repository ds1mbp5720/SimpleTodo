package com.example.data.mapper

import com.example.data.room.entity.UserInfo
import com.example.domain.model.UserModel

fun UserModel.toEntity() = UserInfo(
    image = image,
    name = name,
    birthday = birthday
)

fun UserInfo.toModel() = UserModel(
    image = image,
    name = name,
    birthday = birthday
)