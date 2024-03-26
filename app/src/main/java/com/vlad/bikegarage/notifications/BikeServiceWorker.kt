package com.vlad.bikegarage.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.domain.use_case.GetBikeByName
import com.vlad.bikegarage.bikes.domain.use_case.GetRidesForBike
import com.vlad.bikegarage.settings.domain.Preferences
import com.vlad.bikegarage.util.Constants
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class BikeServiceWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters,
    private val getBikeByName: GetBikeByName,
    private val getRidesForBike: GetRidesForBike,
    private val preferences: Preferences
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        val bikeName = inputData.getString(Constants.BIKE_NAME_KEY)
        val rideDistance = inputData.getString(Constants.RIDE_DISTANCE)
        withContext(Dispatchers.IO) {
            if (preferences.getDefaultBikeName() == bikeName) {
                val totalKmForBike =
                    getRidesForBike.invoke(bikeName).sumOf { it.distance }
                val bike = getBikeByName(bikeName)
                val bikeServiceInDistance = bike.serviceIn

                if (bikeServiceInDistance - totalKmForBike <= 100) {
                    //showNotification
                    showNotification(context, bikeName, (bikeServiceInDistance - totalKmForBike).toString(), bike.bikeColor)
                }
            }
        }
        return Result.success()
    }
    override suspend fun getForegroundInfo(): ForegroundInfo {
        val notification = NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
            .setContentTitle("")
            .setContentText("")
            .setSmallIcon(R.drawable.icon_dropdown)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()
        return ForegroundInfo(1, notification)
    }
    private fun showNotification(context: Context, bikeName: String, distance: String, bikeColor: Int) {
        val notification = BikeServiceNotificationHandler(context)
        notification.showBikeServiceNotification(bikeName, distance, bikeColor)
    }
}