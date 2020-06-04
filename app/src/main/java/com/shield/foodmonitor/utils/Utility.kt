package com.shield.foodmonitor.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

class Utility {

    companion object{
        fun convertToString(bitmap: Bitmap): String? {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val imgByte: ByteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(imgByte, Base64.DEFAULT)
        }
    }
}