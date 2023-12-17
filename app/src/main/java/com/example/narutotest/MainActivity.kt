package com.example.narutotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.work.BackoffPolicy
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.narutotest.notification.NotificationWorker
import com.example.narutotest.ui.navigation.NarutoNavGraph
import com.example.narutotest.ui.theme.NarutoTestTheme
import java.time.Duration
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NarutoTestTheme(
                dynamicColor = false
            ) {
                LaunchedEffect(key1 = Unit) {
                    val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
                        repeatInterval = 20,
                        repeatIntervalTimeUnit = TimeUnit.MINUTES
                    )
                        .setBackoffCriteria(
                            backoffPolicy = BackoffPolicy.LINEAR,
                            duration = Duration.ofSeconds(15)
                        )
                        .build()
                    val workManager = WorkManager.getInstance(applicationContext)
                    workManager.enqueueUniquePeriodicWork(
                        "myUniqueWork",
                        ExistingPeriodicWorkPolicy.UPDATE,
                        workRequest)
                }

                NarutoNavGraph()
            }
        }
    }
}