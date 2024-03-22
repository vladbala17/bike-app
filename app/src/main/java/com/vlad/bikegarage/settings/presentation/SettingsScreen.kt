@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class
)

package com.vlad.bikegarage.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlad.bikegarage.R
import com.vlad.bikegarage.settings.presentation.components.DefaultSwitch
import com.vlad.bikegarage.settings.presentation.components.DropDownField
import com.vlad.bikegarage.settings.presentation.components.Label
import com.vlad.bikegarage.settings.presentation.components.NumericTextField
import com.vlad.bikegarage.ui.theme.BikeGarageTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondaryVariant)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Label(
            stringResource(R.string.distance_units_label),
            true,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        DropDownField(
            listOf(
                stringResource(id = R.string.distance_unit_km),
                stringResource(id = R.string.distance_unit_miles)
            ),
            selectedItem = state.value.distanceUnit,
            onSelectedItem = { selectedItem ->
                viewModel.onEvent(SettingsEvent.OnDistanceUnitSet(selectedItem))
            },
            R.drawable.icon_dropdown,
            modifier = Modifier.fillMaxWidth()
        )
        Label(
            stringResource(R.string.service_reminder_label),
            false,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            NumericTextField(
                state.value.serviceIntervalReminder,
                onServiceReminderAdded = { distanceReminder ->
                    viewModel.onEvent(SettingsEvent.OnServiceIntervalReminderSet(distanceReminder))
                },
                state.value.distanceUnit,
                modifier = Modifier.weight(1f)
            )
            DefaultSwitch(
                state.value.isServiceNotifyEnabled,
                onCheckedChanged = {
                    viewModel.onEvent(SettingsEvent.OnNotifyReminder)
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Label(
            stringResource(R.string.default_bike_label),
            true,
            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
        )
        DropDownField(
            listOf(
                "Bike 1",
                "Bike 2",
                "Bike 3",
                "Bike 4",
                "Bike 5",
            ),
            selectedItem = state.value.defaultBike,
            onSelectedItem = { selectedItem ->
                viewModel.onEvent(SettingsEvent.OnDefaultBikeSet(selectedItem))
            },
            R.drawable.icon_dropdown,
            modifier = Modifier.fillMaxWidth()
        )
    }
}



@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun Development() {
    BikeGarageTheme {
        Label("Required Name", isMandatory = true, modifier = Modifier.fillMaxWidth())
    }
}


@Preview(showBackground = true)
@Composable
fun Development2() {
    BikeGarageTheme {
        DropDownField(
            listItems = listOf(),
            selectedItem = "text",
            onSelectedItem = {},
            icon = R.drawable.icon_dropdown
        )
    }
}