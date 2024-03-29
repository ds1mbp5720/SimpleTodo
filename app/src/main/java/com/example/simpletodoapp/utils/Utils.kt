package com.example.simpletodoapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream
import java.sql.Timestamp
import java.text.SimpleDateFormat

// camera 사진 촬영 후 Uri 생성 함수
fun getImageUri(inContext: Context, inImage: Bitmap, title: String): Uri? {
    val bytes = ByteArrayOutputStream()
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, title, null)
    return Uri.parse(path)
}

@SuppressLint("SimpleDateFormat")
fun millToDate(mills: Long): String {
    val pattern = "yyyy.MM.dd"
    val formatter = SimpleDateFormat(pattern)
    val date = formatter.format(Timestamp(mills))
    return date
}