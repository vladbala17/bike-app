package com.vlad.bikegarage.navigation

import com.vlad.bikegarage.R

data class BottomNavItem(
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val titleId: Int
)

val BOTTOM_NAV_ITEM_LIST = listOf(
    BottomNavItem(
        route = Route.BIKES,
        selectedIcon = R.drawable.icon_bikes_active,
        unselectedIcon = R.drawable.icon_bikes_inactive,
        titleId = R.string.bikes_title
    ),
    BottomNavItem(
        route = Route.RIDES,
        selectedIcon = R.drawable.rides_active,
        unselectedIcon = R.drawable.rides_inactive,
        titleId = R.string.rides_title
    ),
    BottomNavItem(
        route = Route.SETTINGS,
        selectedIcon = R.drawable.settings_active,
        unselectedIcon = R.drawable.settings_inactive,
        titleId = R.string.settings_title
    )
)
