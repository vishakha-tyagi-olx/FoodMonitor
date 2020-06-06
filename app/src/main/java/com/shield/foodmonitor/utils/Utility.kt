package com.shield.foodmonitor.utils

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.shield.foodmonitor.data.model.DataPayload
import com.shield.foodmonitor.data.model.FirebasePush
import java.io.ByteArrayOutputStream


class Utility {

    companion object {

        fun createPush(): FirebasePush {
            val datapayload = DataPayload("Food Monitor Report")
            val push = FirebasePush(
                "dxDZF9WdR6usSt72omqh3Z:APA91bFE7X1A4gFp8hJiYVJ2qtfHDV2u4OowF4OY_Ki2Q3LTH9xoqXVyGLc_q5JRPdSYqZeAPfKGJtizt8ZLag9f0fwmV5s2qx6DpzDQOWf6xcu1BxWBJ4F8nTDYLZEqCoOLTv20WKLP",
                "type_a", datapayload
            )
            return push

        }



        fun getFirebaseToken(context: Context): String?{
            return getPreferences(context).getString(Constants.Notification.DEVICE_TOKEN, "")
        }

        fun getPreferences(context: Context): SharedPreferences {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("monitpr_preferences", Context.MODE_PRIVATE)
            return sharedPreferences
        }

        fun saveBoolean(context: Context, key: String, value: Boolean) {
            val sharedPreferenceEditor: SharedPreferences.Editor = getPreferences(context).edit()
            sharedPreferenceEditor.putBoolean(key, value)
            sharedPreferenceEditor.apply()
            sharedPreferenceEditor.commit()
        }

        fun saveString(context: Context, key: String, value: String) {
            val sharedPreferenceEditor: SharedPreferences.Editor = getPreferences(context).edit()
            sharedPreferenceEditor.putString(key, value)
            sharedPreferenceEditor.apply()
            sharedPreferenceEditor.commit()
        }

        fun getBoolean(context: Context, key: String): Boolean {
            return getPreferences(context).getBoolean(key, false)
        }

        fun getString(context: Context, key: String): String? {
            return getPreferences(context).getString(key, "")
        }

        fun stepsNotificationTitleMap(): HashMap<String, String> {
            val map = HashMap<String, String>()
            map.put(Constants.CHEF_DETAIL, "Food Monitor Tracking")
            map.put(Constants.COOKING_DETAIL, "Food Monitor Tracking")
            map.put(
                Constants.DELIVERY_DETAIL,
                "Food Monitor Report"
            )
            return map
        }

        fun clearPreferences(context: Context){
            val editor: SharedPreferences.Editor = getPreferences(context).edit()
            editor.clear();
            editor.apply()
        }


        fun stepsNotificationDescMap(): HashMap<String, String> {
            val map = HashMap<String, String>()
            map.put(Constants.CHEF_DETAIL, "Check out the latest updates on your order")
            map.put(Constants.COOKING_DETAIL, "Check out the latest updates on your order")
            map.put(
                Constants.DELIVERY_DETAIL,
                "Your order has been successfully tracked, checkout your order certification"
            )
            return map
        }


        fun stepdescMap(): HashMap<String, String> {
            val map = HashMap<String, String>()
            map.put(
                Constants.CHEF_DETAIL, "He is a 5 star Chef. We have done complete inpection for his sanity to make sure hygine for your order."
            )
            map.put(
                Constants.COOKING_DETAIL, "Your food is in safe hands We are doing complete inpection to make sure hygine for your order."
            )
            map.put(
                Constants.DELIVERY_DETAIL, "Body temperature: 97 degree FWe have done complete inpection for his hygienel"
            )

            return map
        }

        fun checkListMap(): HashMap<String, ArrayList<String>> {
            val map = HashMap<String, ArrayList<String>>()
            map.put(
                Constants.CHEF_DETAIL,
                arrayListOf(
                    "Face mask",
                    "Hand wash",
                    "Hand Gloves",
                    "Plastic Cover Cap",
                    "Chef Cap"
                )
            )
            map.put(
                Constants.COOKING_DETAIL,
                arrayListOf(
                    "Clean Kitchen",
                    "Clean food Storage ",
                    "Clean Utensils",
                    "No sneezing /coughing",
                    "Social Distancing"
                )
            )
            map.put(
                Constants.DELIVERY_DETAIL,
                arrayListOf("Face mask", "Hand wash", "Hand Gloves", "Clean Bike", "Green Route")
            )
            return map
        }

        fun convertToString(bitmap: Bitmap): String? {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val imgByte: ByteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(imgByte, Base64.DEFAULT)
        }

        fun decodeBase64String(encodedString: String): Bitmap {
            val decodedBytes: ByteArray = Base64.decode(encodedString, Base64.DEFAULT)
            val decodedBitmap =
                BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
            return decodedBitmap
        }
    }
}