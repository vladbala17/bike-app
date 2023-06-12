package com.vlad.bikegarage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vlad.bikegarage.bikes.presentation.BikesScreen
import com.vlad.bikegarage.navigation.BOTTOM_NAV_ITEM_LIST
import com.vlad.bikegarage.navigation.BottomNavItem
import com.vlad.bikegarage.navigation.Route
import com.vlad.bikegarage.rides.presentation.RidesScreen
import com.vlad.bikegarage.settings.presentation.SettingsScreen
import com.vlad.bikegarage.ui.theme.BikeGarageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BikeGarageTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = BOTTOM_NAV_ITEM_LIST,
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Modifier.padding(it)
                    NavigationScreen(navController)
                }
            }
        }
    }
}

@Composable
fun NavigationScreen(navController: NavHostController) {
    NavHost(navController, startDestination = Route.RIDES) {
        composable(Route.BIKES) {
            BikesScreen()
        }
        composable(Route.RIDES) {
            RidesScreen()
        }
        composable(Route.SETTINGS) {
            SettingsScreen()
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
        modifier = Modifier.fillMaxWidth(),
        containerColor = colorResource(R.color.dark_blue),
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
                            contentDescription = stringResource(destination.titleId)
                        )
                    } else {
                        Icon(
                            painter = painterResource(destination.unselectedIcon),
                            contentDescription = stringResource(destination.titleId)
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(destination.titleId)
                    )
                }
            )
        }
    }
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