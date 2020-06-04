package com.shield.foodmonitor

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class FoodMonitorApplication: Application() {

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
                Log.d("TAG:token: ", token)

                // register this token at server
            })
    }

}