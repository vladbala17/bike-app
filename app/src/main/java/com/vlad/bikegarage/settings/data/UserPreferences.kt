package com.vlad.bikegarage.settings.data

import android.content.SharedPreferences
import com.vlad.bikegarage.settings.domain.Preferences

class UserPreferences(
    private val sharedPreferences: SharedPreferences
): Preferences {
    override fun saveDistanceUnit(distanceUnit: String) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_DISTANCE_UNIT, distanceUnit)
            .apply()
    }

    override fun saveServiceInterval(distance: Int) {
        sharedPreferences.edit()
            .putInt(Preferences.KEY_SERVICE_INTERVAL, distance)
            .apply()
    }

    override fun saveEnabledNotifications(enabled: Boolean) {
        sharedPreferences.edit()
            .putBoolean(Preferences.KEY_NOTIFICATIONS, enabled)
            .apply()
    }

    override fun saveDefaultBike(bikeName: String) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_DEFAULT_BIKE, bikeName)
            .apply()
    }

    override fun getDistanceUnit(): String {
        return sharedPreferences.getString(Preferences.KEY_DISTANCE_UNIT, "km") ?: "km"
    }

    override fun getServiceInterval(): Int {
        return sharedPreferences.getInt(Preferences.KEY_SERVICE_INTERVAL, 0)
    }

    override fun areNotificationsEnabled(): Boolean {
        return sharedPreferences.getBoolean(Preferences.KEY_NOTIFICATIONS, false)
    }

    override fun getDefaultBikeName(): String {
        return sharedPreferences.getString(Preferences.KEY_DISTANCE_UNIT, "Bike") ?: "No bike"
    }
}