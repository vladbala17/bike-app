package com.vlad.bikegarage.bikes.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.components.ActionButton

@Composable
fun BikesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
    ) {
        ActionButton(
            text = stringResource(R.string.add_bike_label),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }

}

@Preview
@Composable
fun BikeScreenPreview() {
    BikesScreen()
}
