package com.starsolns.pets.views.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.starsolns.pets.R
import com.starsolns.pets.views.ui.MainActivity

class NotificationHelper(val context: Context) {

    //1. create a notification channel
    private val CHANNEL_ID = "Pets_notifications_channel"
    private val NOTIFICATION_ID = 789

    fun createNotificationChannel(){
        //ensure the build version is above api 26 to enable notifications
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = CHANNEL_ID
            val channelDesc = "Channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel  = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = channelDesc
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

    private fun playNotificationSound() {
        try {
            val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(context, defaultSound)
            ringtone.play()
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to play sound", Toast.LENGTH_LONG).show()
        }
    }

    //2. Create the notification
    //Define the notification specifics
    fun createNotification(){
        var intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.big_dog)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.dog_icon)
            .setLargeIcon(icon)
            .setContentTitle("Pets available")
            .setContentText("Best pets shop in the country")
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(icon)
                    .bigLargeIcon(null)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)

    }

    fun deleteNotification(){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannel(CHANNEL_ID)
        }
    }

}