package com.vlad.bikegarage.rides.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.list.EmptyHeader
import com.vlad.bikegarage.bikes.presentation.list.components.ConfirmationDialog
import com.vlad.bikegarage.rides.domain.model.Ride
import com.vlad.bikegarage.rides.presentation.list.RidesEvent
import com.vlad.bikegarage.rides.presentation.list.RidesViewModel
import com.vlad.bikegarage.rides.presentation.list.components.RideListItem

@Preview
@Composable
fun RidesScreen(
    viewModel: RidesViewModel = hiltViewModel(),
    onEditRide: (Int) -> Unit = {},
    onRideClick: (Int) -> Unit = {},
    onNavigateToScreen: () -> Unit = {}
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
    ) {
        if (state.value.rides.isEmpty()) {
            EmptyHeader(
                icon = R.drawable.missing_ride_card,
                showText = false,
                onButtonClick = { onNavigateToScreen() }
            )
        }
        LazyColumn {
            items(items = state.value.rides, key = { ride: Ride -> ride.id }) { ride: Ride ->
                RideListItem(
                    ride = ride,
                    modifier = Modifier.padding(8.dp),
                    onEditRideMenuClick = { onEditRide(ride.id) },
                    onDeleteRide = {
                        viewModel.onEvent(RidesEvent.OnDeleteRide(ride.rideName))
                    },
                    onRideItemClick = onRideClick
                )
            }
        }
        if (state.value.showDialog) {
            ConfirmationDialog(
                onDismissRequest = { viewModel.onEvent(RidesEvent.OnDismissDialog) },
                onConfirmation = {
                    viewModel.onEvent(RidesEvent.OnDeleteConfirmation)
                },
                dialogText = state.value.rideName,
            )
        }
    }
}