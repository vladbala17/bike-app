package com.vlad.bikegarage.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.vlad.bikegarage.R
import com.vlad.bikegarage.util.Constants
import kotlin.random.Random

class BikeServiceNotificationHandler(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    fun showBikeServiceNotification(bikeName: String, distance: String) {
        val notification = NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
            .setContentTitle(bikeName)
            .setContentText("Service in $distance")
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(Random.nextInt(), notification)
    }
}