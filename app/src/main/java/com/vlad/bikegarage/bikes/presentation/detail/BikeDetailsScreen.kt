package com.vlad.bikegarage.bikes.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.addbikes.components.BikeFactory
import com.vlad.bikegarage.bikes.presentation.list.components.LinearProgressBar
import com.vlad.bikegarage.rides.domain.model.Ride
import com.vlad.bikegarage.rides.presentation.list.components.RideCard

@Preview
@Composable
fun BikeDetailScreen(
    bikeId: Int = 0,
    viewModel: BikeDetailViewModel = hiltViewModel<BikeDetailViewModel, BikeDetailViewModel.BikeDetailViewModelFactory> { bikeDetailViewModelFactory ->
        bikeDetailViewModelFactory.create(bikeId)
    }
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondaryVariant)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BikeFactory(bodyColor = state.value.bikeColor, bodyType = state.value.bikeType)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.wheels_label),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = state.value.wheelSize,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onPrimary
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.service_in_label) + " ",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(state.value.remainingServiceKm)
                    }
                    append(stringResource(id = R.string.km_label))
                },
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onPrimary
            )
        }
        LinearProgressBar(
            progress = 0.4f,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.total_rides_label) + " ",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = state.value.totalRides.toString(),
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onPrimary
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.total_rides_distance_label) + " ",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(state.value.totalRidesDistance.toString())
                    }
                    append(stringResource(id = R.string.km_label))
                },
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onPrimary
            )
        }

        Text(
            text = stringResource(id = R.string.rides_title).uppercase(),
            color = Color.Gray,
            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 8.dp)
        )

        LazyColumn() {
            items(state.value.rideList, key = { ride: Ride -> ride.id }) { ride: Ride ->
                RideCard(
                    rideTitle = ride.rideName,
                    bikeName = ride.bikeName,
                    distance = ride.distance,
                    durationHours = ride.durationHours,
                    durationMinutes = ride.durationMinutes,
                    date = ride.date
                )
            }
        }
    }
}