package com.vlad.bikegarage.rides.presentation.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.bikegarage.R

@Preview
@Composable
fun RideDetailScreen(
    rideId: Int = 0,
    viewModel: RideDetailViewModel = hiltViewModel<RideDetailViewModel, RideDetailViewModel.RideDetailViewModelFactory> { rideDetailViewModelFactory ->
        rideDetailViewModelFactory.create(rideId)
    }
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colors.secondaryVariant)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                painter = painterResource(id = R.drawable.icon_bikes_inactive),
                contentDescription = "ride card menu icon",
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.White), CircleShape)
                    .padding(4.dp)
                    .clip(
                        CircleShape
                    )
            )
            Text(
                text = state.value.rideTitle,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onPrimary,
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = stringResource(R.string.ride_bike_label) + ":",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = state.value.bikeName,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = stringResource(R.string.ride_distance_label) + ":",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = state.value.distance + "km",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = stringResource(R.string.ride_duration_label) + ":",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = state.value.duration,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = stringResource(R.string.ride_date_label) + ":",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = state.value.date,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }

}