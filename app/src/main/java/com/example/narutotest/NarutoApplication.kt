package com.example.narutotest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.narutotest.notification.NarutoNotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NarutoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val channel = NotificationChannel(
            NarutoNotificationService.NARUTO_CHANNEL_ID,
            getString(R.string.naruto_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}