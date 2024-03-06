package com.vlad.bikegarage.bikes.presentation.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.domain.model.BikeType
import com.vlad.bikegarage.bikes.presentation.addbikes.components.BikeFactory
import com.vlad.bikegarage.ui.theme.OceanBlue

@Preview
@Composable
fun BikeCard(
    bikeId: Int = 0,
    bikeName: String = "Nukeproof Scout 290",
    wheelSize: String = "29'",
    remainingServiceDistance: String = "170",
    bikeType: BikeType = BikeType.Electric,
    bikeColor: Color = Color.DarkGray,
    backgroundColor: Color = OceanBlue,
    onEditBikeMenuClick: (Int) -> Unit = {},
    onDeleteBikeMenuClick: (String) -> Unit = {},
    onBikeCardClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(backgroundColor = backgroundColor, modifier = modifier) {
        var displayMenu by remember { mutableStateOf(false) }
        Column(modifier = Modifier
            .padding(16.dp)
            .clickable { onBikeCardClick(bikeId) }) {
            Box(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                IconButton(
                    onClick = {
                        displayMenu = !displayMenu
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_more),
                        contentDescription = "bike card menu",
                    )
                }
                CardDropDownMenu(
                    displayMenu = displayMenu,
                    onDismissRequest = { displayMenu = false },
                    bikeId = bikeId,
                    onEditBikeClick = onEditBikeMenuClick,
                    onDeleteBikeClick = onDeleteBikeMenuClick
                )
            }
            BikeFactory(
                modifier = Modifier.fillMaxWidth(),
                bodyType = bikeType,
                bodyColor = bikeColor
            )
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
            LinearProgressBar(
                progress = 0.4f
            )
        }
    }
}