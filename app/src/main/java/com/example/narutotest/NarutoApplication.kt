package com.example.narutotest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.narutotest.di.NarutoAppContainer
import com.example.narutotest.notification.NarutoNotificationService

class NarutoApplication : Application() {
    lateinit var container: NarutoAppContainer
    override fun onCreate() {
        super.onCreate()
        container = NarutoAppContainer(this)

        val channel = NotificationChannel(
            NarutoNotificationService.NARUTO_CHANNEL_ID,
            getString(R.string.naruto_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}