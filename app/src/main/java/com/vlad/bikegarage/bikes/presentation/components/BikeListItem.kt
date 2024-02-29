package com.vlad.bikegarage.bikes.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.domain.model.Bike
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.bikes.presentation.addbikes.components.BikeFactory
import com.vlad.bikegarage.ui.theme.Blue
import com.vlad.bikegarage.ui.theme.GrayBlue
import com.vlad.bikegarage.ui.theme.OceanBlue

@Composable
fun BikeListItem(bike: Bike, modifier: Modifier) {
    BikeCard(
        bikeName = bike.name,
        wheelSize = bike.wheelSize,
        bikeType = bike.bikeType,
        modifier = modifier
    )
}

//bike.serviceIn.toFloat() / (bike.serviceIn.toFloat() - bike.remainingServiceDistance.toFloat())
@Preview
@Composable
fun CustomProgressBar(
    progress: Float = 0.8f,
    progressColor: Color = Blue,
    backgroundColor: Color = GrayBlue,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Transparent)
            .height(25.dp),
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .height(6.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(progressColor)
                    .height(6.dp)
                    .fillMaxWidth(progress)
            )
            Image(
                painter = painterResource(id = R.drawable.loading_wrench),
                contentDescription = "loading wrench",
                modifier = Modifier
                    .fillMaxHeight()
                    .offset((-6).dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.loading_circle),
                contentDescription = "loading cercle",
                modifier = Modifier.fillMaxHeight()
            )
            Image(
                painter = painterResource(id = R.drawable.loading_bolt),
                contentDescription = "loading bolt",
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}

@Preview
@Composable
fun BikeCard(
    bikeName: String = "Nukeproof Scout 290",
    wheelSize: String = "29'",
    remainingServiceDistance: String = "170",
    bikeType: BikeType = BikeType.Electric,
    backgroundColor: Color = OceanBlue,
    modifier: Modifier = Modifier
) {
    Card(backgroundColor = backgroundColor, modifier = modifier) {

        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.icon_more),
                contentDescription = "bike card menu",
                modifier = Modifier.align(Alignment.End)
            )
            BikeFactory(modifier = Modifier.fillMaxWidth())
            Text(
                text = bikeName,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onPrimary,

                )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(R.string.wheels_label),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = wheelSize,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
                Text(
                    text = stringResource(id = R.string.service_in_label) + " ",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = remainingServiceDistance,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            CustomProgressBar(
                progress = 0.4f
            )
        }
    }
}