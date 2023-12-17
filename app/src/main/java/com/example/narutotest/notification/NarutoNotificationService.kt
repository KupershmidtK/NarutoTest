package com.example.narutotest.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.narutotest.MainActivity
import com.example.narutotest.R

class NarutoNotificationService (
    private val context: Context
) {


    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    fun showNotification(text: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, NARUTO_CHANNEL_ID)
            .setSmallIcon(R.drawable.naruto)
            .setContentTitle("Naruto")
            .setContentText(text)
            .setGroup(NARUTO_NOTIFICATION_GROUP_ID)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(messageId++, notification)
    }
    companion object {
        const val NARUTO_CHANNEL_ID = "narutoChannelId"
        const val NARUTO_NOTIFICATION_GROUP_ID = "narutoNotificationGroup.Characters"
        private var messageId: Int = 1
    }
}