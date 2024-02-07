package com.vlad.bikegarage.rides.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.components.ActionButton

@Composable
fun RidesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
    ) {
        ActionButton(
            text = stringResource(R.string.add_ride_label),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}