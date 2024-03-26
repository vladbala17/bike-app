package com.vlad.bikegarage

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.vlad.bikegarage.util.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BikeGarageApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

    }

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.NOTIFICATION_CHANNEL_ID,
                Constants.NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.description = "Used to show service reminder for default bike"

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}