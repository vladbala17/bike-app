package com.vlad.bikegarage.bikes.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.presentation.components.ActionButton
import com.vlad.bikegarage.bikes.presentation.components.BikeListItem

@Composable
fun BikesScreen(
    viewModel: BikesViewModel = hiltViewModel(),
    onNavigateToScreen: () -> Unit = {},
    onEditBike: (Int) -> Unit = {}
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
            items(items = state.value.bikes, key = { item: Bike -> item.id }) { item: Bike ->
                BikeListItem(
                    bike = item,
                    modifier = Modifier.padding(8.dp),
                    onEditBikeMenuClick = { onEditBike(item.id) })
            }
        }
    }
}

@Preview
@Composable
fun BikeScreenPreview() {
    BikesScreen()
}

@Preview()
@Composable
fun EmptyHeader(
    pageTitle: String = "Bikes", icon: Int = R.drawable.missing_bike_card,
    onButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "bike",
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dotted_line),
                contentDescription = "dotted line",
                modifier = Modifier
                    .padding(start = 20.dp)

            )
            Text(
                text = stringResource(id = R.string.no_bikes_info),
                color = Color.White,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        ActionButton(
            text = stringResource(R.string.add_bike_label),
            onButtonClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

