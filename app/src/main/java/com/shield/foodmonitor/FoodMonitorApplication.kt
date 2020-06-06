package com.shield.foodmonitor

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.shield.foodmonitor.utils.Constants
import com.shield.foodmonitor.utils.Utility

class FoodMonitorApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: FoodMonitorApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun showKeyboard(editText: EditText) {
            val imm =
                instance?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
        }
    }



    override fun onCreate() {
        super.onCreate()
        registerPushNotification()
    }

    private fun registerPushNotification() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                Utility.saveString(applicationContext, Constants.Notification.DEVICE_TOKEN, token!!)
                Log.d("TAG:token: ", token)

                // register this token at server
            })
    }

}