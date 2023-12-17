package com.example.narutotest.notification

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.narutotest.data.local.NarutoDao
import com.example.narutotest.data.local.NarutoDatabase

class NotificationWorker(val context: Context, val params: WorkerParameters): CoroutineWorker(context, params) {
    private val narutoDao: NarutoDao = NarutoDatabase.getDatabase(context).dao
    override suspend fun doWork(): Result {
        val id = (1..1000).random()
        val text = narutoDao.getCharById(id).name
        NarutoNotificationService(context).showNotification(text)
        Log.d("NARUTO", "Send next notification")
        return Result.success()
    }
}