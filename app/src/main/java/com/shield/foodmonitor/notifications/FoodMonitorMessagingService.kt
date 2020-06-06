package com.shield.foodmonitor.notifications

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.shield.foodmonitor.R
import com.shield.foodmonitor.ui.NotificationActivity
import com.shield.foodmonitor.utils.Constants
import com.shield.foodmonitor.utils.Utility


class FoodMonitorMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("TAG", remoteMessage.data.toString())
        prepareNotification(applicationContext, remoteMessage.data)
    }






        private fun prepareNotification(context: Context, data: Map<String, String>) {

            val notificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                val mChannel = NotificationChannel(
                    Constants.Notification.CHANNEL_ID, Constants.Notification.CHANNEL_NAME, importance)
                notificationManager.createNotificationChannel(mChannel)
            }
            val intent = Intent(context, NotificationActivity::class.java)
            intent.putExtra(Constants.Notification.NAVIGATE_TO, data.get("status"))
            /*if(data.containsKey(Constants.Notification.IMAGE_URL)){
                intent.putExtra(Constants.Notification.IMAGE_URL, data.get(Constants.Notification.IMAGE_URL))
            }*/
            intent.putExtra(Constants.Notification.IMAGE_URL, "https://firebasestorage.googleapis.com/v0/b/foodmonitoring-6dee4.appspot.com/o/chicken-rice.jpg?alt=media&token=54409b83-e1af-4804-ac0e-fba446b0b7a1")
            val taskStackBuilder: TaskStackBuilder = TaskStackBuilder
                .create(this)
            taskStackBuilder.addNextIntentWithParentStack(intent)
            val pendingIntent: PendingIntent = taskStackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

            val builder = NotificationCompat.Builder(context, Constants.Notification.CHANNEL_ID)
            builder.apply {
                setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
                setContentTitle(Utility.stepsNotificationTitleMap().get(data.get("status")))
                setContentText(Utility.stepsNotificationDescMap().get(data.get("status")))
                setOngoing(false)
                setAutoCancel(true)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentIntent(pendingIntent)
                setVibrate(LongArray(0))
                priority = Notification.PRIORITY_HIGH
            }


            // hide the notification after its selected
          //  notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
            notificationManager.notify(1, builder.build())
        }





}