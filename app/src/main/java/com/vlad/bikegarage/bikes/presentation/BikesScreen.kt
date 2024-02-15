package com.vlad.bikegarage.bikes.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.components.ActionButton

@Composable
fun BikesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
    ) {
        LazyColumn(
        ) {
            item {
                EmptyHeader()
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
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "bike",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.heightIn(0.dp, 250.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dotted_line),
                contentDescription = "dotted line",
                modifier = Modifier
                    .padding(start = 20.dp).heightIn(0.dp,200.dp)
            )
            Text(
                text = stringResource(id = R.string.no_bikes_info),
                color = Color.White,
                style = androidx.compose.material.MaterialTheme.typography.h1,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        ActionButton(
            text = stringResource(R.string.add_bike_label),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}


