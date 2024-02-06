package com.vlad.bikegarage.settings.domain

interface Preferences {
    fun saveDistanceUnit(distanceUnit: String)
    fun saveServiceInterval(distance: Int)
    fun saveEnabledNotifications(enabled: Boolean)
    fun saveDefaultBike(bikeName: String)

    fun getDistanceUnit(): String
    fun getServiceInterval(): Int
    fun areNotificationsEnabled(): Boolean
    fun getDefaultBikeName(): String

    companion object {
        const val KEY_DISTANCE_UNIT= "unit"
        const val KEY_SERVICE_INTERVAL= "interval"
        const val KEY_NOTIFICATIONS= "notifications"
        const val KEY_DEFAULT_BIKE= "bike"
    }
}