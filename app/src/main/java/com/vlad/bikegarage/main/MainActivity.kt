@file:OptIn(ExperimentalMaterialApi::class)

package com.vlad.bikegarage.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.addbikes.AddBikesScreen
import com.vlad.bikegarage.bikes.presentation.detail.BikeDetailScreen
import com.vlad.bikegarage.bikes.presentation.list.BikesScreen
import com.vlad.bikegarage.navigation.BOTTOM_NAV_ITEM_LIST
import com.vlad.bikegarage.navigation.BottomNavItem
import com.vlad.bikegarage.navigation.Route
import com.vlad.bikegarage.rides.presentation.RidesScreen
import com.vlad.bikegarage.rides.presentation.addride.AddRideScreen
import com.vlad.bikegarage.settings.presentation.SettingsScreen
import com.vlad.bikegarage.ui.theme.BikeGarageTheme
import com.vlad.bikegarage.ui.theme.NoRippleInteractionSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BikeGarageTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val viewModel: MainActivityViewModel = hiltViewModel()
                val state = viewModel.state.collectAsStateWithLifecycle()
                Scaffold(
                    topBar = {
                        TopNavigationBar(
                            navController = navController,
                            pageTitle = state.value.title,
                            onClick = {}
                        )
                    },
                    bottomBar = {
                        if ((currentRoute(navController = navController) != Route.ADD_BIKES) &&
                            (currentRoute(navController = navController) != Route.ADD_RIDES)
                        ) {
                            BottomNavigationBar(
                                items = BOTTOM_NAV_ITEM_LIST,
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                    viewModel.onEvent(MainScreenEvent.PageChanged(it.route))
                                }
                            )
                        }
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        NavigationScreen(navController, onAddBikeClick = {
                            navController.navigate(Route.ADD_BIKES)
                            viewModel.onEvent(MainScreenEvent.PageChanged(Route.ADD_BIKES))
                        }, onAddRideClick = {
                            navController.navigate(Route.ADD_RIDES)
                            viewModel.onEvent(MainScreenEvent.PageChanged(Route.ADD_RIDES))
                        })
                    }
                }
            }
        }
    }


}

@Composable
fun NavigationScreen(
    navController: NavHostController,
    onAddBikeClick: () -> Unit,
    onAddRideClick: () -> Unit
) {
    NavHost(navController, startDestination = Route.BIKES) {
        composable(Route.BIKES) {
            StatusBarColor(color = MaterialTheme.colors.background)
            BikesScreen(onNavigateToScreen = onAddBikeClick,
                onEditBike = { bikeId ->
                    navController.navigate(Route.ADD_BIKES + "/$bikeId")
                },
                onBikeClick = { bikeId ->
                    navController.navigate(Route.BIKE_DETAIL + "/$bikeId")
                })
        }
        composable(Route.RIDES) {
            StatusBarColor(color = MaterialTheme.colors.background)
            RidesScreen(onNavigateToScreen = onAddRideClick)
        }
        composable(Route.SETTINGS) {
            StatusBarColor(color = MaterialTheme.colors.secondaryVariant)
            SettingsScreen()
        }
        composable(route = Route.ADD_BIKES + "/{bikeId}",
            arguments = listOf(
                navArgument("bikeId") {
                    type = NavType.IntType
                }
            )) {
            val bikeId = it.arguments?.getInt("bikeId")!!
            StatusBarColor(color = MaterialTheme.colors.background)
            AddBikesScreen(bikeId = bikeId,
                onAddBike = {
                    navController.navigate(Route.BIKES)
                })
        }
        composable(Route.ADD_BIKES) {
            StatusBarColor(color = MaterialTheme.colors.background)
            AddBikesScreen(
                onAddBike = {
                    navController.navigate(Route.BIKES)
                })
        }
        composable(route = Route.BIKE_DETAIL + "/{bikeId}",
            arguments = listOf(
                navArgument("bikeId") {
                    type = NavType.IntType
                }
            )
        ) {
            val bikeId = it.arguments?.getInt("bikeId")!!
            StatusBarColor(color = MaterialTheme.colors.background)
            BikeDetailScreen(bikeId = bikeId)
        }
        composable(Route.ADD_RIDES) {
            StatusBarColor(color = MaterialTheme.colors.background)
            AddRideScreen()
        }


    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backstackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        containerColor = MaterialTheme.colors.surface,
        tonalElevation = 5.dp,
    ) {
        items.forEach { destination ->
            val selected = destination.route == backstackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(destination) },
                icon = {
                    if (selected) {
                        Icon(
                            painter = painterResource(destination.selectedIcon),
                            contentDescription = stringResource(destination.titleId),
                            tint = colorResource(R.color.blue)
                        )
                    } else {
                        Icon(
                            painter = painterResource(destination.unselectedIcon),
                            contentDescription = stringResource(destination.titleId),
                            tint = colorResource(R.color.white)
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(destination.titleId),
                        color = if (selected) {
                            colorResource(R.color.blue)
                        } else {
                            colorResource(R.color.white)
                        },
                        style = MaterialTheme.typography.h6
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colors.surface
                ),
                interactionSource = NoRippleInteractionSource()
            )
        }
    }
}

@Composable
fun TopNavigationBar(
    navController: NavController,
    pageTitle: String,
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = pageTitle,
                color = Color.White,
                style = MaterialTheme.typography.h2
            )
        },
        backgroundColor = if (pageTitle == Route.SETTINGS) {
            MaterialTheme.colors.secondaryVariant
        } else {
            MaterialTheme.colors.background
        },
        elevation = 0.dp,
        actions = {
            if (pageTitle == Route.ADD_BIKES) {
                IconButton(
                    onClick = onClick
                ) {
                    Row(
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_x),
                            tint = MaterialTheme.colors.onPrimary,
                            contentDescription = "Close"
                        )
                    }
                }
            } else if (pageTitle != Route.SETTINGS) {
                IconButton(
                    onClick = onClick
                ) {
                    Row(
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_add),
                            tint = MaterialTheme.colors.onPrimary,
                            contentDescription = "Add"
                        )
                        Text(
                            text = if (pageTitle == Route.BIKES) {
                                stringResource(R.string.add_bike_label)
                            } else {
                                stringResource(R.string.add_ride_label)
                            },
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }

        }
    )
}

@Composable
fun StatusBarColor(color: Color) {
    val view = LocalView.current
    val darkTheme = isSystemInDarkTheme()

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BikeGarageTheme {
        Greeting("Android")
    }
}