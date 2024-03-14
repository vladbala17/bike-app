package com.vlad.bikegarage.rides.presentation.list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlad.bikegarage.R
import com.vlad.bikegarage.bikes.presentation.list.components.CardDropDownMenu
import com.vlad.bikegarage.ui.theme.OceanBlue
import com.vlad.bikegarage.util.convertMillisToDateMonthNumber

@Preview
@Composable
fun RideCard(
    rideId: Int = 0,
    rideTitle: String = "Faget MTB Tour",
    distance: Int = 0,
    durationHours: Int = 2,
    durationMinutes: Int = 8,
    date: Long = System.currentTimeMillis(),
    bikeName: String = "Nukeproof Scout 290",
    backgroundColor: Color = OceanBlue,
    onEditRideMenuClick: (Int) -> Unit = {},
    onDeleteRideMenuClick: (String) -> Unit = {},
    onRideCardClick: (Int) -> Unit = {},
    withContextMenu: Boolean = true,
    modifier: Modifier = Modifier
) {
    Card(backgroundColor = backgroundColor, modifier = modifier) {
        var displayMenu by remember { mutableStateOf(false) }
        Column(modifier = Modifier
            .padding(16.dp)
            .clickable { onRideCardClick(rideId) }) {

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
                    text = rideTitle,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                if (withContextMenu) {
                    Box {
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
                            itemId = rideId,
                            onEditItemClick = onEditRideMenuClick,
                            onDeleteItemClick = onDeleteRideMenuClick
                        )
                    }
                }
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(R.string.ride_bike_label) + ": ",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = bikeName,
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
                    text = distance.toString() + "km",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(R.string.ride_duration_label) + ": ",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = stringResource(
                        id = R.string.ride_duration,
                        durationHours,
                        durationMinutes
                    ),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(R.string.ride_date_label) + ": ",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = convertMillisToDateMonthNumber(date),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}