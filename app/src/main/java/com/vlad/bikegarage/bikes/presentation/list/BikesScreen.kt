package com.vlad.bikegarage.bikes.presentation.list

import EmptyHeader
import androidx.compose.foundation.layout.Column
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
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.presentation.list.components.BikeListItem
import com.vlad.bikegarage.bikes.presentation.list.components.ConfirmationDialog

@Composable
fun BikesScreen(
    viewModel: BikesViewModel = hiltViewModel(),
    onNavigateToScreen: () -> Unit = {},
    onEditBike: (Int) -> Unit = {},
    onBikeClick: (Int) -> Unit = {}
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
    ) {
        if (state.value.bikes.isEmpty()) {
            EmptyHeader(onButtonClick = {
                onNavigateToScreen()
            })
        }
        LazyColumn {
            items(items = state.value.bikes, key = { bike: Bike -> bike.id }) { bike: Bike ->
                BikeListItem(
                    bike = bike,
                    modifier = Modifier.padding(8.dp),
                    remainingServiceDistance = bike.remainingServiceDistance,
                    usageUntilService = bike.usageUntilService,
                    onEditBikeMenuClick = { onEditBike(bike.id) },
                    onDeleteBike = {
                        viewModel.onEvent(BikesEvent.OnDeleteBike(bike.name))
                    },
                    onBikeItemClick = onBikeClick
                )
            }
        }

        if (state.value.showDialog) {
            ConfirmationDialog(
                onDismissRequest = { viewModel.onEvent(BikesEvent.OnDismissDialog) },
                onConfirmation = {
                    viewModel.onEvent(BikesEvent.OnDeleteConfirmation)
                },
                dialogText = state.value.bikeName,
            )
        }
    }
}

@Preview
@Composable
fun BikeScreenPreview() {
    BikesScreen()
}

