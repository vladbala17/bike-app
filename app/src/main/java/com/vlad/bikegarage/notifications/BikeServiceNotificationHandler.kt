package com.vlad.bikegarage.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.vlad.bikegarage.R
import com.vlad.bikegarage.main.MainActivity
import com.vlad.bikegarage.util.Constants
import kotlin.random.Random

class BikeServiceNotificationHandler(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val activityIntent = Intent(context, MainActivity::class.java)
    private val activityPendingIntent = PendingIntent.getActivity(
        context,
        1,
        activityIntent,
        PendingIntent.FLAG_IMMUTABLE
    )

    fun showBikeServiceNotification(bikeName: String, distance: String, bikeColor: Int) {
        val notification = NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
            .setContentTitle(bikeName)
            .setContentText("Service in $distance km")
            .setSmallIcon(R.drawable.logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .setColor(bikeColor)
            .setColorized(true)
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(Random.nextInt(), notification)
    }
}