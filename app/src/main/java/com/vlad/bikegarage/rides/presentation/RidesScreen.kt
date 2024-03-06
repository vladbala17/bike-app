package com.vlad.bikegarage.rides.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.list.EmptyHeader
import com.vlad.bikegarage.rides.presentation.list.RidesViewModel

@Preview
@Composable
fun RidesScreen(
    viewModel: RidesViewModel = hiltViewModel(),
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
    }
}